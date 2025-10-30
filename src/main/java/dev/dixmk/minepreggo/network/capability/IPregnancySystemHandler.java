package dev.dixmk.minepreggo.network.capability;

import java.util.Set;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.entity.preggo.BabyType;
import dev.dixmk.minepreggo.entity.preggo.PregnancyPain;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.PregnancySymptom;

public interface IPregnancySystemHandler {
	int getTotalNumOfBabies();
	
	int getDaysByStage();
	void setDaysByStage(@NonNegative int days);
	
	int getPregnancyHealth();
	void setPregnancyHealth(@NonNegative int health);
	
	int getDaysPassed();
	void setDaysPassed(@NonNegative int days);
	void incrementDaysPassed();
	
	int getDaysToGiveBirth();
	void setDaysToGiveBirth(@NonNegative int days);
	void reduceDaysToGiveBirth();
	
    int getPregnancyTimer();
    void setPregnancyTimer(@NonNegative int ticks);
    void incrementPregnancyTimer();
	
	PregnancyStage getMaxPregnancyStage();
	void setMaxPregnancyStage(PregnancyStage stage);

	PregnancyStage getCurrentPregnancyStage();
	void setCurrentPregnancyStage(PregnancyStage stage);
    
	PregnancySymptom getPregnancySymptom();
	void setPregnancySymptom(PregnancySymptom symptom);
	
	PregnancyPain getPregnancyPain();
	void setPregnancyPain(PregnancyPain pain);
	 
	Set<BabyType> getTypesOfBabies();
	
	int getNumOfBabiesByType(BabyType babyType);
	
	void addBaby(BabyType babyType, @NonNegative int num);
}
