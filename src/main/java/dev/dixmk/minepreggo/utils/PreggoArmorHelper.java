package dev.dixmk.minepreggo.utils;

import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

public class PreggoArmorHelper {

	
	private PreggoArmorHelper() {}
	
	
	public static boolean isHelmet(ItemStack item) {
		if (item == null) return false;
		return item.getItem() instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == EquipmentSlot.HEAD;
	}
	
	public static boolean isBoot(ItemStack item) {
		if (item == null) return false;
		return item.getItem() instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == EquipmentSlot.FEET;
	}
	
	public static boolean isLegging(ItemStack item) {
		if (item == null) return false;
		
		return item.getItem() instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == EquipmentSlot.LEGS;
	}
	
	public static boolean canPreggoMobUseChestplate(ItemStack armor, PregnancyStage stage) {
		switch (stage) {
		case P0: {
			return armor.is(PreggoTags.MIN_P0_ARMOR_TAG_KEY);
		}
		case P1: {
			return armor.is(PreggoTags.MIN_P1_ARMOR_TAG_KEY);
		}
		case P2: {
			return armor.is(PreggoTags.MIN_P2_ARMOR_TAG_KEY);
		}
		case P3: {
			return armor.is(PreggoTags.MIN_P3_ARMOR_TAG_KEY);
		}
		case P4: {
			return armor.is(PreggoTags.MIN_P4_ARMOR_TAG_KEY);
		}
		case P5: {
			return armor.is(PreggoTags.MIN_P5_ARMOR_TAG_KEY);
		}
		case P6: {
			return armor.is(PreggoTags.MIN_P6_ARMOR_TAG_KEY);
		}
		default:
			return armor.is(PreggoTags.MIN_P7_ARMOR_TAG_KEY);
		}	
	}
	
	public static boolean canPreggoMobUseLegging(ItemStack armor, PregnancyStage stage) {	
		if (stage == PregnancyStage.P0 && armor.getItem() instanceof ArmorItem armorItem && armorItem.getEquipmentSlot() == EquipmentSlot.LEGS) {
			return true;
		}
		else {
			return armor.is(PreggoTags.KNEE_BRACE_TAG_KEY);
		}
	}

}
