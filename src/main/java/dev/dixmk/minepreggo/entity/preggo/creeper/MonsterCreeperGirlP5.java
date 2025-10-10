package dev.dixmk.minepreggo.entity.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class MonsterCreeperGirlP5 extends AbstractMonsterPregnantCreeperGirl {

	public MonsterCreeperGirlP5(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P5.get(), world);
	}

	public MonsterCreeperGirlP5(EntityType<MonsterCreeperGirlP5> type, Level world) {
		super(type, world, PregnancyStage.P5, PregnancyStage.getRandomFinalCurrentStage(PregnancyStage.P5));
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);	
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.24);
	}
	

}
