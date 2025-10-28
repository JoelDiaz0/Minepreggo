package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP8;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP8MainGUIMenu extends AbstractZombieGirlMainGUIMenu<TamableZombieGirlP8> {
	public ZombieGirlP8MainGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P8_MAIN_GUI.get(), id, inv, extraData, TamableZombieGirlP8.class);
	}
}
