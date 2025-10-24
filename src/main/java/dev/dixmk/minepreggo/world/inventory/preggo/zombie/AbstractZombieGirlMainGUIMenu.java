package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractPreggoMobMainGUIMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

public abstract class AbstractZombieGirlMainGUIMenu<E extends AbstractTamableZombieGirl<?>> extends AbstractPreggoMobMainGUIMenu<E> {	
	
	protected AbstractZombieGirlMainGUIMenu(MenuType<?> menuType, int id, Inventory inv, FriendlyByteBuf extraData, Class<E> zombieGirlClass) {
		super(menuType, id, inv, extraData, zombieGirlClass);	
	}
}
