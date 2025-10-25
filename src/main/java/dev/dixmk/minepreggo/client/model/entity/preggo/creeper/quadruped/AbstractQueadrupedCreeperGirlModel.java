package dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped;

import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractCreeperGirl;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractQueadrupedCreeperGirlModel<E extends AbstractCreeperGirl> extends CreeperModel<E> {	
	protected final ModelPart hat;
	protected final ModelPart head;
	protected final ModelPart belly;
	protected final ModelPart boobs;
	
	protected AbstractQueadrupedCreeperGirlModel(ModelPart root) {
		super(root);
		this.hat = root().getChild("hat");
		this.head = root().getChild("head");
		this.belly = root().getChild("belly");
		this.boobs = root().getChild("boobs");
	}
	
	protected static void createBasicBodyLayer(PartDefinition partdefinition, float extraLeftArmRotationZ, float extraRightArmRotationZ) {	
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(19, 18).addBox(-3.5F, 5.0F, -1.5F, 7.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(16, 24).addBox(-4.0F, 8.0F, -2.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition rightFrontLeg = partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(1, 17).addBox(-3.0F, 0.0F, -2.0F, 4.0F, 12.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(18, 50).addBox(-3.0F, 0.3686F, 0.2378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offset(-1.9F, 12.0F, -3.0F));
		rightFrontLeg.addOrReplaceChild("extraCube2_r1", CubeListBuilder.create().texOffs(18, 50).addBox(-2.5F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(-0.5F, 1.1686F, 1.0378F, 1.405F, 0.0F, 0.0F));
		rightFrontLeg.addOrReplaceChild("extraCube1_r1", CubeListBuilder.create().texOffs(18, 50).addBox(-2.5F, -1.0F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(-0.5F, 2.6686F, 0.8378F, -0.5236F, 0.0F, 0.0F));

		PartDefinition leftFrontLeg = partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(17, 49).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(18, 50).addBox(-2.0F, 0.3686F, 0.2378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offset(2.9F, 12.0F, -3.0F));
		leftFrontLeg.addOrReplaceChild("extraCube2_r2", CubeListBuilder.create().texOffs(18, 50).addBox(-2.5F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.5F, 1.1686F, 1.0378F, 1.405F, 0.0F, 0.0F));
		leftFrontLeg.addOrReplaceChild("extraCube1_r2", CubeListBuilder.create().texOffs(18, 50).addBox(-2.5F, -1.0F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.5F, 2.6686F, 0.8378F, -0.5236F, 0.0F, 0.0F));

		PartDefinition rightHindLeg = partdefinition.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(1, 17).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(18, 50).addBox(-2.0F, 0.3686F, 0.5378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offset(-2.9F, 12.0F, 3.0F));
		rightHindLeg.addOrReplaceChild("extraCube2_r3", CubeListBuilder.create().texOffs(18, 50).addBox(-2.5F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.5F, 1.1686F, 1.3378F, 1.405F, 0.0F, 0.0F));
		rightHindLeg.addOrReplaceChild("extraCube1_r3", CubeListBuilder.create().texOffs(18, 50).addBox(-2.5F, -1.0F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.5F, 2.6686F, 1.1378F, -0.5236F, 0.0F, 0.0F));

		PartDefinition leftHindLeg = partdefinition.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(17, 49).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(18, 50).addBox(-2.0F, 0.3686F, 0.5378F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offset(2.9F, 12.0F, 3.0F));
		leftHindLeg.addOrReplaceChild("extraCube2_r4", CubeListBuilder.create().texOffs(18, 50).addBox(-2.5F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.5F, 1.1686F, 1.3378F, 1.405F, 0.0F, 0.0F));
		leftHindLeg.addOrReplaceChild("extraCube1_r4", CubeListBuilder.create().texOffs(18, 50).addBox(-2.5F, -1.0F, -1.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(0.5F, 2.6686F, 1.1378F, -0.5236F, 0.0F, 0.0F));
	}
	
	public static LayerDefinition createP0BodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		createBasicBodyLayer(partdefinition, 0, 0);	
		PartDefinition body = partdefinition.getChild("body");
		PartDefinition boobs = body.addOrReplaceChild("boobs", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.8F, -2.0F, -0.0262F, 0.0F, 0.0F));
		PartDefinition rightBoob = boobs.addOrReplaceChild("right_boob", CubeListBuilder.create(), PartPose.offset(-2.25F, 0.0F, 0.0F));
		rightBoob.addOrReplaceChild("Boob_1_r1", CubeListBuilder.create().texOffs(0, 64).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.2664F, 2.7279F, -1.0635F, 0.3491F, 0.1309F, 0.0436F));
		PartDefinition leftBoob = boobs.addOrReplaceChild("left_boob", CubeListBuilder.create(), PartPose.offset(2.25F, 0.0F, 0.0F));
		leftBoob.addOrReplaceChild("Boob_2_r1", CubeListBuilder.create().texOffs(0, 64).mirror().addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offsetAndRotation(-0.1674F, 2.7235F, -1.0505F, 0.3491F, -0.1309F, -0.0436F));	
		partdefinition.addOrReplaceChild("belly", CubeListBuilder.create(), PartPose.offset(0.0F, 9.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 96);
	}
}

