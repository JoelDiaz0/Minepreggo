package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoModItems {
	
	private MinepreggoModItems() {}
	
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MinepreggoMod.MODID);
	
	public static final RegistryObject<Item> ZOMBIE_GIRL_SPAWN_EGG = REGISTRY.register("zombie_girl_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.ZOMBIE_GIRL, -1, -1, new Item.Properties()));

}