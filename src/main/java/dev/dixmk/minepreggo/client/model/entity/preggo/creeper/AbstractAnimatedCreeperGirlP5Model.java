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
public class AbstractAnimatedCreeperGirlP5Model<E extends AbstractCreeperGirl> extends AbstractCreeperGirlModel<E> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "creeper_girl_p5_model"), "main");

	protected final ModelPart root;
	protected HierarchicalModel<E> animator;

	protected AbstractAnimatedCreeperGirlP5Model(ModelPart root, HierarchicalModel<E> animator) {
		super(root);
		this.root = root;
		this.animator = animator;
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		createBasicBodyLayer(partdefinition, 0, 0);	
        
		PartDefinition body = partdefinition.getChild("body");
		
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.35F, -2.55F, -0.3316F, 0.0F, 0.0F));

		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.2F, 2.35F));

		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(46, 78).addBox(-2.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(0.1664F, 1.7279F, -3.1635F, 0.3491F, 0.1745F, 0.0436F));

		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.2F, 2.35F));

		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(46, 78).mirror().addBox(-1.5F, -2.5F, -3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.15F)).mirror(false), PartPose.offsetAndRotation(-0.0674F, 1.7235F, -3.1505F, 0.3491F, -0.1745F, -0.0436F));

		PartDefinition belly = body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 80).addBox(-4.0F, -6.5F, -9.0F, 8.0F, 7.0F, 9.0F, new CubeDeformation(0.6F))
		.texOffs(8, 88).addBox(-3.5F, -6.0F, -9.5F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.6F))
		.texOffs(0, 78).addBox(-0.5F, -2.5F, -10.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 11.75F, -1.5F, 0.0873F, 0.0F, 0.0F));

		belly.addOrReplaceChild("bellyCube6_r1", CubeListBuilder.create().texOffs(8, 71).addBox(-4.0F, -3.5F, -0.5F, 8.0F, 7.0F, 1.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -6.5F, -4.5F, -1.5708F, 0.0F, 0.0F));
		belly.addOrReplaceChild("bellyCube5_r1", CubeListBuilder.create().texOffs(26, 80).addBox(-4.0F, -3.5F, -0.5F, 8.0F, 7.0F, 1.0F, new CubeDeformation(0.6F)), PartPose.offsetAndRotation(0.0F, 0.5F, -4.5F, 0.0F, -1.5708F, -1.5708F));
		belly.addOrReplaceChild("bellyCube4_r1", CubeListBuilder.create().texOffs(35, 89).mirror().addBox(-4.0F, -3.0F, -0.5F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.6F)).mirror(false), PartPose.offsetAndRotation(-4.0F, -3.0F, -4.5F, 0.0F, -1.5708F, 0.0F));
		belly.addOrReplaceChild("bellyCube3_r1", CubeListBuilder.create().texOffs(35, 89).addBox(-4.0F, -3.0F, -0.5F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.6F)), PartPose.offsetAndRotation(4.0F, -3.0F, -4.5F, 0.0F, 1.5708F, 0.0F));

		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(16, 80).addBox(-4.0F, -3.5F, -10.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.6F))
		.texOffs(48, 80).addBox(-3.5F, -3.0F, -10.8F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.6F))
		.texOffs(25, 91).addBox(-0.5F, 0.5F, -11.9F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.0F, 8.5F, 1.0F, 0.0436F, 0.0F, 0.0F));
        
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(44, 73).addBox(-1.2F, 0.25F, 2.45F, 3.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F))
		.texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(52, 71).addBox(-1.85F, -0.25F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(52, 71).addBox(-2.15F, -0.25F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F))
		.texOffs(44, 73).addBox(-1.8F, 0.25F, 2.45F, 3.0F, 3.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offset(1.9F, 12.0F, 0.0F));
		
		return LayerDefinition.create(meshdefinition, 64, 96);
	}

}
