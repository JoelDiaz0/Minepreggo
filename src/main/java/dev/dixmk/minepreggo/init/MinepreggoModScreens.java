package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP0InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.creeper.CreeperGirlP0MainGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP0InventaryGUIScreen;
import dev.dixmk.minepreggo.client.gui.preggo.zombie.ZombieGirlP0MainGUIScreen;
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
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P0_MAIN_GUI.get(), ZombieGirlP0MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.ZOMBIE_GIRL_P0_INVENTARY_GUI.get(), ZombieGirlP0InventaryGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P0_MAIN_GUI.get(), CreeperGirlP0MainGUIScreen::new);
			MenuScreens.register(MinepreggoModMenus.CREEPER_GIRL_P0_INVENTARY_GUI.get(), CreeperGirlP0InventaryGUIScreen::new);

		});
	}
	
}
