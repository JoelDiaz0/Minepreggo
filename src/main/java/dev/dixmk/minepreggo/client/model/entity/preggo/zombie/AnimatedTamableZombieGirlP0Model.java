package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.client.entity.animation.preggo.zombie.ZombieGirlP0Animation;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP0;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;

public class AnimatedTamableZombieGirlP0Model extends AbstractAnimatedZombieGirlP0Model<TamableZombieGirlP0> {
	
	public AnimatedTamableZombieGirlP0Model(ModelPart root) {
		super(root, new HierarchicalModel<TamableZombieGirlP0>() {
			
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(TamableZombieGirlP0 zombieGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
					
			    if (zombieGirl.isAttacking()) {
				    this.animate(zombieGirl.attackAnimationState, ZombieGirlP0Animation.ATTACK, ageInTicks, 1f);	
			        return; // attack overrides walk/idle
			    }
				
				if (zombieGirl.walkAnimation.isMoving()) {
					if (zombieGirl.isAggressive()) {
						this.animateWalk(ZombieGirlP0Animation.AGGRESSION, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
					else {
						this.animateWalk(ZombieGirlP0Animation.WALK, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
				}
				
				switch (zombieGirl.getAnimationState()) {
				case WAIT: {
					this.animate(zombieGirl.loopAnimationState, ZombieGirlP0Animation.WAIT, ageInTicks, 1f);						
					break;
				}
				case SIT: {
					this.animate(zombieGirl.loopAnimationState, ZombieGirlP0Animation.RIDING, ageInTicks, 1f);						
					break;
				}	
				case DIZZY: {
					this.animate(zombieGirl.loopAnimationState, ZombieGirlP0Animation.MORNING_SICKNESS, ageInTicks, 1f);						
					break;
				}			
				default:
					this.animate(zombieGirl.loopAnimationState, ZombieGirlP0Animation.IDLE, ageInTicks, 1f);						
				}		
			}	
		});
	}


	@Override
	public void setupAnim(TamableZombieGirlP0 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}
	
}
