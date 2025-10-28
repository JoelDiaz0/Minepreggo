package dev.dixmk.minepreggo.entity.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterPregnantHumanoidCreeperGirl;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class MonsterCreeperGirlP3 extends AbstractMonsterPregnantHumanoidCreeperGirl {

	public MonsterCreeperGirlP3(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P3.get(), world);
	}

	public MonsterCreeperGirlP3(EntityType<MonsterCreeperGirlP3> type, Level world) {
		super(type, world, PregnancyStage.P3, PregnancyStage.getRandomFinalCurrentStage(PregnancyStage.P3));
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);	
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return AbstractMonsterHumanoidCreeperGirl.getBasicAttributes(0.24);
	}
	

}
