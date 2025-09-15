package dev.dixmk.minepreggo.item;


import net.minecraft.world.item.Rarity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class PillagerBrainItem extends AbstractBrain {
	public PillagerBrainItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(1).saturationMod(0.3f).build()));
	}	
}
