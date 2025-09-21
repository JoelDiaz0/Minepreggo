package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import net.minecraft.world.entity.player.Inventory;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP0;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP0InventaryGUIMenu;
import net.minecraft.network.chat.Component;


public class CreeperGirlP0InventaryGUIScreen extends AbstractCreeperGirlInventaryGUIScreen<TamableCreeperGirlP0, CreeperGirlP0InventaryGUIMenu> {

	public CreeperGirlP0InventaryGUIScreen(CreeperGirlP0InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}

}
