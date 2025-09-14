package dev.dixmk.minepreggo.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;
import dev.dixmk.minepreggo.client.model.ZombieGirlP0Model;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class MinepreggoModModels {
	
	MinepreggoModModels() {} 
	
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ZombieGirlP0Model.LAYER_LOCATION, ZombieGirlP0Model::createMainBodyLayer);
		event.registerLayerDefinition(ZombieGirlP0Model.LAYER_INNER_ARMOR_LOCATION, ZombieGirlP0Model::createInnerLayer);
		event.registerLayerDefinition(ZombieGirlP0Model.LAYER_OUTER_ARMOR_LOCATION, ZombieGirlP0Model::createOuterLayer);
	}
	
}