package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP1;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP1InventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP1InventaryGUIScreen extends AbstractCreeperGirlInventaryGUIScreen<TamableCreeperGirlP1, CreeperGirlP1InventaryGUIMenu> {

	public CreeperGirlP1InventaryGUIScreen(CreeperGirlP1InventaryGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}

}
