package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP8;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP8InventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP8InventaryGUIScreen extends AbstractZombieGirlInventaryGUIScreen<TamableZombieGirlP8, ZombieGirlP8InventaryGUIMenu> {
	public ZombieGirlP8InventaryGUIScreen(ZombieGirlP8InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
