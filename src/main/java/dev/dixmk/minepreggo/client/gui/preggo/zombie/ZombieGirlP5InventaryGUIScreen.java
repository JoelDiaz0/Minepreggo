package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP5;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP5InventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP5InventaryGUIScreen extends AbstractZombieGirlInventaryGUIScreen<TamableZombieGirlP5, ZombieGirlP5InventaryGUIMenu> {
	public ZombieGirlP5InventaryGUIScreen(ZombieGirlP5InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
