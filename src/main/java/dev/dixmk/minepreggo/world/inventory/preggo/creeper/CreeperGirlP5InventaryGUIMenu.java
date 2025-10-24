package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP5;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP5InventaryGUIMenu extends AbstractCreeperGirlInventaryGUIMenu<TamableCreeperGirlP5> {
	public CreeperGirlP5InventaryGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P5_INVENTARY_GUI.get(), id, inv, extraData, TamableCreeperGirlP5.class);
	}
}