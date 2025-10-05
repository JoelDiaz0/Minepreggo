package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP3;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP3MainGUIMenu extends AbstractCreeperGirlMainGUIMenu<TamableCreeperGirlP3> {	
	public CreeperGirlP3MainGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P3_MAIN_GUI.get(), TamableCreeperGirlP3.class, id, inv, extraData);
	}
}
