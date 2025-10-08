package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP5;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP5InventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP5InventaryGUIScreen extends AbstractCreeperGirlInventaryGUIScreen<TamableCreeperGirlP5, CreeperGirlP5InventaryGUIMenu> {
	public CreeperGirlP5InventaryGUIScreen(CreeperGirlP5InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
