package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP0InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP0MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP1InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP1MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP2InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP2MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP3InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP3MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP4InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP4MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP5InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP5MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP6InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP6MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP7InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP7MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP0InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP0MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP1InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP1MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP2InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP2MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP3InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP3MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP4InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP4MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP5InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP5MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP6InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP6MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP7InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP7MainGUIScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MinepreggoModScreens {

	private MinepreggoModScreens() {}
	
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			/*Zombie Girl*/
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P0_MAIN_GUI.get(), ZombieGirlP0MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P0_INVENTARY_GUI.get(), ZombieGirlP0InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P1_MAIN_GUI.get(), ZombieGirlP1MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P1_INVENTARY_GUI.get(), ZombieGirlP1InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P2_MAIN_GUI.get(), ZombieGirlP2MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P2_INVENTARY_GUI.get(), ZombieGirlP2InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P3_MAIN_GUI.get(), ZombieGirlP3MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P3_INVENTARY_GUI.get(), ZombieGirlP3InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P4_MAIN_GUI.get(), ZombieGirlP4MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P4_INVENTARY_GUI.get(), ZombieGirlP4InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P5_MAIN_GUI.get(), ZombieGirlP5MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P5_INVENTARY_GUI.get(), ZombieGirlP5InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P6_MAIN_GUI.get(), ZombieGirlP6MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P6_INVENTARY_GUI.get(), ZombieGirlP6InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P7_MAIN_GUI.get(), ZombieGirlP7MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P7_INVENTARY_GUI.get(), ZombieGirlP7InventaryGUIScreen::new);
			
			/*Creeper Girl*/
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P0_MAIN_GUI.get(), CreeperGirlP0MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P0_INVENTARY_GUI.get(), CreeperGirlP0InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P1_MAIN_GUI.get(), CreeperGirlP1MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P1_INVENTARY_GUI.get(), CreeperGirlP1InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P2_MAIN_GUI.get(), CreeperGirlP2MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P2_INVENTARY_GUI.get(), CreeperGirlP2InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P3_MAIN_GUI.get(), CreeperGirlP3MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P3_INVENTARY_GUI.get(), CreeperGirlP3InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P4_MAIN_GUI.get(), CreeperGirlP4MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P4_INVENTARY_GUI.get(), CreeperGirlP4InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P5_MAIN_GUI.get(), CreeperGirlP5MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P5_INVENTARY_GUI.get(), CreeperGirlP5InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P6_MAIN_GUI.get(), CreeperGirlP6MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P6_INVENTARY_GUI.get(), CreeperGirlP6InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P7_MAIN_GUI.get(), CreeperGirlP7MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P7_INVENTARY_GUI.get(), CreeperGirlP7InventaryGUIScreen::new);
		});
	}
}
