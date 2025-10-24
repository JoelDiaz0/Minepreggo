package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import net.minecraft.world.entity.player.Inventory;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP0;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;

public class ZombieGirlP0InventaryGUIMenu extends AbstractZombieGirlInventaryGUIMenu<TamableZombieGirlP0> {
	public ZombieGirlP0InventaryGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P0_INVENTARY_GUI.get(), id, inv, extraData, TamableZombieGirlP0.class);
	
	}
}
