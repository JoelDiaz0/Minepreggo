package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP5;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP5InventaryGUIMenu extends AbstractZombieGirlInventaryGUIMenu<TamableZombieGirlP5> {
	public ZombieGirlP5InventaryGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P5_INVENTARY_GUI.get(), id, inv, extraData, TamableZombieGirlP5.class);
	
	}
}
