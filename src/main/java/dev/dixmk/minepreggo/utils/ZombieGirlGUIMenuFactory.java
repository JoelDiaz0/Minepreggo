package dev.dixmk.minepreggo.utils;

import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP0;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP1;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP2;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP3;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP4;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP5;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP6;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP7;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP0MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP1MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP2MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP3MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP4MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP5MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP6MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP7MainGUIMenu;
import io.netty.buffer.Unpooled;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP0InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP1InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP2InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP3InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP4InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP5InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP6InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP7InventaryGUIMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class ZombieGirlGUIMenuFactory {

	private ZombieGirlGUIMenuFactory() {}
	
	public static<T extends AbstractTamableZombieGirl<?>> MenuProvider createMainGUIMenuProvider(Class<T> zombieGirlClass, BlockPos blockPos, int entityId) {
        return new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("ZombieGirlMainGUI");
            }

            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
                packetBuffer.writeBlockPos(blockPos);
                packetBuffer.writeVarInt(entityId);
                
                if (zombieGirlClass == TamableZombieGirlP0.class) {
                	return new ZombieGirlP0MainGUIMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP1.class) {
                	return new ZombieGirlP1MainGUIMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP2.class) {
                	return new ZombieGirlP2MainGUIMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP3.class) {
                	return new ZombieGirlP3MainGUIMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP4.class) {
                	return new ZombieGirlP4MainGUIMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP5.class) {
                	return new ZombieGirlP5MainGUIMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP6.class) {
                	return new ZombieGirlP6MainGUIMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP7.class) {
                	return new ZombieGirlP7MainGUIMenu(id, inventory, packetBuffer);
                }
                else {
                    throw new IllegalArgumentException("Unsupported ZombieGirl GUI phase");
                }       
            }
        };
    }
    
	
	public static<T extends AbstractTamableZombieGirl<?>> MenuProvider createInventoryGUIMenuProvider(Class<T> zombieGirlClass, BlockPos blockPos, int entityId) {
        return new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("ZombieGirlInventoryGUI");
            }

            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
                packetBuffer.writeBlockPos(blockPos);
                packetBuffer.writeVarInt(entityId);

                if (zombieGirlClass == TamableZombieGirlP0.class) {
                	return new ZombieGirlP0InventaryGUIMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP1.class) {
                	return new ZombieGirlP1InventaryGUIMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP2.class) {
                	return new ZombieGirlP2InventaryGUIMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP3.class) {
                	return new ZombieGirlP3InventaryGUIMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP4.class) {
                	return new ZombieGirlP4InventaryGUIMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP5.class) {
                	return new ZombieGirlP5InventaryGUIMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP6.class) {
                	return new ZombieGirlP6InventaryGUIMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP7.class) {
                	return new ZombieGirlP7InventaryGUIMenu(id, inventory, packetBuffer);
                }
                else {
                    throw new IllegalArgumentException("Unsupported ZombieGirl GUI phase");
                }    
            }
        };
    }
}
