package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP7;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP7InventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP7InventaryGUIScreen extends AbstractZombieGirlInventaryGUIScreen<TamableZombieGirlP7, ZombieGirlP7InventaryGUIMenu> {
	public ZombieGirlP7InventaryGUIScreen(ZombieGirlP7InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
