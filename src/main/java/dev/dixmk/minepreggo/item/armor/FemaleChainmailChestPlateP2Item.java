package dev.dixmk.minepreggo.item.armor;


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
import net.minecraft.client.model.HumanoidModel;

import java.util.function.Consumer;

import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.utils.PreggoModelHelper;

public abstract class FemaleChainmailChestPlateP2Item extends ArmorItem {
	protected FemaleChainmailChestPlateP2Item(ArmorItem.Type type, Item.Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForType(ArmorItem.Type type) {
				return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * 15;
			}

			@Override
			public int getDefenseForType(ArmorItem.Type type) {
				return new int[]{2, 4, 5, 2}[type.getSlot().getIndex()];
			}

			@Override
			public int getEnchantmentValue() {
				return 12;
			}

			@Override
			public SoundEvent getEquipSound() {
				return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("item.armor.equip_chain"));
			}

			@Override
			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(Items.CHAINMAIL_CHESTPLATE), new ItemStack(MinepreggoModItems.FEMALE_CHAINMAIL_CHEST_PLATE_P0_CHESTPLATE.get()), new ItemStack(MinepreggoModItems.FEMALE_CHAINMAIL_CHEST_PLATE_P1_CHESTPLATE.get()));
			}

			@Override
			public String getName() {
				return "female_chainmail_chest_plate_p2";
			}

			@Override
			public float getToughness() {
				return 0f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0f;
			}
		}, type, properties);
	}

	public static class Chestplate extends FemaleChainmailChestPlateP2Item {
		public Chestplate() {
			super(ArmorItem.Type.CHESTPLATE, new Item.Properties());
		}

		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
					return PreggoModelHelper.createMaternalP3HumanoidArmorModel(living, stack, slot, defaultModel);
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "minepreggo:textures/models/armor/chainmail_p2_layer_1.png";
		}
	}
}
