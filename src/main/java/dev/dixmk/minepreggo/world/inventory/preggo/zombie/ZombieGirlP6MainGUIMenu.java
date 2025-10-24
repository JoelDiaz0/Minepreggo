package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP6;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP6MainGUIMenu extends AbstractZombieGirlMainGUIMenu<TamableZombieGirlP6> {
	public ZombieGirlP6MainGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P6_MAIN_GUI.get(), id, inv, extraData, TamableZombieGirlP6.class);
	}
}
