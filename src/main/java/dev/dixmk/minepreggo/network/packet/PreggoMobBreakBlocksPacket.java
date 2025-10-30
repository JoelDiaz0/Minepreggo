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
public record PreggoMobBreakBlocksPacket(int preggoMobId, boolean canBreakBlocks) {
	
	public static PreggoMobBreakBlocksPacket decode(FriendlyByteBuf buffer) {	
		return new PreggoMobBreakBlocksPacket(
				buffer.readVarInt(),
				buffer.readBoolean());
	}
	
	public static void encode(PreggoMobBreakBlocksPacket message, FriendlyByteBuf buffer) {
		buffer.writeVarInt(message.preggoMobId);
		buffer.writeBoolean(message.canBreakBlocks);
	}
	
	public static void handler(PreggoMobBreakBlocksPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			var serverPlayer = context.getSender();		
			if (serverPlayer == null) return;		
			var world = serverPlayer.level();
			
			if (!world.isClientSide() && world.getEntity(message.preggoMobId) instanceof ITamablePreggoMob mob) {
				mob.setBreakBlocks(message.canBreakBlocks);
			}
		});
		context.setPacketHandled(true);
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(PreggoMobBreakBlocksPacket.class, PreggoMobBreakBlocksPacket::encode, PreggoMobBreakBlocksPacket::decode, PreggoMobBreakBlocksPacket::handler);
	}
}
