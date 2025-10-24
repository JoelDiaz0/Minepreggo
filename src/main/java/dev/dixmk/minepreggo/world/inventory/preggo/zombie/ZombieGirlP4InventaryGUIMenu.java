package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP4;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP4InventaryGUIMenu extends AbstractZombieGirlInventaryGUIMenu<TamableZombieGirlP4> {
	public ZombieGirlP4InventaryGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P4_INVENTARY_GUI.get(), id, inv, extraData, TamableZombieGirlP4.class);
	
	}
}
