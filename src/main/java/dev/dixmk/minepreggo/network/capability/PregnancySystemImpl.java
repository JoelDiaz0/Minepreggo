package dev.dixmk.minepreggo.network.capability;

import java.util.EnumMap;
import java.util.Set;
import java.util.function.Supplier;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.entity.preggo.BabyType;
import dev.dixmk.minepreggo.entity.preggo.PregnancyPain;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.PregnancySymptom;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

public class PregnancySystemImpl implements IPregnancySystemHandler {	
	// Server Data
	private int daysByStage = 0;
	private int daysToGiveBirth = 0;
	private int daysPassed = 0;
	private int pregnancyHealth = 0;
	private int pregnancyTimer = 0;
	private int pregnancyPainTimer = 0;
	private int numOfJumps = 0;
	private int sprintingTimer = 0;
	private PregnancyStage currentPregnancyStage = PregnancyStage.P0;
	private PregnancyStage lastPregnancyStage = PregnancyStage.P4;
	private EnumMap<BabyType, @NonNull Integer> babies = new EnumMap<>(BabyType.class);

	// Client Data
	private PregnancySymptom currentPregnancySymptom = PregnancySymptom.NONE;
	private PregnancyPain currentPregnancyPain = PregnancyPain.NONE;
	
	@Override
	public int getDaysByStage() {
		return this.daysByStage;
	}

	@Override
	public void setDaysByStage(int days) {
		this.daysByStage = Math.max(days, 0);
	}

	@Override
	public int getPregnancyHealth() {
		return this.pregnancyHealth;
	}

	@Override
	public void setPregnancyHealth(int health) {
		this.pregnancyHealth = Mth.clamp(health, 0, 100);
	}

	@Override
	public int getDaysPassed() {
		return this.daysPassed;
	}

	@Override
	public void setDaysPassed(int days) {
		this.daysPassed = Math.max(days, 0);
	}

	@Override
	public int getDaysToGiveBirth() {
		return this.daysToGiveBirth;
	}

	@Override
	public void setDaysToGiveBirth(int days) {
		this.daysToGiveBirth = Math.max(days, 0);
	}

	@Override
	public void incrementDaysPassed() {
		this.daysByStage++;
	}

	@Override
	public void reduceDaysToGiveBirth() {
		if (this.daysToGiveBirth > 0) --this.daysToGiveBirth;
	}
	
	@Override
	public int getPregnancyTimer() {
		return this.pregnancyTimer;
	}

	@Override
	public void setPregnancyTimer(int ticks) {
		this.pregnancyTimer = Math.max(ticks, 0);
	}
	
	@Override
	public void incrementPregnancyTimer() {
		++this.pregnancyTimer;
	}
	
	@Override
	public PregnancyStage getMaxPregnancyStage() {
		return this.lastPregnancyStage;
	}

	@Override
	public void setMaxPregnancyStage(PregnancyStage stage) {
		this.lastPregnancyStage = stage;
	}

	@Override
	public PregnancyStage getCurrentPregnancyStage() {
		return this.currentPregnancyStage;
	}

	@Override
	public void setCurrentPregnancyStage(PregnancyStage stage) {
		this.currentPregnancyStage = stage;
	}

	@Override
	public PregnancySymptom getPregnancySymptom() {
		return this.currentPregnancySymptom;
	}

	@Override
	public void setPregnancySymptom(PregnancySymptom symptom) {
		this.currentPregnancySymptom = symptom;
	}

	@Override
	public PregnancyPain getPregnancyPain() {
		return this.currentPregnancyPain;
	}

	@Override
	public void setPregnancyPain(PregnancyPain pain) {
		this.currentPregnancyPain = pain;
	}

	@Override
	public Set<BabyType> getTypesOfBabies() {
		return this.babies.keySet();
	}

	@Override
	public int getNumOfBabiesByType(BabyType babyType) {
		return this.babies.get(babyType);
	}

	@Override
	public int getTotalNumOfBabies() {	
		return this.babies.values().stream().mapToInt(Integer::intValue).sum();
	}

	@Override
	public void addBaby(BabyType babyType, int count) {	
		this.babies.compute(babyType, (k, v) -> (v == null) ? count : v + count);	
	}
	
	@NonNull
	public Tag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putInt("DataDaysByStage", daysByStage);
		nbt.putInt("DataDaysToGiveBirth", daysToGiveBirth);
		nbt.putInt("DataDaysPassed", daysPassed);
		nbt.putInt("DataPregnancyHealth", pregnancyHealth);
		nbt.putInt("DataPregnancyTimer", pregnancyTimer);
		nbt.putInt("DataCurrentPregnancyStage", currentPregnancyStage.ordinal());
		nbt.putInt("DataLastPregnancyStage", lastPregnancyStage.ordinal());
		nbt.putInt("DataCurrentPregnancySymptom", currentPregnancySymptom.ordinal());
		nbt.putInt("DataCurrentPregnancyPain", currentPregnancyPain.ordinal());
		
		ListTag list = new ListTag();
		this.babies.forEach((key, value) -> {		
			CompoundTag pair = new CompoundTag();
		    pair.putInt("key", key.ordinal());
		    pair.putInt("value", value);
			list.add(pair);
		});
		nbt.put("DataBabies", list);
		
		return nbt;
	}
	
	public void deserializeNBT(@NonNull Tag tag) {
		CompoundTag nbt = (CompoundTag) tag;
		daysByStage = nbt.getInt("DataDaysByStage");
		daysToGiveBirth = nbt.getInt("DataDaysToGiveBirth");
		daysPassed = nbt.getInt("DataDaysPassed");
		pregnancyHealth = nbt.getInt("DataPregnancyHealth");
		pregnancyTimer = nbt.getInt("DataPregnancyTimer");
		currentPregnancyStage = PregnancyStage.values()[nbt.getInt("DataCurrentPregnancyStage")];
		lastPregnancyStage = PregnancyStage.values()[nbt.getInt("DataLastPregnancyStage")];
		currentPregnancySymptom = PregnancySymptom.values()[nbt.getInt("DataCurrentPregnancySymptom")];
		currentPregnancyPain = PregnancyPain.values()[nbt.getInt("DataCurrentPregnancyPain")];	
		
		EnumMap<@NonNull BabyType, @NonNull Integer> b = new EnumMap<>(BabyType.class);
		
		ListTag list = nbt.getList("DataBabies", Tag.TAG_COMPOUND);
		
	    for (var t : list) {
	        CompoundTag pair = (CompoundTag) t;
	        BabyType key = BabyType.values()[pair.getInt("key")];
	        int value = pair.getInt("value");
	        b.put(key, value);
	    }
		
		babies = b;
	}
	
	public void copyFrom(@NonNull PregnancySystemImpl newData) {
		this.babies = newData.babies;
		this.currentPregnancyPain = newData.currentPregnancyPain;
		this.currentPregnancyStage = newData.currentPregnancyStage;
		this.currentPregnancySymptom = newData.currentPregnancySymptom;
		this.daysByStage = newData.daysByStage;
		this.daysPassed = newData.daysByStage;
		this.daysToGiveBirth = newData.daysToGiveBirth;
		this.lastPregnancyStage = newData.lastPregnancyStage;
		this.numOfJumps = newData.numOfJumps;
		this.pregnancyHealth = newData.pregnancyHealth;
		this.pregnancyPainTimer = newData.pregnancyPainTimer;
		this.pregnancyTimer = newData.pregnancyTimer;
		this.sprintingTimer = newData.sprintingTimer;
	}
	
	public void sync(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer), 
				new SyncClientDataPacket(serverPlayer.getId(), this.currentPregnancySymptom, this.currentPregnancyPain));
	}
	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public record SyncClientDataPacket(int targetId, PregnancySymptom pregnancySymptom, PregnancyPain pregnancyPain) {		
		public static SyncClientDataPacket decode(FriendlyByteBuf buffer) {	
			return new SyncClientDataPacket(
					buffer.readVarInt(), 
					buffer.readEnum(PregnancySymptom.class), 
					buffer.readEnum(PregnancyPain.class));
		}
		
		public static void encode(SyncClientDataPacket message, FriendlyByteBuf buffer) {
			buffer.writeVarInt(message.targetId);
			buffer.writeEnum(message.pregnancySymptom);
			buffer.writeEnum(message.pregnancyPain);
		}
		
		public static void handler(SyncClientDataPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {			
				if (context.getDirection().getReceptionSide().isClient()) {
					Minecraft.getInstance().player.level().getEntity(message.targetId).getCapability(MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM, null).ifPresent(c -> {					
						c.currentPregnancySymptom = message.pregnancySymptom;
						c.currentPregnancyPain = message.pregnancyPain;
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
