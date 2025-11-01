package dev.dixmk.minepreggo.item.armor;

import java.util.function.Consumer;

import dev.dixmk.minepreggo.utils.PreggoModelHelper;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public abstract class FemaleLeatherChestPlateItem extends DyeableArmorItem {
	protected FemaleLeatherChestPlateItem() {
		super(ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE, new Item.Properties());	
	}
	
	public static class ChestplateP0 extends FemaleLeatherChestPlateItem {		
		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
					return PreggoModelHelper.createFemaleP0HumanoidArmorModel(living, stack, slot, defaultModel);
				}
			});
		}	
		
		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {		    
		    if ("overlay".equals(type)) {
		        return "minepreggo:textures/models/armor/leather_p0_layer_1_overlay.png";
		    }
		    return "minepreggo:textures/models/armor/leather_p0_layer_1.png";
		}
	}	
	
	public static class MaternityChestplateP1 extends FemaleLeatherChestPlateItem {		
		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
					return PreggoModelHelper.createMaternalP1HumanoidArmorModel(living, stack, slot, defaultModel);
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		    if ("overlay".equals(type)) {
		        return "minepreggo:textures/models/armor/leather_p1_layer_1_overlay.png";
		    }
		    return "minepreggo:textures/models/armor/leather_p1_layer_1.png";
		}	
	}
	
	public static class MaternityChestplateP2 extends FemaleLeatherChestPlateItem {		
		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
					return PreggoModelHelper.createMaternalP2HumanoidArmorModel(living, stack, slot, defaultModel);
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		    if ("overlay".equals(type)) {
		        return "minepreggo:textures/models/armor/leather_p2_layer_1_overlay.png";
		    }
		    return "minepreggo:textures/models/armor/leather_p2_layer_1.png";
		}	
	}
	
	public static class MaternityChestplateP3 extends FemaleLeatherChestPlateItem {		
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
		    if ("overlay".equals(type)) {
		        return "minepreggo:textures/models/armor/leather_p3_layer_1_overlay.png";
		    }
		    return "minepreggo:textures/models/armor/leather_p3_layer_1.png";
		}	
	}
	
	public static class MaternityChestplateP4 extends FemaleLeatherChestPlateItem {		
		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
					return PreggoModelHelper.createMaternalP4HumanoidArmorModel(living, stack, slot, defaultModel);
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		    if ("overlay".equals(type)) {
		        return "minepreggo:textures/models/armor/leather_p4_layer_1_overlay.png";
		    }
		    return "minepreggo:textures/models/armor/leather_p4_layer_1.png";
		}	
	}
}
