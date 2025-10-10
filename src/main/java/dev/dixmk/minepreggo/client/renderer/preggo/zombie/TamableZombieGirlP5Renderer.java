package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AnimatedTamableZombieGirlP5Model;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP5;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT) 
public class TamableZombieGirlP5Renderer extends AbstractTamablePregnantZombieGirlRenderer<TamableZombieGirlP5, AnimatedTamableZombieGirlP5Model> {

	public TamableZombieGirlP5Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractZombieGirlModel.LAYER_LOCATION_P5, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public TamableZombieGirlP5Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new AnimatedTamableZombieGirlP5Model(context.bakeLayer(main)), new AnimatedTamableZombieGirlP5Model(context.bakeLayer(inner)), new AnimatedTamableZombieGirlP5Model(context.bakeLayer(outter)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableZombieGirlP5 p_115812_) {
		return ZOMBIE_GIRL_P5_LOCATION;
	}
}
