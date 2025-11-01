package dev.dixmk.minepreggo.client.gui.preggo;

import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.inventory.preggo.PreggoMobMedicalCheckUpGUIMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class PreggoMobMedicalCheckUpGUIScreen 
	extends AbstractMedicalCheckUpGUIScreen<PreggoMob, ScientificIllager, PreggoMobMedicalCheckUpGUIMenu> {

	public PreggoMobMedicalCheckUpGUIScreen(PreggoMobMedicalCheckUpGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}
}
