package dev.dixmk.minepreggo.entity.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.entity.preggo.ISimplePregnancy;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.utils.PreggoMobHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
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

public abstract class AbstractMonsterPregnantCreeperGirl extends AbstractMonsterCreeperGirl implements ISimplePregnancy {

	private static final EntityDataAccessor<Boolean> DATA_HAS_PREGNANCY_PAIN = SynchedEntityData.defineId(AbstractMonsterPregnantCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	private int pregnancyPainTimer = 0;
	
	protected AbstractMonsterPregnantCreeperGirl(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_HAS_PREGNANCY_PAIN, false);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		compoundTag.putBoolean("DataHasPregnancyPain", this.getEntityData().get(DATA_HAS_PREGNANCY_PAIN));
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);	
		this.entityData.set(DATA_HAS_PREGNANCY_PAIN, compoundTag.getBoolean("DataHasPregnancyPain"));
	}
	
	@Override
	public void die(DamageSource source) {
		super.die(source);			
		PreggoMobHelper.spawnFetusesAndBabiesCreeper(this);
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "preggo_death"));
	}
	
	protected void setExplosionByCurrentPregnancyStage() {	
		if (getCurrentPregnancyStage() == PregnancyStage.P2
				|| getCurrentPregnancyStage() == PregnancyStage.P3) {
			++this.explosionRadius;
		}
		else if (getCurrentPregnancyStage() == PregnancyStage.P4
				|| getCurrentPregnancyStage() == PregnancyStage.P5) {
			++this.explosionItensity;
			++this.explosionRadius;
		}
		else if (getCurrentPregnancyStage() == PregnancyStage.P6
				|| getCurrentPregnancyStage() == PregnancyStage.P7) {
			this.explosionItensity += 2;
			this.explosionRadius += 2;
		}
	}
	
	@Override
	@Nullable	
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34297_, DifficultyInstance p_34298_, MobSpawnType p_34299_, @Nullable SpawnGroupData p_34300_, @Nullable CompoundTag p_34301_) {
		p_34300_ = super.finalizeSpawn(p_34297_, p_34298_, p_34299_, p_34300_, p_34301_); 
		this.setExplosionByCurrentPregnancyStage();	
		return p_34300_;
	}
	
	@Override
	protected void registerGoals() {	
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			public boolean canUse() {
				return super.canUse() 
					&& !hasPregnancyPain();		
			}
		});
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() 
					&& !hasPregnancyPain();		
			}
		});
		this.goalSelector.addGoal(2, new AbstractCreeperGirl.SwellGoal<>(this) {		
			@Override
			public boolean canUse() {												
				return super.canUse() 
					&& getCanExplote()
					&& !hasPregnancyPain();
			}
		});
		this.goalSelector.addGoal(3, new FloatGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() 
					&& !hasPregnancyPain();		
			}
		});
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Ocelot.class, 6F, 1, 1.2) {
			@Override
			public boolean canUse() {
				return super.canUse() 
					&& !hasPregnancyPain();		
			}
		});
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Cat.class, 6F, 1, 1.2) {
			@Override
			public boolean canUse() {
				return super.canUse() 
					&& !hasPregnancyPain();		
			}
		});	
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0) {
			@Override
			public boolean canUse() {
				return super.canUse() 
					&& !hasPregnancyPain();		
			}
		});
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8F) {
			@Override
			public boolean canUse() {
				return super.canUse() 
					&& !hasPregnancyPain();		
			}
		});
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() 
					&& !hasPregnancyPain();		
			}
		});
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true) {
			@Override
			public boolean canUse() {
				return super.canUse() 
					&& !hasPregnancyPain();		
			}
		});
	}
	
	
	@Override
	public boolean hasPregnancyPain() {
		return this.entityData.get(DATA_HAS_PREGNANCY_PAIN);
	}
	
	@Override
	public void setPregnancyPain(boolean value) {
		this.entityData.set(DATA_HAS_PREGNANCY_PAIN, value);
	}
	
	@Override
	public int getPregnancyPainTimer() {
		return this.pregnancyPainTimer;
	}

	@Override
	public void setPregnancyPainTimer(int tick) {
		this.pregnancyPainTimer = tick;
	}
}

