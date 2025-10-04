package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.entity.animation.preggo.creeper.CreeperGirlP0Animation;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP0;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AnimatedTamableCreeperGirlP0Model extends AbstractAnimatedCreeperGirlP0Model<TamableCreeperGirlP0> {

	public AnimatedTamableCreeperGirlP0Model(ModelPart root) {
		super(root, new HierarchicalModel<TamableCreeperGirlP0>() {
			
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(TamableCreeperGirlP0 creeperGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
						
			    if (creeperGirl.isAttacking()) {
				    this.animate(creeperGirl.attackAnimationState, CreeperGirlP0Animation.ATTACK, ageInTicks, 1f);	
			        return; // attack overrides walk/idle
			    }
				
				if (creeperGirl.walkAnimation.isMoving()) {
					if (creeperGirl.isAggressive()) {
						this.animateWalk(CreeperGirlP0Animation.AGGRESSION, limbSwing, limbSwingAmount * 4F, 1f, 1f);
					}
					else {
						this.animateWalk(CreeperGirlP0Animation.WALK, limbSwing, limbSwingAmount * 4F, 1f, 1f);
					}
				}
				
				switch (creeperGirl.getState()) {
				case WAIT: {
					this.animate(creeperGirl.loopAnimationState, CreeperGirlP0Animation.WAIT, ageInTicks, 1f);						
					break;
				}
				case SIT: {
					this.animate(creeperGirl.loopAnimationState, CreeperGirlP0Animation.RIDING, ageInTicks, 1f);						
					break;
				}		
				default:
					this.animate(creeperGirl.loopAnimationState, CreeperGirlP0Animation.IDLE, ageInTicks, 1f);						
				}
			}	
		});
	}


	@Override
	public void setupAnim(TamableCreeperGirlP0 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	
		if (entity.hasCustomHeadAnimation()) {
			this.hat.copyFrom(this.head);
		}
		else {
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
	
}
