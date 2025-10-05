package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP3;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP3MainGUIMenu extends AbstractZombieGirlMainGUIMenu<TamableZombieGirlP3> {
	public ZombieGirlP3MainGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P3_MAIN_GUI.get(), TamableZombieGirlP3.class, id, inv, extraData);
	}
}

