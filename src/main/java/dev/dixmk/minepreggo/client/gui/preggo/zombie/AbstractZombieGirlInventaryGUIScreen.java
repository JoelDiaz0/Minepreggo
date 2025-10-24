package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.client.gui.preggo.AbstractPreggoMobInventaryGUIScreen;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.AbstractZombieGirlInventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;


public abstract class AbstractZombieGirlInventaryGUIScreen
	<E extends AbstractTamableZombieGirl<?>, C extends AbstractZombieGirlInventaryGUIMenu<E>> extends AbstractPreggoMobInventaryGUIScreen<E, C> {

	protected AbstractZombieGirlInventaryGUIScreen(C container, Inventory inventory, Component text) {
		super(container, inventory, text, PreggoGUIHelper.ZOMBIE_GIRL_INVENTARY_TEXTURE);
	}

}
