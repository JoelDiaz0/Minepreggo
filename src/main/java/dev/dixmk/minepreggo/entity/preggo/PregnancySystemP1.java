package dev.dixmk.minepreggo.entity.preggo;

import java.util.Comparator;
import java.util.List;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.utils.PreggoMobHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public abstract class PregnancySystemP1<
	E extends TamableAnimal & IPreggoMob & IPregnancySystem & IPregnancyP1> extends PreggoMobSystem<E> {

	protected PregnancySystemP1(@Nonnull E preggoMob) {
		super(preggoMob);
	}
	
	protected Result evaluatePregnancyStageChange() {
	    if (preggoMob.getDaysPassed() == preggoMob.getDaysByStage()) {
	        return Result.SUCCESS;
	    }
	    return Result.NOTHING;
	}
	
	protected void evaluatePregnancyTimer() {
        if (preggoMob.getPregnancyTimer() >= MinepreggoModConfig.getTotalTicksByDay()) {
        	preggoMob.setPregnancyTimer(0);
        	preggoMob.setDaysPassed(preggoMob.getDaysPassed() + 1);
        	preggoMob.setDaysToGiveBirth(preggoMob.getDaysToGiveBirth() - 1);
        } else {
        	preggoMob.setPregnancyTimer(preggoMob.getPregnancyTimer() + 1);
        }
	}
	
	protected Result evaluateMiscarriage(Level level, double x, double y, double z, final int totalTicksOfMiscarriage) {
   	    
	    if (preggoMob.getPregnancyPain() == PregnancyPain.MISCARRIAGE) {
	        if (preggoMob.getPregnancyPainTimer() < totalTicksOfMiscarriage) {
	        	preggoMob.setPregnancyPainTimer(preggoMob.getPregnancyPainTimer() + 1);
	            level.addParticle(ParticleTypes.FALLING_DRIPSTONE_LAVA, x, (y + preggoMob.getBbHeight() * 0.35), z, 0, 1, 0);
	        } else {
	        	return Result.SUCCESS;
	        }
	        return Result.PROCESS; 
	    }
	    
	    return Result.NOTHING;
	}
	
	protected void evaluateCravingTimer(final int totalTicksOfCraving) {   				
		if (preggoMob.getCraving() < 20) {
	        if (preggoMob.getCravingTimer() >= totalTicksOfCraving) {
	        	preggoMob.setCraving(preggoMob.getCraving() + 1);
	        	preggoMob.setCravingTimer(0);
	        }
	        else {
	        	preggoMob.setCravingTimer(preggoMob.getCravingTimer() + 1);
	        }
		}	
	}
	
	protected void evaluateAngry(Level level, double x, double y, double z, final float angerProbability) {

	    if (!preggoMob.isAngry()) {
	        if (!level.isClientSide() && activateAngry()) {
	        	preggoMob.setAngry(true);
	        }
	    } else {
	        if (!level.isClientSide() && !PreggoMobHelper.hasValidTarget(preggoMob) && randomSource.nextFloat() < angerProbability) {
	            final Vec3 center = new Vec3(x, y, z);      
	
	            List<Player> owner = level.getEntitiesOfClass(Player.class, new AABB(center, center).inflate(12), preggoMob::isOwnedBy)
	                    .stream()
	                    .sorted(Comparator.comparingDouble(entcnd -> entcnd.distanceToSqr(center)))
	                    .toList();
	           
	            if (!owner.isEmpty()) {
	            	preggoMob.setTarget(owner.get(0));
	            }
	        }
	    }
	}

	protected void evaluatePregnancyPains(Level level) {
		if (preggoMob.getPregnancyPain() == PregnancyPain.NONE) {		
			if (!level.isClientSide()
				&& randomSource.nextFloat() < PregnancySystemConstants.LOW_MORNING_SICKNESS_PROBABILITY) {
				preggoMob.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);		
			}		
		} 
		else {
			if (preggoMob.getPregnancyPainTimer() >= PregnancySystemConstants.TOTAL_TICKS_MORNING_SICKNESS) {
				preggoMob.setPregnancyPainTimer(0);
				preggoMob.setPregnancyPain(PregnancyPain.NONE);
			}
			else {
				preggoMob.setPregnancyPainTimer(preggoMob.getPregnancyPainTimer() + 1);
			}
		}
	}
	
	protected void evaluatePregnancySymptoms(Level level) {		
		if (preggoMob.getPregnancySymptom() == PregnancySymptom.NONE) {
			if (preggoMob.getCraving() >= PregnancySystemConstants.ACTIVATE_CRAVING_SYMPTOM && !level.isClientSide()) {		
				preggoMob.setPregnancySymptom(PregnancySymptom.CRAVING);	
			}
		}
		else {
			if (preggoMob.getCravingChosen() == Craving.NONE) {
				preggoMob.setCravingChosen(CravingFactory.getRandomCraving(randomSource));
			}
		}
	}
	
	
	@Override
	public void evaluate() {
		
		if (evaluatePregnancyStageChange() == Result.SUCCESS) {
			return;
		}
		
		final var level = preggoMob.level();
		final var x =  preggoMob.getX();
		final var y = preggoMob.getY();
		final var z = preggoMob.getZ();
		
		if (evaluateMiscarriage(level, x, y, z, PregnancySystemConstants.TOTAL_TICKS_MISCARRIAGE) == Result.SUCCESS) {
			return; 
		}
		
		this.evaluatePregnancyTimer();
		this.evaluateHungryTimer(level, x, y, z, MinepreggoModConfig.getTotalTicksOfHungryP1());
		this.evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP1());
		this.evaluateAngry(level, x, y, z, PregnancySystemConstants.LOW_ANGER_PROBABILITY);
		
		this.evaluatePregnancySymptoms(level);
		this.evaluatePregnancyPains(level);
	}
	
	protected boolean activateAngry() {
		return preggoMob.getCraving() >= 20 || preggoMob.getHungry() <= 2;
	}

	
	protected abstract void changePregnancyStage();
	
	protected abstract void finishMiscarriage();
	
	
	
	@Override
	protected final void startPregnancy() {}

}

