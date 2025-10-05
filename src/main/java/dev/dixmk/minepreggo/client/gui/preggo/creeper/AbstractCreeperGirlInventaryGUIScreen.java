package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.AbstractCreeperGirlInventaryGUIMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public abstract class AbstractCreeperGirlInventaryGUIScreen<E extends AbstractTamableCreeperGirl,
	T extends AbstractCreeperGirlInventaryGUIMenu<E>> extends AbstractContainerScreen<T> {

	private E creeperGirl;

	protected AbstractCreeperGirlInventaryGUIScreen(T container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.imageWidth = 176;
		this.imageHeight = 166;
		this.creeperGirl = container.getCreeperGirl();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		if (creeperGirl != null) {
			InventoryScreen.renderEntityInInventoryFollowsAngle(guiGraphics, this.leftPos + 51, this.topPos + 78, 40, 0f + (float) Math.atan((this.leftPos + 51 - mouseX) / 40.0), (float) Math.atan((this.topPos + 29 - mouseY) / 40.0), creeperGirl);
		}
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(PreggoGUIHelper.CREEPER_GIRL_INVENTARY_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {}
	
	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}


	@Override
	public void init() {
		super.init();
	}
	
}
