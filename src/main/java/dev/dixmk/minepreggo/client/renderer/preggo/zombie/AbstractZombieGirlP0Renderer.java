package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractAnimatedZombieGirlP0;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractZombieGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public abstract class AbstractZombieGirlP0Renderer<E extends AbstractZombieGirl> extends AbstractZombieGirlRenderer<E, AbstractAnimatedZombieGirlP0<E>> {

	private static final ResourceLocation ZOMBIE_GIRL_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entities/preggo/zombie/zombie_girl_p0.png");

	protected AbstractZombieGirlP0Renderer(Context context, AbstractAnimatedZombieGirlP0<E> model, AbstractAnimatedZombieGirlP0<E> inner, AbstractAnimatedZombieGirlP0<E> outter) {
		super(context, model, inner, outter);
	}

	@Override
	public ResourceLocation getTextureLocation(E entity) {
		return ZOMBIE_GIRL_LOCATION;
	}
	
}
