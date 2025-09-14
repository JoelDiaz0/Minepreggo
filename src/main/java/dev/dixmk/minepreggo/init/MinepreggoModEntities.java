package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.entity.preggo.girl.zombie.ZombieGirlEntity;
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
	
	
	public static final RegistryObject<EntityType<ZombieGirlEntity>> ZOMBIE_GIRL = register("zombie_girl",
			EntityType.Builder.<ZombieGirlEntity>of(ZombieGirlEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ZombieGirlEntity::new).sized(0.6f, 1.8f));


	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(ZombieGirlEntity::init);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(ZOMBIE_GIRL.get(), ZombieGirlEntity.createAttributes().build());
	}
}
