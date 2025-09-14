package dev.dixmk.minepreggo.client.model;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.entity.preggo.girl.zombie.AbstractZombieGirl;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;


public class ZombieGirlP0Model<E extends AbstractZombieGirl> extends FemaleHumanoidModel<E> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "zombie_girl_p0_model"), "main");
	public static final ModelLayerLocation LAYER_INNER_ARMOR_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "zombie_girl_p0_inner_model"), "inner");
	public static final ModelLayerLocation LAYER_OUTER_ARMOR_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "zombie_girl_p0_outer_model"), "outer");
	
	
	public ZombieGirlP0Model(ModelPart root) {
		super(root);
	}

	protected static void createBasicBodyLayer(PartDefinition partdefinition, float extraLeftArmRotationY, float extraRightArmRotationY) {
		
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
		.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
			
		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create()
		.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, -1.5708F, extraRightArmRotationY, 0.0F));

		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, -1.5708F, extraLeftArmRotationY, 0.0F));

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(1.9F, 12.0F, 0.0F));
	}
	
	
    // Inner armor (leggings layer)
    public static LayerDefinition createInnerLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0.35F), 0.0F);
        PartDefinition partdefinition = mesh.getRoot();
        partdefinition.getChild("body").addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.7F, -1.9F, 0.1134F, 0.0F, 0.0F));
      
        return LayerDefinition.create(mesh, 64, 32);
    }

    // Outer armor (chestplate, helmet, boots)
    public static LayerDefinition createOuterLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0.85F), 0.0F);
        PartDefinition partdefinition = mesh.getRoot();
        partdefinition.getChild("body").addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.7F, -1.9F, 0.1134F, 0.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }
	
	public static LayerDefinition createMainBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		createBasicBodyLayer(partdefinition, 0, 0);
	
		PartDefinition boobs = partdefinition.getChild("body").addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.7F, -1.9F, 0.1134F, 0.0F, 0.0F));
			
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 1.0F, 2.0F));

		rightBoob.addOrReplaceChild("right_boob_aux", CubeListBuilder.create().texOffs(18, 66).addBox(-2.0F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(1.0F, 0.0F, -2.0F, 0.3491F, 0.1309F, 0.0436F));

		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 1.0F, 2.0F));

		leftBoob.addOrReplaceChild("left_boob_aux", CubeListBuilder.create().texOffs(18, 66).mirror().addBox(-0.9F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.0F, -2.0F, 0.3491F, -0.1309F, -0.0436F));	
			
		
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	

	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.moveHead(entity, netHeadYaw, headPitch);
	}
}
