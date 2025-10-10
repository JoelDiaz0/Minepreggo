package dev.dixmk.minepreggo.entity.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.Craving;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP1;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.PregnancySystemP1;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.utils.PreggoMobHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableZombieGirlP1 extends AbstractTamablePregnantZombieGirl<PregnancySystemP1<TamableZombieGirlP1>> implements IPregnancyP1 {
	
	public TamableZombieGirlP1(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get(), world);
	}

	public TamableZombieGirlP1(EntityType<TamableZombieGirlP1> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}
	
	@Override
	protected PregnancySystemP1<TamableZombieGirlP1> createPreggoMobSystem() {
		return new PregnancySystemP1<>(this) {
			@Override
			protected void changePregnancyStage() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.transferPregnancyP1Data(preggoMob, zombieGirl);			
					PreggoMobHelper.transferPreggoMobInventary(preggoMob, zombieGirl);
					PreggoMobHelper.transferAttackTarget(preggoMob, zombieGirl);
					preggoMob.discard();
				}
			}
			
			@Override
			protected void finishMiscarriage() {
				TamableZombieGirlP0.applyDefaultPostMiscarriage(preggoMob);
			}
		};
	}

	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.235);
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
