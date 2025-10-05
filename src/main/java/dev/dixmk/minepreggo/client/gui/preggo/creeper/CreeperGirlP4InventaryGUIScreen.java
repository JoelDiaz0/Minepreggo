package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP4;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP4InventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP4InventaryGUIScreen extends AbstractCreeperGirlInventaryGUIScreen<TamableCreeperGirlP4, CreeperGirlP4InventaryGUIMenu> {
	public CreeperGirlP4InventaryGUIScreen(CreeperGirlP4InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
