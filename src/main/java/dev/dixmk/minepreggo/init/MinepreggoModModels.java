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
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractAnimatedCreeperGirlP1Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractAnimatedCreeperGirlP2Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractAnimatedCreeperGirlP3Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractAnimatedCreeperGirlP4Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractAnimatedCreeperGirlP5Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractAnimatedCreeperGirlP6Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractAnimatedCreeperGirlP7Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractAnimatedZombieGirlP0Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractAnimatedZombieGirlP1Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractAnimatedZombieGirlP2Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractAnimatedZombieGirlP3Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractAnimatedZombieGirlP4Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractAnimatedZombieGirlP5Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractAnimatedZombieGirlP6Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractAnimatedZombieGirlP7Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class MinepreggoModModels {
	
	MinepreggoModModels() {} 
	
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		
		/*Zombie Girl*/
		event.registerLayerDefinition(AbstractAnimatedZombieGirlP0Model.LAYER_LOCATION, AbstractAnimatedZombieGirlP0Model::createBodyLayer);
		event.registerLayerDefinition(AbstractAnimatedZombieGirlP1Model.LAYER_LOCATION, AbstractAnimatedZombieGirlP1Model::createBodyLayer);
		event.registerLayerDefinition(AbstractAnimatedZombieGirlP2Model.LAYER_LOCATION, AbstractAnimatedZombieGirlP2Model::createBodyLayer);
		event.registerLayerDefinition(AbstractAnimatedZombieGirlP3Model.LAYER_LOCATION, AbstractAnimatedZombieGirlP3Model::createBodyLayer);
		event.registerLayerDefinition(AbstractAnimatedZombieGirlP4Model.LAYER_LOCATION, AbstractAnimatedZombieGirlP4Model::createBodyLayer);
		event.registerLayerDefinition(AbstractAnimatedZombieGirlP5Model.LAYER_LOCATION, AbstractAnimatedZombieGirlP5Model::createBodyLayer);
		event.registerLayerDefinition(AbstractAnimatedZombieGirlP6Model.LAYER_LOCATION, AbstractAnimatedZombieGirlP6Model::createBodyLayer);
		event.registerLayerDefinition(AbstractAnimatedZombieGirlP7Model.LAYER_LOCATION, AbstractAnimatedZombieGirlP7Model::createBodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel::createInnerLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractZombieGirlModel::createOuterLayer);
		
		/*Creeper Girl*/
		event.registerLayerDefinition(AbstractAnimatedCreeperGirlP0Model.LAYER_LOCATION, AbstractAnimatedCreeperGirlP0Model::createBodyLayer);
		event.registerLayerDefinition(AbstractAnimatedCreeperGirlP1Model.LAYER_LOCATION, AbstractAnimatedCreeperGirlP1Model::createBodyLayer);
		event.registerLayerDefinition(AbstractAnimatedCreeperGirlP2Model.LAYER_LOCATION, AbstractAnimatedCreeperGirlP2Model::createBodyLayer);
		event.registerLayerDefinition(AbstractAnimatedCreeperGirlP3Model.LAYER_LOCATION, AbstractAnimatedCreeperGirlP3Model::createBodyLayer);
		event.registerLayerDefinition(AbstractAnimatedCreeperGirlP4Model.LAYER_LOCATION, AbstractAnimatedCreeperGirlP4Model::createBodyLayer);
		event.registerLayerDefinition(AbstractAnimatedCreeperGirlP5Model.LAYER_LOCATION, AbstractAnimatedCreeperGirlP5Model::createBodyLayer);
		event.registerLayerDefinition(AbstractAnimatedCreeperGirlP6Model.LAYER_LOCATION, AbstractAnimatedCreeperGirlP6Model::createBodyLayer);
		event.registerLayerDefinition(AbstractAnimatedCreeperGirlP7Model.LAYER_LOCATION, AbstractAnimatedCreeperGirlP7Model::createBodyLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractCreeperGirlModel::createInnerLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractCreeperGirlModel::createOuterLayer);
				
		/*Maternal Armors*/
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