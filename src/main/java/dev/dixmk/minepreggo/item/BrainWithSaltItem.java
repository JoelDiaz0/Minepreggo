package dev.dixmk.minepreggo.item;


import net.minecraft.world.item.Rarity;

import org.checkerframework.checker.index.qual.NonNegative;

import dev.dixmk.minepreggo.entity.preggo.ICraving;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class BrainWithSaltItem extends AbstractBrain implements ICraving {
	public BrainWithSaltItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(1).alwaysEat().saturationMod(0.1f).build()));
	}
	
	@Override
	@NonNegative
	public int getGratification() {
		return 6;
	}
}
