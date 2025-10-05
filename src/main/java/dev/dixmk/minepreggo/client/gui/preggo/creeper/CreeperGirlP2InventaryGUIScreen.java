package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP2;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP2InventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP2InventaryGUIScreen extends AbstractCreeperGirlInventaryGUIScreen<TamableCreeperGirlP2, CreeperGirlP2InventaryGUIMenu> {
	public CreeperGirlP2InventaryGUIScreen(CreeperGirlP2InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
