package dev.dixmk.minepreggo.entity.preggo.zombie;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.entity.preggo.Craving;
import dev.dixmk.minepreggo.entity.preggo.ICraving;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP1;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.PregnancySystemP1;
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
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class TamableZombieGirlP1 extends AbstractTamablePregnantZombieGirl implements IPregnancyP1 {
	private final PregnancySystemP1<TamableZombieGirlP1> preggoMobSystem;
	
	public TamableZombieGirlP1(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get(), world);
	}

	public TamableZombieGirlP1(EntityType<TamableZombieGirlP1> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
		preggoMobSystem = new PregnancySystemP1<TamableZombieGirlP1>(this) {
			@Override
			protected void changePregnancyStage() {
		
			}
			
			@Override
			protected void finishMiscarriage() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);
					PreggoMobHelper.transferPreggoMobBasicData(preggoMob, zombieGirl);
					preggoMob.discard();
				}
			}

			@SuppressWarnings("unchecked")
			@Override
			@Nullable
			protected <I extends Item & ICraving> I getCraving(Craving craving) {					
				if (craving == Craving.NONE) {
					return null;
				}		
				return (I) CRAVING_ENUM_MAP.get(craving);
			}

			@Override
			protected boolean isFood(ItemStack food) {		
				return food.is(PreggoTags.ZOMBIE_GIRL_FOOD);
			}
		};
	}
	
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		PreggoAIHelper.setTamablePregnantZombieGirlGoals(this);
	}
	
	@Override
	public void baseTick() {
		super.baseTick();
		this.refreshDimensions();	
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
		
		if (sourceentity instanceof ServerPlayer serverPlayer
				&& preggoMobSystem.canOwnerAccessGUI(sourceentity)) {
			final var entityId = this.getId();
			final var blockPos = serverPlayer.blockPosition();
				
			if (serverPlayer.isShiftKeyDown()) {
				NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("ZombieGirlInventaryGUI");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
						packetBuffer.writeBlockPos(blockPos);
						packetBuffer.writeVarInt(entityId);			
						return new ZombieGirlP1InventaryGUIMenu(id, inventory, packetBuffer);
					}
				}, buf -> {
					buf.writeBlockPos(blockPos);
					buf.writeVarInt(entityId);
				});
			}
			else {				
				NetworkHooks.openScreen(serverPlayer, new MenuProvider() {		
					@Override
					public Component getDisplayName() {
						return Component.literal("ZombieGirlMainGUI");
					}
					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
						packetBuffer.writeBlockPos(blockPos);
						packetBuffer.writeVarInt(entityId);					
						return new ZombieGirlP1MainGUIMenu(id, inventory, packetBuffer);				
					}
				}, buf -> {
				    buf.writeBlockPos(blockPos);         
				    buf.writeVarInt(entityId);
				});		
			}
		}
		else {		
			preggoMobSystem.evaluateRightClick(sourceentity);
		}	
		
		
		return InteractionResult.SUCCESS;
	}
	
	
	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.235);
	}
	
	@Override
	public PregnancyStage getCurrentPregnancyStage() {
		return PregnancyStage.P1;
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

}
