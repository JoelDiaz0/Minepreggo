package dev.dixmk.minepreggo.world.inventory.preggo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
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

@Mod.EventBusSubscriber
public abstract class AbstractPreggoMobInventaryGUIMenu
	<E extends PreggoMob & ITamablePreggoMob> extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {

	public final Level world;
	public final Player player;
	public final Class<E> preggoMobClass;
	protected ContainerLevelAccess access = ContainerLevelAccess.NULL;
	protected IItemHandler internal;
	protected Map<Integer, Slot> customSlots = new HashMap<>();
	protected boolean bound = false;
	protected final Optional<E> preggoMob;
	protected final int invetorySize;
	
	protected AbstractPreggoMobInventaryGUIMenu(MenuType<?> container, int id, Inventory inv, FriendlyByteBuf extraData, int invetorySize, Class<E> preggoMobClass) {
		super(container, id);		
		this.player = inv.player;
		this.world = inv.player.level();
		this.preggoMobClass = preggoMobClass;		
		this.invetorySize = invetorySize;
		this.preggoMob = this.readBuffer(extraData);
		
		this.preggoMob.ifPresentOrElse(mob -> 
			mob.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
				this.internal = capability;
				this.bound = true;
			}), () -> MinepreggoMod.LOGGER.error("PREGGO MOB IS NULL"));
			
		this.createInventory(inv);	
	}

	
	protected abstract void createInventory(Inventory inv);
	
	protected Optional<E> readBuffer(FriendlyByteBuf extraData) {		
		E mob = null;
		if (extraData != null) {
			this.access = ContainerLevelAccess.create(world, extraData.readBlockPos());		
			
			var e = world.getEntity(extraData.readVarInt());
			
			if (e != null && preggoMobClass.isInstance(e))  {
				mob = preggoMobClass.cast(e);
			}	
		}		
		return Optional.ofNullable(mob);
	}
	
	public Optional<E> getPreggoMob() {
		return this.preggoMob;
	}
			
	@Override
	public boolean stillValid(Player player) {	
		return this.preggoMob.isPresent() && this.preggoMob.get().isAlive();
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
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < invetorySize) {
				if (!this.moveItemStackTo(itemstack1, invetorySize, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickCraft(itemstack1, itemstack);
			} else if (!this.moveItemStackTo(itemstack1, 0, invetorySize, false)) {
				if (index < invetorySize + 27) {
					if (!this.moveItemStackTo(itemstack1, invetorySize + 27, this.slots.size(), true))
						return ItemStack.EMPTY;
				} else {
					if (!this.moveItemStackTo(itemstack1, invetorySize, invetorySize + 27, false))
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
	
	public Map<Integer, Slot> get() {
		return customSlots;
	}
	
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		Player player = event.player;
		if (event.phase == TickEvent.Phase.END && player.containerMenu instanceof AbstractPreggoMobInventaryGUIMenu<?> container) {						
			container.getPreggoMob().ifPresent(mob -> PreggoGUIHelper.syncPreggoMobInventaryOnTick(player.level(), mob));
		}
	}
}
