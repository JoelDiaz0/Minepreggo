package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP8;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP8InventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP8InventaryGUIScreen extends AbstractCreeperGirlInventaryGUIScreen<TamableCreeperGirlP8, CreeperGirlP8InventaryGUIMenu> {
	public CreeperGirlP8InventaryGUIScreen(CreeperGirlP8InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
