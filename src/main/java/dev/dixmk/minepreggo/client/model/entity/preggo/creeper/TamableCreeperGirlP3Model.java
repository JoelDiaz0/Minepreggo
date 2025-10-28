package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.entity.animation.preggo.HumanoidGirlAnimation;
import dev.dixmk.minepreggo.client.entity.animation.preggo.creeper.CreeperGirlAnimation;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP3;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableCreeperGirlP3Model extends AbstractTamablePregnantCreeperGirlModel<TamableCreeperGirlP3> {
	
	public TamableCreeperGirlP3Model(ModelPart root) {
		super(root, new HierarchicalModel<TamableCreeperGirlP3>() {
			
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(TamableCreeperGirlP3 creeperGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
					
			    if (creeperGirl.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
			    	this.animate(creeperGirl.loopAnimationState, HumanoidGirlAnimation.MEDIUM_BELLY_INFLATION, ageInTicks, 1f);
			    }
				
			    if (creeperGirl.isAttacking()) {
				    this.animate(creeperGirl.attackAnimationState, CreeperGirlAnimation.ATTACK, ageInTicks, 1f);	
			    }
				
				if (creeperGirl.walkAnimation.isMoving()) {
					if (creeperGirl.isAggressive()) {
						this.animateWalk(CreeperGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
					else {
						this.animateWalk(CreeperGirlAnimation.WALK, limbSwing, limbSwingAmount * 4.5F, 1f, 1f);
					}
				}
						
				if (creeperGirl.isIncapacitated()) {
					switch (creeperGirl.getPregnancyPain()) {
					case MORNING_SICKNESS: {
						this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.MORNING_SICKNESS, ageInTicks, 1f);										
						break;
					}
					case MISCARRIAGE: {
						this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.MISCARRIAGE, ageInTicks, 1f);						
						break;
					}
					case KICKING: {
						this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.KICKING1, ageInTicks, 1f);						
						break;
					}
					default:
						break;						
					}	
				} 
				
				if (creeperGirl.isPanic()) {
					this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.IDLE, ageInTicks, 1f);						
					return;
				} 
												
				if (creeperGirl.isWaiting()) {
					this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.WAIT2, ageInTicks, 1f);										
				}
				else if (creeperGirl.isPassenger()) {
					this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.RIDING, ageInTicks, 1f);						
				}
				else {
					this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.IDLE, ageInTicks, 1f);						
				}
			}	
		});
	}

}
