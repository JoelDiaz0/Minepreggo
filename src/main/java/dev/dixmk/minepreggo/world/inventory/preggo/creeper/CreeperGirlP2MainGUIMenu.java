package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP2;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP2MainGUIMenu extends AbstractCreeperGirlMainGUIMenu<TamableCreeperGirlP2> {	
	public CreeperGirlP2MainGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P2_MAIN_GUI.get(), TamableCreeperGirlP2.class, id, inv, extraData);
	}
}
