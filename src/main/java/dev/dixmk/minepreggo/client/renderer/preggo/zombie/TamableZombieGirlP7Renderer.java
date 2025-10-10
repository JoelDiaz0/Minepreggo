package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AnimatedTamableZombieGirlP7Model;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP7;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT) 
public class TamableZombieGirlP7Renderer extends AbstractTamablePregnantZombieGirlRenderer<TamableZombieGirlP7, AnimatedTamableZombieGirlP7Model> {

	public TamableZombieGirlP7Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractZombieGirlModel.LAYER_LOCATION_P7, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public TamableZombieGirlP7Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new AnimatedTamableZombieGirlP7Model(context.bakeLayer(main)), new AnimatedTamableZombieGirlP7Model(context.bakeLayer(inner)), new AnimatedTamableZombieGirlP7Model(context.bakeLayer(outter)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableZombieGirlP7 p_115812_) {
		return ZOMBIE_GIRL_P7_LOCATION;
	}
}
