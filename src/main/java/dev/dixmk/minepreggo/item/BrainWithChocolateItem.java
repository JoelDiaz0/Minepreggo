package dev.dixmk.minepreggo.item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.entity.preggo.ICraving;

public class BrainWithChocolateItem extends AbstractBrain implements ICraving {
	public BrainWithChocolateItem() {
		super(8);
	}
	
	@Override
	@NonNegative
	public int getGratification() {
		return 10;
	}
}
