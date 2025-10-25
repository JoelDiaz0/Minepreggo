package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.entity.animation.preggo.HumanoidGirlAnimation;
import dev.dixmk.minepreggo.client.entity.animation.preggo.creeper.CreeperGirlAnimation;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractMonsterPregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterPregnantHumanoidCreeperGirl;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterPregnantCreeperGirlModel<E extends AbstractMonsterPregnantHumanoidCreeperGirl> extends AbstractHumanoidCreeperGirlModel<E> {

	protected final ModelPart root;
	protected HierarchicalModel<E> animator;
	
	protected AbstractMonsterPregnantCreeperGirlModel(ModelPart root) {
		this(root, createDefaultHierarchicalModel(root));		
	}

	protected AbstractMonsterPregnantCreeperGirlModel(ModelPart root, HierarchicalModel<E> animator) {
		super(root);	
		this.root = root;
		this.animator = animator;
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
	
	private static<E extends AbstractMonsterPregnantCreeperGirl> HierarchicalModel<E> createDefaultHierarchicalModel(ModelPart root) {
		return new HierarchicalModel<E>() {

			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(E creeperGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
				
				if (creeperGirl.hasPregnancyPain()) {
				    this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.CONTRACTION2, ageInTicks, 1f);	
				}
				else if (creeperGirl.isPassenger()) {
				    this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.RIDING, ageInTicks, 1f);	
				}
				else {
				    this.animate(creeperGirl.loopAnimationState, CreeperGirlAnimation.IDLE, ageInTicks, 1f);	
				}
			}
		};
	}
	
}
