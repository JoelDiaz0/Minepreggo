package dev.dixmk.minepreggo.item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.entity.preggo.ICraving;

public class ActivatedGunpowderWithChocolateItem extends AbstractGunpowder implements ICraving {
	public ActivatedGunpowderWithChocolateItem() {
		super(6);
	}

	@Override
	@NonNegative
	public int getGratification() {
		return 6;
	}
}
