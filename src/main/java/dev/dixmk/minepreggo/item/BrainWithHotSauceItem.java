package dev.dixmk.minepreggo.item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.entity.preggo.Craving;
import dev.dixmk.minepreggo.entity.preggo.ICraving;

public class BrainWithHotSauceItem extends AbstractBrain implements ICraving {
	public BrainWithHotSauceItem() {
		super(7);
	}
	
	@Override
	@NonNegative
	public int getGratification() {
		return 8;
	}
	
	@Override
	public Craving getTypeOfCraving() {
		return Craving.SPICY;
	}
}
