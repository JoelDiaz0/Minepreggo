package dev.dixmk.minepreggo.entity.preggo.creeper;

import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;

import java.util.UUID;

import dev.dixmk.minepreggo.entity.preggo.PreggoMobSystem;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.utils.PreggoAIHelper;
import dev.dixmk.minepreggo.utils.PreggoMobHelper;
import dev.dixmk.minepreggo.utils.PreggoTags;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP0InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP0MainGUIMenu;
import io.netty.buffer.Unpooled;

public class TamableCreeperGirlP0 extends AbstractTamableCreeperGirl {

	private static final UUID SPEED_MODIFIER_TIRENESS_UUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
	private static final AttributeModifier SPEED_MODIFIER_TIRENESS = new AttributeModifier(SPEED_MODIFIER_TIRENESS_UUID, "Tireness speed boost", -0.2D, AttributeModifier.Operation.MULTIPLY_BASE);
	private static final EntityDataAccessor<Boolean> DATA_TIRENESS_ID = SynchedEntityData.defineId(TamableCreeperGirlP0.class, EntityDataSerializers.BOOLEAN);
	
	private final PreggoMobSystem<TamableCreeperGirlP0> preggoMobSystem;
		
	public TamableCreeperGirlP0(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0.get(), world);
	}

	public TamableCreeperGirlP0(EntityType<TamableCreeperGirlP0> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
		preggoMobSystem = new PreggoMobSystem<>(this) {
			@Override
			protected void startPregnancy() {		
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var creeperGirl = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P1.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.transferPreggoMobBasicData(preggoMob, creeperGirl);			
					preggoMob.discard();
				}			
			}

			@Override
			protected boolean isFood(ItemStack food) {
				return food.is(PreggoTags.CREEPER_GIRL_FOOD);
			}
		};
	}
	
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
		TamableCreeperGirlP0 retval = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0.get().create(serverWorld);
		retval.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(retval.blockPosition()), MobSpawnType.BREEDING, null, null);
		retval.setBaby(true);
		return retval;
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		PreggoAIHelper.setTamableCreeperGirlGoals(this);
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {	
		boolean success = super.hurt(source, amount);	
		if(!success) return false;	
		//CreeperGirlP0EntityIsHurtProcedure.execute(source, this);
		
		return success;
	}
	
	@Override
	public void baseTick() {
		super.baseTick();
		this.refreshDimensions();
		this.preggoMobSystem.evaluateBaseTick();
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
						return Component.literal("CreeperGirlInventaryGUI");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
						packetBuffer.writeBlockPos(blockPos);
						packetBuffer.writeVarInt(entityId);			
						return new CreeperGirlP0InventaryGUIMenu(id, inventory, packetBuffer);
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
						return Component.literal("CreeperGirlMainGUI");
					}
					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
						packetBuffer.writeBlockPos(blockPos);
						packetBuffer.writeVarInt(entityId);					
						return new CreeperGirlP0MainGUIMenu(id, inventory, packetBuffer);				
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
		return getBasicAttributes(0.24);
	}
}
