package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import net.minecraft.world.entity.player.Inventory;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP0;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP0InventaryGUIMenu;
import net.minecraft.network.chat.Component;


public class ZombieGirlP0InventaryGUIScreen extends AbstractZombieGirlInventaryGUIScreen<TamableZombieGirlP0, ZombieGirlP0InventaryGUIMenu> {

	public ZombieGirlP0InventaryGUIScreen(ZombieGirlP0InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
