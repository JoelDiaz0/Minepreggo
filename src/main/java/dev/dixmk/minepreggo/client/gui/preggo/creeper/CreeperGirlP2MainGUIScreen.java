package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP2;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP2MainGUIMenu;
import net.minecraft.client.gui.GuiGraphics;

public class CreeperGirlP2MainGUIScreen extends AbstractCreeperGirlMainGUIScreen<TamableCreeperGirlP2, CreeperGirlP2MainGUIMenu> {

	public CreeperGirlP2MainGUIScreen(CreeperGirlP2MainGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 185;
		this.imageHeight = 120;
	}
	
	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(PreggoGUIHelper.DEFAULT_P2_MAIN_GUI_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);


		if (this.creeperGirl != null) {
			PreggoGUIHelper.renderCreeperGirlP2MainGUI(guiGraphics, this.leftPos, this.topPos, this.creeperGirl.getHealth(), this.creeperGirl);
		}

	
		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		if (this.creeperGirl != null)
			PreggoGUIHelper.renderP2LabelMainGUI(guiGraphics, this.font, this.creeperGirl);
	}

}

