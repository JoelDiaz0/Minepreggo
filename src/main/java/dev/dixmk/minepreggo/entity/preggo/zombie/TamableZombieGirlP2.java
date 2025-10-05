package dev.dixmk.minepreggo.entity.preggo.zombie;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.entity.preggo.Craving;
import dev.dixmk.minepreggo.entity.preggo.ICraving;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP2;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.PregnancySystemP2;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.utils.PreggoAIHelper;
import dev.dixmk.minepreggo.utils.PreggoMobHelper;
import dev.dixmk.minepreggo.utils.PreggoTags;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP1InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP1MainGUIMenu;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class TamableZombieGirlP2 extends AbstractTamablePregnantZombieGirl implements IPregnancyP2 {
	private final PregnancySystemP2<TamableZombieGirlP2> preggoMobSystem;
	
	public TamableZombieGirlP2(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2.get(), world);
	}

	public TamableZombieGirlP2(EntityType<TamableZombieGirlP2> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
		preggoMobSystem = new PregnancySystemP2<TamableZombieGirlP2>(this) {
			@Override
			protected void changePregnancyStage() {
		
			}
			
			@Override
			protected void finishMiscarriage() {
			}

			@Override
			@Nullable
			protected <I extends Item & ICraving> I getCraving(Craving craving) {					
				return null;
			}

			@Override
			protected boolean isFood(ItemStack food) {		
				return true;
			}
		};
	}
	
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@Override
	public void tick() {
		this.preggoMobSystem.evaluateOnTick();
	}
	
	@Override
	public void aiStep() {
		super.aiStep();
		this.updateSwingTime();
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
	
		if (super.mobInteract(sourceentity, hand) == InteractionResult.SUCCESS) 
			return InteractionResult.SUCCESS;
				
		return InteractionResult.SUCCESS;
	}
	
	
	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.235);
	}
	
	@Override
	public PregnancyStage getCurrentPregnancyStage() {
		return PregnancyStage.P2;
	}
	
	@Override
	public int getCraving() {
		return this.entityData.get(DATA_CRAVING);
	}

	@Override
	public void setCraving(int craving) {
		this.entityData.set(DATA_CRAVING, craving);
	}
	
	@Override
	public int getCravingTimer() {
		return this.cravingTimer;
	}

	@Override
	public void setCravingTimer(int timer) {
		this.cravingTimer = timer;
	}

	@Override
	public Craving getCravingChosen() {
		return this.entityData.get(DATA_CRAVING_CHOSEN);
	}

	@Override
	public void setCravingChosen(Craving craving) {
		this.entityData.set(DATA_CRAVING_CHOSEN, craving);
	}

	@Override
	public int getMilking() {
	    return this.entityData.get(DATA_MILKING);
	}
	
	@Override
	public void setMilking(int milking) {
	    this.entityData.set(DATA_MILKING, milking);
	}
	
	@Override
	public int getMilkingTimer() {
	    return this.milkingTimer;
	}
	
	@Override
	public void setMilkingTimer(int timer) {
	    this.milkingTimer = timer;
	}
}
