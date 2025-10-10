package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractMonsterZombieGirl;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;

public abstract class AbstractMonsterZombieGirlModel<E extends AbstractMonsterZombieGirl> extends AbstractZombieGirlModel<E> {

	protected final ModelPart root;
	protected HierarchicalModel<E> animator;
	
	protected AbstractMonsterZombieGirlModel(ModelPart root, HierarchicalModel<E> animator) {
		super(root);
		this.root = root;
		this.animator = animator;
		this.belly.visible = false;
	}

	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);			
		this.moveHead(entity, netHeadYaw, headPitch);
	}
}
