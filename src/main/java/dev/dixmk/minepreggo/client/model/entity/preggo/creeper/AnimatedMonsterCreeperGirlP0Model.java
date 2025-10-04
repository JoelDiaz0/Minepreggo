package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.entity.animation.preggo.creeper.CreeperGirlP0Animation;
import dev.dixmk.minepreggo.entity.preggo.creeper.MonsterCreeperGirlP0;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AnimatedMonsterCreeperGirlP0Model extends AbstractAnimatedCreeperGirlP0Model<MonsterCreeperGirlP0> {

	public AnimatedMonsterCreeperGirlP0Model(ModelPart root) {
		super(root, new HierarchicalModel<MonsterCreeperGirlP0>() {
			
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(MonsterCreeperGirlP0 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
						
				if (entity.walkAnimation.isMoving()) {
					if (entity.isAggressive()) {
						this.animateWalk(CreeperGirlP0Animation.AGGRESSION, limbSwing, limbSwingAmount * 4F, 1f, 1f);
					}
					else {
						this.animateWalk(CreeperGirlP0Animation.WALK, limbSwing, limbSwingAmount * 4F, 1f, 1f);
					}
				} 
				
				this.animate(entity.loopAnimationState, CreeperGirlP0Animation.IDLE, ageInTicks, 1f);						
			    this.animate(entity.attackAnimationState, CreeperGirlP0Animation.ATTACK, ageInTicks, 1f);
			}	
		});
	}


	@Override
	public void setupAnim(MonsterCreeperGirlP0 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}
	
	
}
