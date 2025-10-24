package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP1;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP1MainGUIMenu;


public class CreeperGirlP1MainGUIScreen extends AbstractCreeperGirlMainGUIScreen<TamableCreeperGirlP1, CreeperGirlP1MainGUIMenu> {

	public CreeperGirlP1MainGUIScreen(CreeperGirlP1MainGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 183;
		this.imageHeight = 112;
	}
	
	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(PreggoGUIHelper.DEFAULT_P1_MAIN_GUI_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
	
		this.preggoMob.ifPresent(creeperGirl -> PreggoGUIHelper.renderCreeperGirlP1MainGUI(guiGraphics, this.leftPos, this.topPos, creeperGirl.getHealth(), creeperGirl));

		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		this.preggoMob.ifPresent(creeperGirl -> PreggoGUIHelper.renderP1LabelMainGUI(guiGraphics, this.font, creeperGirl));
	}
}
