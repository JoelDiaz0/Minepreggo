package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AnimatedTamableCreeperGirlP1Model;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP1;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableCreeperGirlP1Renderer extends AbstractTamablePregnantCreeperGirlRenderer<TamableCreeperGirlP1, AnimatedTamableCreeperGirlP1Model> {
	
	public TamableCreeperGirlP1Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractCreeperGirlModel.LAYER_LOCATION_P1, AbstractCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public TamableCreeperGirlP1Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new AnimatedTamableCreeperGirlP1Model(context.bakeLayer(main)), new AnimatedTamableCreeperGirlP1Model(context.bakeLayer(inner)), new AnimatedTamableCreeperGirlP1Model(context.bakeLayer(outter)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableCreeperGirlP1 p_115812_) {
		return AbstractCreeperGirlRenderer.CREEPER_GIRL_P1_LOCATION;
	}
}
