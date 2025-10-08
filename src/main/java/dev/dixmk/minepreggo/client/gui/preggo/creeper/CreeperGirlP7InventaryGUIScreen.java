package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP7;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP7InventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP7InventaryGUIScreen extends AbstractCreeperGirlInventaryGUIScreen<TamableCreeperGirlP7, CreeperGirlP7InventaryGUIMenu> {
	public CreeperGirlP7InventaryGUIScreen(CreeperGirlP7InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}