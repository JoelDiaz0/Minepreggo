package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP5;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP5MainGUIMenu extends AbstractCreeperGirlMainGUIMenu<TamableCreeperGirlP5> {	
	public CreeperGirlP5MainGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P5_MAIN_GUI.get(), id, inv, extraData, TamableCreeperGirlP5.class);
	}
}
