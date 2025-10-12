package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie;

import java.util.Optional;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractTamableZombieGirlModel;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamableZombieGirl;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableZombieGirlExpressionLayer 
	<E extends AbstractTamableZombieGirl<?>, M extends AbstractTamableZombieGirlModel<E>> extends RenderLayer<E, M> {

	protected static final RenderType SAD2 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_sad2.png"));
	protected static final RenderType SAD3 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_sad3.png"));
	protected static final RenderType PAIN4 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_pain4.png"));
	protected static final RenderType SURPRISED2 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_surprised2.png"));

	public TamableZombieGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource p_117350_, int p_117351_, E p_117352_, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {		
		var render = renderType(p_117352_);
		render.ifPresent(r -> getParentModel().head.render(poseStack, p_117350_.getBuffer(r), p_117351_, OverlayTexture.NO_OVERLAY));
	}
	
	public Optional<RenderType> renderType(E zombieGirl) {	
		
		if (zombieGirl.isOnFire()) {
			return Optional.of(SURPRISED2);
		}
		else if (zombieGirl.hasEffect(MobEffects.CONFUSION)) {
			return Optional.of(PAIN4);
		}
		else if (zombieGirl.isWaiting()) {
			return Optional.of(SAD2);
		}
		else if (zombieGirl.isSavage()) {
			return Optional.of(SAD3);
		}
		else {
			return Optional.empty();
		}
	}
}
