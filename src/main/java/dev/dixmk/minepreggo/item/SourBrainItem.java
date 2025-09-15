package dev.dixmk.minepreggo.item;


import net.minecraft.world.item.Rarity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class SourBrainItem extends AbstractBrain {
	public SourBrainItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(1).alwaysEat().saturationMod(0.1f).build()));
	}
}
