package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP1;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP1InventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP1InventaryGUIScreen extends AbstractZombieGirlInventaryGUIScreen<TamableZombieGirlP1, ZombieGirlP1InventaryGUIMenu> {
	public ZombieGirlP1InventaryGUIScreen(ZombieGirlP1InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}