package dev.dixmk.minepreggo.network.preggo.zombie;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.utils.ZombieGirlGUIMenuFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ZombieGirlInventaryMenuPacket {
	private final int x;
	private final int y;
	private final int z;
	private final int zombieGirlId;
	
	public ZombieGirlInventaryMenuPacket(int x, int y, int z, int zombieGirlId) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.zombieGirlId = zombieGirlId;
	}

	public static ZombieGirlInventaryMenuPacket decode(FriendlyByteBuf buffer) {	
		return new ZombieGirlInventaryMenuPacket(
				buffer.readInt(),
				buffer.readInt(),
				buffer.readInt(),
				buffer.readVarInt());
	}
	
	public static void encode(ZombieGirlInventaryMenuPacket message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		buffer.writeVarInt(message.zombieGirlId);
	}
	
	@SuppressWarnings("deprecation")
	public static void handler(ZombieGirlInventaryMenuPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			var serverPlayer = context.getSender();		
			if (serverPlayer == null) return;
			
			
			var world = serverPlayer.level();
			
			// security measure to prevent arbitrary chunk generation
			if (!world.hasChunkAt(new BlockPos(message.x, message.y, message.z))) return;
		
			if (world.getEntity(message.zombieGirlId) instanceof AbstractTamableZombieGirl<?> zombieGirl) {			
				final var entityId = zombieGirl.getId();
				final var blockPos = serverPlayer.blockPosition();
			
				NetworkHooks.openScreen(serverPlayer, ZombieGirlGUIMenuFactory.createInventoryGUIMenuProvider(zombieGirl.getClass(), blockPos, entityId), buf -> {
				    buf.writeBlockPos(blockPos);
				    buf.writeVarInt(entityId);
				});			
			}		
		});
		
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(ZombieGirlInventaryMenuPacket.class, ZombieGirlInventaryMenuPacket::encode, ZombieGirlInventaryMenuPacket::decode, ZombieGirlInventaryMenuPacket::handler);
	}
}

