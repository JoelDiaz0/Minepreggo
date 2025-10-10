package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractTamablePregnantCreeperGirlModel;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamablePregnantCreeperGirlRenderer 	
	<E extends AbstractTamablePregnantCreeperGirl<?>, M extends AbstractTamablePregnantCreeperGirlModel<E>> extends AbstractTamableCreeperGirlRenderer<E, M> {

	protected AbstractTamablePregnantCreeperGirlRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);
	}

}
