package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP7;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableZombieGirlP7Model extends AbstractTamablePregnantZombieGirlModel<TamableZombieGirlP7> {
	
	public TamableZombieGirlP7Model(ModelPart root) {
		super(root, TamableZombieGirlP4Model.createDefaultP4HierarchicalModel(root));
	}
}
