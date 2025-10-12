package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import java.util.Optional;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractTamableCreeperGirlModel;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
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
public class TamableCreeperGirlExpressionLayer
	<E extends AbstractTamableCreeperGirl<?>, M extends AbstractTamableCreeperGirlModel<E>> extends RenderLayer<E, M> {
	
	protected static final RenderType SAD2 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_sad2.png"));
	protected static final RenderType SAD3 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_sad3.png"));
	protected static final RenderType PAIN4 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_pain4.png"));

	public TamableCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource p_117350_, int p_117351_, E p_117352_, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {		
		var render = renderType(p_117352_);
		render.ifPresent(r -> getParentModel().head.render(poseStack, p_117350_.getBuffer(r), p_117351_, OverlayTexture.NO_OVERLAY));
	}
	
	public Optional<RenderType> renderType(E creeperGirl) {	
		if (creeperGirl.hasEffect(MobEffects.CONFUSION)) {
			return Optional.of(PAIN4);
		}
		else if (creeperGirl.isWaiting()) {
			return Optional.of(SAD2);
		}
		else if (creeperGirl.isSavage()) {
			return Optional.of(SAD3);
		}
		else {
			return Optional.empty();
		}
	}
}
