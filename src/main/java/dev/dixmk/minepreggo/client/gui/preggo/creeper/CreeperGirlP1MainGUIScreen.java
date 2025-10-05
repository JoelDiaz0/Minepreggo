package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;

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
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		if (creeperGirl != null) {
			InventoryScreen.renderEntityInInventoryFollowsAngle(guiGraphics, this.leftPos + -32, this.topPos + 105, 45, 0f + (float) Math.atan((this.leftPos + -32 - mouseX) / 40.0), (float) Math.atan((this.topPos + 49 - mouseY) / 40.0), creeperGirl);
		}
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}
	
	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(PreggoGUIHelper.DEFAULT_P1_MAIN_GUI_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);

		if (this.creeperGirl != null) {
			PreggoGUIHelper.renderCreeperGirlP1MainGUI(guiGraphics, this.leftPos, this.topPos, this.creeperGirl.getHealth(), this.creeperGirl);
		}
		
		
		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		if (this.creeperGirl != null)
			PreggoGUIHelper.renderP1LabelMainGUI(guiGraphics, this.font, this.creeperGirl);
	}
}
