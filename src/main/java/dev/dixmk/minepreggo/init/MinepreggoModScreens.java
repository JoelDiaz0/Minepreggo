package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP0InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP0MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP1InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP1MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP2InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP2MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP3InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP3MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP0InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP0MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP1InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP1MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP2InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP2MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP3InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP3MainGUIScreen;
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
			/*Creeper Girl*/
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P0_MAIN_GUI.get(), CreeperGirlP0MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P0_INVENTARY_GUI.get(), CreeperGirlP0InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P1_MAIN_GUI.get(), CreeperGirlP1MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P1_INVENTARY_GUI.get(), CreeperGirlP1InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P2_MAIN_GUI.get(), CreeperGirlP2MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P2_INVENTARY_GUI.get(), CreeperGirlP2InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P3_MAIN_GUI.get(), CreeperGirlP3MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P3_INVENTARY_GUI.get(), CreeperGirlP3InventaryGUIScreen::new);
		});
	}
}
