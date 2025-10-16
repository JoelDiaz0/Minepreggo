package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractMonsterZombieGirlModel;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractMonsterZombieGirl;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterZombieGirlExpressionLayer 
	<E extends AbstractMonsterZombieGirl, M extends AbstractMonsterZombieGirlModel<E>> extends RenderLayer<E, M> {

	protected static final RenderType HOSTIL = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_hostil.png"));
	protected static final RenderType SURPRISED = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_hostil_surprised.png"));

	public MonsterZombieGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource p_117350_, int p_117351_, E p_117352_, float p_117353_,
			float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {		
		poseStack.pushPose();
		getParentModel().head.render(poseStack, p_117350_.getBuffer(renderType(p_117352_)), p_117351_, LivingEntityRenderer.getOverlayCoords(p_117352_, 0.0F));	
		poseStack.popPose();
	}
	
	@Nonnull
	public RenderType renderType(E creeperGirl) {	
		if (creeperGirl.isOnFire()) {
			return SURPRISED;
		}
		return HOSTIL;
	}
}
