package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP5;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP5MainGUIMenu extends AbstractZombieGirlMainGUIMenu<TamableZombieGirlP5> {
	public ZombieGirlP5MainGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P5_MAIN_GUI.get(), TamableZombieGirlP5.class, id, inv, extraData);
	}
}
