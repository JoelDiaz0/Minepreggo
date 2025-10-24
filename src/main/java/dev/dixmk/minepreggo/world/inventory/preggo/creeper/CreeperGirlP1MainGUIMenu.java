package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP1;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP1MainGUIMenu extends AbstractCreeperGirlMainGUIMenu<TamableCreeperGirlP1> {	
	public CreeperGirlP1MainGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P1_MAIN_GUI.get(), id, inv, extraData, TamableCreeperGirlP1.class);
	}
}