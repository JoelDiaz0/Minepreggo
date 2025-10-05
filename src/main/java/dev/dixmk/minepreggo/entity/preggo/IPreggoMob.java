package dev.dixmk.minepreggo.entity.preggo;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public interface IPreggoMob {
		
	static final int HEALING_COOLDOWN_DURATION = 60;
	
	static final int HEAD_INVENTARY_SLOT = EquipmentSlot.HEAD.getFilterFlag();
	static final int CHEST_INVENTARY_SLOT = EquipmentSlot.CHEST.getFilterFlag();
	static final int LEGS_INVENTARY_SLOT = EquipmentSlot.LEGS.getFilterFlag();
	static final int FEET_INVENTARY_SLOT = EquipmentSlot.FEET.getFilterFlag();
	static final int MAINHAND_INVENTARY_SLOT = EquipmentSlot.MAINHAND.getFilterFlag();
	static final int OFFHAND_INVENTARY_SLOT = EquipmentSlot.OFFHAND.getFilterFlag();
	static final int FOOD_INVENTARY_SLOT = 6;
	
	
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

    int getHealingTimer();
    void setHealingTimer(int ticks);
    
    boolean isPregnant();

    boolean isPanic();
    void setPanic(boolean panic);
    
    int getPregnancyTimer();
    void setPregnancyTimer(int ticks);
    
    
    boolean hasCustomHeadAnimation();
    
	String getPreggoName();
	
	ItemStackHandler getInventary();
	
	CombinedInvWrapper getCombined();
	
}