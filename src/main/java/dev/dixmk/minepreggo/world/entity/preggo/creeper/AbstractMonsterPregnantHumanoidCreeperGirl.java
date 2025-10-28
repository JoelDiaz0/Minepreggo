package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractMonsterPregnantCreeperGirl;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;

public abstract class AbstractMonsterPregnantHumanoidCreeperGirl extends AbstractMonsterPregnantCreeperGirl {

	protected AbstractMonsterPregnantHumanoidCreeperGirl(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_,
			PregnancyStage currentPregnancyStage, PregnancyStage maxPregnancyStage) {
		super(p_21803_, p_21804_, currentPregnancyStage, maxPregnancyStage);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource p_219165_, DifficultyInstance p_219166_) {			
		AbstractCreeperGirl.populateDefaultEquipmentSlots(this, p_219165_, p_219166_);
	}	
	
}
