package dev.dixmk.minepreggo.client.gui.preggo.creeper;

import java.util.ArrayList;
import java.util.List;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.gui.components.ToggleableCheckbox;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractCreeperGirl.CombatMode;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.network.preggo.creeper.CreeperGirlMainGUIPacket;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.network.preggo.creeper.CreeperGirlCombatModePacket;
import dev.dixmk.minepreggo.network.preggo.creeper.CreeperGirlInventaryMenuPacket;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.AbstractCreeperGirlMainGUIMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class AbstractCreeperGirlMainGUIScreen<E extends AbstractTamableCreeperGirl<?> 
	,T extends AbstractCreeperGirlMainGUIMenu<E>> extends AbstractContainerScreen<T> {

	protected final Level world;
	protected final int x;
	protected final int y;
	protected final int z;
	protected final Player entity;
	protected final E creeperGirl;
	
	protected Button buttonWait;
	protected Button buttonFollow;
	protected Button buttonRide;
	protected Button buttonDismount;
	protected ImageButton inventoryButton;
	protected ImageButton sexButton;
	
	private final List<ToggleableCheckbox> checkboxes = new ArrayList<>();
	
	protected AbstractCreeperGirlMainGUIScreen(T container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		
		var pos = container.getPos();
		
		this.x = pos.x;
		this.y = pos.y;
		this.z = pos.z;
		
		this.entity = container.entity;
		this.imageWidth = 187;
		this.imageHeight = 103;
		this.creeperGirl = container.getCreeperGirl();
	}
	
	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);	
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
					
		if (creeperGirl == null) {
			MinepreggoMod.LOGGER.error("Creeper Girl was null");
			return;
		}	
			
		final var combatMode = creeperGirl.getcombatMode();		
		final var creeperGirlId = creeperGirl.getId();
	
		addCheckbox(this.leftPos + 170, this.topPos, 20, 20, Component.translatable("gui.minepreggo.creeper_girl_main.checkbox_explode"), combatMode == CombatMode.EXPLODE,() -> 
			MinepreggoModPacketHandler.INSTANCE.sendToServer(new CreeperGirlCombatModePacket(x, y, z, CombatMode.EXPLODE, creeperGirlId))
		);				
		addCheckbox(this.leftPos + 170, this.topPos + 36, 20, 20, Component.translatable("gui.minepreggo.creeper_girl_main.checkbox_dont_explode"), combatMode == CombatMode.DONT_EXPLODE, () -> 
			MinepreggoModPacketHandler.INSTANCE.sendToServer(new CreeperGirlCombatModePacket(x, y, z, CombatMode.DONT_EXPLODE, creeperGirlId))
		);				
		addCheckbox(this.leftPos + 170, this.topPos + 72, 20, 20, Component.translatable("gui.minepreggo.creeper_girl_main.checkbox_fight_and_explode"), combatMode == CombatMode.FIGHT_AND_EXPLODE, () -> 
			MinepreggoModPacketHandler.INSTANCE.sendToServer(new CreeperGirlCombatModePacket(x, y, z, CombatMode.FIGHT_AND_EXPLODE, creeperGirlId))
		);				

		
		
		buttonWait = Button.builder(Component.translatable("gui.minepreggo.preggo_mob_main.button_wait"), e -> {
			if (!creeperGirl.isWaiting()) {
				MinepreggoModPacketHandler.INSTANCE.sendToServer(new CreeperGirlMainGUIPacket(x, y, z, 0, creeperGirlId));
			}
		}).bounds(this.leftPos + 14, this.topPos + 7, 46, 20).build(builder -> new Button(builder) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (!creeperGirl.isWaiting())
					super.render(guiGraphics, gx, gy, ticks);
			}
		});
		this.addRenderableWidget(buttonWait);

		
		buttonFollow = Button.builder(Component.translatable("gui.minepreggo.preggo_mob_main.button_follow"), e -> {
			if (creeperGirl.isWaiting()) {
				MinepreggoModPacketHandler.INSTANCE.sendToServer(new CreeperGirlMainGUIPacket(x, y, z, 1, creeperGirlId));
			}
		}).bounds(this.leftPos + 9, this.topPos + 30, 56, 20).build(builder -> new Button(builder) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (creeperGirl.isWaiting())
					super.render(guiGraphics, gx, gy, ticks);
			}
		});
		this.addRenderableWidget(buttonFollow);
			
		buttonRide = Button.builder(Component.translatable("gui.minepreggo.preggo_mob_main.button_ride"), e -> {
			if (!creeperGirl.isPassenger()) {
				MinepreggoModPacketHandler.INSTANCE.sendToServer(new CreeperGirlMainGUIPacket(x, y, z, 2, creeperGirlId));
			}
		}).bounds(this.leftPos + 13, this.topPos + 54, 46, 20).build(builder -> new Button(builder) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (!creeperGirl.isPassenger())
					super.render(guiGraphics, gx, gy, ticks);
			}
		});
		this.addRenderableWidget(buttonRide);

		buttonDismount = Button.builder(Component.translatable("gui.minepreggo.preggo_mob_main.button_dismount"), e -> {
			if (creeperGirl.isPassenger()) {
				MinepreggoModPacketHandler.INSTANCE.sendToServer(new CreeperGirlMainGUIPacket(x, y, z, 3, creeperGirlId));
			}
		}).bounds(this.leftPos + 4, this.topPos + 77, 67, 20).build(builder -> new Button(builder) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (creeperGirl.isPassenger())
					super.render(guiGraphics, gx, gy, ticks);
			}
		});
		this.addRenderableWidget(buttonDismount);
	
		inventoryButton = new ImageButton(this.leftPos - 24, this.topPos + 6, 16, 16, 1, 57, 16, PreggoGUIHelper.ICONS_TEXTURE, 256, 256, 
				e -> MinepreggoModPacketHandler.INSTANCE.sendToServer(new CreeperGirlInventaryMenuPacket(x, y, z, creeperGirlId)));	
		inventoryButton.setTooltip(Tooltip.create(Component.translatable("gui.minepreggo.preggo_mob_inventory.tooltip_inventory")));
		this.addRenderableWidget(inventoryButton);
			
		sexButton = new ImageButton(this.leftPos - 24, this.topPos + 32, 8, 8, 1, 89, 8, PreggoGUIHelper.ICONS_TEXTURE, 256, 256, 
				e -> MinepreggoModPacketHandler.INSTANCE.sendToServer(new CreeperGirlInventaryMenuPacket(x, y, z, creeperGirlId)));	
		sexButton.setTooltip(Tooltip.create(Component.translatable("gui.minepreggo.preggo_mob_inventory.tooltip_sex")));
		this.addRenderableWidget(sexButton);
	}	

	private void addCheckbox(int p_93826_, int p_93827_, int p_93828_, int p_93829_, Component p_93830_, boolean p_93831_, Runnable action) {
		var checkBox = new ToggleableCheckbox(p_93826_, p_93827_, p_93828_, p_93829_, p_93830_, p_93831_, checkboxes, action);		
		this.addRenderableWidget(checkBox);
		checkboxes.add(checkBox);
	}

}
