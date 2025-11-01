package dev.dixmk.minepreggo.network.capability;

import java.util.function.Supplier;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import net.minecraft.client.Minecraft;
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
	// Client Data
	private Gender gender = Gender.FEMALE;
	private boolean customSkin = true;
	private boolean pregnant = false;
	
	// Server Data
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
	
	public void sync(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.DIMENSION.with(serverPlayer.level()::dimension), new SyncClientDataPacket(serverPlayer.getId(), this.gender, this.pregnant, this.customSkin));
	}
	
	public void copyFrom(@NonNull PlayerDataImpl newData) {
		this.customSkin = newData.customSkin;
		this.pregnant = newData.pregnant;
		this.pregnancyInitializerTimer = newData.pregnancyInitializerTimer;
		this.fertility = newData.fertility;
		this.gender = newData.gender;
	}
	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public record SyncClientDataPacket(int targetId, Gender gender, boolean pregnant, boolean customSkin) {
		public static SyncClientDataPacket decode(FriendlyByteBuf buffer) {	
			return new SyncClientDataPacket(
					buffer.readVarInt(),
					buffer.readEnum(Gender.class),
					buffer.readBoolean(),
					buffer.readBoolean());
		}
		
		public static void encode(SyncClientDataPacket message, FriendlyByteBuf buffer) {
			buffer.writeVarInt(message.targetId);
			buffer.writeEnum(message.gender);
			buffer.writeBoolean(message.pregnant);
			buffer.writeBoolean(message.customSkin);
		}
		
		public static void handler(SyncClientDataPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {			
				if (context.getDirection().getReceptionSide().isClient()) {				
					Minecraft.getInstance().player.level().getEntity(message.targetId).getCapability(MinepreggoCapabilities.PLAYER_DATA, null).ifPresent(c -> {					
						c.gender = message.gender;
						c.pregnant = message.pregnant;
						c.customSkin = message.customSkin;				
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
