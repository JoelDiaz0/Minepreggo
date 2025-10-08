package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP6;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP6InventaryGUIMenu extends AbstractZombieGirlInventaryGUIMenu<TamableZombieGirlP6> {
	public ZombieGirlP6InventaryGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P6_INVENTARY_GUI.get(), TamableZombieGirlP6.class, id, inv, extraData);
	
	}
}
