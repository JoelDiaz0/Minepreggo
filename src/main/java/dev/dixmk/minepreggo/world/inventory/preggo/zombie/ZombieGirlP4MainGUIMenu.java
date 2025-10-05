package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP4;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP4MainGUIMenu extends AbstractZombieGirlMainGUIMenu<TamableZombieGirlP4> {
	public ZombieGirlP4MainGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P4_MAIN_GUI.get(), TamableZombieGirlP4.class, id, inv, extraData);
	}
}

