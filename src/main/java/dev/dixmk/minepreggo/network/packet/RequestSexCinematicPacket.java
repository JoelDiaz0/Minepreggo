package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.entity.preggo.PreggoMobState;
import dev.dixmk.minepreggo.server.ServerSexCinematicManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record RequestSexCinematicPacket(int mobId) {
	
	public static RequestSexCinematicPacket decode(FriendlyByteBuf buffer) {	
		return new RequestSexCinematicPacket(
				buffer.readVarInt());
		}
		
	public static void encode(RequestSexCinematicPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.mobId);
	}
	 
	 
    public static void handler(RequestSexCinematicPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {		
            ServerPlayer player = context.getSender();
            if (player == null) return;
            Level level = player.level();
            		
            if (level.getEntity(message.mobId) instanceof TamableAnimal tamableAnimal 
            		&& player.distanceToSqr(tamableAnimal) <= 4
            		&& tamableAnimal instanceof ITamablePreggoMob preggoMob) {
            		      		
                    ServerSexCinematicManager.start(player);   
                                    
            		preggoMob.setState(PreggoMobState.BLUSHED);
                    preggoMob.setCinematicOwner(player);
                    preggoMob.setCinematicEndTime(player.level().getGameTime() + 120);
                           
                    MinepreggoModPacketHandler.INSTANCE.send(
                        PacketDistributor.PLAYER.with(() -> player),
                        new SexCinematicControlPacket(true, message.mobId)
                    );	               
					MinepreggoModPacketHandler.INSTANCE.send(
						PacketDistributor.PLAYER.with(() -> player),
						new RenderSexOverlayPacket(true));           		
            } 
			
		});
		context.setPacketHandled(true);
    }
    
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(RequestSexCinematicPacket.class, RequestSexCinematicPacket::encode, RequestSexCinematicPacket::decode, RequestSexCinematicPacket::handler);
	}
}
