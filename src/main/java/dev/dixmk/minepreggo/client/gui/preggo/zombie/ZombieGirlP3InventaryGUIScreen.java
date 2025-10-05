package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP3;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP3InventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP3InventaryGUIScreen extends AbstractZombieGirlInventaryGUIScreen<TamableZombieGirlP3, ZombieGirlP3InventaryGUIMenu> {
	public ZombieGirlP3InventaryGUIScreen(ZombieGirlP3InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
