package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import net.minecraft.world.entity.player.Inventory;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP0;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;


public class ZombieGirlP0MainGUIMenu extends AbstractZombieGirlMainGUIMenu<TamableZombieGirlP0> {
	public ZombieGirlP0MainGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P0_MAIN_GUI.get(), TamableZombieGirlP0.class, id, inv, extraData);
	}
}
