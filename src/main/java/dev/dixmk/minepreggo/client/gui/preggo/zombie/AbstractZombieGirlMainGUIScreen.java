package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.client.gui.preggo.AbstractPreggoMobMainGUIScreen;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.AbstractZombieGirlMainGUIMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;


public abstract class AbstractZombieGirlMainGUIScreen
	<E extends AbstractTamableZombieGirl<?>, G extends AbstractZombieGirlMainGUIMenu<E>> extends AbstractPreggoMobMainGUIScreen<E, G> {
	
	protected AbstractZombieGirlMainGUIScreen(G container, Inventory inventory, Component text) {
		super(container, inventory, text, 1, 121);
	}
}
