package dev.dixmk.minepreggo.utils;

import java.util.Collections;
import java.util.Map;

import dev.dixmk.minepreggo.client.model.armor.BellyShieldP4Model;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP5Model;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP6Model;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP7Model;
import dev.dixmk.minepreggo.client.model.armor.FemaleChestPlateP0Model;
import dev.dixmk.minepreggo.client.model.armor.FemaleChestPlateP1Model;
import dev.dixmk.minepreggo.client.model.armor.FemaleChestPlateP2Model;
import dev.dixmk.minepreggo.client.model.armor.FemaleChestPlateP3Model;
import dev.dixmk.minepreggo.client.model.armor.KneeBraceModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public abstract class PreggoModelHelper {
	
	private PreggoModelHelper() {}
	
	public static HumanoidModel<LivingEntity> getHumanoidKneeBraceArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("left_leg", new KneeBraceModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(KneeBraceModel.LAYER_LOCATION)).leftLeg,
						"right_leg", new KneeBraceModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(KneeBraceModel.LAYER_LOCATION)).rightLeg,
						"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
						"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
						"body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
						"right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
						"left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> getMaternalP0HumanoidArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("body", new FemaleChestPlateP0Model<>(Minecraft.getInstance().getEntityModels().bakeLayer(FemaleChestPlateP0Model.LAYER_LOCATION)).body,
				"left_arm", new FemaleChestPlateP0Model<>(Minecraft.getInstance().getEntityModels().bakeLayer(FemaleChestPlateP0Model.LAYER_LOCATION)).leftArm,
				"right_arm", new FemaleChestPlateP0Model<>(Minecraft.getInstance().getEntityModels().bakeLayer(FemaleChestPlateP0Model.LAYER_LOCATION)).rightArm,
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
		
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> getMaternalP1HumanoidArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("body", new FemaleChestPlateP1Model<>(Minecraft.getInstance().getEntityModels().bakeLayer(FemaleChestPlateP1Model.LAYER_LOCATION)).body,
				"left_arm", new FemaleChestPlateP1Model<>(Minecraft.getInstance().getEntityModels().bakeLayer(FemaleChestPlateP1Model.LAYER_LOCATION)).leftArm,
				"right_arm", new FemaleChestPlateP1Model<>(Minecraft.getInstance().getEntityModels().bakeLayer(FemaleChestPlateP1Model.LAYER_LOCATION)).rightArm,
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
		
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> getMaternalP2HumanoidArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("body", new FemaleChestPlateP2Model<>(Minecraft.getInstance().getEntityModels().bakeLayer(FemaleChestPlateP2Model.LAYER_LOCATION)).body,
				"left_arm", new FemaleChestPlateP2Model<>(Minecraft.getInstance().getEntityModels().bakeLayer(FemaleChestPlateP2Model.LAYER_LOCATION)).leftArm,
				"right_arm", new FemaleChestPlateP2Model<>(Minecraft.getInstance().getEntityModels().bakeLayer(FemaleChestPlateP2Model.LAYER_LOCATION)).rightArm,
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
		
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> getMaternalP3HumanoidArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("body", new FemaleChestPlateP3Model<>(Minecraft.getInstance().getEntityModels().bakeLayer(FemaleChestPlateP3Model.LAYER_LOCATION)).body,
				"left_arm", new FemaleChestPlateP3Model<>(Minecraft.getInstance().getEntityModels().bakeLayer(FemaleChestPlateP3Model.LAYER_LOCATION)).leftArm,
				"right_arm", new FemaleChestPlateP3Model<>(Minecraft.getInstance().getEntityModels().bakeLayer(FemaleChestPlateP3Model.LAYER_LOCATION)).rightArm,
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
		
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> getMaternalP4HumanoidArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("body", new BellyShieldP4Model<LivingEntity>(Minecraft.getInstance().getEntityModels().bakeLayer(BellyShieldP4Model.LAYER_LOCATION)).body,
				"left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
	
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> getMaternalP5HumanoidArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("body", new BellyShieldP5Model<LivingEntity>(Minecraft.getInstance().getEntityModels().bakeLayer(BellyShieldP5Model.LAYER_LOCATION)).body,
				"left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
	
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
	
	public static HumanoidModel<LivingEntity> getMaternalP6HumanoidArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("body", new BellyShieldP6Model<LivingEntity>(Minecraft.getInstance().getEntityModels().bakeLayer(BellyShieldP6Model.LAYER_LOCATION)).body,
				"left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
	
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
		
	public static HumanoidModel<LivingEntity> getMaternalP7HumanoidArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {	
		var armorModel = new HumanoidModel<LivingEntity>(new ModelPart(Collections.emptyList(),
				Map.of("body", new BellyShieldP7Model<LivingEntity>(Minecraft.getInstance().getEntityModels().bakeLayer(BellyShieldP7Model.LAYER_LOCATION)).body,
				"left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"head", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
				"left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
	
		armorModel.crouching = living.isShiftKeyDown();
		armorModel.riding = defaultModel.riding;
		armorModel.young = living.isBaby();
		return armorModel;
	}
}
