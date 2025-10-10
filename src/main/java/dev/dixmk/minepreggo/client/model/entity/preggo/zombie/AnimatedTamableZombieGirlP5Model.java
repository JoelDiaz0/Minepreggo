package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP5;
import net.minecraft.client.model.geom.ModelPart;

public class AnimatedTamableZombieGirlP5Model extends AbstractTamablePregnantZombieGirlModel<TamableZombieGirlP5> {
	
	public AnimatedTamableZombieGirlP5Model(ModelPart root) {
		super(root, AnimatedTamableZombieGirlP4Model.createDefaultP4HierarchicalModel(root));
	}
}
