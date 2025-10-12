package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AnimatedTamableCreeperGirlP3Model;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP3;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableCreeperGirlP3Renderer extends AbstractTamablePregnantCreeperGirlRenderer<TamableCreeperGirlP3, AnimatedTamableCreeperGirlP3Model> {
	
	public TamableCreeperGirlP3Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractCreeperGirlModel.LAYER_LOCATION_P3, AbstractCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractCreeperGirlModel.LAYER_ENERGY_ARMOR_P3_LOCATION);
	}
	
	public TamableCreeperGirlP3Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new AnimatedTamableCreeperGirlP3Model(context.bakeLayer(main)), new AnimatedTamableCreeperGirlP3Model(context.bakeLayer(inner)), new AnimatedTamableCreeperGirlP3Model(context.bakeLayer(outter)), new AnimatedTamableCreeperGirlP3Model(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableCreeperGirlP3 p_115812_) {
		return AbstractCreeperGirlRenderer.CREEPER_GIRL_P3_LOCATION;
	}

}
