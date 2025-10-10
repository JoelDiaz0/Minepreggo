package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.entity.animation.preggo.zombie.ZombieGirlAnimation;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractMonsterPregnantCreeperGirl;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterPregnantCreeperGirlModel<E extends AbstractMonsterPregnantCreeperGirl> extends AbstractMonsterCreeperGirlModel<E> {

	protected AbstractMonsterPregnantCreeperGirlModel(ModelPart root) {
		this(root, createDefaultHierarchicalModel(root));		
	}

	protected AbstractMonsterPregnantCreeperGirlModel(ModelPart root, HierarchicalModel<E> animator) {
		super(root, animator);		
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
				
				if (creeperGirl.hasPregnancyPain()) {
					this.animateWalk(ZombieGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount, 1f, 1f);
					return;
				}
				
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
			}
		};
	}
	
}
