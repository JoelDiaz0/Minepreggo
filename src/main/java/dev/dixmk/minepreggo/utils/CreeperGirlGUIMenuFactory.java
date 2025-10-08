package dev.dixmk.minepreggo.utils;

import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP0;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP1;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP2;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP3;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP4;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP5;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP6;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP7;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP0MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP1MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP2MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP3MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP4MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP5MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP6MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP7MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP0InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP1InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP2InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP3InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP4InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP5InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP6InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP7InventaryGUIMenu;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class CreeperGirlGUIMenuFactory {
	
	private CreeperGirlGUIMenuFactory() {}
	
    public static<T extends AbstractTamableCreeperGirl<?>> MenuProvider createMainGUIMenuProvider(Class<T> creeperGirlClass, BlockPos blockPos, int entityId) {
        return new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("CreeperGirlMainGUI");
            }

            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
                packetBuffer.writeBlockPos(blockPos);
                packetBuffer.writeVarInt(entityId);
                
                if (creeperGirlClass == TamableCreeperGirlP0.class) {
                	return new CreeperGirlP0MainGUIMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP1.class) {
                	return new CreeperGirlP1MainGUIMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP2.class) {
                	return new CreeperGirlP2MainGUIMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP3.class) {
                	return new CreeperGirlP3MainGUIMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP4.class) {
                	return new CreeperGirlP4MainGUIMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP5.class) {
                	return new CreeperGirlP5MainGUIMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP6.class) {
                	return new CreeperGirlP6MainGUIMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP7.class) {
                	return new CreeperGirlP7MainGUIMenu(id, inventory, packetBuffer);
                }
                else {
                    throw new IllegalArgumentException("Unsupported CreeperGirl GUI phase");
                } 
            }
        };
    }
    
	
	public static<T extends AbstractTamableCreeperGirl<?>> MenuProvider createInventaryGUIMenuProvider(Class<T> creeperGirlClass, BlockPos blockPos, int entityId) {
        return new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("CreeperGirlMainGUI");
            }

            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
                packetBuffer.writeBlockPos(blockPos);
                packetBuffer.writeVarInt(entityId);
                
                if (creeperGirlClass == TamableCreeperGirlP0.class) {
                	return new CreeperGirlP0InventaryGUIMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP1.class) {
                	return new CreeperGirlP1InventaryGUIMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP2.class) {
                	return new CreeperGirlP2InventaryGUIMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP3.class) {
                	return new CreeperGirlP3InventaryGUIMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP4.class) {
                	return new CreeperGirlP4InventaryGUIMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP5.class) {
                	return new CreeperGirlP5InventaryGUIMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP6.class) {
                	return new CreeperGirlP6InventaryGUIMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP7.class) {
                	return new CreeperGirlP7InventaryGUIMenu(id, inventory, packetBuffer);
                }
                else {
                    throw new IllegalArgumentException("Unsupported CreeperGirl GUI phase");
                }             }
        };
    }
}
