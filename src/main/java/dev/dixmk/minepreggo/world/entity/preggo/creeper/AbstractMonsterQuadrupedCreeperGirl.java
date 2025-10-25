package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractMonsterCreeperGirl;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;

public abstract class AbstractMonsterQuadrupedCreeperGirl extends AbstractMonsterCreeperGirl {

	protected AbstractMonsterQuadrupedCreeperGirl(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_);
		this.setCanPickUpLoot(false);
	}


	
}
