package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP6;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP6InventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP6InventaryGUIScreen extends AbstractCreeperGirlInventaryGUIScreen<TamableCreeperGirlP6, CreeperGirlP6InventaryGUIMenu> {
	public CreeperGirlP6InventaryGUIScreen(CreeperGirlP6InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
