package dev.dixmk.minepreggo.item;


import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.Minecraft;

import net.mcreator.minepreggo.init.MinepreggoModItems;
import net.mcreator.minepreggo.client.model.ModelFemaleChestPlateP3;

import java.util.function.Consumer;
import java.util.Map;
import java.util.Collections;

public abstract class FemaleNetheriteChestPlateP3Item extends ArmorItem {
	public FemaleNetheriteChestPlateP3Item(ArmorItem.Type type, Item.Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForType(ArmorItem.Type type) {
				return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * 37;
			}

			@Override
			public int getDefenseForType(ArmorItem.Type type) {
				return new int[]{3, 6, 8, 3}[type.getSlot().getIndex()];
			}

			@Override
			public int getEnchantmentValue() {
				return 9;
			}

			@Override
			public SoundEvent getEquipSound() {
				return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_netherite"));
			}

			@Override
			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(Items.NETHERITE_INGOT), new ItemStack(Items.NETHERITE_CHESTPLATE), new ItemStack(MinepreggoModItems.FEMALE_NETHERITE_CHEST_PLATE_CHESTPLATE.get()),
						new ItemStack(MinepreggoModItems.FEMALE_NETHERITE_CHEST_PLATE_P_1_CHESTPLATE.get()), new ItemStack(MinepreggoModItems.FEMALE_NETHERITE_CHEST_PLATE_P_2_CHESTPLATE.get()));
			}

			@Override
			public String getName() {
				return "female_netherite_chest_plate_p_3";
			}

			@Override
			public float getToughness() {
				return 3f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0.1f;
			}
		}, type, properties);
	}

	public static class Chestplate extends FemaleNetheriteChestPlateP3Item {
		public Chestplate() {
			super(ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant());
		}

		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
					HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("body", new ModelFemaleChestPlateP3(Minecraft.getInstance().getEntityModels().bakeLayer(ModelFemaleChestPlateP3.LAYER_LOCATION)).Body,
							"left_arm", new ModelFemaleChestPlateP3(Minecraft.getInstance().getEntityModels().bakeLayer(ModelFemaleChestPlateP3.LAYER_LOCATION)).LeftArm, "right_arm",
							new ModelFemaleChestPlateP3(Minecraft.getInstance().getEntityModels().bakeLayer(ModelFemaleChestPlateP3.LAYER_LOCATION)).RightArm, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
							new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
					armorModel.crouching = living.isShiftKeyDown();
					armorModel.riding = defaultModel.riding;
					armorModel.young = living.isBaby();
					return armorModel;
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/netherite_p3_layer_1.png";
		}
	}
}
