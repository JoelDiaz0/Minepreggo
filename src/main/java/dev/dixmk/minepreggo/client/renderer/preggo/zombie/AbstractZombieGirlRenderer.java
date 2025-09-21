package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractZombieGirl;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractZombieGirlRenderer<E extends AbstractZombieGirl, M extends AbstractZombieGirlModel<E>> extends HumanoidMobRenderer<E, M> {
	protected AbstractZombieGirlRenderer(EntityRendererProvider.Context context, M main, M inner, M outter) {
		super(context, main, 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, inner, outter, context.getModelManager()));
	}
}
