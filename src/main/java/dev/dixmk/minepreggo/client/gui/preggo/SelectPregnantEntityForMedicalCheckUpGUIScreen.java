package dev.dixmk.minepreggo.client.gui.preggo;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.gui.GuiGraphics;

import java.util.List;
import java.util.Optional;

import com.mojang.blaze3d.systems.RenderSystem;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.gui.components.PreggoMobScrollList;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractZombieGirl;
import dev.dixmk.minepreggo.network.packet.RequestPreggoMobMedicalCheckUpPacket;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.inventory.preggo.SelectPregnantEntityForMedicalCheckUpGUIMenu;

public class SelectPregnantEntityForMedicalCheckUpGUIScreen extends AbstractContainerScreen<SelectPregnantEntityForMedicalCheckUpGUIMenu> {
	
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/select_preggo_mob_for_medical_check_up_gui.png");
	
	private final Optional<ScientificIllager> scientificIllager;
	private final List<? extends LivingEntity> pregnantEntities;
	
	public SelectPregnantEntityForMedicalCheckUpGUIScreen(SelectPregnantEntityForMedicalCheckUpGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.scientificIllager = container.getScienticIllager();
		this.imageWidth = 113;
		this.imageHeight = 166;
		this.pregnantEntities = container.getPregnantLivingEntities();	
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.minepreggo.select_preggo_mob_for_medical_check_up_gui.label_illager_scientific"), 9, 9, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		var list = new PreggoMobScrollList(minecraft, imageWidth, imageHeight, topPos + 20, imageHeight, 20);
		list.setLeftPos(leftPos);
		
		this.pregnantEntities.forEach(entity -> {		
			if (entity instanceof AbstractCreeperGirl creeperGirl) {
				list.addEntry(creeperGirl.getSimpleName(), PreggoGUIHelper.DEFAULT_ICON_CREEPER_GIRL_TEXTURE, 64, 96, this.createRequestMedicalCheckUpPacket(creeperGirl));
			}
			else if (entity instanceof AbstractZombieGirl zombieGirl) {
				list.addEntry(zombieGirl.getSimpleName(), PreggoGUIHelper.DEFAULT_ICON_ZOMBIE_GIRL_TEXTURE, 64, 96, this.createRequestMedicalCheckUpPacket(zombieGirl));
			}
			else if (entity instanceof AbstractClientPlayer abstractClientPlayer) {
				list.addEntry(abstractClientPlayer.getDisplayName().getString(), abstractClientPlayer.getSkinTextureLocation(), 64, 64);
			}
		});
				
		this.addRenderableWidget(list);	
	}
	
	private Optional<Runnable> createRequestMedicalCheckUpPacket(PreggoMob preggoMob) {	
		Runnable onClick = null;			
		if (this.scientificIllager.isPresent()) {
			onClick = () -> MinepreggoModPacketHandler.INSTANCE.sendToServer(new RequestPreggoMobMedicalCheckUpPacket(preggoMob.getId(), this.scientificIllager.get().getId()));
		}	
		return Optional.ofNullable(onClick);
	}
}

