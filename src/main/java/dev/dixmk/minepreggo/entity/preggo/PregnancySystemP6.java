package dev.dixmk.minepreggo.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;

import net.minecraft.world.entity.TamableAnimal;

public abstract class PregnancySystemP6<E extends TamableAnimal
	& IPreggoMob & IPregnancySystem & IPregnancyP6> extends PregnancySystemP5<E> {

	protected PregnancySystemP6(@Nonnull E preggoMob) {
		super(preggoMob);
	}
	
	@Override
	public void evaluate() {
		
		final var level = preggoMob.level();
		final var x =  preggoMob.getX();
		final var y = preggoMob.getY();
		final var z = preggoMob.getZ();
		
		if (evaluteBirth(level, x, y, z,
				PregnancySystemConstants.TOTAL_TICKS_PREBIRTH_P6,
				PregnancySystemConstants.TOTAL_TICKS_BIRTH_P6) == Result.SUCCESS) {
			return;
		}
		
		if (evaluatePregnancyStageChange() == Result.SUCCESS) {
			return;
		}
		
		if (evaluateMiscarriage(level, x, y, z,
				PregnancySystemConstants.TOTAL_TICKS_MISCARRIAGE) == Result.SUCCESS) {
			return;
		}
		
		this.evaluatePregnancyTimer();
		this.evaluateHungryTimer(level, x, y, z, MinepreggoModConfig.getTotalTicksOfHungryP6());
		this.evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP6());
		this.evaluateMilkingTimer(MinepreggoModConfig.getTotalTicksOfMilkingP6());
		this.evaluateBellyRubsTimer(MinepreggoModConfig.getTotalTicksOfBellyRubsP6());
		this.evaluateHornyTimer(MinepreggoModConfig.getTotalTicksOfHornyP6());
		this.evaluateAngry(level, x, y, z, PregnancySystemConstants.HIGH_ANGER_PROBABILITY);
		
		this.evaluatePregnancySymptoms(level);
		this.evaluatePregnancyPains(level,
				PregnancySystemConstants.LOW_MORNING_SICKNESS_PROBABILITY,
				PregnancySystemConstants.HIGH_PREGNANCY_PAIN_PROBABILITY,
				PregnancySystemConstants.TOTAL_TICKS_KICKING_P6,
				PregnancySystemConstants.TOTAL_TICKS_CONTRACTION_P6);
	}
}
