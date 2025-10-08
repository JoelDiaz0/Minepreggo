package dev.dixmk.minepreggo.network.preggo.creeper;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.utils.CreeperGirlGUIMenuFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreeperGirlInventaryMenuPacket {
	private final int x;
	private final int y;
	private final int z;
	private final int creeperGirlId;
	
	public CreeperGirlInventaryMenuPacket(int x, int y, int z, int creeperGirlId) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.creeperGirlId = creeperGirlId;
	}

	public static CreeperGirlInventaryMenuPacket decode(FriendlyByteBuf buffer) {	
		return new CreeperGirlInventaryMenuPacket(
				buffer.readInt(),
				buffer.readInt(),
				buffer.readInt(),
				buffer.readVarInt());
	}
	
	public static void encode(CreeperGirlInventaryMenuPacket message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		buffer.writeVarInt(message.creeperGirlId);
	}
	
	@SuppressWarnings("deprecation")
	public static void handler(CreeperGirlInventaryMenuPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			var serverPlayer = context.getSender();		
			if (serverPlayer == null) return;
					
			var world = serverPlayer.level();
			
			// security measure to prevent arbitrary chunk generation
			if (!world.hasChunkAt(new BlockPos(message.x, message.y, message.z))) return;
				
			if (world.getEntity(message.creeperGirlId) instanceof AbstractTamableCreeperGirl<?> creeperGirl) {			
				final var entityId = creeperGirl.getId();
				final var blockPos = serverPlayer.blockPosition();
				
				NetworkHooks.openScreen(serverPlayer, CreeperGirlGUIMenuFactory.createInventaryGUIMenuProvider(creeperGirl.getClass(), blockPos, entityId), buf -> {
				    buf.writeBlockPos(blockPos);
				    buf.writeVarInt(entityId);
				});			
			}		
		});
		
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(CreeperGirlInventaryMenuPacket.class, CreeperGirlInventaryMenuPacket::encode, CreeperGirlInventaryMenuPacket::decode, CreeperGirlInventaryMenuPacket::handler);
	}
}
