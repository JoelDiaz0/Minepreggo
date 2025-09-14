package dev.dixmk.minepreggo.client.renderer.preggo.girl.zombie;

import dev.dixmk.minepreggo.client.model.ZombieGirlP0Model;
import dev.dixmk.minepreggo.entity.preggo.girl.zombie.AbstractZombieGirl;

import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;


public abstract class AbstractZombieGirlRenderer<E extends AbstractZombieGirl, M extends ZombieGirlP0Model<E>> extends HumanoidMobRenderer<E, M> {

	protected AbstractZombieGirlRenderer(EntityRendererProvider.Context p_173910_, M p_173911_, M p_173912_, M p_173913_) {
		super(p_173910_, p_173911_, 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, p_173912_, p_173913_, p_173910_.getModelManager()));
	}
	
	
	
}
