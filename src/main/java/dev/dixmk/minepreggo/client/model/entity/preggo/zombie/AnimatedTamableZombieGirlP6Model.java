package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP6;
import net.minecraft.client.model.geom.ModelPart;

public class AnimatedTamableZombieGirlP6Model extends AbstractTamablePregnantZombieGirlModel<TamableZombieGirlP6> {
	
	public AnimatedTamableZombieGirlP6Model(ModelPart root) {
		super(root, AnimatedTamableZombieGirlP4Model.createDefaultP4HierarchicalModel(root));
	}
}
