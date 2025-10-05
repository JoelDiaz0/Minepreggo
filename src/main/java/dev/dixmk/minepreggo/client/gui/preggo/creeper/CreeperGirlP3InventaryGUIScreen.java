package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP3;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP3InventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP3InventaryGUIScreen extends AbstractCreeperGirlInventaryGUIScreen<TamableCreeperGirlP3, CreeperGirlP3InventaryGUIMenu> {
	public CreeperGirlP3InventaryGUIScreen(CreeperGirlP3InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
