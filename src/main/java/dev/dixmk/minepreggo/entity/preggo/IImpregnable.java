package dev.dixmk.minepreggo.entity.preggo;

import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public interface IImpregnable {
		
	PregnancyStage getMaxPregnancyStage();
	void setMaxPregnancyStage(PregnancyStage stage);

	PregnancyStage getCurrentPregnancyStage();
	
	PregnancyIllness getPregnancyIllness();
	void setPregnancyIllness(PregnancyIllness illness);
	
    int getPregnancyIllnessTimer();
    void setPregnancyIllnessTimer(int ticks);
	
    int getHungry();
    void setHungry(int hungry);

    boolean isWaiting();
    void setWaiting(boolean waiting);

    int getHungryTimer();
    void setHungryTimer(int ticks);

    boolean isPregnant();

    int getPregnancyTimer();
    void setPregnancyTimer(int ticks);

    boolean isSavage();
    void setSavage(boolean savage);
	
	public String getPreggoName();
	
	public ItemStackHandler getInventary();
	
	public CombinedInvWrapper getCombined();
	
	public BabyType getBabyType();
}