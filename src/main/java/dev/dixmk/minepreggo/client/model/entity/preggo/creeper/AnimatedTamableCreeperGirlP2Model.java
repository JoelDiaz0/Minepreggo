package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.entity.animation.preggo.zombie.ZombieGirlAnimation;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP2;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AnimatedTamableCreeperGirlP2Model extends AbstractTamablePregnantCreeperGirlModel<TamableCreeperGirlP2> {
	public AnimatedTamableCreeperGirlP2Model(ModelPart root) {
		super(root, new HierarchicalModel<TamableCreeperGirlP2>() {
			
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(TamableCreeperGirlP2 creeperGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
					
			    if (creeperGirl.isAttacking()) {
				    this.animate(creeperGirl.attackAnimationState, ZombieGirlAnimation.ATTACK, ageInTicks, 1f);	
			    }
				
				if (creeperGirl.walkAnimation.isMoving()) {
					if (creeperGirl.isAggressive()) {
						this.animateWalk(ZombieGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
					else {
						this.animateWalk(ZombieGirlAnimation.WALK, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
				}
						
				if (creeperGirl.isIncapacitated()) {
					switch (creeperGirl.getPregnancyPain()) {
					case MORNING_SICKNESS: {
						this.animate(creeperGirl.loopAnimationState, ZombieGirlAnimation.MORNING_SICKNESS, ageInTicks, 1f);										
						break;
					}
					case MISCARRIAGE: {
						this.animate(creeperGirl.loopAnimationState, ZombieGirlAnimation.MISCARRIAGE, ageInTicks, 1f);						
						break;
					}		
					default:
						break;						
					}	
				} else {								
					if (creeperGirl.isPanic()) {
						this.animate(creeperGirl.loopAnimationState, ZombieGirlAnimation.IDLE, ageInTicks, 1f);						
						return;
					} 
									
					switch (creeperGirl.getState()) {
					case WAIT: {
						this.animate(creeperGirl.loopAnimationState, ZombieGirlAnimation.WAIT, ageInTicks, 1f);										
						break;
					}
					case SIT: {
						this.animate(creeperGirl.loopAnimationState, ZombieGirlAnimation.RIDING, ageInTicks, 1f);						
						break;
					}		
					default:
						this.animate(creeperGirl.loopAnimationState, ZombieGirlAnimation.IDLE, ageInTicks, 1f);						
					}			
				}
			}	
		});
	}
}
