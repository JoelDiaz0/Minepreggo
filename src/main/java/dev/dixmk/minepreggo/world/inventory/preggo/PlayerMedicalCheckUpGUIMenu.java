package dev.dixmk.minepreggo.world.inventory.preggo;

import java.util.Optional;

import dev.dixmk.minepreggo.entity.preggo.BabyType;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

public class PlayerMedicalCheckUpGUIMenu extends AbstractMedicalCheckUpGUIMenu<Player, ScientificIllager> {
	private final boolean valid;

	protected PlayerMedicalCheckUpGUIMenu(MenuType<?> p_38851_, int id, Inventory inv, FriendlyByteBuf extraData) {
		super(p_38851_, id, inv);
	
		Player s = null;	
		ScientificIllager t = null;
		
		if (extraData != null) {
			var pos = extraData.readBlockPos();			
			this.pos.x = pos.getX();
			this.pos.y = pos.getY();
			this.pos.z = pos.getZ();
			
			if (level.getEntity(extraData.readVarInt()) instanceof Player pregnantPlayer)  {
				s = pregnantPlayer;					
			}	
		
			if (level.getEntity(extraData.readVarInt()) instanceof ScientificIllager scientificIllager) {
				t = scientificIllager;
			}			
		}
		this.numOfEmerald = 30;
		
		this.target = Optional.ofNullable(t);
		this.source = Optional.ofNullable(s);

		
		this.valid = this.source.isPresent() && this.target.isPresent();
	}

	
	@Override
	public boolean isValid() {
		return this.valid;
	}

	@Override
	public void onSuccessful() {
		
	}

	@Override
	public void onFailure() {
		
	}

	@Override
	public String getName() {
		return this.isValid() ? this.source.get().getDisplayName().getString() : null;
	}

	@Override
	public PregnancyStage getCurrentStage() {
		return null;
	}

	@Override
	public int getPregnancyHealth() {
		return -1;
	}

	@Override
	public int getNumberOfChildren() {
		return -1;
	}

	@Override
	public BabyType getBabyType() {
		return BabyType.HUMAN;
	}

	@Override
	public int getDaysToGiveBirth() {
		return 0;
	}

	@Override
	public int getDaysPassed() {
		return 0;
	}

	@Override
	public int getDaysToNextStage() {
		return 0;
	}
}
