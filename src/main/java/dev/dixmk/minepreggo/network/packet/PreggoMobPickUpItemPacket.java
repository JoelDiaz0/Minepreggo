package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.entity.preggo.ITamablePreggoMob;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record PreggoMobPickUpItemPacket(int preggoMobId, boolean canPickUpItem) {
	
	public static PreggoMobPickUpItemPacket decode(FriendlyByteBuf buffer) {	
		return new PreggoMobPickUpItemPacket(
				buffer.readVarInt(),
				buffer.readBoolean());
	}
	
	public static void encode(PreggoMobPickUpItemPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.preggoMobId);
		buffer.writeBoolean(message.canPickUpItem);
	}
	
	public static void handler(PreggoMobPickUpItemPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			var serverPlayer = context.getSender();		
			if (serverPlayer == null) return;		
			var world = serverPlayer.level();
			
			if (!world.isClientSide() && world.getEntity(message.preggoMobId) instanceof ITamablePreggoMob mob) {
				mob.setPickUpItems(message.canPickUpItem);
			}
		});
		context.setPacketHandled(true);
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(PreggoMobPickUpItemPacket.class, PreggoMobPickUpItemPacket::encode, PreggoMobPickUpItemPacket::decode, PreggoMobPickUpItemPacket::handler);
	}
}
