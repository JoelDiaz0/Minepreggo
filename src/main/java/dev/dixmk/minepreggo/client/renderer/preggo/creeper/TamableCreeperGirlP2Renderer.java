package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractCreeperGirlModel;
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
		this(context, AbstractCreeperGirlModel.LAYER_LOCATION_P2, AbstractCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public TamableCreeperGirlP2Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new AnimatedTamableCreeperGirlP2Model(context.bakeLayer(main)), new AnimatedTamableCreeperGirlP2Model(context.bakeLayer(inner)), new AnimatedTamableCreeperGirlP2Model(context.bakeLayer(outter)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableCreeperGirlP2 p_115812_) {
		return AbstractCreeperGirlRenderer.CREEPER_GIRL_P2_LOCATION;
	}

}
