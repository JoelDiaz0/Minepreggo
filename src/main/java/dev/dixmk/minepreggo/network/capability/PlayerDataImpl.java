package dev.dixmk.minepreggo.network.capability;

import java.util.function.Supplier;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

public class PlayerDataImpl implements IPlayerData {
	private Gender gender = Gender.FEMALE;
	private boolean customSkin = true;
	private boolean pregnant = false;
	private float fertility = 0;
	private int pregnancyInitializerTimer = 0;
		
	@Override
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public Gender getGender() {
		return this.gender;
	}

	@Override
	public boolean canGetPregnant() {
		return this.gender == Gender.FEMALE;
	}

	@Override
	public boolean isUsingCustomSkin() {
		return this.customSkin;
	}

	@Override
	public boolean isPregnant() {
		return this.pregnant;
	}

	@Override
	public void impregnate() {
		if (this.canGetPregnant()) {
			this.pregnant = true;
		}
	}

	@Override
	public float getFertilityPercentage() {
		return this.fertility;
	}

	@Override
	public void setFertilityPercentage(float percentage) {
		this.fertility = Mth.clamp(percentage, 0, 1F);
	}

	@Override
	public void incrementFertilityPercentage() {
		this.fertility = Math.min(this.fertility + 0.05F, 1F);
	}

	@Override
	public void reduceFertilityPercentage() {
		this.fertility = Math.max(this.fertility - 0.05F, 0F);	
	}

	@Override
	public void incrementFertilityTimer() {
		++this.pregnancyInitializerTimer;
	}

	@Override
	public void startPregnancy() {

	}
	
	@NonNull
	public Tag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putInt("DataGender", gender.ordinal());
		nbt.putInt("DataPregnancyInitializerTimer", pregnancyInitializerTimer);
		nbt.putFloat("DataFertility", fertility);
		nbt.putBoolean("DataCustomSkin", customSkin);
		nbt.putBoolean("DataPregnant", pregnant);
		return nbt;
	}
	
	public void deserializeNBT(@NonNull Tag tag) {
		CompoundTag nbt = (CompoundTag) tag;
		pregnancyInitializerTimer = nbt.getInt("DataPregnancyInitializerTimer");
		gender = Gender.values()[nbt.getInt("DataGender")];
		fertility = nbt.getFloat("DataFertility");
		customSkin = nbt.getBoolean("DataCustomSkin");
		pregnant = nbt.getBoolean("DataPregnant");
	}
	
	public void syncAllVariables(ServerPlayer entity) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.DIMENSION.with(entity.level()::dimension), new PlayerDataSyncPacket(entity.getId(), this));
	}
	
	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	static record PlayerDataClientSyncPacket(int targetId, Gender gender, boolean pregnant, boolean customSkin) {
		public static PlayerDataClientSyncPacket decode(FriendlyByteBuf buffer) {	
			return new PlayerDataClientSyncPacket(
					buffer.readVarInt(),
					buffer.readEnum(Gender.class),
					buffer.readBoolean(),
					buffer.readBoolean());
		}
		
		public static void encode(PlayerDataClientSyncPacket message, FriendlyByteBuf buffer) {
			buffer.writeVarInt(message.targetId);
			buffer.writeEnum(message.gender);
			buffer.writeBoolean(message.pregnant);
			buffer.writeBoolean(message.customSkin);
		}
		
		public static void handler(PlayerDataClientSyncPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {			
				if (!context.getDirection().getReceptionSide().isServer()) {
					
					
					
				}			
			});
			context.setPacketHandled(true);
		}
		
		@SubscribeEvent
		public static void registerMessage(FMLCommonSetupEvent event) {
			MinepreggoModPacketHandler.addNetworkMessage(PlayerDataClientSyncPacket.class, PlayerDataClientSyncPacket::encode, PlayerDataClientSyncPacket::decode, PlayerDataClientSyncPacket::handler);
		}
	}
	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	static record PlayerDataSyncPacket(int targetId, PlayerDataImpl data) {
		public static PlayerDataSyncPacket decode(FriendlyByteBuf buffer) {	
			var targetId = buffer.readVarInt();
			var data = new PlayerDataImpl();
			data.deserializeNBT(buffer.readNbt());
			return new PlayerDataSyncPacket(targetId, data);
		}
		
		public static void encode(PlayerDataSyncPacket message, FriendlyByteBuf buffer) {
			buffer.writeVarInt(message.targetId);
			buffer.writeNbt((CompoundTag) message.data.serializeNBT());
		}
		
		public static void handler(PlayerDataSyncPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {			
				if (context.getDirection().getReceptionSide().isClient()) {
					
					
					
				}			
			});
			context.setPacketHandled(true);
		}
		
		@SubscribeEvent
		public static void registerMessage(FMLCommonSetupEvent event) {
			MinepreggoModPacketHandler.addNetworkMessage(PlayerDataSyncPacket.class, PlayerDataSyncPacket::encode, PlayerDataSyncPacket::decode, PlayerDataSyncPacket::handler);
		}
	}
	
	
}
