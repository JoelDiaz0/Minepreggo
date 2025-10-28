package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP8;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP8InventaryGUIMenu extends AbstractCreeperGirlInventaryGUIMenu<TamableCreeperGirlP8> {
	public CreeperGirlP8InventaryGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P8_INVENTARY_GUI.get(), id, inv, extraData, TamableCreeperGirlP8.class);
	}
}
