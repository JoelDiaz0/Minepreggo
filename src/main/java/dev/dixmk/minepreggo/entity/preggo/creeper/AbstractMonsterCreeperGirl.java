package dev.dixmk.minepreggo.entity.preggo.creeper;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public abstract class AbstractMonsterCreeperGirl extends AbstractCreeperGirl {
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
	@Nullable	
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34297_, DifficultyInstance p_34298_, MobSpawnType p_34299_, @Nullable SpawnGroupData p_34300_, @Nullable CompoundTag p_34301_) {
		p_34300_ = super.finalizeSpawn(p_34297_, p_34298_, p_34299_, p_34300_, p_34301_); 
		this.setRandomCombatMode();
		return p_34300_;
	}
	
	
	@Override
	protected void registerGoals() {
		super.registerGoals();		
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
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
	
	public static boolean checkMonsterCreeperGirlSpawnRules(EntityType<? extends AbstractMonsterCreeperGirl> p_219014_, ServerLevelAccessor p_219015_, MobSpawnType p_219016_, BlockPos p_219017_, RandomSource p_219018_) {
		return p_219015_.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(p_219015_, p_219017_, p_219018_) && checkMobSpawnRules(p_219014_, p_219015_, p_219016_, p_219017_, p_219018_);
	}
}
