package dev.dixmk.minepreggo.utils;

import java.util.concurrent.atomic.AtomicReference;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.entity.preggo.IPreggoMob;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandlerModifiable;

public class PreggoGUIHelper {

	private PreggoGUIHelper() {}
	
	public static final ResourceLocation CREEPER_GIRL_CRAVING_1 = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/activated_gunpowder_with_chocolate.png");
	public static final ResourceLocation CREEPER_GIRL_CRAVING_2 = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/activated_gunpowder_with_salt.png");
	public static final ResourceLocation CREEPER_GIRL_CRAVING_3 = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/sour_activated_gunpowder.png");
	public static final ResourceLocation CREEPER_GIRL_CRAVING_4 = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/activated_gunpowder_with_hot_sauce.png");

	public static final ResourceLocation ZOMBIE_GIRL_CRAVING_1 = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/brain_with_chocolate.png");
	public static final ResourceLocation ZOMBIE_GIRL_CRAVING_2 = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/brain_with_salt.png");
	public static final ResourceLocation ZOMBIE_GIRL_CRAVING_3 = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/sour_brain.png");
	public static final ResourceLocation ZOMBIE_GIRL_CRAVING_4 = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/brain_with_hot_sauce.png");
	
	public static final ResourceLocation DEFAULT_INVENTARY_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/default_preggo_mob_inventory_gui.png");
	
	public static final ResourceLocation ZOMBIE_GIRL_INVENTARY_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/zombie_girl_inventory_gui.png");
	
	public static final ResourceLocation DEFAULT_P0_MAIN_GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/default_preggo_mob_p0_main_gui.png");
	public static final ResourceLocation DEFAULT_P1_MAIN_GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/default_preggo_mob_p1_main_gui.png");
	public static final ResourceLocation DEFAULT_P2_MAIN_GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/default_preggo_mob_p2_main_gui.png");
	public static final ResourceLocation DEFAULT_P3_MAIN_GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/default_preggo_mob_p3_main_gui.png");
	public static final ResourceLocation DEFAULT_P4_MAIN_GUI_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/default_preggo_mob_p4_main_gui.png");
	
	public static final ResourceLocation DEFAULT_ICON_CREEPER_GIRL_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entities/creeper_girl_p0.png");
	public static final ResourceLocation DEFAULT_ICON_ZOMBIE_GIRL_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entities/zombie_girl_p0.png");
	
	public static final ResourceLocation DEFAULT_ICON_HUMAN_TEXTURE = ResourceLocation.withDefaultNamespace("textures/entity/player/wide/alex.png");
	public static final ResourceLocation MINECRAFT_ICONS_TEXTURE = ResourceLocation.withDefaultNamespace("textures/gui/icons.png");

	public static final ResourceLocation PICKLE_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/salty_pickle.png");
	public static final ResourceLocation LEMON_POPSICLES_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/lemon_ice_popsicles.png");
	public static final ResourceLocation LEMON_ICECREAM_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/lemon_ice_cream.png");
	public static final ResourceLocation HOT_CHICKEN_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/spicy_chicken.png");
	public static final ResourceLocation CHOCOLATE_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/item/chocolate_bar.png");

	public static final ResourceLocation ICONS_TEXTURE = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/screens/icons.png");
	

	public static<T extends TamableAnimal & IPreggoMob> void syncPreggoMobInventaryOnStart(T preggoEntity) {
	    if (preggoEntity == null)
	        return;
	    
        preggoEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
            if (capability instanceof IItemHandlerModifiable modHandlerEntSetSlot)
                modHandlerEntSetSlot.setStackInSlot(4, preggoEntity.getMainHandItem().copy());
        });
          
        preggoEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
            if (capability instanceof IItemHandlerModifiable modHandlerEntSetSlot)
                modHandlerEntSetSlot.setStackInSlot(5, preggoEntity.getOffhandItem().copy());
        });
	      
	    for (int i = 0; i < 4; i++) {
            final int slotid = i;
            preggoEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
                if (capability instanceof IItemHandlerModifiable modHandlerEntSetSlot)
                    modHandlerEntSetSlot.setStackInSlot(slotid, preggoEntity.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, slotid)).copy());
            });
	    }
	}
	

	public static<T extends TamableAnimal & IPreggoMob> void syncPreggoMobInventaryOnTick(Level world, T preggoEntity) {
	    if (preggoEntity == null)
	        return;
	    
	    if (!world.isClientSide()) {
	        preggoEntity.setItemInHand(InteractionHand.MAIN_HAND, getItemStack(4, preggoEntity).copy());
	        preggoEntity.setItemInHand(InteractionHand.OFF_HAND, getItemStack(5, preggoEntity).copy());
	        for (int i = 0; i < 4; i++) 
                preggoEntity.setItemSlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, i), getItemStack(i, preggoEntity));	   
	    }
	}

	private static<T extends TamableAnimal & IPreggoMob> ItemStack getItemStack(int sltid, T preggoEntity) {
	    AtomicReference<ItemStack> retval = new AtomicReference<>(ItemStack.EMPTY);
	    preggoEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> retval.set(capability.getStackInSlot(sltid).copy()));
	    return retval.get();
	}
	
	
	
	
	
	
	public static void renderDefaultPreggoP0MainGUI(GuiGraphics guiGraphics, int leftPos, int topPos, float health, IPreggoMob p0) {

		for (int i = 0, pos = 74; i < 10; i++, pos += 10) {
			guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 56, 16, 27, 9, 9, 256, 256);
			
			guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 67, 16, 45, 9, 9, 256, 256);		
		}	
		
		for (int i = 0, pos = 74; i < 3; i++, pos += 10) {		
			guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 78, 16, 45, 9, 9, 256, 256);			
		}

		var hungry = p0.getHungry();
		
		for (int i = 0, pos = 74, oddValue = 1, evenValue = 2; i < 10; ++i, pos += 10, oddValue += 2, evenValue += 2) {		
			if (hungry >= evenValue) {
				guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 56, 52, 27, 9, 9, 256, 256);			
			} else if (hungry >= oddValue) {
				guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 56, 61, 27, 9, 9, 256, 256);
			}

			if (health >= evenValue) {
				guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 67, 52, 0, 9, 9, 256, 256);
			} else if (health >= oddValue) {
				guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 67, 61, 0, 9, 9, 256, 256);
			} else if (health <= 0.75F) {
				guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 67, 61, 0, 9, 9, 256, 256);
				break;
			}
		}
		
		for (int i = 0, pos = 74, oddValue = 21, evenValue = 22; i < 3; ++i, pos += 10, oddValue += 2, evenValue += 2) {			
			if (health >= evenValue) {
				guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 78, 52, 0, 9, 9, 256, 256);
			} else if (health >= oddValue) {
				guiGraphics.blit(MINECRAFT_ICONS_TEXTURE, leftPos + pos, topPos + 78, 61, 0, 9, 9, 256, 256);
			}		
		}
	}
	
	public static void renderDefaultPreggoP0LabelMainGUI(GuiGraphics guiGraphics, Font font, IPreggoMob p0, boolean post) {	
		guiGraphics.drawString(font, Component.translatable("gui.minepreggo.zombie_girl_p_0_main_gui.label_state"), 78, 21, -12829636, false);
		guiGraphics.drawString(font, p0.getPreggoName(), 90, 4, -12829636, false);
	
		if (!post) {
			if (p0.isPregnant()) {
				guiGraphics.drawString(font, "Maybe Pregnant?", 109, 21, -12829636, false);
			} else {
				guiGraphics.drawString(font, "Not Pregnant", 109, 21, -12829636, false);
			}
		}

	}
}
