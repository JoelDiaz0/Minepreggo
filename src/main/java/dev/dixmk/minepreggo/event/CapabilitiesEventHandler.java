package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.capability.PlayerDataImpl;
import dev.dixmk.minepreggo.network.capability.PlayerDataProvider;
import dev.dixmk.minepreggo.network.capability.PregnancyEffectsImpl;
import dev.dixmk.minepreggo.network.capability.PregnancyEffectsProvider;
import dev.dixmk.minepreggo.network.capability.PregnancySystemImpl;
import dev.dixmk.minepreggo.network.capability.PregnancySystemProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CapabilitiesEventHandler {

	private CapabilitiesEventHandler() {}
	
	@SubscribeEvent
	public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer)) {
			event.addCapability(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "player_pregnancy_system"), new PregnancySystemProvider());
			event.addCapability(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "player_data"), new PlayerDataProvider());
			event.addCapability(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "player_pregnancy_effects"), new PregnancyEffectsProvider());
		}
	}
	
	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PregnancySystemImpl.class);
		event.register(PlayerDataImpl.class);
		event.register(PregnancyEffectsImpl.class);
	}
	
	@SubscribeEvent
	public static void onPlayerLoggedInSync(PlayerEvent.PlayerLoggedInEvent event) {
		if (event.getEntity() instanceof ServerPlayer serverPlayer && !serverPlayer.level().isClientSide()) {	
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA, null).ifPresent(c -> c.sync(serverPlayer));
		}
	}

	@SubscribeEvent
	public static void onPlayerRespawnedSync(PlayerEvent.PlayerRespawnEvent event) {
		if (event.getEntity() instanceof ServerPlayer serverPlayer && !serverPlayer.level().isClientSide()) {	
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA, null).ifPresent(c -> c.sync(serverPlayer));
		}
	}

	@SubscribeEvent
	public static void onPlayerChangedDimensionSync(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (event.getEntity() instanceof ServerPlayer serverPlayer && !serverPlayer.level().isClientSide()) {	
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA, null).ifPresent(c -> c.sync(serverPlayer));
		}
	}
	
	@SubscribeEvent
	public static void clonePlayer(PlayerEvent.Clone event) {
        if (event.getOriginal().level().isClientSide()) return;

        final var originalPlayer = event.getOriginal();
        final var newPlayer = event.getEntity();
        
        var origialPlayerDataCap = originalPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA);
        var newPlayerDataCap = newPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA);
		
        var origialPregnancySystemCap = originalPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM);
        var newPregnancySystemCap = newPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM);
      
        var origialPregnancyEffectsCap = originalPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS);
        var newPregnancyEffectsCap = newPlayer.getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS);
        
        
        if (!event.isWasDeath()) {       
        	origialPlayerDataCap.ifPresent(oriCap -> newPlayerDataCap.ifPresent(newCap -> newCap.copyFrom(oriCap)));       
        	origialPregnancySystemCap.ifPresent(oriCap -> newPregnancySystemCap.ifPresent(newCap -> newCap.copyFrom(oriCap)));       
        	origialPregnancyEffectsCap.ifPresent(oriCap -> newPregnancyEffectsCap.ifPresent(newCap -> newCap.copyFrom(oriCap))); 
        }        
        
        if (newPlayer instanceof ServerPlayer serverPlayer) {
        	newPlayerDataCap.ifPresent(c -> c.sync(serverPlayer));
            newPregnancyEffectsCap.ifPresent(c -> c.sync(serverPlayer));
            newPregnancySystemCap.ifPresent(c -> c.sync(serverPlayer));
        }     
	}
}
