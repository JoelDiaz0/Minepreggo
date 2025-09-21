package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractAnimatedZombieGirlP0Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AnimatedTamableZombieGirlP0Model;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP0;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableZombieGirlP0Renderer extends AbstractZombieGirlP0Renderer<TamableZombieGirlP0> {

	public TamableZombieGirlP0Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractAnimatedZombieGirlP0Model.LAYER_LOCATION, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public TamableZombieGirlP0Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new AnimatedTamableZombieGirlP0Model(context.bakeLayer(main)), new AnimatedTamableZombieGirlP0Model(context.bakeLayer(inner)), new AnimatedTamableZombieGirlP0Model(context.bakeLayer(outter)));
	}
	

}