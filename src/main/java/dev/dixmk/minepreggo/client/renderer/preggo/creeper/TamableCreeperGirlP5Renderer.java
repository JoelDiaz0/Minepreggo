package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AnimatedTamableCreeperGirlP5Model;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP5;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableCreeperGirlP5Renderer extends AbstractTamablePregnantCreeperGirlRenderer<TamableCreeperGirlP5, AnimatedTamableCreeperGirlP5Model> {
	
	public TamableCreeperGirlP5Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractCreeperGirlModel.LAYER_LOCATION_P5, AbstractCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public TamableCreeperGirlP5Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new AnimatedTamableCreeperGirlP5Model(context.bakeLayer(main)), new AnimatedTamableCreeperGirlP5Model(context.bakeLayer(inner)), new AnimatedTamableCreeperGirlP5Model(context.bakeLayer(outter)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableCreeperGirlP5 p_115812_) {
		return AbstractCreeperGirlRenderer.CREEPER_GIRL_P5_LOCATION;
	}

}
