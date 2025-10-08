package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP6;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP6MainGUIMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CreeperGirlP6MainGUIScreen extends AbstractCreeperGirlMainGUIScreen<TamableCreeperGirlP6, CreeperGirlP6MainGUIMenu> {

	public CreeperGirlP6MainGUIScreen(CreeperGirlP6MainGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 178;
		this.imageHeight = 139;
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(PreggoGUIHelper.DEFAULT_P4_MAIN_GUI_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		if (this.creeperGirl != null) {
			PreggoGUIHelper.renderCreeperGirlP4MainGUI(guiGraphics, this.leftPos, this.topPos, this.creeperGirl.getHealth(), this.creeperGirl);
		}
	
		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		if (this.creeperGirl != null)
			PreggoGUIHelper.renderP4LabelMainGUI(guiGraphics, this.font, this.creeperGirl);
	}

}
