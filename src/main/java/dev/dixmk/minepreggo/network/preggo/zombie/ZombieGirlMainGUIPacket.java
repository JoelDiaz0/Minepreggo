package dev.dixmk.minepreggo.network.preggo.zombie;

import java.util.Comparator;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.entity.preggo.PreggoMobState;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamableZombieGirl;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;



@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ZombieGirlMainGUIPacket {
	
	private final int x;
	private final int y;
	private final int z;
	private final int buttonId;
	private final int zombieGirlId;

	public ZombieGirlMainGUIPacket(int x, int y, int z, int buttonId, int creeperGirlId) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.buttonId = buttonId;
		this.zombieGirlId = creeperGirlId;
	}
	
	public static ZombieGirlMainGUIPacket decode(FriendlyByteBuf buffer) {	
		return new ZombieGirlMainGUIPacket(
				buffer.readInt(),
				buffer.readInt(),
				buffer.readInt(),
				buffer.readInt(),
				buffer.readVarInt());
	}
	
	public static void encode(ZombieGirlMainGUIPacket message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
		buffer.writeInt(message.buttonId);
		buffer.writeVarInt(message.zombieGirlId);
	}

	@SuppressWarnings("deprecation")
	public static void handler(ZombieGirlMainGUIPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			var player = context.getSender();
			int x = message.x;
			int y = message.y;
			int z = message.z;
			var world = player.level();
			
			// security measure to prevent arbitrary chunk generation
			if (!world.hasChunkAt(new BlockPos(x, y, z)))
				return;

			if (world.getEntity(message.zombieGirlId) instanceof AbstractTamableZombieGirl zombieGirl) {
				int buttonId = message.buttonId;
				
				if ((buttonId == 0 || buttonId == 1)) {			
					boolean flag = buttonId == 0;						
					zombieGirl.setWaiting(flag);	
					zombieGirl.setState(flag ? PreggoMobState.WAIT : PreggoMobState.IDLE);
				}
				else if (buttonId == 2 && !zombieGirl.isPassenger()) {
					var abstractHorse = world.getEntitiesOfClass(AbstractHorse.class, AABB.ofSize(new Vec3(x, y, z), 6, 6, 6), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null);

					if (abstractHorse != null) {
						zombieGirl.startRiding(abstractHorse);
					}
				}
				else if (buttonId == 3 && zombieGirl.isPassenger()) {
					zombieGirl.stopRiding();
				}
			}
		});
		
		context.setPacketHandled(true);
	}



	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		MinepreggoModPacketHandler.addNetworkMessage(ZombieGirlMainGUIPacket.class, ZombieGirlMainGUIPacket::encode, ZombieGirlMainGUIPacket::decode, ZombieGirlMainGUIPacket::handler);
	}
}
