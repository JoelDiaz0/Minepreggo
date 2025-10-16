package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP0;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP0MainGUIMenu;

public class ZombieGirlP0MainGUIScreen extends AbstractZombieGirlMainGUIScreen<TamableZombieGirlP0, ZombieGirlP0MainGUIMenu> {

	public ZombieGirlP0MainGUIScreen(ZombieGirlP0MainGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		
		guiGraphics.blit(PreggoGUIHelper.DEFAULT_P0_MAIN_GUI_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
	
		if (this.zombieGirl != null)
			PreggoGUIHelper.renderDefaultPreggoP0MainGUI(guiGraphics, this.leftPos, this.topPos, this.zombieGirl.getHealth(), this.zombieGirl);

		RenderSystem.disableBlend();
	}
	
	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		if (this.zombieGirl != null)
			PreggoGUIHelper.renderDefaultPreggoP0LabelMainGUI(guiGraphics, this.font, this.zombieGirl);
	}

}

