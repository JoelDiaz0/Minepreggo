package dev.dixmk.minepreggo.entity.preggo;

import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.world.entity.player.Player;

public class PregnantPreggoMobSystem
	<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem> extends PreggoMobSystem<E> {

	public PregnantPreggoMobSystem(E preggoMob, int totalTicksOfHungry) {
		super(preggoMob, totalTicksOfHungry);
	}

	@Override
	public boolean canOwnerAccessGUI(Player source) {
		return super.canOwnerAccessGUI(source) && !preggoMob.isIncapacitated();
	}
	
	@Override
	protected boolean canFeedHerself() {
		return super.canFeedHerself() && !preggoMob.isIncapacitated();
	}
}
