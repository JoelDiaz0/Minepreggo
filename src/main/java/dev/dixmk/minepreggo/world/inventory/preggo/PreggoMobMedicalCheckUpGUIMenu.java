package dev.dixmk.minepreggo.world.inventory.preggo;

import java.util.Optional;

import dev.dixmk.minepreggo.entity.preggo.BabyType;
import dev.dixmk.minepreggo.entity.preggo.IPregnancySystem;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.registries.ForgeRegistries;

public class PreggoMobMedicalCheckUpGUIMenu extends AbstractMedicalCheckUpGUIMenu<PreggoMob, ScientificIllager> {

	private final boolean valid;
	private final Optional<IPregnancySystem> pregnancySystem;
	
	public PreggoMobMedicalCheckUpGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.PREGGO_MOB_MEDICAL_CHECKUP.get(), id, inv);	
		
		PreggoMob s = null;	
		IPregnancySystem p = null;
		ScientificIllager t = null;
		
		if (extraData != null) {
			var pos = extraData.readBlockPos();			
			this.pos.x = pos.getX();
			this.pos.y = pos.getY();
			this.pos.z = pos.getZ();	
		
			if (level.getEntity(extraData.readVarInt()) instanceof PreggoMob preggoMob)  {
				s = preggoMob;			
				if (preggoMob instanceof IPregnancySystem pregSystem) {
					p = pregSystem;
				}		
			}	
			
			if (level.getEntity(extraData.readVarInt()) instanceof ScientificIllager scientificIllager) {
				t = scientificIllager;
			}
		}		
		
		this.target = Optional.ofNullable(t);
		this.source = Optional.ofNullable(s);	
		this.pregnancySystem = Optional.ofNullable(p);
		this.valid = this.source.isPresent() && this.pregnancySystem.isPresent() && this.target.isPresent();	
		this.numOfEmerald = 30;
	}
	
	@Override
	public void onSuccessful() {	
		this.source.ifPresent(e -> {		
			if (this.level.isClientSide()) {
				this.level.playLocalSound(this.pos.x, this.pos.x, this.pos.x, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace(("entity.vindicator.celebrate"))), SoundSource.NEUTRAL, 1, 1, false);				
			}
		});
	}

	@Override
	public void onFailure() {
		this.source.ifPresent(e -> {		
			if (this.level.isClientSide()) {
				this.level.playLocalSound(this.pos.x, this.pos.y, this.pos.z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace(("entity.vindicator.celebrate"))), SoundSource.NEUTRAL, 1, 1, false);				
			}
		});
	}

	@Override
	public String getName() {		
		return this.valid ? this.source.get().getSimpleName() : null;
	}

	@Override
	public PregnancyStage getCurrentStage() {
		return this.valid ? this.pregnancySystem.get().getCurrentPregnancyStage() : null;
	}

	@Override
	public int getPregnancyHealth() {
		return this.valid ? this.pregnancySystem.get().getPregnancyHealth() : -1;
	}

	@Override
	public int getNumberOfChildren() {
		return this.valid ? this.pregnancySystem.get().getPregnancyHealth() : -1;
	}

	@Override
	public BabyType getBabyType() {
		return this.valid ? this.pregnancySystem.get().getBabyType() : null;
	}

	@Override
	public int getDaysToGiveBirth() {
		return this.valid ? this.pregnancySystem.get().getDaysToGiveBirth() : -1;
	}

	@Override
	public int getDaysPassed() {
		return this.valid ? this.pregnancySystem.get().getDaysPassed() : -1;
	}

	@Override
	public int getDaysToNextStage() {
		return this.valid ? this.pregnancySystem.get().getDaysByStage() - this.pregnancySystem.get().getDaysPassed(): -1;
	}

	@Override
	public boolean isValid() {
		return this.valid;
	}
}