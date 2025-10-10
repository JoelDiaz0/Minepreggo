package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractMonsterZombieGirlModel;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractMonsterZombieGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterZombieGirlRenderer
	<E extends AbstractMonsterZombieGirl, M extends AbstractMonsterZombieGirlModel<E>> extends AbstractZombieGirlRenderer<E, M> {

	protected AbstractMonsterZombieGirlRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);
	}

}