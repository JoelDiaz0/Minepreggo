package dev.dixmk.minepreggo.event;

import java.util.ArrayList;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.network.capability.PlayerDataImpl;
import dev.dixmk.minepreggo.network.capability.PlayerDataProvider;
import dev.dixmk.minepreggo.network.capability.PregnancyEffectsImpl;
import dev.dixmk.minepreggo.network.capability.PregnancyEffectsProvider;
import dev.dixmk.minepreggo.network.capability.PregnancySystemImpl;
import dev.dixmk.minepreggo.network.capability.PregnancySystemProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
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
	public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getEntity().level().isClientSide()) {

		}
	}

	@SubscribeEvent
	public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
		if (!event.getEntity().level().isClientSide()) {

		}
	}

	@SubscribeEvent
	public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getEntity().level().isClientSide()) {

		}
	}
	
	@SubscribeEvent
	public static void clonePlayer(PlayerEvent.Clone event) {
		event.getOriginal().revive();
	
		
		
	}
}
