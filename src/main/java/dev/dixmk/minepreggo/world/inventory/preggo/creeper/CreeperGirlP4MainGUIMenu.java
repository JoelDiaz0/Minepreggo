package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP4;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP4MainGUIMenu extends AbstractCreeperGirlMainGUIMenu<TamableCreeperGirlP4> {	
	public CreeperGirlP4MainGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P4_MAIN_GUI.get(), id, inv, extraData, TamableCreeperGirlP4.class);
	}
}
