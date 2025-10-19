package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.entity.preggo.IPreggoMob;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.utils.PreggoArmorHelper;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.utils.PreggoMessageHelper;
import dev.dixmk.minepreggo.utils.PreggoTags;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

@Mod.EventBusSubscriber
public abstract class AbstractZombieGirlInventaryGUIMenu<T extends AbstractTamableZombieGirl<?>> extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
	public final Level world;
	public final Player entity;
	protected ContainerLevelAccess access = ContainerLevelAccess.NULL;
	protected IItemHandler internal;
	protected Map<Integer, Slot> customSlots = new HashMap<>();
	protected boolean bound = false;
	protected T zombieGirl = null;
	protected final Class<T> zombieGirlClass;	
	
	protected AbstractZombieGirlInventaryGUIMenu(MenuType<?> menuType, Class<T> zombieGirlClass, int id, Inventory inv, FriendlyByteBuf extraData) {
		super(menuType, id);
		
		this.entity = inv.player;
		this.world = inv.player.level();
		this.internal = new ItemStackHandler(AbstractTamableZombieGirl.INVENTORY_SIZE);
		this.zombieGirlClass = zombieGirlClass;

		if (extraData != null) {
			this.access = ContainerLevelAccess.create(world, extraData.readBlockPos());		
			
			if (world.getEntity(extraData.readVarInt()) instanceof AbstractTamableZombieGirl<?> e)		
				zombieGirl = create(e);
			if (zombieGirl != null)
				zombieGirl.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
					this.internal = capability;
					this.bound = true;
			});
		}

		this.customSlots.put(IPreggoMob.HEAD_INVENTORY_SLOT, this.addSlot(new SlotItemHandler(internal, 4, 8, 8) {
			@Override
			public boolean mayPlace(ItemStack itemstack) {
				return PreggoArmorHelper.isHelmet(itemstack);
			}
		}));
		
		this.customSlots.put(IPreggoMob.CHEST_INVENTORY_SLOT, this.addSlot(new SlotItemHandler(internal, 3, 8, 26) {
			@Override
			public boolean mayPlace(ItemStack itemstack) {													
				if (!PreggoArmorHelper.isChest(itemstack)) {
					return false;
				}					
				var stage = PregnancyStage.getNonPregnancyStage();		
				if (zombieGirl instanceof AbstractTamablePregnantZombieGirl<?,?> pregZombieGirl) {
					stage = pregZombieGirl.getCurrentPregnancyStage();		
				}			
				if (!PreggoArmorHelper.canPreggoMobUseChestplate(itemstack, stage) && !entity.level().isClientSide()) {                                               
	                entity.displayClientMessage(PreggoMessageHelper.getPreggoMobArmorChestMessage(stage, zombieGirl.getPreggoName()), true);        
	                return false;
				}				
				return true;
			}
		}));
		this.customSlots.put(IPreggoMob.LEGS_INVENTORY_SLOT, this.addSlot(new SlotItemHandler(internal, 2, 8, 44) {
			@Override
			public boolean mayPlace(ItemStack itemstack) {
				if (!PreggoArmorHelper.isLegging(itemstack)) {
					return false;
				}
				var stage = PregnancyStage.getNonPregnancyStage();
				if (zombieGirl instanceof AbstractTamablePregnantZombieGirl<?,?> pregZombieGirl) {
					stage = pregZombieGirl.getCurrentPregnancyStage();			
				}
				if (!PreggoArmorHelper.canPreggoMobUseLegging(itemstack, stage) && !entity.level().isClientSide()) {            	
	                entity.displayClientMessage(PreggoMessageHelper.getPreggoMobArmorLeggingsMessage(zombieGirl.getPreggoName()), true);           
	                return false;
				}
				return true;
			}
		}));
		this.customSlots.put(IPreggoMob.FEET_INVENTORY_SLOT, this.addSlot(new SlotItemHandler(internal, 1, 8, 62) {
			@Override
			public boolean mayPlace(ItemStack itemstack) {
				return PreggoArmorHelper.isBoot(itemstack);
			}
		}));
		
		this.customSlots.put(IPreggoMob.MAINHAND_INVENTORY_SLOT, this.addSlot(new SlotItemHandler(internal, 0, 77, 62)));
		this.customSlots.put(IPreggoMob.OFFHAND_INVENTORY_SLOT, this.addSlot(new SlotItemHandler(internal, 5, 95, 62)));
		this.customSlots.put(IPreggoMob.FOOD_INVENTORY_SLOT, this.addSlot(new SlotItemHandler(internal, 6, 113, 62) {
			@Override
			public boolean mayPlace(ItemStack itemstack) {
				return itemstack.is(PreggoTags.ZOMBIE_GIRL_FOOD);
			}
		}));	
		
		this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 134, 8)));
		this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, 152, 8)));
		this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 134, 26)));
		this.customSlots.put(10, this.addSlot(new SlotItemHandler(internal, 10, 152, 26)));
		this.customSlots.put(11, this.addSlot(new SlotItemHandler(internal, 11, 134, 44)));
		this.customSlots.put(12, this.addSlot(new SlotItemHandler(internal, 12, 152, 44)));
		this.customSlots.put(13, this.addSlot(new SlotItemHandler(internal, 13, 134, 62)));
		this.customSlots.put(14, this.addSlot(new SlotItemHandler(internal, 14, 152, 62)));
		
		for (int si = 0; si < 3; ++si)
			for (int sj = 0; sj < 9; ++sj)
				this.addSlot(new Slot(inv, sj + (si + 1) * 9, 0 + 8 + sj * 18, 0 + 84 + si * 18));
		for (int si = 0; si < 9; ++si)
			this.addSlot(new Slot(inv, si, 0 + 8 + si * 18, 0 + 142));
		
		if (zombieGirl != null)	
			PreggoGUIHelper.syncPreggoMobInventaryOnStart(zombieGirl);
	}

	@Override
	public boolean stillValid(Player player) {
		if (this.bound && this.zombieGirl != null) {
			return this.zombieGirl.isAlive();
		}
		return true;
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < AbstractTamableZombieGirl.INVENTORY_SIZE) {
				if (!this.moveItemStackTo(itemstack1, AbstractTamableZombieGirl.INVENTORY_SIZE, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickCraft(itemstack1, itemstack);
			} else if (!this.moveItemStackTo(itemstack1, 0, AbstractTamableZombieGirl.INVENTORY_SIZE, false)) {
				if (index < AbstractTamableZombieGirl.INVENTORY_SIZE + 27) {
					if (!this.moveItemStackTo(itemstack1, AbstractTamableZombieGirl.INVENTORY_SIZE + 27, this.slots.size(), true))
						return ItemStack.EMPTY;
				} else {
					if (!this.moveItemStackTo(itemstack1, AbstractTamableZombieGirl.INVENTORY_SIZE, AbstractTamableZombieGirl.INVENTORY_SIZE + 27, false))
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
	
    private T create(AbstractTamableZombieGirl<?> entity) {
        if (zombieGirlClass.isInstance(entity)) {
            return zombieGirlClass.cast(entity);
        }
        throw new IllegalArgumentException("Tipo incorrecto: " + entity.getClass());
    }
    
	public T getZombieGirl() {
		return zombieGirl;
	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		Player player = event.player;
		if (event.phase == TickEvent.Phase.END && player.containerMenu instanceof AbstractZombieGirlInventaryGUIMenu<?> container) {	
			PreggoGUIHelper.syncPreggoMobInventaryOnTick(player.level(), container.zombieGirl);
		}
	}
}
