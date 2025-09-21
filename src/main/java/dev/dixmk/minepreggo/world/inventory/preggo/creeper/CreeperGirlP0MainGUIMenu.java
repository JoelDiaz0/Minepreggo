package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import net.minecraft.world.entity.player.Inventory;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP0;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;


public class CreeperGirlP0MainGUIMenu extends AbstractCreeperGirlMainGUIMenu<TamableCreeperGirlP0> {	
	public CreeperGirlP0MainGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P0_MAIN_GUI.get(), TamableCreeperGirlP0.class, id, inv, extraData);
	}
}
