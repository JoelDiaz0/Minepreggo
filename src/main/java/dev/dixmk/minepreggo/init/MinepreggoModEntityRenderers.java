package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterCreeperGirlP0Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP0Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.MonsterZombieGirlP0Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP0Renderer;
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
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get(), TamableZombieGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P0.get(), MonsterCreeperGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0.get(), TamableCreeperGirlP0Renderer::new);
	}

}