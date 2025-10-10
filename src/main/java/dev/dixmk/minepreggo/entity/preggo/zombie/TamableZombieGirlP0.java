package dev.dixmk.minepreggo.entity.preggo.zombie;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

import java.util.UUID;

import dev.dixmk.minepreggo.entity.preggo.PostPregnancy;
import dev.dixmk.minepreggo.entity.preggo.PreggoMobSystem;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.utils.PreggoMobHelper;

public class TamableZombieGirlP0 extends AbstractTamableZombieGirl<PreggoMobSystem<TamableZombieGirlP0>> {

	private static final UUID SPEED_MODIFIER_TIRENESS_UUID = UUID.fromString("c80701e3-c90d-420e-97c2-4f232fcbaffe");
	private static final AttributeModifier SPEED_MODIFIER_TIRENESS = new AttributeModifier(SPEED_MODIFIER_TIRENESS_UUID, "Tireness speed", -0.1D, AttributeModifier.Operation.MULTIPLY_BASE);		
	private static final UUID MAX_HEALTH_MODIFIER_TIRENESS_UUID = UUID.fromString("598672e2-1413-4f1e-8e77-3166716d0bd9");
	private static final AttributeModifier MAX_HEALTH_MODIFIER_TIRENESS = new AttributeModifier(MAX_HEALTH_MODIFIER_TIRENESS_UUID, "Tireness max health", -0.3D, AttributeModifier.Operation.MULTIPLY_BASE);	

	private static final EntityDataAccessor<PostPregnancy> DATA_POST_PREGNANCY = SynchedEntityData.defineId(TamableZombieGirlP0.class, MinepreggoModEntityDataSerializers.POST_PREGNANCY);
	private int postPregnancyTimer = 0;
	private static final int POST_PREGNANCY_COOLDOWN = 6000;
	
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
	protected PreggoMobSystem<TamableZombieGirlP0> createPreggoMobSystem() {
		return new PreggoMobSystem<>(this) {
			@Override
			protected void startPregnancy() {
				if (preggoMob.level() instanceof ServerLevel serverLevel) {
					var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get().spawn(serverLevel, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), MobSpawnType.CONVERSION);		
					PreggoMobHelper.transferPreggoMobBasicData(preggoMob, zombieGirl);			
					PreggoMobHelper.transferPreggoMobInventary(preggoMob, zombieGirl);
					PreggoMobHelper.transferAttackTarget(preggoMob, zombieGirl);
					preggoMob.discard();
				}
			}		
		};
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_POST_PREGNANCY, PostPregnancy.NONE);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("DataPostPregnancy", this.entityData.get(DATA_POST_PREGNANCY).ordinal());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.entityData.set(DATA_POST_PREGNANCY, PostPregnancy.values()[compound.getInt("DataPostPregnancy")]);
	}
	
	@Override
   	public void aiStep() {
      super.aiStep();
      
      if (this.level().isClientSide()) {
    	  return;
      }
      
      if (this.entityData.get(DATA_POST_PREGNANCY) != PostPregnancy.NONE) {	  
    	  if (postPregnancyTimer > POST_PREGNANCY_COOLDOWN) {
    		  postPregnancyTimer = 0;
    		  this.entityData.set(DATA_POST_PREGNANCY, PostPregnancy.NONE);
    		  removePostPregnancyAttibutes(this);
    	  }
    	  else {
    		  ++postPregnancyTimer;
    	  }   	  
      } 
	}

	public static AttributeSupplier.Builder createAttributes() {
		return AbstractTamableZombieGirl.getBasicAttributes(0.235);
	}
	
	@Override
	public PregnancyStage getCurrentPregnancyStage() {
		return PregnancyStage.P0;
	}
	
	public static TamableZombieGirlP0 spawnPostMiscarriage(ServerLevel serverLevel, double x, double y, double z) {
		var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);	
		zombieGirl.getEntityData().set(DATA_POST_PREGNANCY, PostPregnancy.MISCARRIAGE);
		applyPostPregnancyAttibutes(zombieGirl);
		return zombieGirl;
	}
	
	public static TamableZombieGirlP0 spawnPostPartum(ServerLevel serverLevel, double x, double y, double z) {
		var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);	
		zombieGirl.getEntityData().set(DATA_POST_PREGNANCY, PostPregnancy.PARTUM);
		applyPostPregnancyAttibutes(zombieGirl);
		return zombieGirl;
	}
	
	public static<E extends AbstractTamablePregnantZombieGirl<?>> void applyDefaultPostMiscarriage(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			var zombieGirl = TamableZombieGirlP0.spawnPostMiscarriage(serverLevel, source.getX(), source.getY(), source.getZ());
			PreggoMobHelper.transferPreggoMobBasicData(source, zombieGirl);
			PreggoMobHelper.transferPreggoMobInventary(source, zombieGirl);
			PreggoMobHelper.transferAttackTarget(source, zombieGirl);
			source.discard();
		}
	}
	
	public static<E extends AbstractTamablePregnantZombieGirl<?>> void applyDefaultPostPartum(E source) {
		if (source.level() instanceof ServerLevel serverLevel) {
			var zombieGirl = TamableZombieGirlP0.spawnPostPartum(serverLevel, source.getX(), source.getY(), source.getZ());
			PreggoMobHelper.transferPreggoMobBasicData(source, zombieGirl);
			PreggoMobHelper.transferPreggoMobInventary(source, zombieGirl);
			PreggoMobHelper.transferAttackTarget(source, zombieGirl);
			source.discard();
		}
	}
	
	private static void applyPostPregnancyAttibutes(TamableZombieGirlP0 zombieGirl) {
		AttributeInstance speed = zombieGirl.getAttribute(Attributes.MOVEMENT_SPEED);
		AttributeInstance maxHealth = zombieGirl.getAttribute(Attributes.MAX_HEALTH);	
		speed.addTransientModifier(TamableZombieGirlP0.SPEED_MODIFIER_TIRENESS);	
		maxHealth.addTransientModifier(TamableZombieGirlP0.MAX_HEALTH_MODIFIER_TIRENESS);	
	}
	
	private static void removePostPregnancyAttibutes(TamableZombieGirlP0 zombieGirl) {
		AttributeInstance speed = zombieGirl.getAttribute(Attributes.MOVEMENT_SPEED);
		AttributeInstance maxHealth = zombieGirl.getAttribute(Attributes.MAX_HEALTH);	
		speed.removeModifier(TamableZombieGirlP0.SPEED_MODIFIER_TIRENESS);	
		maxHealth.removeModifier(TamableZombieGirlP0.MAX_HEALTH_MODIFIER_TIRENESS);	
	}
	
}
