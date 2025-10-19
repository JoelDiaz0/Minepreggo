package dev.dixmk.minepreggo.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.entity.preggo.Craving;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP5;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.PregnancySystemP5;
import dev.dixmk.minepreggo.entity.preggo.PregnantPreggoMobSystem;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.utils.PreggoMobHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamableCreeperGirlP5 extends AbstractTamablePregnantCreeperGirl<PregnantPreggoMobSystem<TamableCreeperGirlP5>,PregnancySystemP5<TamableCreeperGirlP5>> implements IPregnancyP5 {
	
	public TamableCreeperGirlP5(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P5.get(), world);
	}

	public TamableCreeperGirlP5(EntityType<TamableCreeperGirlP5> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}

	@Override
	protected PregnantPreggoMobSystem<TamableCreeperGirlP5> createPreggoMobSystem() {
		return new PregnantPreggoMobSystem<>(this, MinepreggoModConfig.getTotalTicksOfHungryP2());
	}
	
	@Override
	protected PregnancySystemP5<TamableCreeperGirlP5> createPregnancySystem() {
		return new PregnancySystemP5<>(this) {
			@Override
			protected void changePregnancyStage() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P6.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);
					PreggoMobHelper.transferPregnancyP4Data(preggoMob, creeperGirl);
					PreggoMobHelper.transferPreggoMobInventary(preggoMob, creeperGirl);
					PreggoMobHelper.transferAttackTarget(preggoMob, creeperGirl);
					preggoMob.discard();
				}
			}
			
			@Override
			protected void finishMiscarriage() {
				TamableCreeperGirlP0.applyDefaultPostPartum(preggoMob);
			}
			
			@Override
			protected void finishBirth() {
				TamableCreeperGirlP0.applyDefaultPostPartum(preggoMob);
			}
		};
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.215);
	}
	
	@Override
	public PregnancyStage getCurrentPregnancyStage() {
		return PregnancyStage.P5;
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

	@Override
	public int getHorny() {
        return this.entityData.get(DATA_HORNY);
    }
	
	@Override
	public void setHorny(int horny) {
        this.entityData.set(DATA_HORNY, horny);
    }
	
	@Override
	public int getHornyTimer() {
        return this.hornyTimer;
    }
	
	@Override
	public void setHornyTimer(int timer) {
        this.hornyTimer = timer;
    }
}
