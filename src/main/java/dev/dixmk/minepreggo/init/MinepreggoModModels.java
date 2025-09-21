package dev.dixmk.minepreggo.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP4Model;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP5Model;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP6Model;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP7Model;
import dev.dixmk.minepreggo.client.model.armor.FemaleChestPlateP0Model;
import dev.dixmk.minepreggo.client.model.armor.FemaleChestPlateP1Model;
import dev.dixmk.minepreggo.client.model.armor.FemaleChestPlateP2Model;
import dev.dixmk.minepreggo.client.model.armor.FemaleChestPlateP3Model;
import dev.dixmk.minepreggo.client.model.armor.KneeBraceModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractAnimatedCreeperGirlP0Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractAnimatedZombieGirlP0Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class MinepreggoModModels {
	
	MinepreggoModModels() {} 
	
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(AbstractAnimatedZombieGirlP0Model.LAYER_LOCATION, AbstractAnimatedZombieGirlP0Model::createBodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel::createInnerLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractZombieGirlModel::createOuterLayer);
		
		event.registerLayerDefinition(AbstractAnimatedCreeperGirlP0Model.LAYER_LOCATION, AbstractAnimatedCreeperGirlP0Model::createBodyLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractCreeperGirlModel::createInnerLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractCreeperGirlModel::createOuterLayer);
		
		event.registerLayerDefinition(BellyShieldP4Model.LAYER_LOCATION, BellyShieldP4Model::createBodyLayer);
		event.registerLayerDefinition(BellyShieldP5Model.LAYER_LOCATION, BellyShieldP5Model::createBodyLayer);
		event.registerLayerDefinition(BellyShieldP6Model.LAYER_LOCATION, BellyShieldP6Model::createBodyLayer);
		event.registerLayerDefinition(BellyShieldP7Model.LAYER_LOCATION, BellyShieldP7Model::createBodyLayer);
		event.registerLayerDefinition(FemaleChestPlateP0Model.LAYER_LOCATION, FemaleChestPlateP0Model::createBodyLayer);
		event.registerLayerDefinition(FemaleChestPlateP1Model.LAYER_LOCATION, FemaleChestPlateP1Model::createBodyLayer);
		event.registerLayerDefinition(FemaleChestPlateP2Model.LAYER_LOCATION, FemaleChestPlateP2Model::createBodyLayer);
		event.registerLayerDefinition(FemaleChestPlateP3Model.LAYER_LOCATION, FemaleChestPlateP3Model::createBodyLayer);
		event.registerLayerDefinition(KneeBraceModel.LAYER_LOCATION, KneeBraceModel::createBodyLayer);

	}
}