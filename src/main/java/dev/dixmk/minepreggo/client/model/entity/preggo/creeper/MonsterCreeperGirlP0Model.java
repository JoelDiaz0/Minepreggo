package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.MonsterCreeperGirlP0;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterCreeperGirlP0Model extends AbstractMonsterCreeperGirlModel<MonsterCreeperGirlP0> {
	public MonsterCreeperGirlP0Model(ModelPart root) {
		super(root);
	}
	
	@Override
	public void setupAnim(MonsterCreeperGirlP0 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		
		if (entity.isBaby()) {
			this.boobs.xScale = 0.75F;
			this.boobs.yScale = 0.75F;
			this.boobs.zScale = 0.75F;
			this.head.xScale = 1.25F;
			this.head.yScale = 1.25F;
			this.head.zScale = 1.25F;
			this.hat.xScale = 1.25F;
			this.hat.yScale = 1.25F;
			this.hat.zScale = 1.25F; 
			this.boobs.setPos(0F, 1.5F, -0.075F);
		}	
	}
}
