package dev.dixmk.minepreggo.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class AbstractBrain extends Item {

    protected AbstractBrain(Properties p_41383_) {
		super(p_41383_);
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 40;
	}
}
