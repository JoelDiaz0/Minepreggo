package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP2;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP2InventaryGUIMenu extends AbstractZombieGirlInventaryGUIMenu<TamableZombieGirlP2> {
	public ZombieGirlP2InventaryGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P2_INVENTARY_GUI.get(), TamableZombieGirlP2.class, id, inv, extraData);
	
	}
}
