package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.item.ActivatedGunpowderItem;
import dev.dixmk.minepreggo.item.ActivatedGunpowderWithChocolateItem;
import dev.dixmk.minepreggo.item.ActivatedGunpowderWithHotSauceItem;
import dev.dixmk.minepreggo.item.ActivatedGunpowderWithSaltItem;
import dev.dixmk.minepreggo.item.BabyHumanItem;
import dev.dixmk.minepreggo.item.BabyHumanoidCreeperItem;
import dev.dixmk.minepreggo.item.BabyQuadrupedCreeperItem;
import dev.dixmk.minepreggo.item.BabyZombieItem;
import dev.dixmk.minepreggo.item.BreastMilkBottleItem;
import dev.dixmk.minepreggo.item.ChocolateBarItem;
import dev.dixmk.minepreggo.item.CumSpecimenTubeItem;
import dev.dixmk.minepreggo.item.DeadHumanFetusItem;
import dev.dixmk.minepreggo.item.DeadHumanoidCreeperFetusItem;
import dev.dixmk.minepreggo.item.DeadQuadrupedCreeperFetusItem;
import dev.dixmk.minepreggo.item.DeadZombieFetusItem;
import dev.dixmk.minepreggo.item.ExplosiveBreastMilkBottleItem;
import dev.dixmk.minepreggo.item.HotChickenItem;
import dev.dixmk.minepreggo.item.HotSauceItem;
import dev.dixmk.minepreggo.item.LemonIceCreamItem;
import dev.dixmk.minepreggo.item.LemonIcePopsiclesItem;
import dev.dixmk.minepreggo.item.LemonItem;
import dev.dixmk.minepreggo.item.PickleItem;
import dev.dixmk.minepreggo.item.BrainWithChocolateItem;
import dev.dixmk.minepreggo.item.BrainWithHotSauceItem;
import dev.dixmk.minepreggo.item.BrainWithSaltItem;
import dev.dixmk.minepreggo.item.PillagerBrainItem;
import dev.dixmk.minepreggo.item.RottenBreastMilkBottleItem;
import dev.dixmk.minepreggo.item.SaltItem;
import dev.dixmk.minepreggo.item.SaltyWaterBottleItem;
import dev.dixmk.minepreggo.item.SourActivatedGunpowderItem;
import dev.dixmk.minepreggo.item.SourBrainItem;
import dev.dixmk.minepreggo.item.SpecimenTubeItem;
import dev.dixmk.minepreggo.item.VillagerBrainItem;
import dev.dixmk.minepreggo.item.WitchBrainItem;
import dev.dixmk.minepreggo.item.ZombieLifeSubstanceItem;
import dev.dixmk.minepreggo.item.armor.BellyShieldP4Item;
import dev.dixmk.minepreggo.item.armor.BellyShieldP5Item;
import dev.dixmk.minepreggo.item.armor.BellyShieldP6Item;
import dev.dixmk.minepreggo.item.armor.BellyShieldP7Item;
import dev.dixmk.minepreggo.item.armor.DiamondKneeBraceItem;
import dev.dixmk.minepreggo.item.armor.FemaleChainmailChestPlateItem;
import dev.dixmk.minepreggo.item.armor.FemaleChainmailChestPlateP1Item;
import dev.dixmk.minepreggo.item.armor.FemaleChainmailChestPlateP2Item;
import dev.dixmk.minepreggo.item.armor.FemaleChainmailChestPlateP3Item;
import dev.dixmk.minepreggo.item.armor.FemaleDiamondChestPlateItem;
import dev.dixmk.minepreggo.item.armor.FemaleDiamondChestPlateP1Item;
import dev.dixmk.minepreggo.item.armor.FemaleDiamondChestPlateP2Item;
import dev.dixmk.minepreggo.item.armor.FemaleDiamondChestPlateP3Item;
import dev.dixmk.minepreggo.item.armor.FemaleGoldenChestPlateItem;
import dev.dixmk.minepreggo.item.armor.FemaleGoldenChestPlateP1Item;
import dev.dixmk.minepreggo.item.armor.FemaleGoldenChestPlateP2Item;
import dev.dixmk.minepreggo.item.armor.FemaleGoldenChestPlateP3Item;
import dev.dixmk.minepreggo.item.armor.FemaleIronChestPlateItem;
import dev.dixmk.minepreggo.item.armor.FemaleIronChestplateP1Item;
import dev.dixmk.minepreggo.item.armor.FemaleIronChestplateP2Item;
import dev.dixmk.minepreggo.item.armor.FemaleIronChestplateP3Item;
import dev.dixmk.minepreggo.item.armor.FemaleNetheriteChestPlateItem;
import dev.dixmk.minepreggo.item.armor.FemaleNetheriteChestPlateP1Item;
import dev.dixmk.minepreggo.item.armor.FemaleNetheriteChestPlateP2Item;
import dev.dixmk.minepreggo.item.armor.FemaleNetheriteChestPlateP3Item;
import dev.dixmk.minepreggo.item.armor.GoldKneeBraceItem;
import dev.dixmk.minepreggo.item.armor.IronKneeBraceItem;
import dev.dixmk.minepreggo.item.armor.NetheriteKneeBraceItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoModItems {
	
	private MinepreggoModItems() {}
	
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MinepreggoMod.MODID);
	
	public static final RegistryObject<Item> MONSTER_ZOMBIE_GIRL_SPAWN_EGG = REGISTRY.register("monster_zombie_girl_p0_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P0, -16724839, -16738048, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_ZOMBIE_GIRL_SPAWN_EGG = REGISTRY.register("tamable_zombie_girl_p0_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0, -16724839, -16738048, new Item.Properties()));

	public static final RegistryObject<Item> MONSTER_CREEPER_GIRL_P0_SPAWN_EGG = REGISTRY.register("monster_creeper_girl_p0_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P0, -16738048, -26368, new Item.Properties()));
	public static final RegistryObject<Item> TAMABLE_CREEPER_GIRL_P0_SPAWN_EGG = REGISTRY.register("tamable_creeper_girl_p0_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0, -16738048, -26368, new Item.Properties()));

	
	
	/*
	 
	 * 	public static final RegistryObject<Item> ZOMBIE_GIRL_P_0_SPAWN_EGG = REGISTRY.register("zombie_girl_p_0_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.ZOMBIE_GIRL_P_0, -16724839, -16738048, new Item.Properties()));
	public static final RegistryObject<Item> ZOMBIE_GIRL_P_1_SPAWN_EGG = REGISTRY.register("zombie_girl_p_1_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.ZOMBIE_GIRL_P_1, -16724839, -16738048, new Item.Properties()));
	public static final RegistryObject<Item> ZOMBIE_GIRL_P_2_SPAWN_EGG = REGISTRY.register("zombie_girl_p_2_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.ZOMBIE_GIRL_P_2, -16724839, -16738048, new Item.Properties()));


	public static final RegistryObject<Item> ZOMBIE_GIRL_P_0_POST_MISCARRIAGE_SPAWN_EGG = REGISTRY.register("zombie_girl_p_0_post_miscarriage_spawn_egg",
			() -> new ForgeSpawnEggItem(MinepreggoModEntities.ZOMBIE_GIRL_P_0_POST_MISCARRIAGE, -16724839, -16738048, new Item.Properties()));
	public static final RegistryObject<Item> ZOMBIE_GIRL_P_0_POSTPARTUM_SPAWN_EGG = REGISTRY.register("zombie_girl_p_0_postpartum_spawn_egg",
			() -> new ForgeSpawnEggItem(MinepreggoModEntities.ZOMBIE_GIRL_P_0_POSTPARTUM, -16724839, -16738048, new Item.Properties()));
	
	public static final RegistryObject<Item> ZOMBIE_GIRL_P_3_SPAWN_EGG = REGISTRY.register("zombie_girl_p_3_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.ZOMBIE_GIRL_P_3, -16724839, -16738048, new Item.Properties()));
	public static final RegistryObject<Item> ZOMBIE_GIRL_P_4_SPAWN_EGG = REGISTRY.register("zombie_girl_p_4_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.ZOMBIE_GIRL_P_4, -16724839, -16738048, new Item.Properties()));
	public static final RegistryObject<Item> ZOMBIE_GIRL_P_5_SPAWN_EGG = REGISTRY.register("zombie_girl_p_5_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.ZOMBIE_GIRL_P_5, -16724839, -16738048, new Item.Properties()));
	public static final RegistryObject<Item> ZOMBIE_GIRL_P_6_SPAWN_EGG = REGISTRY.register("zombie_girl_p_6_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.ZOMBIE_GIRL_P_6, -16724839, -16738048, new Item.Properties()));
	public static final RegistryObject<Item> ZOMBIE_GIRL_P_7_SPAWN_EGG = REGISTRY.register("zombie_girl_p_7_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.ZOMBIE_GIRL_P_7, -16724839, -16738048, new Item.Properties()));
	
		public static final RegistryObject<Item> CREEPER_GIRL_P_1_SPAWN_EGG = REGISTRY.register("creeper_girl_p_1_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.CREEPER_GIRL_P_1, -16738048, -26368, new Item.Properties()));
	public static final RegistryObject<Item> CREEPER_GIRL_P_2_SPAWN_EGG = REGISTRY.register("creeper_girl_p_2_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.CREEPER_GIRL_P_2, -16738048, -26368, new Item.Properties()));
	public static final RegistryObject<Item> CREEPER_GIRL_P_3_SPAWN_EGG = REGISTRY.register("creeper_girl_p_3_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.CREEPER_GIRL_P_3, -16738048, -26368, new Item.Properties()));
	public static final RegistryObject<Item> CREEPER_GIRL_P_4_SPAWN_EGG = REGISTRY.register("creeper_girl_p_4_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.CREEPER_GIRL_P_4, -16738048, -26368, new Item.Properties()));
	public static final RegistryObject<Item> CREEPER_GIRL_P_5_SPAWN_EGG = REGISTRY.register("creeper_girl_p_5_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.CREEPER_GIRL_P_5, -16738048, -26368, new Item.Properties()));
	public static final RegistryObject<Item> CREEPER_GIRL_P_6_SPAWN_EGG = REGISTRY.register("creeper_girl_p_6_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.CREEPER_GIRL_P_6, -16738048, -26368, new Item.Properties()));
	public static final RegistryObject<Item> CREEPER_GIRL_P_7_SPAWN_EGG = REGISTRY.register("creeper_girl_p_7_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.CREEPER_GIRL_P_7, -16738048, -26368, new Item.Properties()));
	public static final RegistryObject<Item> CREEPER_GIRL_P_0_POST_MISCARRIAGE_SPAWN_EGG = REGISTRY.register("creeper_girl_p_0_post_miscarriage_spawn_egg",
			() -> new ForgeSpawnEggItem(MinepreggoModEntities.CREEPER_GIRL_P_0_POST_MISCARRIAGE, -16738048, -26368, new Item.Properties()));
	public static final RegistryObject<Item> CREEPER_GIRL_P_0_POSTPARTUM_SPAWN_EGG = REGISTRY.register("creeper_girl_p_0_postpartum_spawn_egg",
			() -> new ForgeSpawnEggItem(MinepreggoModEntities.CREEPER_GIRL_P_0_POSTPARTUM, -16738048, -26368, new Item.Properties()));
	
	public static final RegistryObject<Item> FERTILITY_WITCH_SPAWN_EGG = REGISTRY.register("fertility_witch_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.FERTILITY_WITCH, -26113, -3407668, new Item.Properties()));
	public static final RegistryObject<Item> M_ZOMBIE_GIRL_P_3_SPAWN_EGG = REGISTRY.register("m_zombie_girl_p_3_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.M_ZOMBIE_GIRL_P_3, -16751053, -16751002, new Item.Properties()));
	public static final RegistryObject<Item> M_ZOMBIE_GIRL_P_5_SPAWN_EGG = REGISTRY.register("m_zombie_girl_p_5_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.M_ZOMBIE_GIRL_P_5, -16751053, -16751002, new Item.Properties()));
	public static final RegistryObject<Item> M_ZOMBIE_GIRL_P_7_SPAWN_EGG = REGISTRY.register("m_zombie_girl_p_7_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.M_ZOMBIE_GIRL_P_7, -16751053, -16751002, new Item.Properties()));
	public static final RegistryObject<Item> M_CREEPER_GIRL_P_3_SPAWN_EGG = REGISTRY.register("m_creeper_girl_p_3_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.M_CREEPER_GIRL_P_3, -16711885, -39424, new Item.Properties()));
	public static final RegistryObject<Item> M_CREEPER_GIRL_P_5_SPAWN_EGG = REGISTRY.register("m_creeper_girl_p_5_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.M_CREEPER_GIRL_P_5, -16711885, -39424, new Item.Properties()));
	public static final RegistryObject<Item> M_CREEPER_GIRL_P_7_SPAWN_EGG = REGISTRY.register("m_creeper_girl_p_7_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.M_CREEPER_GIRL_P_7, -16711885, -39424, new Item.Properties()));
	public static final RegistryObject<Item> HUMANOID_CREEPER_LIFE_SUBSTANCE = REGISTRY.register("humanoid_creeper_life_substance", () -> new HumanoidCreeperLifeSubstanceItem());
	
	public static final RegistryObject<Item> ILLAGER_SCIENTIFIC_SPAWN_EGG = REGISTRY.register("illager_scientific_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.ILLAGER_SCIENTIFIC, -10066330, -13421773, new Item.Properties()));
	public static final RegistryObject<Item> ILL_ENDER_GIRL_SPAWN_EGG = REGISTRY.register("ill_ender_girl_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.ILL_ENDER_GIRL, -1, -1, new Item.Properties()));
	public static final RegistryObject<Item> ILL_QUADRUPED_CREEPER_GIRL_SPAWN_EGG = REGISTRY.register("ill_quadruped_creeper_girl_spawn_egg",
			() -> new ForgeSpawnEggItem(MinepreggoModEntities.ILL_QUADRUPED_CREEPER_GIRL, -1, -1, new Item.Properties()));
	
		public static final RegistryObject<Item> CREEPER_GIRL_SPAWN_EGG = REGISTRY.register("creeper_girl_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.CREEPER_GIRL, -16738048, -26368, new Item.Properties()));
	public static final RegistryObject<Item> CREEPER_GIRL_P_0_SPAWN_EGG = REGISTRY.register("creeper_girl_p_0_spawn_egg", () -> new ForgeSpawnEggItem(MinepreggoModEntities.CREEPER_GIRL_P_0, -16738048, -26368, new Item.Properties()));

	
	*/
	
	
	
	public static final RegistryObject<Item> LEMON = REGISTRY.register("lemon", LemonItem::new);
	public static final RegistryObject<Item> LEMON_ICE_CREAM = REGISTRY.register("lemon_ice_cream", LemonIceCreamItem::new);
	public static final RegistryObject<Item> CHOCOLATE_BAR = REGISTRY.register("chocolate_bar", ChocolateBarItem::new);
	
	public static final RegistryObject<Item> CHILI_PEPPER = REGISTRY.register("chili_pepper", () -> new ItemNameBlockItem(MinepreggoModBlocks.CHILIPEPPERS.get(), (new Item.Properties().stacksTo(64).rarity(Rarity.COMMON))));
	public static final RegistryObject<Item> CUCUMBER = REGISTRY.register("cucumber", () -> new ItemNameBlockItem(MinepreggoModBlocks.CUCUMBERS.get(), (new Item.Properties().stacksTo(64).rarity(Rarity.COMMON))));

	public static final RegistryObject<Item> LEMON_ICE_POPSICLES = REGISTRY.register("lemon_ice_popsicles", LemonIcePopsiclesItem::new);
	public static final RegistryObject<Item> SALT = REGISTRY.register("salt", SaltItem::new);
	public static final RegistryObject<Item> HOT_CHICKEN = REGISTRY.register("hot_chicken", HotChickenItem::new);
	public static final RegistryObject<Item> BREAST_MILK_BOTTLE = REGISTRY.register("breast_milk_bottle", BreastMilkBottleItem::new);
	public static final RegistryObject<Item> VILLAGER_BRAIN = REGISTRY.register("villager_brain", VillagerBrainItem::new);
	public static final RegistryObject<Item> PILLAGER_BRAIN = REGISTRY.register("pillager_brain", PillagerBrainItem::new);
	public static final RegistryObject<Item> WITCH_BRAIN = REGISTRY.register("witch_brain", WitchBrainItem::new);
	public static final RegistryObject<Item> DEAD_HUMAN_FETUS = REGISTRY.register("dead_human_fetus", DeadHumanFetusItem::new);
	public static final RegistryObject<Item> DEAD_ZOMBIE_FETUS = REGISTRY.register("dead_zombie_fetus", DeadZombieFetusItem::new);
	public static final RegistryObject<Item> ACTIVATED_GUNPOWDER = REGISTRY.register("activated_gunpowder", ActivatedGunpowderItem::new);
	public static final RegistryObject<Item> BRAIN_WITH_CHOCOLATE = REGISTRY.register("brain_with_chocolate", BrainWithChocolateItem::new);
	public static final RegistryObject<Item> BRAIN_WITH_SALT = REGISTRY.register("brain_with_salt", BrainWithSaltItem::new);
	public static final RegistryObject<Item> BRAIN_WITH_HOT_SAUCE = REGISTRY.register("brain_with_hot_sauce", BrainWithHotSauceItem::new);
	public static final RegistryObject<Item> SOUR_BRAIN = REGISTRY.register("sour_brain", SourBrainItem::new);
	public static final RegistryObject<Item> ACTIVATED_GUNPOWDER_WITH_CHOCOLATE = REGISTRY.register("activated_gunpowder_with_chocolate", ActivatedGunpowderWithChocolateItem::new);
	public static final RegistryObject<Item> ACTIVATED_GUNPOWDER_WITH_SALT = REGISTRY.register("activated_gunpowder_with_salt", ActivatedGunpowderWithSaltItem::new);
	public static final RegistryObject<Item> ACTIVATED_GUNPOWDER_WITH_HOT_SAUCE = REGISTRY.register("activated_gunpowder_with_hot_sauce", ActivatedGunpowderWithHotSauceItem::new);
	public static final RegistryObject<Item> SOUR_ACTIVATED_GUNPOWDER = REGISTRY.register("sour_activated_gunpowder", SourActivatedGunpowderItem::new);
	public static final RegistryObject<Item> HOT_SAUCE = REGISTRY.register("hot_sauce", HotSauceItem::new);

	public static final RegistryObject<Item> FEMALE_CHAINMAIL_CHEST_PLATE_CHESTPLATE = REGISTRY.register("female_chainmail_chest_plate_chestplate", FemaleChainmailChestPlateItem.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_IRON_CHEST_PLATE_CHESTPLATE = REGISTRY.register("female_iron_chest_plate_chestplate", FemaleIronChestPlateItem.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_GOLDEN_CHEST_PLATE_CHESTPLATE = REGISTRY.register("female_golden_chest_plate_chestplate", FemaleGoldenChestPlateItem.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_DIAMOND_CHEST_PLATE_CHESTPLATE = REGISTRY.register("female_diamond_chest_plate_chestplate", FemaleDiamondChestPlateItem.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_NETHERITE_CHEST_PLATE_CHESTPLATE = REGISTRY.register("female_netherite_chest_plate_chestplate", FemaleNetheriteChestPlateItem.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_CHAINMAIL_CHEST_PLATE_P_1_CHESTPLATE = REGISTRY.register("female_chainmail_chest_plate_p1_chestplate", FemaleChainmailChestPlateP1Item.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_IRON_CHESTPLATE_P_1_CHESTPLATE = REGISTRY.register("female_iron_chestplate_p1_chestplate", FemaleIronChestplateP1Item.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_GOLDEN_CHEST_PLATE_P_1_CHESTPLATE = REGISTRY.register("female_golden_chest_plate_p1_chestplate", FemaleGoldenChestPlateP1Item.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_DIAMOND_CHEST_PLATE_P_1_CHESTPLATE = REGISTRY.register("female_diamond_chest_plate_p1_chestplate", FemaleDiamondChestPlateP1Item.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_NETHERITE_CHEST_PLATE_P_1_CHESTPLATE = REGISTRY.register("female_netherite_chest_plate_p1_chestplate", FemaleNetheriteChestPlateP1Item.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_CHAINMAIL_CHEST_PLATE_P_2_CHESTPLATE = REGISTRY.register("female_chainmail_chest_plate_p2_chestplate", FemaleChainmailChestPlateP2Item.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_IRON_CHESTPLATE_P_2_CHESTPLATE = REGISTRY.register("female_iron_chestplate_p2_chestplate", FemaleIronChestplateP2Item.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_GOLDEN_CHEST_PLATE_P_2_CHESTPLATE = REGISTRY.register("female_golden_chest_plate_p2_chestplate", FemaleGoldenChestPlateP2Item.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_DIAMOND_CHEST_PLATE_P_2_CHESTPLATE = REGISTRY.register("female_diamond_chest_plate_p2_chestplate", FemaleDiamondChestPlateP2Item.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_NETHERITE_CHEST_PLATE_P_2_CHESTPLATE = REGISTRY.register("female_netherite_chest_plate_p2_chestplate", FemaleNetheriteChestPlateP2Item.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_CHAINMAIL_CHEST_PLATE_P_3_CHESTPLATE = REGISTRY.register("female_chainmail_chest_plate_p3_chestplate", FemaleChainmailChestPlateP3Item.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_IRON_CHESTPLATE_P_3_CHESTPLATE = REGISTRY.register("female_iron_chestplate_p3_chestplate", FemaleIronChestplateP3Item.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_GOLDEN_CHEST_PLATE_P_3_CHESTPLATE = REGISTRY.register("female_golden_chest_plate_p3_chestplate", FemaleGoldenChestPlateP3Item.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_DIAMOND_CHEST_PLATE_P_3_CHESTPLATE = REGISTRY.register("female_diamond_chest_plate_p3_chestplate", FemaleDiamondChestPlateP3Item.Chestplate::new);
	public static final RegistryObject<Item> FEMALE_NETHERITE_CHEST_PLATE_P_3_CHESTPLATE = REGISTRY.register("female_netherite_chest_plate_p3_chestplate", FemaleNetheriteChestPlateP3Item.Chestplate::new);
	public static final RegistryObject<Item> IRON_KNEE_BRACE_LEGGINGS = REGISTRY.register("iron_knee_brace_leggings", IronKneeBraceItem.Leggings::new);
	public static final RegistryObject<Item> GOLD_KNEE_BRACE_LEGGINGS = REGISTRY.register("gold_knee_brace_leggings", GoldKneeBraceItem.Leggings::new);
	public static final RegistryObject<Item> DIAMOND_KNEE_BRACE_LEGGINGS = REGISTRY.register("diamond_knee_brace_leggings", DiamondKneeBraceItem.Leggings::new);
	public static final RegistryObject<Item> NETHERITE_KNEE_BRACE_LEGGINGS = REGISTRY.register("netherite_knee_brace_leggings", NetheriteKneeBraceItem.Leggings::new);
	
	public static final RegistryObject<Item> BELLY_SHIELD_P_4_CHESTPLATE = REGISTRY.register("belly_shield_p4_chestplate", BellyShieldP4Item.Chestplate::new);
	public static final RegistryObject<Item> BELLY_SHIELD_P_5_CHESTPLATE = REGISTRY.register("belly_shield_p5_chestplate", BellyShieldP5Item.Chestplate::new);
	public static final RegistryObject<Item> BELLY_SHIELD_P_6_CHESTPLATE = REGISTRY.register("belly_shield_p6_chestplate", BellyShieldP6Item.Chestplate::new);
	public static final RegistryObject<Item> BELLY_SHIELD_P_7_CHESTPLATE = REGISTRY.register("belly_shield_p7_chestplate", BellyShieldP7Item.Chestplate::new);

	public static final RegistryObject<Item> ROTTEN_BREAST_MILK_BOTTLE = REGISTRY.register("rotten_breast_milk_bottle", RottenBreastMilkBottleItem::new);
	public static final RegistryObject<Item> EXPLOSIVE_BREAST_MILK_BOTTLE = REGISTRY.register("explosive_breast_milk_bottle", ExplosiveBreastMilkBottleItem::new);
	
	public static final RegistryObject<Item> PICKLE = REGISTRY.register("pickle", PickleItem::new);
	public static final RegistryObject<Item> DEAD_HUMANOID_CREEPER_FETUS = REGISTRY.register("dead_humanoid_creeper_fetus", DeadHumanoidCreeperFetusItem::new);
	public static final RegistryObject<Item> DEAD_QUADRUPED_CREEPER_FETUS = REGISTRY.register("dead_quadruped_creeper_fetus", DeadQuadrupedCreeperFetusItem::new);
	public static final RegistryObject<Item> BABY_HUMAN = REGISTRY.register("baby_human", BabyHumanItem::new);
	public static final RegistryObject<Item> BABY_ZOMBIE = REGISTRY.register("baby_zombie", BabyZombieItem::new);
	public static final RegistryObject<Item> BABY_HUMANOID_CREEPER = REGISTRY.register("baby_humanoid_creeper", BabyHumanoidCreeperItem::new);
	public static final RegistryObject<Item> BABY_QUADRUPED_CREEPER = REGISTRY.register("baby_quadruped_creeper", BabyQuadrupedCreeperItem::new);
	public static final RegistryObject<Item> SPECIMEN_TUBE = REGISTRY.register("specimen_tube", SpecimenTubeItem::new);
	public static final RegistryObject<Item> CUM_SPECIMEN_TUBE = REGISTRY.register("cum_specimen_tube", CumSpecimenTubeItem::new);
	public static final RegistryObject<Item> ZOMBIE_LIFE_SUBSTANCE = REGISTRY.register("zombie_life_substance", ZombieLifeSubstanceItem::new);
	public static final RegistryObject<Item> SALTY_WATER_BOTTLE = REGISTRY.register("salty_water_bottle", SaltyWaterBottleItem::new);
	

}