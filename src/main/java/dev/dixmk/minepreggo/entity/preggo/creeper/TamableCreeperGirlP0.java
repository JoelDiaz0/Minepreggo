package dev.dixmk.minepreggo.entity.preggo.creeper;

import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.utils.PreggoAIHelper;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.utils.PreggoMobHelper;
import dev.dixmk.minepreggo.utils.PreggoTags;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP0InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.creeper.CreeperGirlP0MainGUIMenu;
import io.netty.buffer.Unpooled;

public class TamableCreeperGirlP0 extends AbstractTamableCreeperGirl {

	public TamableCreeperGirlP0(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0.get(), world);
	}

	public TamableCreeperGirlP0(EntityType<TamableCreeperGirlP0> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
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
		
		if (PreggoMobHelper.evaluatePreggoMobPregnancyBeginning(this)) {
			return;
		}	
		PreggoMobHelper.evaluatePreggoMobHungryTimer(this);
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
		
		if (sourceentity instanceof ServerPlayer serverPlayer) {
			if (PreggoGUIHelper.canOwnerAccessPreggoMobGUI(serverPlayer, this, PreggoTags.CREEPER_GIRL_FOOD)) {
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
				this.spawnTamingParticles(PreggoMobHelper.evaluatePreggoMobHungry(this, serverPlayer, PreggoTags.CREEPER_GIRL_FOOD));
			}	
		}

		return InteractionResult.SUCCESS;
	}
	
	
	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.24);
	}

}
