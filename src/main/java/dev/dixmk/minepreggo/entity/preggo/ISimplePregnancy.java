package dev.dixmk.minepreggo.entity.preggo;

import net.minecraft.util.RandomSource;

public interface ISimplePregnancy {

	PregnancyStage getCurrentPregnancyStage();
	
	PregnancyStage getMaxPregnancyStage();
	
	int getTotalDaysPassed();
	
	boolean hasPregnancyPain();
	void setPregnancyPain(boolean value);
	
	int getPregnancyPainTimer();
	void setPregnancyPainTimer(int tick);
	
	static int getRandomTotalDaysPassed(PregnancyStage currentPregnancyStage, PregnancyStage maxPregnancyStage, RandomSource randomSource) {
		
		int max = maxPregnancyStage.ordinal();
		int current = currentPregnancyStage.ordinal();
		
		if (max < 4) {
			max = 4;
		} 
		
		if (current > max) {
			current = max;
		}
		
		final int daysByStage = PregnancySystemConstants.TOTAL_PREGNANCY_DAYS / max;
		
		return randomSource.nextInt(daysByStage * current, daysByStage * (max + 1));
	}
}
