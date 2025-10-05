package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP4;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP4InventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP4InventaryGUIScreen extends AbstractZombieGirlInventaryGUIScreen<TamableZombieGirlP4, ZombieGirlP4InventaryGUIMenu> {
	public ZombieGirlP4InventaryGUIScreen(ZombieGirlP4InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
