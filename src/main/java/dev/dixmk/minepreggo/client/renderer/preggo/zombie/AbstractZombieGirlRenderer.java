package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.PregnantFemaleHumanoidModel;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractZombieGirl;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;

public abstract class AbstractZombieGirlRenderer<E extends AbstractZombieGirl, M extends PregnantFemaleHumanoidModel<E>> extends HumanoidMobRenderer<E, M> {
	protected AbstractZombieGirlRenderer(EntityRendererProvider.Context context, M model, M p_173912_, M p_173913_) {
		super(context, model, 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, p_173912_, p_173913_, context.getModelManager()));
	}
}
