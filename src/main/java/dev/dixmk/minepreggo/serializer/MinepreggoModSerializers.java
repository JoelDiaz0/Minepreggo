package dev.dixmk.minepreggo.serializer;

import dev.dixmk.minepreggo.entity.preggo.PregnancyIllness;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import net.minecraft.network.syncher.EntityDataSerializer;

public class MinepreggoModSerializers {

	private MinepreggoModSerializers() {}
	
	public static final EntityDataSerializer<PregnancyIllness> PREGNANCY_ILLNESS = EntityDataSerializer.simpleEnum(PregnancyIllness.class);
	
	public static final EntityDataSerializer<PregnancyStage> PREGNANCY_STAGE = EntityDataSerializer.simpleEnum(PregnancyStage.class);

	
	
}
