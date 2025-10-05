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
public class AbstractAnimatedCreeperGirlP2Model<E extends AbstractCreeperGirl> extends AbstractCreeperGirlModel<E> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "creeper_girl_p2_model"), "main");

	protected final ModelPart root;
	protected HierarchicalModel<E> animator;

	protected AbstractAnimatedCreeperGirlP2Model(ModelPart root, HierarchicalModel<E> animator) {
		super(root);
		this.root = root;
		this.animator = animator;
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		createBasicBodyLayer(partdefinition, 0, 0);	
        
		PartDefinition body = partdefinition.getChild("body");
		
		body.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(18, 82).addBox(-3.0F, -3.0F, -2.0F, 6.0F, 6.0F, 3.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 8.5F, -3.0F));

		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.5F, -2.25F, -0.3491F, 0.0F, 0.0F));

		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.6F, 2.35F));

		rightBoob.addOrReplaceChild("rightBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).addBox(-2.0F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.2F)), PartPose.offsetAndRotation(0.9F, 0.0F, -2.0F, 0.3491F, 0.1309F, 0.0436F));

		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.0F, 0.6F, 2.35F));

		leftBoob.addOrReplaceChild("letfBoobCube_r1", CubeListBuilder.create().texOffs(18, 66).mirror().addBox(-0.9F, -0.2717F, -3.266F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.2F)).mirror(false), PartPose.offsetAndRotation(-0.9F, 0.0F, -2.0F, 0.3491F, -0.1309F, -0.0436F));
        
		return LayerDefinition.create(meshdefinition, 64, 96);
	}

}
