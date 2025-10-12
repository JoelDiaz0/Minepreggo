package dev.dixmk.minepreggo;

import org.apache.logging.log4j.Logger;

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
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP4Model;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP5Model;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP6Model;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP7Model;
import dev.dixmk.minepreggo.client.model.armor.FemaleChestPlateP0Model;
import dev.dixmk.minepreggo.client.model.armor.FemaleChestPlateP1Model;
import dev.dixmk.minepreggo.client.model.armor.FemaleChestPlateP2Model;
import dev.dixmk.minepreggo.client.model.armor.FemaleChestPlateP3Model;
import dev.dixmk.minepreggo.client.model.armor.KneeBraceModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterCreeperGirlP0Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterCreeperGirlP3Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterCreeperGirlP5Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterCreeperGirlP7Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP0Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP1Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP2Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP3Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP4Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP5Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP6Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableCreeperGirlP7Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.MonsterZombieGirlP0Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.MonsterZombieGirlP3Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.MonsterZombieGirlP5Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.MonsterZombieGirlP7Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP0Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP1Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP2Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP3Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP4Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP5Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP6Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP7Renderer;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractMonsterCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.creeper.MonsterCreeperGirlP0;
import dev.dixmk.minepreggo.entity.preggo.creeper.MonsterCreeperGirlP3;
import dev.dixmk.minepreggo.entity.preggo.creeper.MonsterCreeperGirlP5;
import dev.dixmk.minepreggo.entity.preggo.creeper.MonsterCreeperGirlP7;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP0;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP1;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP2;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP3;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP4;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP5;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP6;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP7;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractMonsterZombieGirl;
import dev.dixmk.minepreggo.entity.preggo.zombie.MonsterZombieGirlP0;
import dev.dixmk.minepreggo.entity.preggo.zombie.MonsterZombieGirlP3;
import dev.dixmk.minepreggo.entity.preggo.zombie.MonsterZombieGirlP5;
import dev.dixmk.minepreggo.entity.preggo.zombie.MonsterZombieGirlP7;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP0;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP1;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP2;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP3;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP4;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP5;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP6;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP7;
import dev.dixmk.minepreggo.init.MinepreggoModBlocks;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.init.MinepreggoModTabs;

import org.apache.logging.log4j.LogManager;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;

@Mod(MinepreggoMod.MODID)
public class MinepreggoMod {
	public static final Logger LOGGER = LogManager.getLogger(MinepreggoMod.class);
	
	public static final String MODID = "minepreggo";

	public MinepreggoMod(FMLJavaModLoadingContext context) {
		MinecraftForge.EVENT_BUS.register(this);
	
		IEventBus modEventBus = context.getModEventBus();	
		MinepreggoModItems.REGISTRY.register(modEventBus);
		MinepreggoModEntities.REGISTRY.register(modEventBus);
		MinepreggoModBlocks.REGISTRY.register(modEventBus);
		MinepreggoModTabs.REGISTRY.register(modEventBus);
		MinepreggoModSounds.REGISTRY.register(modEventBus);
		MinepreggoModTabs.REGISTRY.register(modEventBus);
		MinepreggoModMenus.REGISTRY.register(modEventBus);
		MinepreggoModMobEffects.REGISTRY.register(modEventBus);
		MinepreggoModEntityDataSerializers.register();
       	
		modEventBus.addListener(this::registerLayerDefinitions);	
		modEventBus.addListener(this::registerAttributes);
		modEventBus.addListener(this::onSpawnPlacementRegister);
		modEventBus.addListener(this::clientLoad);
		modEventBus.addListener(this::registerEntityRenderers);
		
		context.registerConfig(ModConfig.Type.CLIENT, MinepreggoModConfig.CLIENT_SPEC);
		context.registerConfig(ModConfig.Type.COMMON, MinepreggoModConfig.COMMON_SPEC);
		context.registerConfig(ModConfig.Type.SERVER, MinepreggoModConfig.SERVER_SPEC);
	}

	private void onSpawnPlacementRegister(SpawnPlacementRegisterEvent event) {
		event.register(
        		MinepreggoModEntities.MONSTER_CREEPER_GIRL_P0.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterCreeperGirl::checkMonsterCreeperGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_CREEPER_GIRL_P3.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterCreeperGirl::checkMonsterCreeperGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_CREEPER_GIRL_P5.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterCreeperGirl::checkMonsterCreeperGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_CREEPER_GIRL_P7.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterCreeperGirl::checkMonsterCreeperGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P0.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterZombieGirl::checkMonsterZombieGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P3.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterZombieGirl::checkMonsterZombieGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P5.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterZombieGirl::checkMonsterZombieGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
		event.register(
        		MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P7.get(),
        		SpawnPlacements.Type.ON_GROUND,
        		Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
        		AbstractMonsterZombieGirl::checkMonsterZombieGirlSpawnRules,
        		SpawnPlacementRegisterEvent.Operation.OR);
    }
	
	private void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P0.get(), MonsterZombieGirlP0.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P3.get(), MonsterZombieGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P5.get(), MonsterZombieGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P7.get(), MonsterZombieGirlP7.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get(), TamableZombieGirlP0.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get(), TamableZombieGirlP1.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2.get(), TamableZombieGirlP2.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P3.get(), TamableZombieGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P4.get(), TamableZombieGirlP4.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P5.get(), TamableZombieGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6.get(), TamableZombieGirlP6.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P7.get(), TamableZombieGirlP7.createAttributes().build());

		event.put(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P0.get(), MonsterCreeperGirlP0.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P3.get(), MonsterCreeperGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P5.get(), MonsterCreeperGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P7.get(), MonsterCreeperGirlP7.createAttributes().build());	
		event.put(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0.get(), TamableCreeperGirlP0.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P1.get(), TamableCreeperGirlP1.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P2.get(), TamableCreeperGirlP2.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P3.get(), TamableCreeperGirlP3.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P4.get(), TamableCreeperGirlP4.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P5.get(), TamableCreeperGirlP5.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P6.get(), TamableCreeperGirlP6.createAttributes().build());
		event.put(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P7.get(), TamableCreeperGirlP7.createAttributes().build());
	}
	
	
	private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		
		/*Zombie Girl*/
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P0, AbstractZombieGirlModel::createP0BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P1, AbstractZombieGirlModel::createP1BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P2, AbstractZombieGirlModel::createP2BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P3, AbstractZombieGirlModel::createP3BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P4, AbstractZombieGirlModel::createP4BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P5, AbstractZombieGirlModel::createP5BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P6, AbstractZombieGirlModel::createP6BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P7, AbstractZombieGirlModel::createP7BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel::createInnerLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractZombieGirlModel::createOuterLayer);
		
		/*Creeper Girl*/
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_LOCATION_P0, AbstractCreeperGirlModel::createP0BodyLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_LOCATION_P1, AbstractCreeperGirlModel::createP1BodyLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_LOCATION_P2, AbstractCreeperGirlModel::createP2BodyLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_LOCATION_P3, AbstractCreeperGirlModel::createP3BodyLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_LOCATION_P4, AbstractCreeperGirlModel::createP4BodyLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_LOCATION_P5, AbstractCreeperGirlModel::createP5BodyLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_LOCATION_P6, AbstractCreeperGirlModel::createP6BodyLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_LOCATION_P7, AbstractCreeperGirlModel::createP7BodyLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractCreeperGirlModel::createInnerLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractCreeperGirlModel::createOuterLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_ENERGY_ARMOR_P0_LOCATION, AbstractCreeperGirlModel::createP0BodyLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_ENERGY_ARMOR_P1_LOCATION, AbstractCreeperGirlModel::createP1BodyLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_ENERGY_ARMOR_P2_LOCATION, AbstractCreeperGirlModel::createP2BodyLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_ENERGY_ARMOR_P3_LOCATION, AbstractCreeperGirlModel::createP3BodyLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_ENERGY_ARMOR_P4_LOCATION, AbstractCreeperGirlModel::createP4BodyLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_ENERGY_ARMOR_P5_LOCATION, AbstractCreeperGirlModel::createP5BodyLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_ENERGY_ARMOR_P6_LOCATION, AbstractCreeperGirlModel::createP6BodyLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_ENERGY_ARMOR_P7_LOCATION, AbstractCreeperGirlModel::createP7BodyLayer);

		
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
	
	private void clientLoad(FMLClientSetupEvent event) {
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
	
	private void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P0.get(), MonsterZombieGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P3.get(), MonsterZombieGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P5.get(), MonsterZombieGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P7.get(), MonsterZombieGirlP7Renderer::new);	
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get(), TamableZombieGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get(), TamableZombieGirlP1Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2.get(), TamableZombieGirlP2Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P3.get(), TamableZombieGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P4.get(), TamableZombieGirlP4Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P5.get(), TamableZombieGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6.get(), TamableZombieGirlP6Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P7.get(), TamableZombieGirlP7Renderer::new);
	
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P0.get(), MonsterCreeperGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P3.get(), MonsterCreeperGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P5.get(), MonsterCreeperGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P7.get(), MonsterCreeperGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0.get(), TamableCreeperGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P1.get(), TamableCreeperGirlP1Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P2.get(), TamableCreeperGirlP2Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P3.get(), TamableCreeperGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P4.get(), TamableCreeperGirlP4Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P5.get(), TamableCreeperGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P6.get(), TamableCreeperGirlP6Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P7.get(), TamableCreeperGirlP7Renderer::new);
	}
}
