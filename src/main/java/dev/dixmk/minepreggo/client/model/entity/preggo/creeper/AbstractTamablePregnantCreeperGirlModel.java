package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantHumanoidCreeperGirl;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamablePregnantCreeperGirlModel<E extends AbstractTamablePregnantHumanoidCreeperGirl<?,?>> extends AbstractHumanoidCreeperGirlModel<E> {
	
	protected final ModelPart root;
	protected HierarchicalModel<E> animator;
	
	protected AbstractTamablePregnantCreeperGirlModel(ModelPart root, HierarchicalModel<E> animator) {
		super(root);
		this.root = root;
		this.animator = animator;
		this.belly.visible = true;
	}
}
