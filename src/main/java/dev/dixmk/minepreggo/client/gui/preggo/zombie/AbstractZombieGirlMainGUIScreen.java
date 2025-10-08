package dev.dixmk.minepreggo.client.gui.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.network.preggo.zombie.ZombieGirlInventaryMenuPacket;
import dev.dixmk.minepreggo.network.preggo.zombie.ZombieGirlMainGUIPacket;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.AbstractZombieGirlMainGUIMenu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class AbstractZombieGirlMainGUIScreen<E extends AbstractTamableZombieGirl<?>,
	T extends AbstractZombieGirlMainGUIMenu<E>> extends AbstractContainerScreen<T> {
	
	protected final Level world;
	protected int x;
	protected int y;
	protected int z;
	protected final Player entity;
	protected Button buttonWait;
	protected Button buttonFollow;
	protected Button buttonRide;
	protected Button buttonDismount;
	protected final E zombieGirl;
	protected ImageButton inventoryButton;
	
	protected AbstractZombieGirlMainGUIScreen(T container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		
		var pos = container.getPos();
		
		this.x = pos.x;	
		this.y = pos.x;
		this.z = pos.x;
		
		this.entity = container.entity;
		this.imageWidth = 187;
		this.imageHeight = 103;
		this.zombieGirl = container.getZombieGirl();
	}
	
	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);

		if (mouseX > leftPos + -28 && mouseX < leftPos + -4 && mouseY > topPos + 2 && mouseY < topPos + 26)
			guiGraphics.renderTooltip(font, Component.translatable("gui.minepreggo.main.tooltip_tooltip"), mouseX, mouseY);
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
	public void init() {
		super.init();

		if (zombieGirl == null) {
			MinepreggoMod.LOGGER.error("Zombie Girl was null");
			return;
		}

		int zombieGirlId = zombieGirl.getId();
		
		buttonWait = Button.builder(Component.translatable("gui.minepreggo.zombie_girl_p_0_main_gui.button_wait"), e -> {
			if (!zombieGirl.isWaiting()) {
				MinepreggoModPacketHandler.INSTANCE.sendToServer(new ZombieGirlMainGUIPacket(x, y, z, 0, zombieGirlId));
			}
		}).bounds(this.leftPos + 14, this.topPos + 7, 46, 20).build(builder -> new Button(builder) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (!zombieGirl.isWaiting())
					super.render(guiGraphics, gx, gy, ticks);
			}
		});
		this.addRenderableWidget(buttonWait);

		
		buttonFollow = Button.builder(Component.translatable("gui.minepreggo.zombie_girl_p_0_main_gui.button_follow"), e -> {
			if (zombieGirl.isWaiting()) {
				MinepreggoModPacketHandler.INSTANCE.sendToServer(new ZombieGirlMainGUIPacket(x, y, z, 1, zombieGirlId));
			}
		}).bounds(this.leftPos + 9, this.topPos + 30, 56, 20).build(builder -> new Button(builder) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (zombieGirl.isWaiting())
					super.render(guiGraphics, gx, gy, ticks);
			}
		});
		this.addRenderableWidget(buttonFollow);
	
		buttonRide = Button.builder(Component.translatable("gui.minepreggo.zombie_girl_p_0_main_gui.button_ride"), e -> {
			if (!zombieGirl.isPassenger()) {
				MinepreggoModPacketHandler.INSTANCE.sendToServer(new ZombieGirlMainGUIPacket(x, y, z, 2, zombieGirlId));
			}
		}).bounds(this.leftPos + 13, this.topPos + 54, 46, 20).build(builder -> new Button(builder) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (!zombieGirl.isPassenger())
					super.render(guiGraphics, gx, gy, ticks);
			}
		});
		this.addRenderableWidget(buttonRide);


		
		buttonDismount = Button.builder(Component.translatable("gui.minepreggo.zombie_girl_p_0_main_gui.button_dismount"), e -> {
			if (zombieGirl.isPassenger()) {
				MinepreggoModPacketHandler.INSTANCE.sendToServer(new ZombieGirlMainGUIPacket(x, y, z, 3, zombieGirlId));
			}
		}).bounds(this.leftPos + 4, this.topPos + 77, 67, 20).build(builder -> new Button(builder) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (zombieGirl.isPassenger())
					super.render(guiGraphics, gx, gy, ticks);
			}
		});
		this.addRenderableWidget(buttonDismount);
		
		inventoryButton = new ImageButton(this.leftPos + -24, this.topPos + 6, 1, 57, 16, 16, 16, PreggoGUIHelper.ICONS_TEXTURE, 256, 256, e -> {
			entity.level().playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("ui.button.click")), SoundSource.NEUTRAL, 1, 1, false);
			this.minecraft.player.closeContainer();
			MinepreggoModPacketHandler.INSTANCE.sendToServer(new ZombieGirlInventaryMenuPacket(x, y, z, zombieGirlId));		
		});	
		this.addRenderableWidget(inventoryButton);
	}

}
