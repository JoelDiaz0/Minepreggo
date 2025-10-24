package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP6;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP6MainGUIMenu extends AbstractCreeperGirlMainGUIMenu<TamableCreeperGirlP6> {	
	public CreeperGirlP6MainGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P6_MAIN_GUI.get(), id, inv, extraData, TamableCreeperGirlP6.class);
	}
}
