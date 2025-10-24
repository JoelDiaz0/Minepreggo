package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.entity.preggo.IPreggoMob;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PreggoMobWaitPacket {

	private final int preggoMobId;
	private final boolean wait;
	
	public PreggoMobWaitPacket(int preggoMobId, boolean wait) {
		this.preggoMobId = preggoMobId;
		this.wait = wait;
	}
	
	public static PreggoMobWaitPacket decode(FriendlyByteBuf buffer) {	
		return new PreggoMobWaitPacket(
				buffer.readVarInt(),
				buffer.readBoolean());
	}
	
	public static void encode(PreggoMobWaitPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.preggoMobId);
		buffer.writeBoolean(message.wait);
	}
	
	public static void handler(PreggoMobWaitPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			var serverPlayer = context.getSender();		
			if (serverPlayer == null) return;		
			var world = serverPlayer.level();
			
			if (!world.isClientSide() && world.getEntity(message.preggoMobId) instanceof IPreggoMob mob) {
				mob.setWaiting(message.wait);	
			}
		});
		context.setPacketHandled(true);
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(PreggoMobWaitPacket.class, PreggoMobWaitPacket::encode, PreggoMobWaitPacket::decode, PreggoMobWaitPacket::handler);
	}
}
