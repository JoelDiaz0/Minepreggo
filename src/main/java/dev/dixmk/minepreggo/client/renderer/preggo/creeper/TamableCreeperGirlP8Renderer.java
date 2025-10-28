package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.TamableCreeperGirlP8Model;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP8;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableCreeperGirlP8Renderer extends AbstractTamablePregnantCreeperGirlRenderer<TamableCreeperGirlP8, TamableCreeperGirlP8Model> {
	
	public TamableCreeperGirlP8Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P8, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P8_LOCATION);
	}
	
	public TamableCreeperGirlP8Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new TamableCreeperGirlP8Model(context.bakeLayer(main)), new TamableCreeperGirlP8Model(context.bakeLayer(inner)), new TamableCreeperGirlP8Model(context.bakeLayer(outter)), new TamableCreeperGirlP8Model(context.bakeLayer(armor)));
	}
	@Override
	public ResourceLocation getTextureLocation(TamableCreeperGirlP8 p_115812_) {

		return AbstractHumanoidCreeperGirlRenderer.CREEPER_GIRL_P8_LOCATION;
	}
}
