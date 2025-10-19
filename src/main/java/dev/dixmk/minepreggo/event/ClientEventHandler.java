package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.SexCinematicManager;
import dev.dixmk.minepreggo.client.screen.effect.SexOverlay;
import dev.dixmk.minepreggo.network.packet.SexCinematicAbortPacket;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, value = Dist.CLIENT)
public class ClientEventHandler {
	
	private ClientEventHandler() {}
	
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
        	SexOverlay.tick();
        }
        
        // Only act at the start of the client tick
        if (event.phase == TickEvent.Phase.START) {
            Minecraft mc = Minecraft.getInstance();
            if ((mc.player == null || mc.level == null) && SexCinematicManager.isInCinematic()) {
                SexCinematicManager.endCinematic();
                return;
            }

            if (!SexCinematicManager.isInCinematic()) {
                return; // Nothing to do
            }

            int mobId = SexCinematicManager.getActiveMobId();
            var mob = mc.level.getEntity(mobId);

            boolean shouldCancel = false;

            // Player is gone (shouldn't happen mid-cinematic, but safe)
            if (mc.player.isRemoved() 
            		|| (mob == null || mob.isRemoved())
            		|| mc.player.distanceToSqr(mob) > 16D) {
                shouldCancel = true;
            }
        
            if (shouldCancel) {
                SexCinematicManager.endCinematic();
                MinepreggoModPacketHandler.INSTANCE.sendToServer(new SexCinematicAbortPacket(mobId));
                return;
            }

            mc.player.input.leftImpulse = 0.0F;
            mc.player.input.forwardImpulse = 0.0F;
            mc.player.input.jumping = false;
            mc.player.input.shiftKeyDown = false;

            mc.player.setYRot(SexCinematicManager.getStoredYaw());
            mc.player.setXRot(SexCinematicManager.getStoredPitch());
            mc.player.yRotO = SexCinematicManager.getStoredYaw();
            mc.player.xRotO = SexCinematicManager.getStoredPitch(); 
        };
    } 
}
