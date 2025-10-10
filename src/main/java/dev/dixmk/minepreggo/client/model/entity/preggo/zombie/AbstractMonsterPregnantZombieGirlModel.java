package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.client.entity.animation.preggo.zombie.ZombieGirlAnimation;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractMonsterPregnantZombieGirl;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;

public abstract class AbstractMonsterPregnantZombieGirlModel<E extends AbstractMonsterPregnantZombieGirl> extends AbstractMonsterZombieGirlModel<E> {

	protected AbstractMonsterPregnantZombieGirlModel(ModelPart root) {
		super(root, new HierarchicalModel<E>() {

			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(E zombieGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				
				if (zombieGirl.hasPregnancyPain()) {
					this.animateWalk(ZombieGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount, 1f, 1f);
					return;
				}
				
			    if (zombieGirl.isAttacking()) {
				    this.animate(zombieGirl.attackAnimationState, ZombieGirlAnimation.ATTACK, ageInTicks, 1f);	
			    }
				
				if (zombieGirl.walkAnimation.isMoving()) {
					if (zombieGirl.isAggressive()) {
						this.animateWalk(ZombieGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
					else {
						this.animateWalk(ZombieGirlAnimation.WALK, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
				}						
			}});
		
		this.belly.visible = true;
	}

	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);			
		if (entity.hasPregnancyPain()) {
			this.hat.copyFrom(this.head);
		}
		else {
			this.moveHead(entity, netHeadYaw, headPitch);
		}
	}
}
