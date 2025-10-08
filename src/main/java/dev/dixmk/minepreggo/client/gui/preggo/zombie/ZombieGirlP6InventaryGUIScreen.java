package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP6;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP6InventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP6InventaryGUIScreen extends AbstractZombieGirlInventaryGUIScreen<TamableZombieGirlP6, ZombieGirlP6InventaryGUIMenu> {
	public ZombieGirlP6InventaryGUIScreen(ZombieGirlP6InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
