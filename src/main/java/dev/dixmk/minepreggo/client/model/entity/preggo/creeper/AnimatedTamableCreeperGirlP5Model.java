package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP5;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AnimatedTamableCreeperGirlP5Model extends AbstractTamablePregnantCreeperGirlModel<TamableCreeperGirlP5> {
	
	public AnimatedTamableCreeperGirlP5Model(ModelPart root) {
		super(root,  AnimatedTamableCreeperGirlP4Model.createDefaultP4HierarchicalModel(root));
	}
}
