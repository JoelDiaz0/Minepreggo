package dev.dixmk.minepreggo.network.capability;

import java.util.function.Supplier;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.entity.preggo.Craving;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

public class PregnancyEffectsImpl implements IPregnancyEffectsHandler {
	// Server Data
	private int cravingTimer = 0;
	private int milkingTimer = 0;
	private int bellyRubsTimer = 0;
	private int hornyTimer = 0;
	
	// Client Data
	private int craving = 0;
	private int milking = 0;
	private int bellyRubs = 0;
	private int horny = 0;
	private Craving typeOfCraving = Craving.NONE;
	
	@Override
	public Craving getTypeOfCraving() {
		return this.typeOfCraving;
	}
	
	@Override
	public void setTypeOfCraving(Craving craving) {
		this.typeOfCraving = craving;
	}
	
	@Override
	public boolean isValidCraving(Craving craving, Item item) {
		return false;
	}
	
	@Override
	public int getCraving() {
		return this.craving;
	}
	
	@Override
	public void setCraving(int craving) {
		this.craving = Mth.clamp(craving, 0, 20);
	}
	
	@Override
	public void incrementCraving() {
		++this.craving;
	}
	
	@Override
	public int getCravingTimer() {
		return this.cravingTimer;
	}
	
	@Override
	public void setCravingTimer(int timer) {
		this.cravingTimer = Math.max(0, timer);
	}
	
	@Override
	public void incrementCravingTimer() {
		++this.cravingTimer;
	}
	
	@Override
	public int getMilking() {
		return this.milking;
	}
	
	@Override
	public void setMilking(int milking) {
		this.milking = Mth.clamp(milking, 0, 20);
	}
	
	@Override
	public void incrementMilking() {
		++this.milking;		
	}
	
	@Override
	public int getMilkingTimer() {
		return this.milkingTimer;
	}
	
	@Override
	public void setMilkingTimer(int timer) {
		this.milkingTimer = Math.max(0, timer);
		
	}
	
	@Override
	public void incrementMilkingTimer() {
		++this.milkingTimer;
	}
	
	@Override
	public int getBellyRubs() {
		return this.bellyRubs;
	}
	
	@Override
	public void setBellyRubs(int bellyRubs) {
		this.bellyRubs = Mth.clamp(bellyRubs, 0, 20);
	}
	
	@Override
	public void incrementBellyRubs() {
		++this.bellyRubs;
	}
	
	@Override
	public int getBellyRubsTimer() {
		return this.bellyRubsTimer;
	}
	
	@Override
	public void setBellyRubsTimer(int timer) {
		this.bellyRubsTimer = Math.max(0, timer);
	}
	
	@Override
	public void incrementBellyRubsTimer() {
		++this.bellyRubsTimer;
	}
	
	@Override
	public int getHorny() {
		return this.horny;
	}
	
	@Override
	public void setHorny(int horny) {
		this.horny = Mth.clamp(horny, 0, 20);
		
	}
	
	@Override
	public void incrementHorny() {
		++this.horny;
	}
	
	@Override
	public int getHornyTimer() {
		return this.hornyTimer;
	}
	
	@Override
	public void setHornyTimer(int timer) {
		this.hornyTimer = Math.max(0, timer);
		
	}
	
	@Override
	public void incrementHornyTimer() {
		++this.hornyTimer;	
	}
	
	@NonNull
	public Tag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putInt("DataCraving", craving);
		nbt.putInt("DataCravingTimer", cravingTimer);
		nbt.putInt("DataTypeOfCraving", typeOfCraving.ordinal());
		nbt.putInt("DataMilking", milking);
		nbt.putInt("DataMilkingTimer", milkingTimer);
		nbt.putInt("DataBellyRubs", bellyRubs);
		nbt.putInt("DataBellyRubsTimer", bellyRubsTimer);
		nbt.putInt("DataHorny", horny);
		nbt.putInt("DataHornyTimer", hornyTimer);
		return nbt;
	}
	
	public void deserializeNBT(@NonNull Tag tag) {
		CompoundTag nbt = (CompoundTag) tag;
		craving = nbt.getInt("DataCraving");
		cravingTimer = nbt.getInt("DataCravingTimer");
		typeOfCraving = Craving.values()[nbt.getInt("DataTypeOfCraving")];
		milking = nbt.getInt("DataMilking");
		milkingTimer = nbt.getInt("DataMilkingTimer");
		bellyRubs = nbt.getInt("DataBellyRubs");
		bellyRubsTimer = nbt.getInt("DataBellyRubsTimer");
		horny = nbt.getInt("DataHorny");
		hornyTimer = nbt.getInt("DataHornyTimer");
	}
	
	public void copyFrom(@NonNull PregnancyEffectsImpl newData) {
		this.bellyRubs = newData.bellyRubs;
		this.bellyRubsTimer = newData.bellyRubsTimer;
		this.craving = newData.craving;
		this.cravingTimer = newData.cravingTimer;
		this.horny = newData.horny;
		this.hornyTimer = newData.hornyTimer;
		this.milking = newData.milking;
		this.milkingTimer = newData.milkingTimer;
		this.typeOfCraving = newData.typeOfCraving;
	}
	
	public void sync(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), 
				new SyncClientDataPacket(serverPlayer.getId(), this.craving, this.milking, this.bellyRubs, this.horny, this.typeOfCraving));
	}
	
	
	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public record SyncClientDataPacket(int targetId, int craving, int milking, int bellyRubs, int horny, Craving typeOfCraving) {		
		public static SyncClientDataPacket decode(FriendlyByteBuf buffer) {	
			return new SyncClientDataPacket(
					buffer.readVarInt(),
					buffer.readInt(),
					buffer.readInt(),
					buffer.readInt(),
					buffer.readInt(),
					buffer.readEnum(Craving.class));
		}
		
		public static void encode(SyncClientDataPacket message, FriendlyByteBuf buffer) {
			buffer.writeVarInt(message.targetId);
			buffer.writeInt(message.craving);
			buffer.writeInt(message.milking);
			buffer.writeInt(message.bellyRubs);
			buffer.writeInt(message.horny);
			buffer.writeEnum(message.typeOfCraving);
		}
		
		public static void handler(SyncClientDataPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {			
				if (context.getDirection().getReceptionSide().isClient()) {
					Minecraft.getInstance().player.level().getEntity(message.targetId).getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS, null).ifPresent(c -> {					
						c.craving = message.craving;
						c.milking = message.milking;
						c.bellyRubs = message.bellyRubs;
						c.horny = message.horny;
						c.typeOfCraving = message.typeOfCraving;
					});										
				}			
			});
			context.setPacketHandled(true);
		}
		
		@SubscribeEvent
		public static void registerMessage(FMLCommonSetupEvent event) {
			MinepreggoModPacketHandler.addNetworkMessage(SyncClientDataPacket.class, SyncClientDataPacket::encode, SyncClientDataPacket::decode, SyncClientDataPacket::handler);
		}
	}
}
