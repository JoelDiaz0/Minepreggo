package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.MonsterAnimatedZombieGirlP0Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractAnimatedZombieGirlP0;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.entity.preggo.zombie.MonsterZombieGirlP0;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class MonsterZombieGirlP0Renderer extends AbstractZombieGirlP0Renderer<MonsterZombieGirlP0> {

	public MonsterZombieGirlP0Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractAnimatedZombieGirlP0.LAYER_LOCATION, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public MonsterZombieGirlP0Renderer(EntityRendererProvider.Context context, ModelLayerLocation p_174459_, ModelLayerLocation p_174460_, ModelLayerLocation p_174461_) {
		super(context, new MonsterAnimatedZombieGirlP0Model(context.bakeLayer(p_174459_)), new MonsterAnimatedZombieGirlP0Model(context.bakeLayer(p_174460_)), new MonsterAnimatedZombieGirlP0Model(context.bakeLayer(p_174461_)));
	}
	

}
