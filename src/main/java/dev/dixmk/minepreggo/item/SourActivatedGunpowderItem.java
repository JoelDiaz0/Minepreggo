package dev.dixmk.minepreggo.item;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.entity.preggo.ICraving;

public class SourActivatedGunpowderItem extends AbstractGunpowder implements ICraving {
	public SourActivatedGunpowderItem() {
		super();
	}
	
	@Override
	@NonNegative
	public int getGratification() {
		return 4;
	}
}

