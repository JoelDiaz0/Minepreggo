package dev.dixmk.minepreggo.entity.preggo;

public interface ISimplePregnancy {

	PregnancyStage getCurrentPregnancyStage();
	
	PregnancyStage getMaxPregnancyStage();
	
	SimplePregnancyData getSimplePregnancyData();
	
	record SimplePregnancyData(int totalDaysPassed, int totalDays) {}
}
