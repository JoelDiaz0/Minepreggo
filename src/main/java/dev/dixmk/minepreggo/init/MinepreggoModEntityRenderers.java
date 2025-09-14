package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.client.renderer.preggo.girl.zombie.ZombieGirlRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MinepreggoModEntityRenderers {

	private MinepreggoModEntityRenderers() {}
	
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(MinepreggoModEntities.ZOMBIE_GIRL.get(), ZombieGirlRenderer::new);
	}

}