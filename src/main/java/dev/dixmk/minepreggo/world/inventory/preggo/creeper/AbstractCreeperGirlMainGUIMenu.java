package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.joml.Vector3i;

import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;

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

public abstract class AbstractCreeperGirlMainGUIMenu<T extends AbstractTamableCreeperGirl> extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
	public final Level world;
	public final Player entity;
	private int x;
	private int y;
	private int z;
	private T creeperGirl = null;
	private ContainerLevelAccess access = ContainerLevelAccess.NULL;
	private final Map<Integer, Slot> customSlots = new HashMap<>();
	private boolean bound = false;
	private Supplier<Boolean> boundItemMatcher = null;
	private Entity boundEntity = null;
	private BlockEntity boundBlockEntity = null;
	
	private final Class<T> creeperGirlClass;
	
	protected AbstractCreeperGirlMainGUIMenu(MenuType<?> menuType, Class<T> creeperGirlClass, int id, Inventory inv, FriendlyByteBuf extraData) {
		super(menuType, id);
		this.entity = inv.player;
		this.world = inv.player.level();
		this.creeperGirlClass = creeperGirlClass;
		
		if (extraData != null) {						
			var pos = extraData.readBlockPos();	
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();	
			
			access = ContainerLevelAccess.create(world, pos); 		
			
			if (world.getEntity(extraData.readVarInt()) instanceof AbstractTamableCreeperGirl e)		
				creeperGirl = create(e);
		}

	}
	
    private T create(AbstractTamableCreeperGirl entity) {
        if (creeperGirlClass.isInstance(entity)) {
            return creeperGirlClass.cast(entity);
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
	
	public T getCreeperGirl() {
		return creeperGirl;
	}
	
	public Vector3i getPos() {
		return new Vector3i(x, y, z);
	}
}
