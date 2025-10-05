package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractZombieGirl;
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
public abstract class AbstractAnimatedZombieGirlP3Model<E extends AbstractZombieGirl> extends AbstractZombieGirlModel<E> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "zombie_girl_p0_model"), "main");

	protected final ModelPart root;
	protected HierarchicalModel<E> animator;
	
	protected AbstractAnimatedZombieGirlP3Model(ModelPart root, HierarchicalModel<E> animator) {
		super(root);
		this.root = root;
		this.animator = animator;
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		createBasicBodyLayer(partdefinition, 0, 0);
	
		PartDefinition body = partdefinition.getChild("body");
		
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.5F, -2.55F, -0.3491F, 0.0F, 0.0F));

		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.6F, 2.35F));

		rightBoob.addOrReplaceChild("right_boob_aux", CubeListBuilder.create().texOffs(18, 66).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(-0.0336F, 1.7279F, -3.1635F, 0.3491F, 0.1745F, 0.0436F));

		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.6F, 2.35F));

		leftBoob.addOrReplaceChild("left_boob_aux", CubeListBuilder.create().texOffs(18, 66).mirror().addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offsetAndRotation(0.1326F, 1.7235F, -3.1505F, 0.3491F, -0.1745F, -0.0436F));

		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(15, 80).addBox(-4.0F, -3.0F, -9.0F, 8.0F, 6.0F, 7.0F, new CubeDeformation(0.3F))
		.texOffs(24, 91).addBox(-0.5F, 1.1993F, -9.7305F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.5F, 1.0F, 0.0436F, 0.0F, 0.0F));
			
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(32, 66).addBox(-1.85F, -0.25F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(32, 66).mirror().addBox(-2.15F, -0.25F, 1.25F, 4.0F, 4.0F, 2.0F, new CubeDeformation(-0.3F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 96);
	}
	

}
