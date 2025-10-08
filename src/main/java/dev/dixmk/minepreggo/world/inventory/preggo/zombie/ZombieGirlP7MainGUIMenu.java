package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP7;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP7MainGUIMenu extends AbstractZombieGirlMainGUIMenu<TamableZombieGirlP7> {
	public ZombieGirlP7MainGUIMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P7_MAIN_GUI.get(), TamableZombieGirlP7.class, id, inv, extraData);
	}
}
