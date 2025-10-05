package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractCreeperGirl;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AbstractAnimatedCreeperGirlP7Model<E extends AbstractCreeperGirl> extends AbstractCreeperGirlModel<E> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "creeper_girl_p7_model"), "main");

	protected final ModelPart root;
	protected HierarchicalModel<E> animator;

	protected AbstractAnimatedCreeperGirlP7Model(ModelPart root, HierarchicalModel<E> animator) {
		super(root);
		this.root = root;
		this.animator = animator;
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		createBasicBodyLayer(partdefinition, 0, 0);	
        
		PartDefinition body = partdefinition.getChild("body");
		
		body.addOrReplaceChild("extraRightWait_r1", CubeListBuilder.create().texOffs(33, 70).mirror().addBox(-1.0F, -7.5F, -3.0F, 2.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 12.0F, 0.5F, 0.0F, 0.3927F, 0.0F));

		body.addOrReplaceChild("extraLeftWait_r1", CubeListBuilder.create().texOffs(33, 70).addBox(-1.0F, -7.75F, -3.0F, 2.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 12.0F, 0.5F, 0.0F, -0.3927F, 0.0F));

		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.1F, -2.65F, -0.384F, 0.0F, 0.0F));

		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.1F, 0.2F, 2.35F));

		rightBoob.addOrReplaceChild("secondExtraRightBoobCube_r1", CubeListBuilder.create().texOffs(51, 71).addBox(-0.7677F, -0.7209F, -4.7947F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.1F))
		.texOffs(48, 90).addBox(-2.5189F, -2.4982F, -4.5196F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F))
		.texOffs(45, 87).addBox(-2.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(0.1664F, 1.7279F, -3.1635F, 0.3491F, 0.1745F, 0.0436F));

		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.1F, 0.2F, 2.35F));

		leftBoob.addOrReplaceChild("secondExtraRightBoobCube_r2", CubeListBuilder.create().texOffs(51, 71).mirror().addBox(0.1388F, -0.657F, -4.7254F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.1F)).mirror(false)
		.texOffs(48, 90).mirror().addBox(-1.4811F, -2.4982F, -4.5196F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F)).mirror(false)
		.texOffs(45, 87).mirror().addBox(-1.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offsetAndRotation(-0.0674F, 1.7235F, -3.1505F, 0.3491F, -0.1745F, -0.0436F));

		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 74).addBox(-5.0F, -6.5F, -12.5F, 10.0F, 10.0F, 12.0F, new CubeDeformation(1.0F))
		.texOffs(12, 86).addBox(-4.45F, -6.0576F, -13.1235F, 9.0F, 9.0F, 1.0F, new CubeDeformation(1.2F))
		.texOffs(12, 86).addBox(-4.4F, -6.0223F, -13.8117F, 9.0F, 9.0F, 1.0F, new CubeDeformation(1.0F))
		.texOffs(12, 86).addBox(-3.95F, -5.4754F, -14.3814F, 8.0F, 8.0F, 1.0F, new CubeDeformation(1.0F))
		.texOffs(46, 72).addBox(-0.5F, -0.05F, -15.85F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 11.75F, -1.75F, 0.0873F, 0.0F, 0.0F));

		belly.addOrReplaceChild("bellyCube8_r1", CubeListBuilder.create().texOffs(10, 86).addBox(-5.5F, -4.5F, -0.5F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.25F, -6.25F, -6.5F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube7_r1", CubeListBuilder.create().texOffs(10, 86).addBox(-5.5F, -4.8F, -0.5F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(0.25F, 3.25F, -6.25F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube6_r1", CubeListBuilder.create().texOffs(10, 86).addBox(-5.6731F, -4.8163F, -0.5F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-4.75F, -1.25F, -6.25F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r1", CubeListBuilder.create().texOffs(12, 86).mirror().addBox(-5.6992F, -4.5174F, -0.5F, 11.0F, 9.0F, 1.0F, new CubeDeformation(1.0F)).mirror(false), PartPose.offsetAndRotation(4.75F, -1.5F, -6.25F, 0.0F, -1.5708F, 0.0F));
        
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(46, 75).addBox(-2.25F, -0.25F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.1F))
		.texOffs(46, 81).addBox(-1.7F, 0.25F, 2.55F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(46, 75).addBox(-1.75F, -0.25F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.1F))
		.texOffs(46, 81).addBox(-1.3F, 0.25F, 2.55F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		
		return LayerDefinition.create(meshdefinition, 64, 96);
	}

}
