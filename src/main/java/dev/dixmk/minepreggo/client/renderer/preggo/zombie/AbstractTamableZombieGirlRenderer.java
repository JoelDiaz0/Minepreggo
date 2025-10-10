package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractTamableZombieGirlModel;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamableZombieGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamableZombieGirlRenderer
	<E extends AbstractTamableZombieGirl<?>, M extends AbstractTamableZombieGirlModel<E>> extends AbstractZombieGirlRenderer<E, M> {

	protected AbstractTamableZombieGirlRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);
	}


	
}
