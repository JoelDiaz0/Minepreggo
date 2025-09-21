package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.joml.Vector3i;

import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamableZombieGirl;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class AbstractZombieGirlMainGUIMenu<T extends AbstractTamableZombieGirl> extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {	
	public final Level world;
	public final Player entity;
	private T zombieGirl = null;
	private int x;
	private int y;
	private int z;
	private ContainerLevelAccess access = ContainerLevelAccess.NULL;
	private final Map<Integer, Slot> customSlots = new HashMap<>();
	private boolean bound = false;
	private Supplier<Boolean> boundItemMatcher = null;
	private Entity boundEntity = null;
	private BlockEntity boundBlockEntity = null;
	private final Class<T> zombieGirlClass;
	
	protected AbstractZombieGirlMainGUIMenu(MenuType<?> menuType, Class<T> zombieGirlClass, int id, Inventory inv, FriendlyByteBuf extraData) {
		super(menuType, id);
		this.entity = inv.player;
		this.world = inv.player.level();
		BlockPos pos = null;
		this.zombieGirlClass = zombieGirlClass;
		
		if (extraData != null) {					
			pos = extraData.readBlockPos();	
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();				    					
			access = ContainerLevelAccess.create(world, pos); 
			
			if (world.getEntity(extraData.readVarInt()) instanceof AbstractTamableZombieGirl e)		
				zombieGirl = create(e);
		}
	}
	
    public T create(AbstractTamableZombieGirl entity) {
        if (zombieGirlClass.isInstance(entity)) {
            return zombieGirlClass.cast(entity);
        }
        throw new IllegalArgumentException("Tipo incorrecto: " + entity.getClass());
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

	public Map<Integer, Slot> get() {
		return customSlots;
	}
	
	public T getZombieGirl() {
		return zombieGirl;
	}
	
	public Vector3i getPos() {
		return new Vector3i(x, y, z);
	}
}
