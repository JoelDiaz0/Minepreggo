package dev.dixmk.minepreggo.entity.preggo;

public interface ISimplePregnancy {

	PregnancyStage getCurrentStage();
	
	SimplePregnancyData getSimplePregnancyData();
	
	
	
	
	record SimplePregnancyData(int numOfBabies, int totalDaysPassed, int totalDays) {}
}
