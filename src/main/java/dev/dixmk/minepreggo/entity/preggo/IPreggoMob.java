package dev.dixmk.minepreggo.entity.preggo;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public interface IPreggoMob {
	
	static final int HEAD_INVENTORY_SLOT = EquipmentSlot.HEAD.getFilterFlag();
	static final int CHEST_INVENTORY_SLOT = EquipmentSlot.CHEST.getFilterFlag();
	static final int LEGS_INVENTORY_SLOT = EquipmentSlot.LEGS.getFilterFlag();
	static final int FEET_INVENTORY_SLOT = EquipmentSlot.FEET.getFilterFlag();
	static final int MAINHAND_INVENTORY_SLOT = EquipmentSlot.MAINHAND.getFilterFlag();
	static final int OFFHAND_INVENTORY_SLOT = EquipmentSlot.OFFHAND.getFilterFlag();
	static final int FOOD_INVENTORY_SLOT = 6;
	
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
    
    boolean isPanic();
    void setPanic(boolean panic);
      
    boolean hasCustomHeadAnimation();
    
	PreggoMobState getState();
	void setState(PreggoMobState state);
    
	String getPreggoName();
	
	ItemStackHandler getInventary();
	
	CombinedInvWrapper getCombined();
}