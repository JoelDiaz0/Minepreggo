package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import dev.dixmk.minepreggo.client.gui.preggo.AbstractPreggoMobInventaryGUIScreen;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.AbstractCreeperGirlInventaryGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public abstract class AbstractCreeperGirlInventaryGUIScreen<E extends AbstractTamableCreeperGirl<?>,
	C extends AbstractCreeperGirlInventaryGUIMenu<E>> extends AbstractPreggoMobInventaryGUIScreen<E, C> {

	protected AbstractCreeperGirlInventaryGUIScreen(C container, Inventory inventory, Component text) {
		super(container, inventory, text, PreggoGUIHelper.CREEPER_GIRL_INVENTARY_TEXTURE);
	}
}
