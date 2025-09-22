package dev.dixmk.minepreggo.network.capability;

import net.minecraft.world.item.Item;

public interface IPregnancyP1Capability {

	int getDaysByStage();
	void setDaysByStage(int days);
	
	int getPregnancyHealth();
	void setPregnancyHealth(int health);
	
	int getDaysPassed();
	void setDaysPassed(int days);
	
	int getDaysToGiveBirth();
	void setDaysToGiveBirth(int days);
	
	Item getCravingChosen();
	void setCravingChosen(Item craving);
	
	int getCraving();
	void setCraving(int craving);
	
	int getCravingTimer();
	void setCravingTimer(int timer);
	
	
}
