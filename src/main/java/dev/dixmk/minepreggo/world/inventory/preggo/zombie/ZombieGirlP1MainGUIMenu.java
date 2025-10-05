package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP1;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP1MainGUIMenu extends AbstractZombieGirlMainGUIMenu<TamableZombieGirlP1> {
	public ZombieGirlP1MainGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P1_MAIN_GUI.get(), TamableZombieGirlP1.class, id, inv, extraData);
	}
}

