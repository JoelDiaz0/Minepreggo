package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP4;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP4MainGUIMenu;
import net.minecraft.client.gui.GuiGraphics;

public class CreeperGirlP4MainGUIScreen extends AbstractCreeperGirlMainGUIScreen<TamableCreeperGirlP4, CreeperGirlP4MainGUIMenu> {

	public CreeperGirlP4MainGUIScreen(CreeperGirlP4MainGUIMenu container, Inventory inventory, Component text) {
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

		this.preggoMob.ifPresent(creeperGirl -> PreggoGUIHelper.renderCreeperGirlP4MainGUI(guiGraphics, this.leftPos, this.topPos, creeperGirl.getHealth(), creeperGirl));
				
		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		this.preggoMob.ifPresent(creeperGirl -> PreggoGUIHelper.renderP4LabelMainGUI(guiGraphics, this.font, creeperGirl));
	}
}
