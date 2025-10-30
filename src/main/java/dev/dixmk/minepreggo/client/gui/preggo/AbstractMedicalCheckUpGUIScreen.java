package dev.dixmk.minepreggo.client.gui.preggo;

import java.util.Optional;

import org.joml.Vector3i;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractMedicalCheckUpGUIMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class AbstractMedicalCheckUpGUIScreen
	<S extends LivingEntity, T extends Mob, C extends AbstractMedicalCheckUpGUIMenu<S, T>> extends AbstractContainerScreen<C> {
	private static final ResourceLocation EMERALD_TEXTURE = ResourceLocation.withDefaultNamespace("textures/item/emerald.png");
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/player_pregnancy_medical_state_gui.png");
	protected final Level level;
	protected final Vector3i pos;	
	protected final Player player;	
	protected final Optional<T> target;
	protected final Optional<S> source;
	protected final int numOfEmerald;
	
	protected AbstractMedicalCheckUpGUIScreen(C container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.level = container.level;
		this.pos = container.getPos();

		this.player = container.player;
		this.imageWidth = 340;
		this.imageHeight = 166;

		this.source = container.getSource();
		this.target = container.getTarget();
		this.numOfEmerald = container.getNumOfEmerald();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.source.ifPresent(s -> InventoryScreen.renderEntityInInventoryFollowsAngle(guiGraphics, this.leftPos + 296, this.topPos + 153, 60, 0f + (float) Math.atan((this.leftPos + 296 - mouseX) / 40.0), (float) Math.atan((this.topPos + 104 - mouseY) / 40.0), s));
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(EMERALD_TEXTURE, this.leftPos + 115, this.topPos + 25, 0, 0, 14, 14, 14, 14);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.literal("Pregnancy Check"), 133, 7, -12829636, false);
		guiGraphics.drawString(this.font, Component.literal("Days"), 25, 95, -12829636, false);
		guiGraphics.drawString(this.font, Component.literal("Birth:"), 7, 115, -12829636, false);
		guiGraphics.drawString(this.font, Component.literal("Passed:"), 7, 135, -12829636, false);
		guiGraphics.drawString(this.font, Component.literal("NextStage:"), 7, 155, -12829636, false);
		guiGraphics.drawString(this.font, Component.literal("Pregnancy:"), 18, 7, -12829636, false);
		guiGraphics.drawString(this.font, Component.literal("Health:"), 7, 43, -12829636, false);
		guiGraphics.drawString(this.font, Component.literal("State:"), 7, 25, -12829636, false);
		guiGraphics.drawString(this.font, Component.literal("Baby:"), 7, 79, -12829636, false);
		guiGraphics.drawString(this.font, Component.literal("NumOfBabies:"), 7, 61, -12829636, false);
		guiGraphics.drawString(this.font, Component.literal("Make Check:"), 187, 52, -1, false);
		guiGraphics.drawString(this.font, Component.literal("Inventory:"), 88, 70, -12829636, false);
			
		if (!this.menu.isValid()) return;
		
		
		guiGraphics.drawString(this.font, Integer.toString(numOfEmerald) , 124, 34, -1, false);
		guiGraphics.drawString(this.font, this.menu.getName(), 282, 7, -12829636, false);	

		if (this.menu.hasPreggoMobChecked()) {		
			guiGraphics.drawString(this.font, this.menu.getCurrentStage().toString(), 40, 25, -12829636, false);
			guiGraphics.drawString(this.font, Integer.toString(this.menu.getPregnancyHealth()), 45, 43, -12829636, false);			
			guiGraphics.drawString(this.font, Integer.toString(this.menu.getNumberOfChildren()), 74, 61, -12829636, false);			
			guiGraphics.drawString(this.font, this.menu.getBabyType().toString(), 34, 79, -12829636, false);	
			
			guiGraphics.drawString(this.font, this.menu.getDaysToGiveBirth() == Integer.MAX_VALUE ? "INF" : Integer.toString(this.menu.getDaysToGiveBirth()), 39, 115, -12829636, false);
			guiGraphics.drawString(this.font, this.menu.getDaysPassed() < 0 ? "?" : Integer.toString(this.menu.getDaysPassed()), 48, 135, -12829636, false);	
			
			// guiGraphics.drawString(this.font, this.menu.getDaysToGiveBirth() == Integer.MAX_VALUE ? "?" : Integer.toString(this.menu.getDaysToNextStage()) , 64, 155, -12829636, false);	
		}
	}

}
