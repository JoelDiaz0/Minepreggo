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
	
	protected static final RenderType WAITING = RenderType.armorCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_sad2.png"));
	protected static final RenderType SAVAGE = RenderType.armorCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_sad2.png"));
	protected static final RenderType NAUSEOUS = RenderType.armorCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/expressions/creeper_girl_face_pain3"));

	public TamableCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	@Override
	public void render(PoseStack p_117349_, MultiBufferSource p_117350_, int p_117351_, E p_117352_, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {		
		var render = renderType(p_117352_);
		render.ifPresent(r -> this.getParentModel().renderToBuffer(p_117349_, p_117350_.getBuffer(r), 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F));
	}
	
	public Optional<RenderType> renderType(E creeperGirl) {	
		if (creeperGirl.hasEffect(MobEffects.CONFUSION)) {
			return Optional.of(WAITING);
		}
		else if (creeperGirl.isWaiting()) {
			return Optional.of(SAVAGE);
		}
		else if (creeperGirl.isSavage()) {
			return Optional.of(NAUSEOUS);
		}
		else {
			return Optional.empty();
		}
	}
}
