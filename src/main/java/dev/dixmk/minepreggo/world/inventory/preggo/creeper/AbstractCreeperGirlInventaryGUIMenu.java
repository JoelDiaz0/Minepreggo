package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.utils.PreggoArmorHelper;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.utils.PreggoMessageHelper;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

@Mod.EventBusSubscriber
public abstract class AbstractCreeperGirlInventaryGUIMenu<T extends AbstractTamableCreeperGirl> extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
	public final Level world;
	public final Player entity;
	protected ContainerLevelAccess access = ContainerLevelAccess.NULL;
	protected IItemHandler internal;
	protected Map<Integer, Slot> customSlots = new HashMap<>();
	protected boolean bound = false;
	protected Supplier<Boolean> boundItemMatcher = null;
	protected T creeperGirl = null;
	protected BlockEntity boundBlockEntity = null;
	protected final Class<T> creeperGirlClass;
	
	protected AbstractCreeperGirlInventaryGUIMenu(MenuType<?> menuType, Class<T> creeperGirlClass, int id, Inventory inv, FriendlyByteBuf extraData) {
		super(menuType, id);
		this.entity = inv.player;
		this.world = inv.player.level();
		this.internal = new ItemStackHandler(12);
		this.creeperGirlClass = creeperGirlClass;
		
		if (extraData != null) {
			access = ContainerLevelAccess.create(world, extraData.readBlockPos());
					
			if (world.getEntity(extraData.readVarInt()) instanceof AbstractTamableCreeperGirl e)		
				creeperGirl = create(e);
						
			if (creeperGirl != null)
				creeperGirl.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
					this.internal = capability;
					this.bound = true;
				});
		}
			

		this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 8, 62) {
			@Override
			public boolean mayPlace(ItemStack itemstack) {
				return PreggoArmorHelper.isBoot(itemstack);
			}
		}));
		this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 8, 44) {
			@Override
			public boolean mayPlace(ItemStack itemstack) {	
				
				var stage = PregnancyStage.P0;
							
				if (creeperGirl instanceof AbstractTamablePregnantCreeperGirl c)
					stage = c.getCurrentPregnancyStage();
									
				if (!PreggoArmorHelper.canPreggoMobUseChestplate(itemstack, stage)) {
	                final var message = PreggoMessageHelper.ARMOR_MESSAGES.get(stage.ordinal());                                
	                if (!entity.level().isClientSide && message != null && message[1] != null) 
	                	entity.displayClientMessage(Component.literal(String.format(message[1], creeperGirl.getPreggoName())), true);
	                return false;
				}
			
				return true;
			}
		}));
		this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 8, 26) {
			@Override
			public boolean mayPlace(ItemStack itemstack) {
				
				var stage = PregnancyStage.P0;
				
				if (creeperGirl instanceof AbstractTamablePregnantCreeperGirl c)
					stage = c.getCurrentPregnancyStage();
				
				if (!PreggoArmorHelper.canPreggoMobUseLegging(itemstack, stage)) {
	                final var message = PreggoMessageHelper.ARMOR_MESSAGES.get(-1);     
	                if (!entity.level().isClientSide && message != null && message[1] != null)     
	                	entity.displayClientMessage(Component.literal(String.format(message[1], creeperGirl.getPreggoName())), true);           
	                return false;
				}	
						
				return true;			
			}
		}));
		this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 8, 8) {
			@Override
			public boolean mayPlace(ItemStack itemstack) {
				return PreggoArmorHelper.isHelmet(itemstack);
			}
		}));
		this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 80, 61)));
		this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 98, 61)));
		this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, 134, 17)));
		this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 152, 17)));
		this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, 134, 35)));
		this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 152, 35)));
		this.customSlots.put(10, this.addSlot(new SlotItemHandler(internal, 10, 134, 53)));
		this.customSlots.put(11, this.addSlot(new SlotItemHandler(internal, 11, 152, 53)));
		
		for (int si = 0; si < 3; ++si)
			for (int sj = 0; sj < 9; ++sj)
				this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 0 + 84 + si * 18));
		for (int si = 0; si < 9; ++si)
			this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 0 + 142));
		
		if (creeperGirl != null)	
			PreggoGUIHelper.syncPreggoMobInventary(creeperGirl);
	}


	@Override
	public boolean stillValid(Player player) {
		if (this.bound) {
			if (this.boundItemMatcher != null)
				return this.boundItemMatcher.get();
			else if (this.boundBlockEntity != null)
				return AbstractContainerMenu.stillValid(this.access, player, this.boundBlockEntity.getBlockState().getBlock());
			else if (this.creeperGirl != null)
				return this.creeperGirl.isAlive();
		}
		return true;
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < 12) {
				if (!this.moveItemStackTo(itemstack1, 12, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickCraft(itemstack1, itemstack);
			} else if (!this.moveItemStackTo(itemstack1, 0, 12, false)) {
				if (index < 12 + 27) {
					if (!this.moveItemStackTo(itemstack1, 12 + 27, this.slots.size(), true))
						return ItemStack.EMPTY;
				} else {
					if (!this.moveItemStackTo(itemstack1, 12, 12 + 27, false))
						return ItemStack.EMPTY;
				}
				return ItemStack.EMPTY;
			}
			if (itemstack1.getCount() == 0)
				slot.set(ItemStack.EMPTY);
			else
				slot.setChanged();
			if (itemstack1.getCount() == itemstack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(playerIn, itemstack1);
		}
		return itemstack;
	}

	@Override
	protected boolean moveItemStackTo(ItemStack p_38904_, int p_38905_, int p_38906_, boolean p_38907_) {
		boolean flag = false;
		int i = p_38905_;
		if (p_38907_) {
			i = p_38906_ - 1;
		}
		if (p_38904_.isStackable()) {
			while (!p_38904_.isEmpty()) {
				if (p_38907_) {
					if (i < p_38905_) {
						break;
					}
				} else if (i >= p_38906_) {
					break;
				}
				Slot slot = this.slots.get(i);
				ItemStack itemstack = slot.getItem();
				if (slot.mayPlace(itemstack) && !itemstack.isEmpty() && ItemStack.isSameItemSameTags(p_38904_, itemstack)) {
					int j = itemstack.getCount() + p_38904_.getCount();
					int maxSize = Math.min(slot.getMaxStackSize(), p_38904_.getMaxStackSize());
					if (j <= maxSize) {
						p_38904_.setCount(0);
						itemstack.setCount(j);
						slot.set(itemstack);
						flag = true;
					} else if (itemstack.getCount() < maxSize) {
						p_38904_.shrink(maxSize - itemstack.getCount());
						itemstack.setCount(maxSize);
						slot.set(itemstack);
						flag = true;
					}
				}
				if (p_38907_) {
					--i;
				} else {
					++i;
				}
			}
		}
		if (!p_38904_.isEmpty()) {
			if (p_38907_) {
				i = p_38906_ - 1;
			} else {
				i = p_38905_;
			}
			while (true) {
				if (p_38907_) {
					if (i < p_38905_) {
						break;
					}
				} else if (i >= p_38906_) {
					break;
				}
				Slot slot1 = this.slots.get(i);
				ItemStack itemstack1 = slot1.getItem();
				if (itemstack1.isEmpty() && slot1.mayPlace(p_38904_)) {
					if (p_38904_.getCount() > slot1.getMaxStackSize()) {
						slot1.setByPlayer(p_38904_.split(slot1.getMaxStackSize()));
					} else {
						slot1.setByPlayer(p_38904_.split(p_38904_.getCount()));
					}
					slot1.setChanged();
					flag = true;
					break;
				}
				if (p_38907_) {
					--i;
				} else {
					++i;
				}
			}
		}
		return flag;
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
		if (!bound && playerIn instanceof ServerPlayer serverPlayer) {
			if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
				for (int j = 0; j < internal.getSlots(); ++j) {
					playerIn.drop(internal.extractItem(j, internal.getStackInSlot(j).getCount(), false), false);
				}
			} else {
				for (int i = 0; i < internal.getSlots(); ++i) {
					playerIn.getInventory().placeItemBackInInventory(internal.extractItem(i, internal.getStackInSlot(i).getCount(), false));
				}
			}
		}
	}

	public Map<Integer, Slot> get() {
		return customSlots;
	}

	
    private T create(AbstractTamableCreeperGirl entity) {
        if (creeperGirlClass.isInstance(entity)) {
            return creeperGirlClass.cast(entity);
        }
        throw new IllegalArgumentException("Tipo incorrecto: " + entity.getClass());
    }
    
	public T getCreeperGirl() {
		return creeperGirl;
	}
	
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		Player player = event.player;
		if (event.phase == TickEvent.Phase.END && player.containerMenu instanceof AbstractCreeperGirlInventaryGUIMenu container) {						
			PreggoGUIHelper.syncPreggoMobInventaryOnTick(player.level(), container.creeperGirl);
		}
	}
	
}
