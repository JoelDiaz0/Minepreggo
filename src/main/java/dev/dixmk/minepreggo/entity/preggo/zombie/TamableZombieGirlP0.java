package dev.dixmk.minepreggo.entity.preggo.zombie;

import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.Level;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;

import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.utils.PreggoAIHelper;
import dev.dixmk.minepreggo.utils.PreggoGUIHelper;
import dev.dixmk.minepreggo.utils.PreggoMobHelper;
import dev.dixmk.minepreggo.utils.PreggoTags;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP0InventaryGUIMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.zombie.ZombieGirlP0MainGUIMenu;
import io.netty.buffer.Unpooled;

public class TamableZombieGirlP0 extends AbstractTamableZombieGirl {

	public TamableZombieGirlP0(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get(), world);
	}
	
	public TamableZombieGirlP0(EntityType<TamableZombieGirlP0> type, Level world) {
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
	protected void registerGoals() {
		super.registerGoals();
		PreggoAIHelper.setTamableZombieGirlGoals(this);
	}

	@Override
	public boolean hurt(DamageSource damagesource, float amount) {			
		boolean success = super.hurt(damagesource, amount);	
		
		if (!success) return false;

		
		//ZombieGirlP0EntityIsHurtProcedure.execute(damagesource, entity);
		return true;
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
	
		InteractionResult retval = super.mobInteract(sourceentity, hand);

		if (sourceentity instanceof ServerPlayer serverPlayer) {
						
			if (PreggoGUIHelper.canOwnerAccessGUI(serverPlayer, this, PreggoTags.ZOMBIE_GIRL_FOOD) ) {	
				
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
							packetBuffer.writeBlockPos(serverPlayer.blockPosition());
							packetBuffer.writeVarInt(entityId);			
							return new ZombieGirlP0InventaryGUIMenu(id, inventory, packetBuffer);
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
							FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
							buf.writeBlockPos(blockPos);
							buf.writeVarInt(entityId);										
							return new ZombieGirlP0MainGUIMenu(id, inventory, buf);				
						}
					}, buf -> {
					    buf.writeBlockPos(blockPos);         
					    buf.writeVarInt(entityId);
					});
				
				}		
			}	
			else {
				this.spawnTamingParticles(PreggoMobHelper.evaluatePreggoMobHungry(this, serverPlayer, PreggoTags.ZOMBIE_GIRL_FOOD));
			}
		}
			
		return retval;
	}
	
	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		return AbstractTamableZombieGirl.getBasicAttributes(0.235);
	}
	
	@Override
	public PregnancyStage getCurrentPregnancyStage() {
		return PregnancyStage.P0;
	}
}
