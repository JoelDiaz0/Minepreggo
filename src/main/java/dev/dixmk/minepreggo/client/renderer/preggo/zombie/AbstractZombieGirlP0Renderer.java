package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractAnimatedZombieGirlP0Model;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractZombieGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractZombieGirlP0Renderer<E extends AbstractZombieGirl> extends AbstractZombieGirlRenderer<E, AbstractAnimatedZombieGirlP0Model<E>> {

	private static final ResourceLocation ZOMBIE_GIRL_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/zombie_girl_p0.png");

	protected AbstractZombieGirlP0Renderer(Context context, AbstractAnimatedZombieGirlP0Model<E> main, AbstractAnimatedZombieGirlP0Model<E> inner, AbstractAnimatedZombieGirlP0Model<E> outter) {
		super(context, main, inner, outter);
	}

	@Override
	public ResourceLocation getTextureLocation(E entity) {
		return ZOMBIE_GIRL_LOCATION;
	}
	
}
