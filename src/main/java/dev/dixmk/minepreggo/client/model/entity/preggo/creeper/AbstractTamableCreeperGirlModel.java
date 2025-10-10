package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamableCreeperGirlModel <E extends AbstractTamableCreeperGirl<?>> extends AbstractCreeperGirlModel<E> {
	protected final ModelPart root;
	protected HierarchicalModel<E> animator;
	
	protected AbstractTamableCreeperGirlModel(ModelPart root, HierarchicalModel<E> animator) {
		super(root);
		this.root = root;
		this.animator = animator;
		this.belly.visible = false;
	}
	
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);			
		if (entity.hasCustomHeadAnimation()) {
			this.hat.copyFrom(this.head);
		}
		else {
			this.moveHead(entity, netHeadYaw, headPitch);
		}	
	}
}
