package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.client.entity.animation.preggo.zombie.ZombieGirlAnimation;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP0;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP1;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AnimatedTamableZombieGirlP1Model extends AbstractAnimatedZombieGirlP0Model<TamableZombieGirlP1> {
	
	public AnimatedTamableZombieGirlP1Model(ModelPart root) {
		super(root, new HierarchicalModel<TamableZombieGirlP1>() {
			
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(TamableZombieGirlP1 zombieGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
					
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
			
				if (zombieGirl.isPanic()) {
					this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.IDLE, ageInTicks, 1f);						
				} else {
					switch (zombieGirl.getState()) {
					case WAIT: {
						this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.WAIT, ageInTicks, 1f);										
						break;
					}
					case SIT: {
						this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.RIDING, ageInTicks, 1f);						
						break;
					}		
					default:
						this.animate(zombieGirl.loopAnimationState, ZombieGirlAnimation.IDLE, ageInTicks, 1f);						
					}	
				}
			}	
		});
	}


	@Override
	public void setupAnim(TamableZombieGirlP1 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
				
		if (entity.hasCustomHeadAnimation()) {
			this.hat.copyFrom(this.head);
		}
		else {
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}	
	}

}
