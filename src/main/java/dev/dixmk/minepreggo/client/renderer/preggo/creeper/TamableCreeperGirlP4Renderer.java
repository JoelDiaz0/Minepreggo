package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AnimatedTamableCreeperGirlP4Model;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP4;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableCreeperGirlP4Renderer extends AbstractTamablePregnantCreeperGirlRenderer<TamableCreeperGirlP4, AnimatedTamableCreeperGirlP4Model> {
	
	public TamableCreeperGirlP4Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P4, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P4_LOCATION);
	}
	
	public TamableCreeperGirlP4Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new AnimatedTamableCreeperGirlP4Model(context.bakeLayer(main)), new AnimatedTamableCreeperGirlP4Model(context.bakeLayer(inner)), new AnimatedTamableCreeperGirlP4Model(context.bakeLayer(outter)), new AnimatedTamableCreeperGirlP4Model(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableCreeperGirlP4 p_115812_) {
		return AbstractHumanoidCreeperGirlRenderer.CREEPER_GIRL_P4_LOCATION;
	}

}
