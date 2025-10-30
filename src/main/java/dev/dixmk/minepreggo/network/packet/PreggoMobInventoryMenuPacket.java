package dev.dixmk.minepreggo.network.packet;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.utils.CreeperGirlGUIMenuFactory;
import dev.dixmk.minepreggo.utils.ZombieGirlGUIMenuFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public record PreggoMobInventoryMenuPacket(int x, int y, int z, int preggoMobId) {

	public static PreggoMobInventoryMenuPacket decode(FriendlyByteBuf buffer) {	
		return new PreggoMobInventoryMenuPacket(
				buffer.readInt(),
				buffer.readInt(),
				buffer.readInt(),
				buffer.readVarInt());
	}
	
	public static void encode(PreggoMobInventoryMenuPacket message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		buffer.writeVarInt(message.preggoMobId);
	}
	
	@SuppressWarnings("deprecation")
	public static void handler(PreggoMobInventoryMenuPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			var serverPlayer = context.getSender();		
			if (serverPlayer == null) return;
					
			var world = serverPlayer.level();
			
			// security measure to prevent arbitrary chunk generation
			if (!world.hasChunkAt(new BlockPos(message.x, message.y, message.z))) return;
				
						
			if (world.getEntity(message.preggoMobId) instanceof TamableAnimal tamableAnimal) {			
				final var blockPos = serverPlayer.blockPosition();
						
				if (tamableAnimal instanceof AbstractTamableCreeperGirl<?> creeperGirl) {
					NetworkHooks.openScreen(serverPlayer, CreeperGirlGUIMenuFactory.createInventoryGUIMenuProvider(creeperGirl.getClass(), blockPos, message.preggoMobId), buf -> {
					    buf.writeBlockPos(blockPos);
					    buf.writeVarInt(message.preggoMobId);
					});
					MinepreggoMod.LOGGER.debug("INVENTARY CREEPER GIRL: id={}, class={}", creeperGirl.getId(), creeperGirl.getClass().getSimpleName());
				}	
				else if (tamableAnimal instanceof AbstractTamableZombieGirl<?> zombieGirl) {
					NetworkHooks.openScreen(serverPlayer, ZombieGirlGUIMenuFactory.createInventoryGUIMenuProvider(zombieGirl.getClass(), blockPos, message.preggoMobId), buf -> {
					    buf.writeBlockPos(blockPos);
					    buf.writeVarInt(message.preggoMobId);
					});
					MinepreggoMod.LOGGER.debug("INVENTARY ZOMBIE GIRL: id={}, class={}", zombieGirl.getId(), zombieGirl.getClass().getSimpleName());
				}
				else {
					MinepreggoMod.LOGGER.error("PREGGO MOB CLASS CANNOT BE RESOLVED");
				}
			}	
			
		});
		
		context.setPacketHandled(true);
	}
	
	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(PreggoMobInventoryMenuPacket.class, PreggoMobInventoryMenuPacket::encode, PreggoMobInventoryMenuPacket::decode, PreggoMobInventoryMenuPacket::handler);
	}
}
