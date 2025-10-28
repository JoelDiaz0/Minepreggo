package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie;

import java.util.Optional;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractZombieGirl;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

public abstract class AbstractZombieGirlExpressionFacialLayer 
	<E extends AbstractZombieGirl, M extends AbstractZombieGirlModel<E>> extends RenderLayer<E, M> {

	
	protected AbstractZombieGirlExpressionFacialLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}
	
	@Override
	public void render(PoseStack poseStack, MultiBufferSource p_117350_, int p_117351_, E p_117352_, float p_117353_,
			float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {		
		
		if (p_117352_.isInvisible()) return;
		
		this.renderType(p_117352_).ifPresent(r -> {
			poseStack.pushPose();	
			getParentModel().head.render(poseStack, p_117350_.getBuffer(r), p_117351_, LivingEntityRenderer.getOverlayCoords(p_117352_, 0.0F));
			poseStack.popPose();
		});
	}
	
	public abstract Optional<RenderType> renderType(E creeperGirl);
}
