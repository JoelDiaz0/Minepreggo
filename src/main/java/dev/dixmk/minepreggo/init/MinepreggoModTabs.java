package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoModTabs {
	
	private MinepreggoModTabs() {}
	
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MinepreggoMod.MODID);

    public static final RegistryObject<CreativeModeTab> MINEPREGGO_TAB = REGISTRY.register("minepreggo_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.literal("Minepreggo"))
            .icon(() -> MinepreggoModItems.LEMON.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(MinepreggoModItems.LEMON.get());
                output.accept(MinepreggoModItems.LEMON_ICE_CREAM.get());
                output.accept(MinepreggoModItems.CHOCOLATE_BAR.get());
                output.accept(MinepreggoModItems.CHILI_PEPPER.get());
                output.accept(MinepreggoModItems.CUCUMBER.get());
                output.accept(MinepreggoModItems.LEMON_ICE_POPSICLES.get());
                output.accept(MinepreggoModItems.SALT.get());
                output.accept(MinepreggoModItems.PICKLE.get());
                output.accept(MinepreggoModItems.SALTY_WATER_BOTTLE.get());
                output.accept(MinepreggoModItems.HOT_CHICKEN.get());
                output.accept(MinepreggoModItems.HOT_SAUCE.get());
                
                output.accept(MinepreggoModItems.BREAST_MILK_BOTTLE.get());
                output.accept(MinepreggoModItems.ROTTEN_BREAST_MILK_BOTTLE.get());
                output.accept(MinepreggoModItems.EXPLOSIVE_BREAST_MILK_BOTTLE.get());

                output.accept(MinepreggoModItems.VILLAGER_BRAIN.get());
                output.accept(MinepreggoModItems.PILLAGER_BRAIN.get());
                output.accept(MinepreggoModItems.WITCH_BRAIN.get());
                output.accept(MinepreggoModItems.BRAIN_WITH_CHOCOLATE.get());
                output.accept(MinepreggoModItems.BRAIN_WITH_SALT.get());
                output.accept(MinepreggoModItems.BRAIN_WITH_HOT_SAUCE.get());
                output.accept(MinepreggoModItems.SOUR_BRAIN.get());
               
                output.accept(MinepreggoModItems.ACTIVATED_GUNPOWDER.get());
                output.accept(MinepreggoModItems.ACTIVATED_GUNPOWDER_WITH_CHOCOLATE.get());
                output.accept(MinepreggoModItems.ACTIVATED_GUNPOWDER_WITH_SALT.get());
                output.accept(MinepreggoModItems.ACTIVATED_GUNPOWDER_WITH_HOT_SAUCE.get());
                output.accept(MinepreggoModItems.SOUR_ACTIVATED_GUNPOWDER.get());
               
                output.accept(MinepreggoModItems.FEMALE_CHAINMAIL_CHEST_PLATE_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_IRON_CHEST_PLATE_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_IRON_CHEST_PLATE_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_DIAMOND_CHEST_PLATE_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_NETHERITE_CHEST_PLATE_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_CHAINMAIL_CHEST_PLATE_P_1_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_IRON_CHESTPLATE_P_1_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_GOLDEN_CHEST_PLATE_P_1_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_DIAMOND_CHEST_PLATE_P_1_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_NETHERITE_CHEST_PLATE_P_1_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_CHAINMAIL_CHEST_PLATE_P_2_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_IRON_CHESTPLATE_P_2_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_GOLDEN_CHEST_PLATE_P_2_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_DIAMOND_CHEST_PLATE_P_2_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_NETHERITE_CHEST_PLATE_P_2_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_CHAINMAIL_CHEST_PLATE_P_3_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_IRON_CHESTPLATE_P_3_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_GOLDEN_CHEST_PLATE_P_3_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_DIAMOND_CHEST_PLATE_P_3_CHESTPLATE.get());
                output.accept(MinepreggoModItems.FEMALE_NETHERITE_CHEST_PLATE_P_3_CHESTPLATE.get());
                output.accept(MinepreggoModItems.IRON_KNEE_BRACE_LEGGINGS.get());
                output.accept(MinepreggoModItems.GOLD_KNEE_BRACE_LEGGINGS.get());
                output.accept(MinepreggoModItems.DIAMOND_KNEE_BRACE_LEGGINGS.get());
                output.accept(MinepreggoModItems.NETHERITE_KNEE_BRACE_LEGGINGS.get());
                output.accept(MinepreggoModItems.BELLY_SHIELD_P_4_CHESTPLATE.get());
                output.accept(MinepreggoModItems.BELLY_SHIELD_P_5_CHESTPLATE.get());
                output.accept(MinepreggoModItems.BELLY_SHIELD_P_6_CHESTPLATE.get());
                output.accept(MinepreggoModItems.BELLY_SHIELD_P_7_CHESTPLATE.get());
                
                output.accept(MinepreggoModItems.ZOMBIE_LIFE_SUBSTANCE.get());
                output.accept(MinepreggoModItems.SPECIMEN_TUBE.get());
                output.accept(MinepreggoModItems.CUM_SPECIMEN_TUBE.get());
               
                output.accept(MinepreggoModItems.BABY_HUMAN.get());
                output.accept(MinepreggoModItems.BABY_ZOMBIE.get());
                output.accept(MinepreggoModItems.BABY_HUMANOID_CREEPER.get());
                output.accept(MinepreggoModItems.BABY_QUADRUPED_CREEPER.get());
                output.accept(MinepreggoModItems.DEAD_HUMAN_FETUS.get());
                output.accept(MinepreggoModItems.DEAD_ZOMBIE_FETUS.get());
                output.accept(MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get());
                output.accept(MinepreggoModItems.DEAD_QUADRUPED_CREEPER_FETUS.get());

                output.accept(MinepreggoModItems.MONSTER_ZOMBIE_GIRL_P0_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.MONSTER_ZOMBIE_GIRL_P3_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.MONSTER_ZOMBIE_GIRL_P5_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.MONSTER_ZOMBIE_GIRL_P7_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_P0_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_P1_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_P2_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_P3_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_P4_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_P5_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_P6_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_P7_SPAWN_EGG.get());
                 
                output.accept(MinepreggoModItems.MONSTER_CREEPER_GIRL_P0_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.MONSTER_CREEPER_GIRL_P3_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.MONSTER_CREEPER_GIRL_P5_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.MONSTER_CREEPER_GIRL_P7_SPAWN_EGG.get());

                output.accept(MinepreggoModItems.TAMABLE_CREEPER_GIRL_P0_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_CREEPER_GIRL_P1_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_CREEPER_GIRL_P2_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_CREEPER_GIRL_P3_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_CREEPER_GIRL_P4_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_CREEPER_GIRL_P5_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_CREEPER_GIRL_P6_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_CREEPER_GIRL_P7_SPAWN_EGG.get());

            }).build());
}
