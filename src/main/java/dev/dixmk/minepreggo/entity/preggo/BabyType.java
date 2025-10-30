package dev.dixmk.minepreggo.entity.preggo;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.init.MinepreggoModItems;
import net.minecraft.world.item.Item;

public enum BabyType {
    ZOMBIE,
    HUMANOID_CREEPER,
    QUADRUPED_CREEPER,
    HUMAN,
	ENDER;
    
	protected static final ImmutableMap<BabyType, Item> ALIVE_BABIES = ImmutableMap.of(
			BabyType.HUMAN, MinepreggoModItems.BABY_HUMAN.get(), 
			BabyType.ZOMBIE, MinepreggoModItems.BABY_ZOMBIE.get(), 
			BabyType.HUMANOID_CREEPER, MinepreggoModItems.BABY_HUMANOID_CREEPER.get(),
			BabyType.QUADRUPED_CREEPER, MinepreggoModItems.BABY_QUADRUPED_CREEPER.get());

	protected static final ImmutableMap<BabyType, Item> DEAD_BABIES = ImmutableMap.of(
			BabyType.HUMAN, MinepreggoModItems.DEAD_HUMAN_FETUS.get(), 
			BabyType.ZOMBIE, MinepreggoModItems.DEAD_ZOMBIE_FETUS.get(), 
			BabyType.HUMANOID_CREEPER, MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get(),
			BabyType.QUADRUPED_CREEPER, MinepreggoModItems.DEAD_QUADRUPED_CREEPER_FETUS.get());

	public static Item getDeadBabyItem(BabyType babyType) {
		return DEAD_BABIES.get(babyType);
	}
	
	public static Item getAliveBabyItem(BabyType babyType) {
		return ALIVE_BABIES.get(babyType);
	}
}
