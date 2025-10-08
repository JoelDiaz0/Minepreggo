package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.entity.preggo.creeper.MonsterCreeperGirlP0;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP0;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP1;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP2;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP3;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP4;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP5;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP6;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP7;
import dev.dixmk.minepreggo.entity.preggo.zombie.MonsterZombieGirlP0;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP0;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP1;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP2;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP3;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP4;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP5;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP6;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP7;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MinepreggoModEntities {
	
	private MinepreggoModEntities() {}
	
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MinepreggoMod.MODID);
	
	
	
	/*Zombie Girl*/
	public static final RegistryObject<EntityType<MonsterZombieGirlP0>> MONSTER_ZOMBIE_GIRL_P0 = register("monster_zombie_girl_p0",
			EntityType.Builder.<MonsterZombieGirlP0>of(MonsterZombieGirlP0::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterZombieGirlP0::new).sized(0.6f, 1.8f));

	public static final RegistryObject<EntityType<TamableZombieGirlP0>> TAMABLE_ZOMBIE_GIRL_P0 = register("tamable_zombie_girl_p0",
			EntityType.Builder.<TamableZombieGirlP0>of(TamableZombieGirlP0::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP0::new).sized(0.6f, 1.8f));

	public static final RegistryObject<EntityType<TamableZombieGirlP1>> TAMABLE_ZOMBIE_GIRL_P1 = register("tamable_zombie_girl_p1",
			EntityType.Builder.<TamableZombieGirlP1>of(TamableZombieGirlP1::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP1::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableZombieGirlP2>> TAMABLE_ZOMBIE_GIRL_P2 = register("tamable_zombie_girl_p2",
			EntityType.Builder.<TamableZombieGirlP2>of(TamableZombieGirlP2::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP2::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableZombieGirlP3>> TAMABLE_ZOMBIE_GIRL_P3 = register("tamable_zombie_girl_p3",
			EntityType.Builder.<TamableZombieGirlP3>of(TamableZombieGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP3::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableZombieGirlP4>> TAMABLE_ZOMBIE_GIRL_P4 = register("tamable_zombie_girl_p4",
			EntityType.Builder.<TamableZombieGirlP4>of(TamableZombieGirlP4::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP4::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableZombieGirlP5>> TAMABLE_ZOMBIE_GIRL_P5 = register("tamable_zombie_girl_p5",
			EntityType.Builder.<TamableZombieGirlP5>of(TamableZombieGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP5::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableZombieGirlP6>> TAMABLE_ZOMBIE_GIRL_P6 = register("tamable_zombie_girl_p6",
			EntityType.Builder.<TamableZombieGirlP6>of(TamableZombieGirlP6::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP6::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableZombieGirlP7>> TAMABLE_ZOMBIE_GIRL_P7 = register("tamable_zombie_girl_p7",
			EntityType.Builder.<TamableZombieGirlP7>of(TamableZombieGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP7::new).sized(0.6f, 1.8f));
	
	/*Creeper Girl*/
	public static final RegistryObject<EntityType<MonsterCreeperGirlP0>> MONSTER_CREEPER_GIRL_P0 = register("monster_creeper_girl_p0",
			EntityType.Builder.<MonsterCreeperGirlP0>of(MonsterCreeperGirlP0::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterCreeperGirlP0::new).sized(0.6f, 1.8f));

	public static final RegistryObject<EntityType<TamableCreeperGirlP0>> TAMABLE_CREEPER_GIRL_P0 = register("tamable_creeper_girl_p0",
			EntityType.Builder.<TamableCreeperGirlP0>of(TamableCreeperGirlP0::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirlP0::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableCreeperGirlP1>> TAMABLE_CREEPER_GIRL_P1 = register("tamable_creeper_girl_p1",
			EntityType.Builder.<TamableCreeperGirlP1>of(TamableCreeperGirlP1::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirlP1::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableCreeperGirlP2>> TAMABLE_CREEPER_GIRL_P2 = register("tamable_creeper_girl_p2",
			EntityType.Builder.<TamableCreeperGirlP2>of(TamableCreeperGirlP2::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirlP2::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableCreeperGirlP3>> TAMABLE_CREEPER_GIRL_P3 = register("tamable_creeper_girl_p3",
			EntityType.Builder.<TamableCreeperGirlP3>of(TamableCreeperGirlP3::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirlP3::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableCreeperGirlP4>> TAMABLE_CREEPER_GIRL_P4 = register("tamable_creeper_girl_p4",
			EntityType.Builder.<TamableCreeperGirlP4>of(TamableCreeperGirlP4::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirlP4::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableCreeperGirlP5>> TAMABLE_CREEPER_GIRL_P5 = register("tamable_creeper_girl_p5",
			EntityType.Builder.<TamableCreeperGirlP5>of(TamableCreeperGirlP5::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirlP5::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableCreeperGirlP6>> TAMABLE_CREEPER_GIRL_P6 = register("tamable_creeper_girl_p6",
			EntityType.Builder.<TamableCreeperGirlP6>of(TamableCreeperGirlP6::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirlP6::new).sized(0.6f, 1.8f));
	
	public static final RegistryObject<EntityType<TamableCreeperGirlP7>> TAMABLE_CREEPER_GIRL_P7 = register("tamable_creeper_girl_p7",
			EntityType.Builder.<TamableCreeperGirlP7>of(TamableCreeperGirlP7::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableCreeperGirlP7::new).sized(0.6f, 1.8f));
	
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(MonsterZombieGirlP0::init);
		event.enqueueWork(TamableZombieGirlP0::init);
		event.enqueueWork(MonsterCreeperGirlP0::init);
		event.enqueueWork(TamableCreeperGirlP0::init);
		event.enqueueWork(TamableCreeperGirlP1::init);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(MONSTER_ZOMBIE_GIRL_P0.get(), MonsterZombieGirlP0.createAttributes().build());
		event.put(TAMABLE_ZOMBIE_GIRL_P0.get(), TamableZombieGirlP0.createAttributes().build());
		event.put(MONSTER_CREEPER_GIRL_P0.get(), MonsterCreeperGirlP0.createAttributes().build());
		event.put(TAMABLE_CREEPER_GIRL_P0.get(), TamableCreeperGirlP0.createAttributes().build());
		event.put(TAMABLE_CREEPER_GIRL_P1.get(), TamableCreeperGirlP1.createAttributes().build());
	}
}
