package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import net.minecraft.world.entity.player.Inventory;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP0;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;

public class CreeperGirlP0InventaryGUIMenu extends AbstractCreeperGirlInventaryGUIMenu<TamableCreeperGirlP0> {
	public CreeperGirlP0InventaryGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_P0_INVENTARY_GUI.get(), TamableCreeperGirlP0.class, id, inv, extraData);
	}
}
