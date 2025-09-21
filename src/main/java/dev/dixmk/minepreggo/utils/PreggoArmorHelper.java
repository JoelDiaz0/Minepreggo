package dev.dixmk.minepreggo.utils;

import java.util.List;
import java.util.function.Predicate;

import com.google.common.collect.ImmutableMap;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
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
	
	public static final Int2ObjectMap<List<Predicate<ItemStack>>> ARMOR_RESTRICTION = customToIntMap(ImmutableMap.of(
			0, List.of(PreggoArmorHelper::isLegging, i -> i.is(PreggoTags.MIN_P0_ARMOR_TAG_KEY)),
			1, List.of(i -> i.is(PreggoTags.KNEE_BRACE_TAG_KEY), i -> i.is(PreggoTags.MIN_P1_ARMOR_TAG_KEY)),
			2, List.of(i -> i.is(PreggoTags.KNEE_BRACE_TAG_KEY), i -> i.is(PreggoTags.MIN_P2_ARMOR_TAG_KEY)),
			3, List.of(i -> i.is(PreggoTags.KNEE_BRACE_TAG_KEY), i -> i.is(PreggoTags.MIN_P3_ARMOR_TAG_KEY)),
			4, List.of(i -> i.is(PreggoTags.KNEE_BRACE_TAG_KEY), i -> i.is(PreggoTags.MIN_P4_ARMOR_TAG_KEY)),
			5, List.of(i -> i.is(PreggoTags.KNEE_BRACE_TAG_KEY), i -> i.is(PreggoTags.MIN_P5_ARMOR_TAG_KEY)),
			6, List.of(i -> i.is(PreggoTags.KNEE_BRACE_TAG_KEY), i -> i.is(PreggoTags.MIN_P6_ARMOR_TAG_KEY)),
			7, List.of(i -> i.is(PreggoTags.KNEE_BRACE_TAG_KEY), i -> i.is(PreggoTags.MIN_P7_ARMOR_TAG_KEY))		
	));
	
	private static Int2ObjectMap<List<Predicate<ItemStack>>> customToIntMap(ImmutableMap<Integer, List<Predicate<ItemStack>>> p_35631_) {
		return new Int2ObjectOpenHashMap<>(p_35631_);
	}
	
}
