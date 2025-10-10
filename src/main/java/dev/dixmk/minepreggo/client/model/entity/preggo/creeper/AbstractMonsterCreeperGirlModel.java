package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.entity.animation.preggo.creeper.CreeperGirlAnimation;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractMonsterCreeperGirl;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterCreeperGirlModel<E extends AbstractMonsterCreeperGirl> extends AbstractCreeperGirlModel<E> {

	protected final ModelPart root;
	protected HierarchicalModel<E> animator;
	
	protected AbstractMonsterCreeperGirlModel(ModelPart root, HierarchicalModel<E> animator) {
		super(root);
		this.root = root;
		this.animator = animator;
		this.belly.visible = false;
	}

	protected AbstractMonsterCreeperGirlModel(ModelPart root) {
		this(root, createDefaultHierarchicalModel(root));
	}
	
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);			
		this.moveHead(entity, netHeadYaw, headPitch);
	}
	
	private static<E extends AbstractMonsterCreeperGirl> HierarchicalModel<E> createDefaultHierarchicalModel(ModelPart root) {
		return new HierarchicalModel<E>() {
			
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
						
				if (entity.isAttacking()) {
				    this.animate(entity.attackAnimationState, CreeperGirlAnimation.ATTACK, ageInTicks, 1f);
				}
				
				if (entity.walkAnimation.isMoving()) {
					if (entity.isAggressive()) {
						this.animateWalk(CreeperGirlAnimation.AGGRESSION, limbSwing, limbSwingAmount * 4F, 1f, 1f);
					}
					else {
						this.animateWalk(CreeperGirlAnimation.WALK, limbSwing, limbSwingAmount * 4F, 1f, 1f);
					}
				} 
				else {
					this.animate(entity.loopAnimationState, CreeperGirlAnimation.IDLE, ageInTicks, 1f);						
				}
			}	
		};
	}
}