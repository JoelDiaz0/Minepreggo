package dev.dixmk.minepreggo.entity.preggo;

public interface IPregnancyP1 extends IImpregnable {
	int getDaysByStage();
	void setDaysByStage(int days);
	
	int getPregnancyHealth();
	void setPregnancyHealth(int health);
	
	int getDaysPassed();
	void setDaysPassed(int days);
	
	int getDaysToGiveBirth();
	void setDaysToGiveBirth(int days);
	
	int getCravingChosen();
	void setCravingChosen(int cravingId);
	
	int getCraving();
	void setCraving(int craving);
	
	int getCravingTimer();
	void setCravingTimer(int timer);
}
