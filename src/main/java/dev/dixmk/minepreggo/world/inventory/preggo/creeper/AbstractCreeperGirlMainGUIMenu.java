package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractPreggoMobMainGUIMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

public abstract class AbstractCreeperGirlMainGUIMenu<E extends AbstractTamableCreeperGirl<?>> extends AbstractPreggoMobMainGUIMenu<E> {

	protected AbstractCreeperGirlMainGUIMenu(MenuType<?> menuType, int id, Inventory inv, FriendlyByteBuf extraData, Class<E> creeperGirlClass) {
		super(menuType, id, inv, extraData, creeperGirlClass);
	}
}
