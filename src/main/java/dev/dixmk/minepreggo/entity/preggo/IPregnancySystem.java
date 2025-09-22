package dev.dixmk.minepreggo.entity.preggo;

public interface IPregnancySystem {
	int getDaysByStage();
	void setDaysByStage(int days);
	
	int getPregnancyHealth();
	void setPregnancyHealth(int health);
	
	int getDaysPassed();
	void setDaysPassed(int days);
	
	int getDaysToGiveBirth();
	void setDaysToGiveBirth(int days);
	
	PregnancySymptom getPregnancySymptom();
	void setPregnancySymptom(PregnancySymptom symptom);
	
	PregnancyState getPregnancyState();
	void setPregnancyState(PregnancyState stage);
	
    int getPregnancyStateTimer();
    void setPregnancyStateTimer(int ticks);
	
	public BabyType getBabyType();
	
	boolean isIncapacitated();
	
	PregnancyStage getCurrentPregnancyStage();
}
