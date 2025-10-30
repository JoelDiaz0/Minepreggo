package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.inventory.preggo.PreggoMobMedicalCheckUpGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.SelectPregnantEntityForMedicalCheckUpGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP0InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP0MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP1InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP1MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP2InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP2MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP3InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP3MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP4InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP4MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP5InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP5MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP6InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP6MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP7InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP7MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP8InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP8MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP0InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP0MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP1InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP1MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP2InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP2MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP3InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP3MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP4InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP4MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP5InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP5MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP6InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP6MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP7InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP7MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP8InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP8MainGUIMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoModMenus {

	private MinepreggoModMenus() {}
	

	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MinepreggoMod.MODID);

	public static final RegistryObject<MenuType<ZombieGirlP0MainGUIMenu>> ZOMBIE_GIRL_P0_MAIN_GUI = REGISTRY.register("zombie_girl_p0_main_gui", () -> IForgeMenuType.create(ZombieGirlP0MainGUIMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP0InventaryGUIMenu>> ZOMBIE_GIRL_P0_INVENTARY_GUI = REGISTRY.register("zombie_girl_p0_inventary_gui", () -> IForgeMenuType.create(ZombieGirlP0InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP1MainGUIMenu>> ZOMBIE_GIRL_P1_MAIN_GUI = REGISTRY.register("zombie_girl_p1_main_gui", () -> IForgeMenuType.create(ZombieGirlP1MainGUIMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP1InventaryGUIMenu>> ZOMBIE_GIRL_P1_INVENTARY_GUI = REGISTRY.register("zombie_girl_p1_inventary_gui", () -> IForgeMenuType.create(ZombieGirlP1InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP2MainGUIMenu>> ZOMBIE_GIRL_P2_MAIN_GUI = REGISTRY.register("zombie_girl_p2_main_gui", () -> IForgeMenuType.create(ZombieGirlP2MainGUIMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP2InventaryGUIMenu>> ZOMBIE_GIRL_P2_INVENTARY_GUI = REGISTRY.register("zombie_girl_p2_inventary_gui", () -> IForgeMenuType.create(ZombieGirlP2InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP3MainGUIMenu>> ZOMBIE_GIRL_P3_MAIN_GUI = REGISTRY.register("zombie_girl_p3_main_gui", () -> IForgeMenuType.create(ZombieGirlP3MainGUIMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP3InventaryGUIMenu>> ZOMBIE_GIRL_P3_INVENTARY_GUI = REGISTRY.register("zombie_girl_p3_inventary_gui", () -> IForgeMenuType.create(ZombieGirlP3InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP4MainGUIMenu>> ZOMBIE_GIRL_P4_MAIN_GUI = REGISTRY.register("zombie_girl_p4_main_gui", () -> IForgeMenuType.create(ZombieGirlP4MainGUIMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP4InventaryGUIMenu>> ZOMBIE_GIRL_P4_INVENTARY_GUI = REGISTRY.register("zombie_girl_p4_inventary_gui", () -> IForgeMenuType.create(ZombieGirlP4InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP5MainGUIMenu>> ZOMBIE_GIRL_P5_MAIN_GUI = REGISTRY.register("zombie_girl_p5_main_gui", () -> IForgeMenuType.create(ZombieGirlP5MainGUIMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP5InventaryGUIMenu>> ZOMBIE_GIRL_P5_INVENTARY_GUI = REGISTRY.register("zombie_girl_p5_inventary_gui", () -> IForgeMenuType.create(ZombieGirlP5InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP6MainGUIMenu>> ZOMBIE_GIRL_P6_MAIN_GUI = REGISTRY.register("zombie_girl_p6_main_gui", () -> IForgeMenuType.create(ZombieGirlP6MainGUIMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP6InventaryGUIMenu>> ZOMBIE_GIRL_P6_INVENTARY_GUI = REGISTRY.register("zombie_girl_p6_inventary_gui", () -> IForgeMenuType.create(ZombieGirlP6InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP7MainGUIMenu>> ZOMBIE_GIRL_P7_MAIN_GUI = REGISTRY.register("zombie_girl_p7_main_gui", () -> IForgeMenuType.create(ZombieGirlP7MainGUIMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP7InventaryGUIMenu>> ZOMBIE_GIRL_P7_INVENTARY_GUI = REGISTRY.register("zombie_girl_p7_inventary_gui", () -> IForgeMenuType.create(ZombieGirlP7InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP8MainGUIMenu>> ZOMBIE_GIRL_P8_MAIN_GUI = REGISTRY.register("zombie_girl_p8_main_gui", () -> IForgeMenuType.create(ZombieGirlP8MainGUIMenu::new));
	public static final RegistryObject<MenuType<ZombieGirlP8InventaryGUIMenu>> ZOMBIE_GIRL_P8_INVENTARY_GUI = REGISTRY.register("zombie_girl_p8_inventary_gui", () -> IForgeMenuType.create(ZombieGirlP8InventaryGUIMenu::new));
	
	public static final RegistryObject<MenuType<CreeperGirlP0MainGUIMenu>> CREEPER_GIRL_P0_MAIN_GUI = REGISTRY.register("creeper_girl_p0_main_gui", () -> IForgeMenuType.create(CreeperGirlP0MainGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP0InventaryGUIMenu>> CREEPER_GIRL_P0_INVENTARY_GUI = REGISTRY.register("creeper_girl_p0_inventary_gui", () -> IForgeMenuType.create(CreeperGirlP0InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP1MainGUIMenu>> CREEPER_GIRL_P1_MAIN_GUI = REGISTRY.register("creeper_girl_p1_main_gui", () -> IForgeMenuType.create(CreeperGirlP1MainGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP1InventaryGUIMenu>> CREEPER_GIRL_P1_INVENTARY_GUI = REGISTRY.register("creeper_girl_p1_inventary_gui", () -> IForgeMenuType.create(CreeperGirlP1InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP2MainGUIMenu>> CREEPER_GIRL_P2_MAIN_GUI = REGISTRY.register("creeper_girl_p2_main_gui", () -> IForgeMenuType.create(CreeperGirlP2MainGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP2InventaryGUIMenu>> CREEPER_GIRL_P2_INVENTARY_GUI = REGISTRY.register("creeper_girl_p2_inventary_gui", () -> IForgeMenuType.create(CreeperGirlP2InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP3MainGUIMenu>> CREEPER_GIRL_P3_MAIN_GUI = REGISTRY.register("creeper_girl_p3_main_gui", () -> IForgeMenuType.create(CreeperGirlP3MainGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP3InventaryGUIMenu>> CREEPER_GIRL_P3_INVENTARY_GUI = REGISTRY.register("creeper_girl_p3_inventary_gui", () -> IForgeMenuType.create(CreeperGirlP3InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP4MainGUIMenu>> CREEPER_GIRL_P4_MAIN_GUI = REGISTRY.register("creeper_girl_p4_main_gui", () -> IForgeMenuType.create(CreeperGirlP4MainGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP4InventaryGUIMenu>> CREEPER_GIRL_P4_INVENTARY_GUI = REGISTRY.register("creeper_girl_p4_inventary_gui", () -> IForgeMenuType.create(CreeperGirlP4InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP5MainGUIMenu>> CREEPER_GIRL_P5_MAIN_GUI = REGISTRY.register("creeper_girl_p5_main_gui", () -> IForgeMenuType.create(CreeperGirlP5MainGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP5InventaryGUIMenu>> CREEPER_GIRL_P5_INVENTARY_GUI = REGISTRY.register("creeper_girl_p5_inventary_gui", () -> IForgeMenuType.create(CreeperGirlP5InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP6MainGUIMenu>> CREEPER_GIRL_P6_MAIN_GUI = REGISTRY.register("creeper_girl_p6_main_gui", () -> IForgeMenuType.create(CreeperGirlP6MainGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP6InventaryGUIMenu>> CREEPER_GIRL_P6_INVENTARY_GUI = REGISTRY.register("creeper_girl_p6_inventary_gui", () -> IForgeMenuType.create(CreeperGirlP6InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP7MainGUIMenu>> CREEPER_GIRL_P7_MAIN_GUI = REGISTRY.register("creeper_girl_p7_main_gui", () -> IForgeMenuType.create(CreeperGirlP7MainGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP7InventaryGUIMenu>> CREEPER_GIRL_P7_INVENTARY_GUI = REGISTRY.register("creeper_girl_p7_inventary_gui", () -> IForgeMenuType.create(CreeperGirlP7InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP8MainGUIMenu>> CREEPER_GIRL_P8_MAIN_GUI = REGISTRY.register("creeper_girl_p8_main_gui", () -> IForgeMenuType.create(CreeperGirlP8MainGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP8InventaryGUIMenu>> CREEPER_GIRL_P8_INVENTARY_GUI = REGISTRY.register("creeper_girl_p8_inventary_gui", () -> IForgeMenuType.create(CreeperGirlP8InventaryGUIMenu::new));

	
	public static final RegistryObject<MenuType<SelectPregnantEntityForMedicalCheckUpGUIMenu>> SELECT_PREGNANT_ENTITY_FOR_MEDICAL_CHECKUP = REGISTRY.register("select_pregnant_entity_for_medical_checkup", () -> IForgeMenuType.create(SelectPregnantEntityForMedicalCheckUpGUIMenu::new));
	public static final RegistryObject<MenuType<PreggoMobMedicalCheckUpGUIMenu>> PREGGO_MOB_MEDICAL_CHECKUP = REGISTRY.register("preggo_mob_medical_checkup", () -> IForgeMenuType.create(PreggoMobMedicalCheckUpGUIMenu::new));

}
