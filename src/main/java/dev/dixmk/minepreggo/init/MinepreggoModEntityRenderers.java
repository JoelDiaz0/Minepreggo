package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.client.renderer.preggo.zombie.MonsterZombieGirlP0Renderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MinepreggoModEntityRenderers {

	private MinepreggoModEntityRenderers() {}
	
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P0.get(), MonsterZombieGirlP0Renderer::new);
	}

}