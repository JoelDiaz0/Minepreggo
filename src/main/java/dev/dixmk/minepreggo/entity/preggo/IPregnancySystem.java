package dev.dixmk.minepreggo.entity.preggo;

import net.minecraft.world.item.Item;

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
	
	PregnancyPain getPregnancyPain();
	void setPregnancyPain(PregnancyPain pain);
	
    int getPregnancyPainTimer();
    void setPregnancyPainTimer(int ticks);
	
	public BabyType getBabyType();
	
	boolean isIncapacitated();
	
	PregnancyStage getCurrentPregnancyStage();
	
	boolean isValidCraving(Craving kindOfCraving, Item item);
}
