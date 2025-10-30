package dev.dixmk.minepreggo.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.entity.preggo.ISimplePregnancy;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.PregnancySystemConstants;
import dev.dixmk.minepreggo.utils.MathHelper;
import dev.dixmk.minepreggo.utils.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
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
import net.minecraftforge.registries.ForgeRegistries;

public abstract class AbstractMonsterPregnantCreeperGirl extends AbstractMonsterCreeperGirl implements ISimplePregnancy {
	private static final EntityDataAccessor<Boolean> DATA_HAS_PREGNANCY_PAIN = SynchedEntityData.defineId(AbstractMonsterPregnantCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	private int pregnancyPainTimer = 0;
	private final PregnancyStage currentPregnanctStage;
	private final PregnancyStage maxPregnanctStage;
	private final int totalDaysPassed;
		
	protected AbstractMonsterPregnantCreeperGirl(EntityType<? extends PreggoMob> p_21803_, Level p_21804_, PregnancyStage currentPregnancyStage, PregnancyStage maxPregnancyStage) {
		super(p_21803_, p_21804_);
		this.currentPregnanctStage = currentPregnancyStage;
		this.maxPregnanctStage = maxPregnancyStage;
		this.totalDaysPassed = ISimplePregnancy.getRandomTotalDaysPassed(currentPregnancyStage, maxPregnancyStage, this.getRandom());
		this.setExplosionByCurrentPregnancyStage();
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
		PreggoMobHelper.spawnBabyAndFetusCreepers(this);
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "preggo_death"));
	}
	
	protected void setExplosionByCurrentPregnancyStage() {	
		final var currentPregnancyStage = this.getCurrentPregnancyStage();
		
		if (currentPregnancyStage == PregnancyStage.P2
				|| currentPregnancyStage == PregnancyStage.P3) {
			++this.explosionRadius;
		}
		else if (currentPregnancyStage == PregnancyStage.P4
				|| currentPregnancyStage == PregnancyStage.P5) {
			++this.explosionItensity;
			++this.explosionRadius;
		}
		else if (currentPregnancyStage == PregnancyStage.P6
				|| currentPregnancyStage == PregnancyStage.P7) {
			this.explosionItensity += 2;
			this.explosionRadius += 2;
		}
	}
	
	@Override
	public boolean hurt(DamageSource damagesource, float amount) {	
		if (super.hurt(damagesource, amount) && !this.hasPregnancyPain() && !this.level().isClientSide()) {		
			final var p = MathHelper.sigmoid(0.1F, 0.4F, 0.1F, Mth.clamp(this.getTotalDaysPassed() /(float) PregnancySystemConstants.TOTAL_PREGNANCY_DAYS , 0, 1), 0.6F);		
			if (this.getRandom().nextFloat() < p) {
				this.setPregnancyPain(true);
			}			
			return true;
		}
		return false;
	}
	
	
	@Override
	public boolean hasCustomHeadAnimation() {
		return this.hasPregnancyPain();
	}
	
	@Override
   	public void tick() {
      super.tick();
         
      if (this.level().isClientSide()) {
    	  return;
      }

      if (this.hasPregnancyPain()) {   	  
    	  final var timer = this.getPregnancyPainTimer();
    	  if (timer > 120) {
    		  this.setPregnancyPainTimer(0);
    		  this.setPregnancyPain(false);
    	  }
    	  else {
        	  this.setPregnancyPainTimer(timer + 1);
    	  }
      }  
	}
	
	@Override
	protected void registerGoals() {
		this.addBehaviourGoals();
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasPregnancyPain();			   
			}
		});
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasPregnancyPain();			   
			}
		});

		this.goalSelector.addGoal(3, new FloatGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() 
					&& !hasPregnancyPain();		
			}
		});
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasPregnancyPain();			   
			}
		});
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8F) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
		});
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
		});
	}
	
	@Override
	protected void addBehaviourGoals() {
		this.goalSelector.addGoal(1, new AbstractCreeperGirl.SwellGoal<>(this) {		
			@Override
			public boolean canUse() {												
				return super.canUse() 
					&& canExplode()
					&& !hasPregnancyPain();
			}
		});
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, true) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasPregnancyPain();			   
			}
		});
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Ocelot.class, 6F, 1, 1.2) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasPregnancyPain();			   
			}
		});
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Cat.class, 6F, 1, 1.2) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasPregnancyPain();			   
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
	
	@Override
	public PregnancyStage getCurrentPregnancyStage() {
		return currentPregnanctStage;
	}

	@Override
	public PregnancyStage getMaxPregnancyStage() {
		return maxPregnanctStage;
	}

	@Override
	public int getTotalDaysPassed() {
		return totalDaysPassed;
	}
}

