package dev.dixmk.minepreggo.entity.preggo.creeper;


import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.entity.preggo.ISimplePregnancy;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class AbstractMonsterCreeperGirl extends AbstractCreeperGirl implements ISimplePregnancy {
	private CombatMode basicCombatMode = CombatMode.EXPLODE;
	
	protected AbstractMonsterCreeperGirl(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_);	
	}

	@Override
	public boolean canBeTamedByPlayer() {
		return false;
	}
	
	@Override
	public boolean getCanExplote() {
		switch (this.basicCombatMode) {
		case FIGHT_AND_EXPLODE: {
			return this.getHealth() <= this.getMaxHealth() * 0.6F;
		}
		case DONT_EXPLODE: {
			return false;
		}	
		default:
			return true;
		}
	}
	
	public CombatMode getCombatMode() {
		return this.basicCombatMode;
	}
	
	protected void setRandomCombatMode() {
		
		final var p = this.getRandom().nextFloat();
		
	    if (p < 0.4F) {    	
	    	this.basicCombatMode = CombatMode.FIGHT_AND_EXPLODE;
	    }
	    else if (p < 0.9F) {
	    	this.maxDistance = 2D;
	    	this.basicCombatMode = CombatMode.EXPLODE;
	    }
	    else {
	    	this.basicCombatMode = CombatMode.DONT_EXPLODE;
	    }
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "preggo_death"));
	}
	
	public SoundEvent getDefaultSoundEvent() {
		return super.getDeathSound();
	}
	
	/*
	@Override
	public void die(DamageSource source) {
		super.die(source);			
		if (getCurrentStage() != PregnancyStage.P0) {		
			PreggoMobHelper.simpleSpawnFetusesAndBabiesCreeper(this);
		}
	}
	*/
	
	protected void setExplosion() {	
		int basicExplosionItensity = this.explosionItensity;
		int basicExplosionRadius = this.explosionRadius;

		if (getCurrentStage() == PregnancyStage.P2
				|| getCurrentStage() == PregnancyStage.P3) {
			++basicExplosionRadius;
		}
		else if (getCurrentStage() == PregnancyStage.P4
				|| getCurrentStage() == PregnancyStage.P5) {
			++basicExplosionItensity;
			++basicExplosionRadius;
		}
		else if (getCurrentStage() == PregnancyStage.P6
				|| getCurrentStage() == PregnancyStage.P7) {
			basicExplosionItensity += 2;
			basicExplosionRadius += 2;
		}
		
		this.explosionItensity = basicExplosionItensity;
		this.explosionRadius = basicExplosionRadius;
	}
	
	@Override
	@Nullable	
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34297_, DifficultyInstance p_34298_, MobSpawnType p_34299_, @Nullable SpawnGroupData p_34300_, @Nullable CompoundTag p_34301_) {
		p_34300_ = super.finalizeSpawn(p_34297_, p_34298_, p_34299_, p_34300_, p_34301_);
	      
		this.setRandomCombatMode();
		this.setExplosion();
		
		return p_34300_;
	}
	
	
	@Override
	protected void registerGoals() {
		super.registerGoals();		
		this.setBasicGoals();
	}
	
	private void setBasicGoals() {
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
			}
		});
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(2, new AbstractCreeperGirl.SwellGoal<>(this) {		
			@Override
			public boolean canUse() {												
				return super.canUse() && getCanExplote();
			}
		});
		this.goalSelector.addGoal(3, new FloatGoal(this));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Ocelot.class, 6F, 1, 1.2));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Cat.class, 6F, 1, 1.2));	
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}
		
	protected static AttributeSupplier.Builder getBasicAttributes(double movementSpeed) {
		return Mob.createMobAttributes()
		.add(Attributes.MAX_HEALTH, 20)
		.add(Attributes.ATTACK_DAMAGE, 2)
		.add(Attributes.FOLLOW_RANGE, 35)
		.add(Attributes.MOVEMENT_SPEED, movementSpeed);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		return null;
	}
}
