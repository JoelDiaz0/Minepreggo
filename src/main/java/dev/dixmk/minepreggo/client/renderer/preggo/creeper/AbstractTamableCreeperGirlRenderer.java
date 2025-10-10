package dev.dixmk.minepreggo.client.renderer.preggo.creeper;


import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractTamableCreeperGirlModel;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamableCreeperGirlRenderer
	<E extends AbstractTamableCreeperGirl<?>, M extends AbstractTamableCreeperGirlModel<E>> extends AbstractCreeperGirlRenderer<E, M> {

	protected AbstractTamableCreeperGirlRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);
	}
}