
package dev.dixmk.minepreggo.item;


import net.minecraft.world.item.Rarity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class ActivatedGunpowderItem extends AbstractGunpowder {
	public ActivatedGunpowderItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON).food((new FoodProperties.Builder()).nutrition(1).saturationMod(0.1f).build()));
	}
}
