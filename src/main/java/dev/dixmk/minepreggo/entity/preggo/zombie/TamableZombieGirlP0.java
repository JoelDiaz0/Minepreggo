package dev.dixmk.minepreggo.entity.preggo.zombie;

import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.Level;

import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;

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
		//PreggoMobAI.setTamableZombieGirlGoals(this);
		//AbstractZombieGirlP0Entity.setZombieGirlSpecialGoal(this);
	}

	@Override
	public boolean hurt(DamageSource damagesource, float amount) {			
		boolean success = super.hurt(damagesource, amount);	
		if(!success) return false;
		Entity entity = this;
		//ZombieGirlP0EntityIsHurtProcedure.execute(damagesource, entity);
		return true;
	}

	/*
	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
	
		InteractionResult retval = super.mobInteract(sourceentity, hand);

		if (sourceentity instanceof ServerPlayer serverPlayer		
				&& sourceentity.isShiftKeyDown()
				&& this.isOwnedBy(sourceentity)	
				&& !this.entityData.get(DATA_IS_INCAPACITATED)
				&& !this.entityData.get(DATA_IS_SAVAGE)
				&& !sourceentity.getMainHandItem().is(ItemTags.create(new ResourceLocation("minepreggo:zombie_all_food")))) {
			
			NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("Zombie Girl (P0)");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
					packetBuffer.writeBlockPos(sourceentity.blockPosition());
					packetBuffer.writeByte(0);
					packetBuffer.writeVarInt(ZombieGirlP0Entity.this.getId());
					return new ZombieGirlP0InventaryGUIMenu(id, inventory, packetBuffer);
				}
			}, buf -> {
				buf.writeBlockPos(sourceentity.blockPosition());
				buf.writeByte(0);
				buf.writeVarInt(this.getId());
			});
		}
		else if (this.isOwnedBy(sourceentity)) {
			double x = this.getX();
			double y = this.getY();
			double z = this.getZ();
			Entity entity = this;
			Level world = this.level();
			ZombieGirlP0RightClickedOnEntityProcedure.execute(world, x, y, z, entity, sourceentity);
		}

		return retval;
	}
	*/

	@Override
	public void baseTick() {
		super.baseTick();
		// ZombieGirlP0OnEntityTickUpdateProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
		this.refreshDimensions();
	}

	@Override
	public EntityDimensions getDimensions(Pose p_33597_) {
		return super.getDimensions(p_33597_).scale(1F);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.updateSwingTime();
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
