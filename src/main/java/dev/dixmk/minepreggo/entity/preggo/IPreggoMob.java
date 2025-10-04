package dev.dixmk.minepreggo.entity.preggo;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public interface IPreggoMob {
		
	static final int HEALING_COOLDOWN_DURATION = 100;
	
	static final int INVENTARY_HEAD = EquipmentSlot.HEAD.getFilterFlag();
	static final int INVENTARY_CHEST = EquipmentSlot.CHEST.getFilterFlag();
	static final int INVENTARY_LEGS = EquipmentSlot.LEGS.getFilterFlag();
	static final int INVENTARY_FEET = EquipmentSlot.FEET.getFilterFlag();
	static final int INVENTARY_MAINHAND = 4;
	static final int INVENTARY_OFFHAND = 5;
	static final int INVENTARY_FOOD = 6;
	
	
	PregnancyStage getMaxPregnancyStage();
	void setMaxPregnancyStage(PregnancyStage stage);

	PreggoMobState getState();
	void setState(PreggoMobState state);
	
    int getHungry();
    void setHungry(int hungry);

    boolean isWaiting();
    void setWaiting(boolean waiting);
    
    boolean isSavage();
    void setSavage(boolean savage);
    
    boolean isAngry();
    void setAngry(boolean angry);
    
    int getHungryTimer();
    void setHungryTimer(int ticks);

    boolean isPregnant();

    int getPregnancyTimer();
    void setPregnancyTimer(int ticks);
    
    
    boolean hasCustomHeadAnimation();
    
	String getPreggoName();
	
	ItemStackHandler getInventary();
	
	CombinedInvWrapper getCombined();
	
}