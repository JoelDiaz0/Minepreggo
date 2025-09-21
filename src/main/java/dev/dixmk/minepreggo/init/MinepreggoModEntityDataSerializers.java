package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.entity.preggo.PreggoMobAnimationState;
import dev.dixmk.minepreggo.entity.preggo.PregnancyIllness;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractCreeperGirl.CombatMode;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;

public class MinepreggoModEntityDataSerializers {

	private MinepreggoModEntityDataSerializers() {}
	
	public static final EntityDataSerializer<PregnancyIllness> PREGNANCY_ILLNESS = EntityDataSerializer.simpleEnum(PregnancyIllness.class);	
	public static final EntityDataSerializer<PregnancyStage> PREGNANCY_STAGE = EntityDataSerializer.simpleEnum(PregnancyStage.class);
	public static final EntityDataSerializer<CombatMode> COMBAT_MODE = EntityDataSerializer.simpleEnum(CombatMode.class);
	public static final EntityDataSerializer<PreggoMobAnimationState> ANIMATION_STATE = EntityDataSerializer.simpleEnum(PreggoMobAnimationState.class);

	
	
    public static void register() {
        EntityDataSerializers.registerSerializer(PREGNANCY_ILLNESS);
        EntityDataSerializers.registerSerializer(PREGNANCY_STAGE);
        EntityDataSerializers.registerSerializer(COMBAT_MODE);
        EntityDataSerializers.registerSerializer(ANIMATION_STATE);
    }
}
