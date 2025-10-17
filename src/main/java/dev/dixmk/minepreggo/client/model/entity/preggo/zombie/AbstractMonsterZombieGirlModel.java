package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.client.entity.animation.preggo.zombie.ZombieGirlAnimation;
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

	protected AbstractMonsterZombieGirlModel(ModelPart root) {
		this(root, createDefaultHierarchicalModel(root));
	}
	
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);			
		this.moveHead(entity, netHeadYaw, headPitch);
	}
	
	private static<E extends AbstractMonsterZombieGirl> HierarchicalModel<E> createDefaultHierarchicalModel(ModelPart root) {
		return new HierarchicalModel<E>() {
			
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
						
				if (entity.isAttacking()) {
				    this.animate(entity.attackAnimationState, ZombieGirlAnimation.ATTACK, ageInTicks, 1f);
				}
				
				if (entity.walkAnimation.isMoving()) {
					if (entity.isAggressive()) {
						this.animateWalk(ZombieGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4F, 1f, 1f);
					}
					else {
						this.animateWalk(ZombieGirlAnimation.WALK, limbSwing, limbSwingAmount * 4F, 1f, 1f);
					}
				} 
				
				this.animate(entity.loopAnimationState, ZombieGirlAnimation.IDLE, ageInTicks, 1f);						
			}	
		};
	}
}
