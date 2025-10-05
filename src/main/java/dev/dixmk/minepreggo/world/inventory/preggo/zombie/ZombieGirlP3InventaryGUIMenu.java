package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP3;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP3InventaryGUIMenu extends AbstractZombieGirlInventaryGUIMenu<TamableZombieGirlP3> {
	public ZombieGirlP3InventaryGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P3_INVENTARY_GUI.get(), TamableZombieGirlP3.class, id, inv, extraData);
	
	}
}
