package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AnimatedTamableCreeperGirlP6Model;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP6;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableCreeperGirlP6Renderer extends AbstractTamablePregnantCreeperGirlRenderer<TamableCreeperGirlP6, AnimatedTamableCreeperGirlP6Model> {
	
	public TamableCreeperGirlP6Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractCreeperGirlModel.LAYER_LOCATION_P6, AbstractCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractCreeperGirlModel.LAYER_ENERGY_ARMOR_P6_LOCATION);
	}
	
	public TamableCreeperGirlP6Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new AnimatedTamableCreeperGirlP6Model(context.bakeLayer(main)), new AnimatedTamableCreeperGirlP6Model(context.bakeLayer(inner)), new AnimatedTamableCreeperGirlP6Model(context.bakeLayer(outter)), new AnimatedTamableCreeperGirlP6Model(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableCreeperGirlP6 p_115812_) {
		return AbstractCreeperGirlRenderer.CREEPER_GIRL_P6_LOCATION;
	}

}
