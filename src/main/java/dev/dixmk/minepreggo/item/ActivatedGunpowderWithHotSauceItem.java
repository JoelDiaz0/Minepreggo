package dev.dixmk.minepreggo.item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.entity.preggo.Craving;
import dev.dixmk.minepreggo.entity.preggo.ICraving;

public class ActivatedGunpowderWithHotSauceItem extends AbstractGunpowder implements ICraving {
	public ActivatedGunpowderWithHotSauceItem() {
		super();
	}
	
	@Override
	@NonNegative
	public int getGratification() {
		return 5;
	}
	
	@Override
	public Craving getTypeOfCraving() {
		return Craving.SPICY;
	}
}
