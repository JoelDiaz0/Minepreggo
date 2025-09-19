package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.client.entity.animation.preggo.zombie.ZombieGirlP0Animation;
import dev.dixmk.minepreggo.entity.preggo.zombie.MonsterZombieGirlP0;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterAnimatedZombieGirlP0Model extends AbstractAnimatedZombieGirlP0<MonsterZombieGirlP0> {

	public MonsterAnimatedZombieGirlP0Model(ModelPart root) {
		super(root, new HierarchicalModel<MonsterZombieGirlP0>() {
			
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(MonsterZombieGirlP0 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
		});
	}
	
	@Override
	public void setupAnim(MonsterZombieGirlP0 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}
	
	
}
