package dev.dixmk.minepreggo.entity.preggo.creeper;

import net.minecraftforge.network.PlayMessages;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.entity.preggo.Craving;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP1;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.PregnancySystemP1;
import dev.dixmk.minepreggo.entity.preggo.PregnantPreggoMobSystem;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.utils.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantHumanoidCreeperGirl;


public class TamableCreeperGirlP1 extends AbstractTamablePregnantHumanoidCreeperGirl<PregnantPreggoMobSystem<TamableCreeperGirlP1>, PregnancySystemP1<TamableCreeperGirlP1>> implements IPregnancyP1 {
	
	public TamableCreeperGirlP1(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P1.get(), world);
	}

	public TamableCreeperGirlP1(EntityType<TamableCreeperGirlP1> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnantPreggoMobSystem<TamableCreeperGirlP1> createPreggoMobSystem() {
		return new PregnantPreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP1());
	}
	
	@Override
	protected PregnancySystemP1<TamableCreeperGirlP1> createPregnancySystem() {
		return new PregnancySystemP1<TamableCreeperGirlP1>(this) {
			@Override
			protected void changePregnancyStage() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P2.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);
					PreggoMobHelper.transferPregnancyP1Data(preggoMob, creeperGirl);
					PreggoMobHelper.transferPreggoMobInventary(preggoMob, creeperGirl);
					PreggoMobHelper.transferAttackTarget(preggoMob, creeperGirl);
				}
			}
			
			@Override
			protected void postMiscarriage() {
				TamableCreeperGirlP0.onPostPartum(preggoMob);
			}
		};
	}

	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.24);
	}
	
	@Override
	public PregnancyStage getCurrentPregnancyStage() {
		return PregnancyStage.P1;
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
}
