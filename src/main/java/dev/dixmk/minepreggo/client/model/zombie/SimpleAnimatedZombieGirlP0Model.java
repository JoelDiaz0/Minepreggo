package dev.dixmk.minepreggo.client.model.zombie;

import dev.dixmk.minepreggo.client.animation.ZombieGirlP0Animation;
import dev.dixmk.minepreggo.client.model.ZombieGirlP0Model;
import dev.dixmk.minepreggo.entity.preggo.girl.zombie.AbstractMonsterZombieGirl;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SimpleAnimatedZombieGirlP0Model<E extends AbstractMonsterZombieGirl> extends ZombieGirlP0Model<E> {

	private final ModelPart root;

	private final HierarchicalModel<E> animator = new HierarchicalModel<>() {
		
		@Override
		public ModelPart root() {
			return root;
		}

		@Override
		public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			this.root().getAllParts().forEach(ModelPart::resetPose);
					
			if (entity.walkAnimation.isMoving()) {
				if (entity.isAggressive()) {
					this.animateWalk(ZombieGirlP0Animation.AGGRESSION, limbSwing, limbSwingAmount * 2F, 1f, 1f);
				}
				else {
					this.animateWalk(ZombieGirlP0Animation.WALK, limbSwing, limbSwingAmount * 2F, 1f, 1f);
				}
			} else {
				this.animate(entity.idleAnimationState, ZombieGirlP0Animation.IDLE, ageInTicks, 1f);						
			    this.animate(entity.attackAnimationState, ZombieGirlP0Animation.ATTACK, ageInTicks, 1f);	
			}
		}	
	};

	public SimpleAnimatedZombieGirlP0Model(ModelPart root) {
		super(root);
		this.root = root;	
	}
	
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}
	
	
}
