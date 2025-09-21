package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractAnimatedCreeperGirlP0Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AnimatedTamableCreeperGirlP0Model;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP0;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableCreeperGirlP0Renderer extends AbstractCreeperGirlP0Renderer<TamableCreeperGirlP0> {
	
	public TamableCreeperGirlP0Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractAnimatedCreeperGirlP0Model.LAYER_LOCATION, AbstractCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public TamableCreeperGirlP0Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new AnimatedTamableCreeperGirlP0Model(context.bakeLayer(main)), new AnimatedTamableCreeperGirlP0Model(context.bakeLayer(inner)), new AnimatedTamableCreeperGirlP0Model(context.bakeLayer(outter)));
	}
	
}
