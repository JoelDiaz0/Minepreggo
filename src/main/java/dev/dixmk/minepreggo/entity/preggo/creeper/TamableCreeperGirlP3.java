package dev.dixmk.minepreggo.entity.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.entity.preggo.Craving;
import dev.dixmk.minepreggo.entity.preggo.ICraving;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP3;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.PregnancySystemP3;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.utils.PreggoAIHelper;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class TamableCreeperGirlP3 extends AbstractTamablePregnantCreeperGirl implements IPregnancyP3 {

	private final PregnancySystemP3<TamableCreeperGirlP3> preggoMobSystem;
	
	public TamableCreeperGirlP3(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P3.get(), world);
	}

	public TamableCreeperGirlP3(EntityType<TamableCreeperGirlP3> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
		preggoMobSystem = new PregnancySystemP3<TamableCreeperGirlP3>(this) {
			@Override
			protected void changePregnancyStage() {	
			}
			
			@Override
			protected void finishMiscarriage() {
			}

			@Override
			@Nullable
			protected <I extends Item & ICraving> I getCraving(Craving craving) {						
				return null;
			}

			@Override
			protected boolean isFood(ItemStack food) {		
				return true;
			}
		};
	}
	
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		PreggoAIHelper.setTamablePregnantCreeperGirlGoals(this);
	}
	
	@Override
	public void tick() {
		this.preggoMobSystem.evaluateOnTick();
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
	
		if (super.mobInteract(sourceentity, hand) == InteractionResult.SUCCESS) 
			return InteractionResult.SUCCESS;
		
		
		return InteractionResult.SUCCESS;
	}
	
	
	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.24);
	}
	
	@Override
	public PregnancyStage getCurrentPregnancyStage() {
		return PregnancyStage.P2;
	}
	
	@Override
	public int getCraving() {
		return this.entityData.get(DATA_CRAVING);
	}

	@Override
	public void setCraving(int craving) {
		this.entityData.set(DATA_CRAVING, craving);
	}
	
	@Override
	public int getCravingTimer() {
		return this.cravingTimer;
	}

	@Override
	public void setCravingTimer(int timer) {
		this.cravingTimer = timer;
	}

	@Override
	public Craving getCravingChosen() {
		return this.entityData.get(DATA_CRAVING_CHOSEN);
	}

	@Override
	public void setCravingChosen(Craving craving) {
		this.entityData.set(DATA_CRAVING_CHOSEN, craving);
	}

	@Override
	public int getMilking() {
	    return this.entityData.get(DATA_MILKING);
	}
	
	@Override
	public void setMilking(int milking) {
	    this.entityData.set(DATA_MILKING, milking);
	}
	
	@Override
	public int getMilkingTimer() {
	    return this.milkingTimer;
	}
	
	@Override
	public void setMilkingTimer(int timer) {
	    this.milkingTimer = timer;
	}
	
	@Override
	public int getBellyRubs() {
	    return this.entityData.get(DATA_BELLY_RUBS);
	}
	
	@Override
	public void setBellyRubs(int bellyRubs) {
	    this.entityData.set(DATA_BELLY_RUBS, bellyRubs);
	}
	
	@Override
	public int getBellyRubsTimer() {
	    return this.bellyRubsTimer;
	}
	
	@Override
	public void setBellyRubsTimer(int timer) {
        this.bellyRubsTimer = timer;
    }
}
