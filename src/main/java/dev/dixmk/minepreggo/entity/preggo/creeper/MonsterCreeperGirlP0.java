package dev.dixmk.minepreggo.entity.preggo.creeper;

import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;

import java.util.UUID;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;

public class MonsterCreeperGirlP0 extends AbstractMonsterCreeperGirl {

	private static final UUID SPEED_MODIFIER_BABY_UUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
	private static final AttributeModifier SPEED_MODIFIER_BABY = new AttributeModifier(SPEED_MODIFIER_BABY_UUID, "Baby speed boost", 0.2D, AttributeModifier.Operation.MULTIPLY_BASE);
	private static final EntityDataAccessor<Boolean> DATA_BABY_ID = SynchedEntityData.defineId(MonsterCreeperGirlP0.class, EntityDataSerializers.BOOLEAN);
	
	public MonsterCreeperGirlP0(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P0.get(), world);
	}

	public MonsterCreeperGirlP0(EntityType<MonsterCreeperGirlP0> type, Level world) {
		super(type, world);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);	
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_BABY_ID, false);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("IsBaby", this.isBaby());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("IsBaby"))
			this.setBaby(compound.getBoolean("IsBaby"));
	}
	
	@Override
	public boolean canBeTamedByPlayer() {
		return true;
	}
	
	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {					
		ItemStack itemstack = sourceentity.getItemInHand(hand);
		if  (itemstack.getItem() instanceof SpawnEggItem) 
			return InteractionResult.sidedSuccess(this.level().isClientSide());

		return super.mobInteract(sourceentity, hand);	
	}
	
	@Override
	public boolean isBaby() {
		return this.getEntityData().get(DATA_BABY_ID);
	}
	
	@Override
	public int getExperienceReward() {
		if (this.isBaby()) {
			this.xpReward = (int)((double)this.xpReward * 2.5D);
		}

		return super.getExperienceReward();
	}
	
	@Override
	protected float getStandingEyeHeight(Pose p_34313_, EntityDimensions p_34314_) {
		return this.isBaby() ? 0.93F : 1.74F;
	}
	
	@Override
	public void setBaby(boolean p_34309_) {
		this.getEntityData().set(DATA_BABY_ID, p_34309_);
		if (this.level() != null && !this.level().isClientSide) {
			AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
			attributeinstance.removeModifier(SPEED_MODIFIER_BABY);
	        this.explosionRadius = 3;
			if (p_34309_) {
				attributeinstance.addTransientModifier(SPEED_MODIFIER_BABY);
		        this.explosionRadius = 1;
			}
		}

	}
	
	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> p_34307_) {
		if (DATA_BABY_ID.equals(p_34307_)) {
			this.refreshDimensions();
		}

		super.onSyncedDataUpdated(p_34307_);
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return getDefaultSoundEvent();
	}
	
	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
		MonsterCreeperGirlP0 retval = MinepreggoModEntities.MONSTER_CREEPER_GIRL_P0.get().create(serverWorld);
		retval.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(retval.blockPosition()), MobSpawnType.BREEDING, null, null);
		retval.setBaby(true);
		return retval;
	}
	
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public void baseTick() {
		super.baseTick();
		this.refreshDimensions();
	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.updateSwingTime();
	}

	@SuppressWarnings("deprecation")
	public static void init() {
		SpawnPlacements.register(MinepreggoModEntities.MONSTER_CREEPER_GIRL_P0.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
	}

	@Override
	@Nullable	
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34297_, DifficultyInstance p_34298_, MobSpawnType p_34299_, @Nullable SpawnGroupData p_34300_, @Nullable CompoundTag p_34301_) {
		p_34300_ = super.finalizeSpawn(p_34297_, p_34298_, p_34299_, p_34300_, p_34301_);

	    if (p_34297_.getRandom().nextFloat() < MinepreggoModConfig.getBabyCreeperGirlProbability()) 
	        this.setBaby(true);
	       
		return p_34300_;
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.24);
	}

	@Override
	public PregnancyStage getCurrentStage() {
		return PregnancyStage.P0;
	}
	
	@Override
	public SimplePregnancyData getSimplePregnancyData() {
		return new SimplePregnancyData(0, 0, 0);
	}
}
