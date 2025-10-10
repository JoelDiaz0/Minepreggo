package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP7;
import net.minecraft.client.model.geom.ModelPart;

public class AnimatedTamableZombieGirlP7Model extends AbstractTamablePregnantZombieGirlModel<TamableZombieGirlP7> {
	
	public AnimatedTamableZombieGirlP7Model(ModelPart root) {
		super(root, AnimatedTamableZombieGirlP4Model.createDefaultP4HierarchicalModel(root));
	}
}
