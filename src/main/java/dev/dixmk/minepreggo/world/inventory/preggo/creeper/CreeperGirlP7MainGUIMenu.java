package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP7;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP7MainGUIMenu extends AbstractCreeperGirlMainGUIMenu<TamableCreeperGirlP7> {	
	public CreeperGirlP7MainGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P7_MAIN_GUI.get(), TamableCreeperGirlP7.class, id, inv, extraData);
	}
}
