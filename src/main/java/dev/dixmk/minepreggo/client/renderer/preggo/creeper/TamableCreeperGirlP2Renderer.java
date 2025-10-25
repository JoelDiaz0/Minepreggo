package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AnimatedTamableCreeperGirlP2Model;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP2;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableCreeperGirlP2Renderer extends AbstractTamablePregnantCreeperGirlRenderer<TamableCreeperGirlP2, AnimatedTamableCreeperGirlP2Model> {
	
	public TamableCreeperGirlP2Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P2, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P2_LOCATION);
	}
	
	public TamableCreeperGirlP2Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new AnimatedTamableCreeperGirlP2Model(context.bakeLayer(main)), new AnimatedTamableCreeperGirlP2Model(context.bakeLayer(inner)), new AnimatedTamableCreeperGirlP2Model(context.bakeLayer(outter)), new AnimatedTamableCreeperGirlP2Model(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableCreeperGirlP2 p_115812_) {
		return AbstractHumanoidCreeperGirlRenderer.CREEPER_GIRL_P2_LOCATION;
	}

}
