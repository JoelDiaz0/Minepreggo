package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterPregnantCreeperGirlModel;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractMonsterPregnantCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterPregnantCreeperGirlRenderer 
	<E extends AbstractMonsterPregnantCreeperGirl, M extends AbstractMonsterPregnantCreeperGirlModel<E>> extends AbstractMonsterCreeperGirlRenderer<E, M> {

	protected AbstractMonsterPregnantCreeperGirlRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);
	}
}
