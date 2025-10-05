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
public class AbstractAnimatedCreeperGirlP6Model<E extends AbstractCreeperGirl> extends AbstractCreeperGirlModel<E> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "creeper_girl_p6_model"), "main");

	protected final ModelPart root;
	protected HierarchicalModel<E> animator;

	protected AbstractAnimatedCreeperGirlP6Model(ModelPart root, HierarchicalModel<E> animator) {
		super(root);
		this.root = root;
		this.animator = animator;
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		createBasicBodyLayer(partdefinition, 0, 0);	
        
		PartDefinition body = partdefinition.getChild("body");
		
		body.addOrReplaceChild("extraRightWait_r1", CubeListBuilder.create().texOffs(0, 64).mirror().addBox(-1.0F, -6.75F, -3.0F, 2.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.5F, 11.75F, 0.5F, 0.0F, 0.3927F, 0.0F));

		body.addOrReplaceChild("extraLeftWait_r1", CubeListBuilder.create().texOffs(0, 64).addBox(-1.0F, -6.75F, -3.0F, 2.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 11.75F, 0.5F, 0.0F, -0.3927F, 0.0F));

		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.4F, -2.45F, -0.2793F, 0.0F, 0.0F));

		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.1F, 0.2F, 2.35F));

		rightBoob.addOrReplaceChild("extraRightBoobCube_r1", CubeListBuilder.create().texOffs(40, 71).addBox(-2.5189F, -2.4982F, -4.5196F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F))
		.texOffs(37, 68).addBox(-2.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.1664F, 1.7279F, -3.1635F, 0.3491F, 0.1745F, 0.0436F));

		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.1F, 0.2F, 2.35F));

		leftBoob.addOrReplaceChild("extraLetfBoobCube_r1", CubeListBuilder.create().texOffs(40, 71).mirror().addBox(-1.4811F, -2.4982F, -4.5196F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)).mirror(false)
		.texOffs(37, 68).mirror().addBox(-1.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(-0.0674F, 1.7235F, -3.1505F, 0.3491F, -0.1745F, -0.0436F));

		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 78).addBox(-5.0F, -6.25F, -10.0F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.7F))
		.texOffs(9, 87).addBox(-4.45F, -5.75F, -10.55F, 9.0F, 7.0F, 1.0F, new CubeDeformation(0.7F))
		.texOffs(9, 87).addBox(-4.5F, -5.8409F, -11.3735F, 9.0F, 7.0F, 1.0F, new CubeDeformation(0.3F))
		.texOffs(30, 74).addBox(-0.35F, -1.75F, -12.4F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 11.75F, -1.5F, 0.1309F, 0.0F, 0.0F));

		belly.addOrReplaceChild("bellyCube7_r1", CubeListBuilder.create().texOffs(31, 78).addBox(-4.5F, -3.9739F, -0.3017F, 9.0F, 9.0F, 1.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(0.0F, -6.25F, -4.75F, -1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube6_r1", CubeListBuilder.create().texOffs(9, 86).addBox(-4.4096F, -4.3F, -0.4531F, 9.0F, 9.0F, 1.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(-0.25F, 1.5F, -5.25F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube5_r1", CubeListBuilder.create().texOffs(10, 88).addBox(-4.2713F, -3.2374F, -0.4F, 9.0F, 7.0F, 1.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(-4.75F, -2.5F, -5.25F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube4_r1", CubeListBuilder.create().texOffs(10, 88).addBox(-4.5F, -3.5F, -0.5F, 9.0F, 7.0F, 1.0F, new CubeDeformation(0.7F)), PartPose.offsetAndRotation(4.75F, -2.2244F, -4.9222F, 0.0F, -1.5708F, 0.0F));
        
		partdefinition.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(13, 66).addBox(-1.95F, -0.25F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F))
		.texOffs(13, 73).addBox(-1.4F, 0.25F, 2.45F, 3.0F, 3.0F, 1.0F, new CubeDeformation(-0.05F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(16, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(13, 66).addBox(-2.05F, -0.25F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.2F))
		.texOffs(13, 73).addBox(-1.7F, 0.25F, 2.45F, 3.0F, 3.0F, 1.0F, new CubeDeformation(-0.05F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		
		return LayerDefinition.create(meshdefinition, 64, 96);
	}

}
