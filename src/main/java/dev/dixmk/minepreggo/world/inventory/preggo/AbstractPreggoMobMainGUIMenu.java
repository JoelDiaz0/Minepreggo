package dev.dixmk.minepreggo.world.inventory.preggo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.joml.Vector3i;

import dev.dixmk.minepreggo.entity.preggo.IPreggoMob;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class AbstractPreggoMobMainGUIMenu
	<E extends TamableAnimal & IPreggoMob> extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {

	public final Level level;
	public final Player player;
	public final Class<E> preggoMobClass;
	protected int x;
	protected int y;
	protected int z;
	protected final Optional<E> preggoMob;
	protected ContainerLevelAccess access = ContainerLevelAccess.NULL;
	protected final Map<Integer, Slot> customSlots = new HashMap<>();
	protected boolean bound = false;
	protected Supplier<Boolean> boundItemMatcher = null;
	protected Entity boundEntity = null;
	protected BlockEntity boundBlockEntity = null;	
	
	protected AbstractPreggoMobMainGUIMenu(MenuType<?> p_38851_, int p_38852_, Inventory inv, FriendlyByteBuf extraData, Class<E> preggoMobClass) {
		super(p_38851_, p_38852_);
		this.player = inv.player;
		this.level = inv.player.level();
		this.preggoMobClass = preggoMobClass;	
		this.preggoMob = this.readBuffer(extraData);
	}

	protected Optional<E> readBuffer(FriendlyByteBuf extraData) {		
		E mob = null;
		if (extraData != null) {
			var pos = extraData.readBlockPos();	
			this.access = ContainerLevelAccess.create(level, pos);		
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();	
			
			var e = level.getEntity(extraData.readVarInt());
			
			if (e != null && preggoMobClass.isInstance(e))  {
				mob = preggoMobClass.cast(e);
			}	
		}		
		return Optional.ofNullable(mob);
	}
	
		
	public Vector3i getPos() {
		return new Vector3i(x, y, z);
	}
	
	public Map<Integer, Slot> get() {
		return customSlots;
	}
	
	public Optional<E> getPreggoMob() {
		return this.preggoMob;
	}
	
	@Override
	public boolean stillValid(Player player) {
		if (this.bound) {
			if (this.boundItemMatcher != null)
				return this.boundItemMatcher.get();
			else if (this.boundBlockEntity != null)
				return AbstractContainerMenu.stillValid(this.access, player, this.boundBlockEntity.getBlockState().getBlock());
			else if (this.boundEntity != null)
				return this.boundEntity.isAlive();
		}
		return true;
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		return ItemStack.EMPTY;
	}
}
