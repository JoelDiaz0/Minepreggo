package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.entity.preggo.zombie.MonsterZombieGirlP0;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP0;
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
	
	
	public static final RegistryObject<EntityType<MonsterZombieGirlP0>> MONSTER_ZOMBIE_GIRL_P0 = register("monster_zombie_girl_p0",
			EntityType.Builder.<MonsterZombieGirlP0>of(MonsterZombieGirlP0::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MonsterZombieGirlP0::new).sized(0.6f, 1.8f));


	public static final RegistryObject<EntityType<TamableZombieGirlP0>> TAMABLE_ZOMBIE_GIRL_P0 = register("tamable_zombie_girl_p0",
			EntityType.Builder.<TamableZombieGirlP0>of(TamableZombieGirlP0::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TamableZombieGirlP0::new).sized(0.6f, 1.8f));

	
	
	
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(MonsterZombieGirlP0::init);
		event.enqueueWork(TamableZombieGirlP0::init);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(MONSTER_ZOMBIE_GIRL_P0.get(), MonsterZombieGirlP0.createAttributes().build());
		event.put(TAMABLE_ZOMBIE_GIRL_P0.get(), TamableZombieGirlP0.createAttributes().build());
	}
}
