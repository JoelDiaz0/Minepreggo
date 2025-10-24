package dev.dixmk.minepreggo.client.gui.preggo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.gui.components.ToggleableCheckbox;
import dev.dixmk.minepreggo.entity.preggo.IPreggoMob;
import dev.dixmk.minepreggo.network.packet.PreggoMobBreakBlocksPacket;
import dev.dixmk.minepreggo.network.packet.PreggoMobInventoryMenuPacket;
import dev.dixmk.minepreggo.network.packet.PreggoMobPickUpItemPacket;
import dev.dixmk.minepreggo.network.packet.PreggoMobWaitPacket;
import dev.dixmk.minepreggo.network.packet.RequestSexCinematicPacket;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.AbstractPreggoMobMainGUIMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class AbstractPreggoMobMainGUIScreen
	<E extends TamableAnimal & IPreggoMob, M extends AbstractPreggoMobMainGUIMenu<E>> extends AbstractContainerScreen<M> {
	
	protected final Level world;
	protected final int x;
	protected final int y;
	protected final int z;
	
	protected final int xSexSprite;
	protected final int ySexSprite;
	
	protected final Player entity;
	protected final Optional<E> preggoMob;
	protected ImageButton inventoryButton;
	protected ImageButton sexButton;
	
	private final List<ToggleableCheckbox> state = new ArrayList<>();
	
	protected AbstractPreggoMobMainGUIScreen(M container, Inventory inventory, Component text, int xSexSprite, int ySexSprite) {
		super(container, inventory, text);	
		this.world = container.level;	
		var pos = container.getPos();	
		this.x = pos.x;
		this.y = pos.y;
		this.z = pos.z;		
		this.xSexSprite = xSexSprite;
		this.ySexSprite = ySexSprite;
		this.entity = container.player;
		this.preggoMob = container.getPreggoMob();
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
	public void tick() {
		super.tick();	
		this.preggoMob.ifPresent(e -> {
			if (e.isAggressive()) {
				this.minecraft.player.closeContainer();
			}
		});
	}
	
	@Override
	public void init() {
		super.init();
		this.addPreggoMobCheckBoxes();
	}	
	
	private void addPreggoMobCheckBoxes() {		
		this.preggoMob.ifPresentOrElse(mob -> {			
			final var isWaiting = mob.isWaiting();
			final var id = mob.getId();
					
			inventoryButton = new ImageButton(this.leftPos - 24, this.topPos + 6, 16, 16, 1, 57, 16, PreggoGUIHelper.ICONS_TEXTURE, 256, 256, 
					e -> MinepreggoModPacketHandler.INSTANCE.sendToServer(new PreggoMobInventoryMenuPacket(x, y, z, id)));	
			inventoryButton.setTooltip(Tooltip.create(Component.translatable("gui.minepreggo.preggo_mob_inventory.tooltip_inventory")));
								
			sexButton = new ImageButton(this.leftPos - 24, this.topPos + 32, 16, 16, this.xSexSprite, this.ySexSprite, 16, PreggoGUIHelper.ICONS_TEXTURE, 256, 256, 
					e -> {
						MinepreggoModPacketHandler.INSTANCE.sendToServer(new RequestSexCinematicPacket(id));
						this.minecraft.player.closeContainer();
					});							
			sexButton.setTooltip(Tooltip.create(Component.translatable("gui.minepreggo.preggo_mob_inventory.tooltip_sex")));
				
			var wait = ToggleableCheckbox.builder(this.leftPos + 6, this.topPos + 5, 20, 20, Component.translatable("gui.minepreggo.preggo_mob_main.button_wait"), isWaiting)
					.group(state)
					.onSelect(() -> MinepreggoModPacketHandler.INSTANCE.sendToServer(new PreggoMobWaitPacket(id, true)))
					.build();
		
			var follow = ToggleableCheckbox.builder(this.leftPos + 6, this.topPos + 29, 20, 20, Component.translatable("gui.minepreggo.preggo_mob_main.button_follow"), !isWaiting)
					.group(state)
					.onSelect(() -> MinepreggoModPacketHandler.INSTANCE.sendToServer(new PreggoMobWaitPacket(id, false)))
					.build();
			
			var pickUpItems = new Checkbox(this.leftPos + 6, this.topPos + 53, 20, 20, Component.translatable("gui.minepreggo.preggo_mob_main.checkbox_pickup"), mob.canPickUpItems()) {
				@Override
				public void onPress() {
					super.onPress();
					MinepreggoModPacketHandler.INSTANCE.sendToServer(new PreggoMobPickUpItemPacket(id, this.selected));
				}
			};
			pickUpItems.setTooltip(Tooltip.create(Component.translatable("gui.minepreggo.preggo_mob_inventory.tooltip_checkbox_pickup")));

					
			var breakBlocks = new Checkbox(this.leftPos + 6, this.topPos + 77, 20, 20, Component.translatable("gui.minepreggo.preggo_mob_main.checkbox_break"), mob.canBreakBlocks()) {
				@Override
				public void onPress() {
					super.onPress();
					MinepreggoModPacketHandler.INSTANCE.sendToServer(new PreggoMobBreakBlocksPacket(id, this.selected));
				}
			};
			breakBlocks.setTooltip(Tooltip.create(Component.translatable("gui.minepreggo.preggo_mob_inventory.tooltip_checkbox_break")));

			
			this.addRenderableWidget(inventoryButton);	
			this.addRenderableWidget(sexButton);
			this.addRenderableWidget(wait);
			this.addRenderableWidget(follow);
			this.addRenderableWidget(pickUpItems);
			this.addRenderableWidget(breakBlocks);	
			
			state.add(wait);
			state.add(follow);
		}, () -> {
			MinepreggoMod.LOGGER.error("preggoMob was null");
			this.minecraft.player.closeContainer();
			return;
		});	
	}
}

