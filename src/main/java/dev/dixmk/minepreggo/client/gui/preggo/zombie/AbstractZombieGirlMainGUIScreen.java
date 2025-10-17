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
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

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
		this.y = pos.y;
		this.z = pos.z;
		
		this.entity = container.entity;
		this.imageWidth = 187;
		this.imageHeight = 103;
		this.zombieGirl = container.getZombieGirl();
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
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}
	
	@Override
	public void init() {
		super.init();

		if (zombieGirl == null) {
			MinepreggoMod.LOGGER.error("Zombie Girl was null");
			return;
		}

		final int zombieGirlId = zombieGirl.getId();
		
		buttonWait = Button.builder(Component.translatable("gui.minepreggo.preggo_mob_main.button_wait"), e -> {
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

		
		buttonFollow = Button.builder(Component.translatable("gui.minepreggo.preggo_mob_main.button_follow"), e -> {
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
	
		buttonRide = Button.builder(Component.translatable("gui.minepreggo.preggo_mob_main.button_ride"), e -> {
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


		
		buttonDismount = Button.builder(Component.translatable("gui.minepreggo.preggo_mob_main.button_dismount"), e -> {
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
		
		inventoryButton = new ImageButton(this.leftPos - 24, this.topPos + 6, 16, 16, 1, 57, 16, PreggoGUIHelper.ICONS_TEXTURE, 256, 256, 
				e -> MinepreggoModPacketHandler.INSTANCE.sendToServer(new ZombieGirlInventaryMenuPacket(x, y, z, zombieGirlId)));	
		inventoryButton.setTooltip(Tooltip.create(Component.translatable("gui.minepreggo.preggo_mob_inventory.tooltip_inventory")));
		
		this.addRenderableWidget(inventoryButton);
	}
}
