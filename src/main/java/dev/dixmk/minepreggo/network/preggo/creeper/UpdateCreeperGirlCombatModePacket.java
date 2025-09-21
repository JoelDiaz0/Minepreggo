package dev.dixmk.minepreggo.network.preggo.creeper;

import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractCreeperGirl.CombatMode;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class UpdateCreeperGirlCombatModePacket {

	private final int x;
	private final int y;
	private final int z;
	private final CombatMode combatMode;
	private final int creeperGirlId;
	
	public UpdateCreeperGirlCombatModePacket(int x, int y, int z, CombatMode combatMode, int creeperGirlId) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.combatMode = combatMode;
		this.creeperGirlId = creeperGirlId;
	}
	
	public static UpdateCreeperGirlCombatModePacket decode(FriendlyByteBuf buffer) {	
		return new UpdateCreeperGirlCombatModePacket(
				buffer.readInt(),
				buffer.readInt(),
				buffer.readInt(),
				buffer.readEnum(CombatMode.class),
				buffer.readVarInt());
	}
	
	
	public static void encode(UpdateCreeperGirlCombatModePacket message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		buffer.writeEnum(message.combatMode);
		buffer.writeVarInt(message.creeperGirlId);
	}

	@SuppressWarnings("deprecation")
	public static void handler(UpdateCreeperGirlCombatModePacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			var player = context.getSender();
			var world = player.level();
			
			// security measure to prevent arbitrary chunk generation
			if (!world.hasChunkAt(new BlockPos(message.x, message.y, message.z))) return;
				
			if (world.getEntity(message.creeperGirlId) instanceof AbstractTamableCreeperGirl creeperGirl) {
				creeperGirl.setcombatMode(message.combatMode);
			}
		});
		context.setPacketHandled(true);
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(UpdateCreeperGirlCombatModePacket.class, UpdateCreeperGirlCombatModePacket::encode, UpdateCreeperGirlCombatModePacket::decode, UpdateCreeperGirlCombatModePacket::handler);
	}
}
