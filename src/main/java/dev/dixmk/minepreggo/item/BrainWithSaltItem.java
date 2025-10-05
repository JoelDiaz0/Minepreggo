package dev.dixmk.minepreggo.item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.entity.preggo.ICraving;

public class BrainWithSaltItem extends AbstractBrain implements ICraving {
	public BrainWithSaltItem() {
		super();
	}
	
	@Override
	@NonNegative
	public int getGratification() {
		return 6;
	}
}
