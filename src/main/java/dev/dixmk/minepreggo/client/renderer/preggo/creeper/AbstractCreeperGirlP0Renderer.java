package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractAnimatedCreeperGirlP0Model;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractCreeperGirlP0Renderer<E extends AbstractCreeperGirl> extends AbstractCreeperGirlRenderer<E, AbstractAnimatedCreeperGirlP0Model<E>> {
	
	private static final ResourceLocation CREEPER_GIRL_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/creeper_girl_p0.png");

	protected AbstractCreeperGirlP0Renderer(Context context, AbstractAnimatedCreeperGirlP0Model<E> model, AbstractAnimatedCreeperGirlP0Model<E> inner, AbstractAnimatedCreeperGirlP0Model<E> outter) {
		super(context, model, inner, outter);
	}

	@Override
	public ResourceLocation getTextureLocation(E entity) {
		return CREEPER_GIRL_LOCATION;
	}
}
