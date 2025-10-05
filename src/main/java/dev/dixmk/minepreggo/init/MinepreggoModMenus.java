package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP0InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP0MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP1InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP1MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP2InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP2MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP0InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP0MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP1InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP1MainGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP2InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP2MainGUIMenu;
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
	
	public static final RegistryObject<MenuType<CreeperGirlP0MainGUIMenu>> CREEPER_GIRL_P0_MAIN_GUI = REGISTRY.register("creeper_girl_p0_main_gui", () -> IForgeMenuType.create(CreeperGirlP0MainGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP0InventaryGUIMenu>> CREEPER_GIRL_P0_INVENTARY_GUI = REGISTRY.register("creeper_girl_p0_inventary_gui", () -> IForgeMenuType.create(CreeperGirlP0InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP1MainGUIMenu>> CREEPER_GIRL_P1_MAIN_GUI = REGISTRY.register("creeper_girl_p1_main_gui", () -> IForgeMenuType.create(CreeperGirlP1MainGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP1InventaryGUIMenu>> CREEPER_GIRL_P1_INVENTARY_GUI = REGISTRY.register("creeper_girl_p1_inventary_gui", () -> IForgeMenuType.create(CreeperGirlP1InventaryGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP2MainGUIMenu>> CREEPER_GIRL_P2_MAIN_GUI = REGISTRY.register("creeper_girl_p2_main_gui", () -> IForgeMenuType.create(CreeperGirlP2MainGUIMenu::new));
	public static final RegistryObject<MenuType<CreeperGirlP2InventaryGUIMenu>> CREEPER_GIRL_P2_INVENTARY_GUI = REGISTRY.register("creeper_girl_p2_inventary_gui", () -> IForgeMenuType.create(CreeperGirlP2InventaryGUIMenu::new));
}
