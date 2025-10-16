package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP1;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP1MainGUIMenu;

public class ZombieGirlP1MainGUIScreen extends AbstractZombieGirlMainGUIScreen<TamableZombieGirlP1, ZombieGirlP1MainGUIMenu> {

	public ZombieGirlP1MainGUIScreen(ZombieGirlP1MainGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 183;
		this.imageHeight = 112;
	}


	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
				
		guiGraphics.blit(PreggoGUIHelper.DEFAULT_P1_MAIN_GUI_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
	
		if (this.zombieGirl != null) {
			PreggoGUIHelper.renderZombieGirlP1MainGUI(guiGraphics, this.leftPos, this.topPos, this.zombieGirl.getHealth(), this.zombieGirl);
		}
		
		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		if (this.zombieGirl != null)
			PreggoGUIHelper.renderP1LabelMainGUI(guiGraphics, this.font, this.zombieGirl);
	}

}
