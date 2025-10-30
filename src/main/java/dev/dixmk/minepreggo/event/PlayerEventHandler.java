package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.server.ServerSexCinematicManager;
import dev.dixmk.minepreggo.utils.PreggoMobDebugHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID)
public class PlayerEventHandler {

	private PlayerEventHandler() {}
	
	@SubscribeEvent
	public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {	
		if (event.getTarget() instanceof PreggoMob t) {
			PreggoMobDebugHelper.check(t);
		}
	}
		
	@SubscribeEvent
	public static void onPlayerQuit(PlayerEvent.PlayerLoggedOutEvent event) {
	    if (event.getEntity() instanceof ServerPlayer player) {
	        ServerSexCinematicManager.end(player);
	    }
	}
}
