package dev.dixmk.minepreggo.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;

public abstract class PregnancySystemP3 <E extends TamableAnimal
	& IPreggoMob & IPregnancySystem & IPregnancyP3> extends PregnancySystemP2<E> {

	protected PregnancySystemP3(@Nonnull E preggoMob) {
		super(preggoMob);
	}
	
	protected void evaluateBellyRubsTimer(final int totalTicksOfBellyRubs) {
		if (preggoMob.getBellyRubs() < 20) {
	        if (preggoMob.getBellyRubsTimer() >= totalTicksOfBellyRubs) {
	        	preggoMob.setBellyRubs(preggoMob.getBellyRubs() + 1);
	        	preggoMob.setBellyRubsTimer(0);
	        }
	        else {
	        	preggoMob.setBellyRubsTimer(preggoMob.getBellyRubsTimer() + 1);
	        }
		}	
	}
	
	@Override
	protected void evaluatePregnancySymptoms(Level level) {		
		if (preggoMob.getPregnancySymptom() == PregnancySymptom.NONE) {		
			if (!level.isClientSide()) {			
				if (preggoMob.getCraving() >= PregnancySystemConstants.ACTIVATE_CRAVING_SYMPTOM) {		
					preggoMob.setPregnancySymptom(PregnancySymptom.CRAVING);	
				}
				else if (preggoMob.getMilking() >= PregnancySystemConstants.ACTIVATE_MILKING_SYMPTOM) {
					preggoMob.setPregnancySymptom(PregnancySymptom.MILKING);
				}
				else if (preggoMob.getBellyRubs() >= PregnancySystemConstants.ACTIVATE_BELLY_RUBS_SYMPTOM) {
					preggoMob.setPregnancySymptom(PregnancySymptom.BELLY_RUBS);
				}
			}
		}
		else {
			if (preggoMob.getPregnancySymptom() == PregnancySymptom.CRAVING
					&& preggoMob.getCravingChosen() == Craving.NONE) {
				preggoMob.setCravingChosen(CravingFactory.getRandomCraving(randomSource));
			}
		}
	}
	
	@Override
	protected final void evaluatePregnancyPains(Level level) {
		
		if (preggoMob.getPregnancyPain() == PregnancyPain.NONE) {		
			if (!level.isClientSide()) {
				if (randomSource.nextFloat() < PregnancySystemConstants.HIGH_MORNING_SICKNESS_PROBABILITY) {
					preggoMob.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);		
				}
				else if (randomSource.nextFloat() < PregnancySystemConstants.LOW_PREGNANCY_PAIN_PROBABILITY) {
					preggoMob.setPregnancyPain(PregnancyPain.KICKING);		
				}
			}		
		} 
		else {					
			if ((preggoMob.getPregnancyPain() == PregnancyPain.MORNING_SICKNESS && preggoMob.getPregnancyPainTimer() >= PregnancySystemConstants.TOTAL_TICKS_MORNING_SICKNESS)
					|| (preggoMob.getPregnancyPain() == PregnancyPain.KICKING && preggoMob.getPregnancyPainTimer() >= PregnancySystemConstants.TOTAL_TICKS_KICKING_P3)) {
				preggoMob.setPregnancyPainTimer(0);
				preggoMob.setPregnancyPain(PregnancyPain.NONE);
			}
			else {
				preggoMob.setPregnancyPainTimer(preggoMob.getPregnancyPainTimer() + 1);
			}
		}
	}
	
	
	@Override
	protected boolean activateAngry() {
		return super.activateAngry() || preggoMob.getBellyRubs() >= 20;
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
		this.evaluateHungryTimer(level, x, y, z, MinepreggoModConfig.getTotalTicksOfHungryP3());
		this.evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP3());
		this.evaluateMilkingTimer(MinepreggoModConfig.getTotalTicksOfMilkingP3());
		this.evaluateBellyRubsTimer(MinepreggoModConfig.getTotalTicksOfBellyRubsP3());
		this.evaluateAngry(level, x, y, z, PregnancySystemConstants.MEDIUM_ANGER_PROBABILITY);
		
		this.evaluatePregnancySymptoms(level);
		this.evaluatePregnancyPains(level);
	}
}
