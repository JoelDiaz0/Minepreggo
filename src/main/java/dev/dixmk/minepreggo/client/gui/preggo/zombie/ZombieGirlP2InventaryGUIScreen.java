package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP2;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP2InventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP2InventaryGUIScreen extends AbstractZombieGirlInventaryGUIScreen<TamableZombieGirlP2, ZombieGirlP2InventaryGUIMenu> {
	public ZombieGirlP2InventaryGUIScreen(ZombieGirlP2InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
