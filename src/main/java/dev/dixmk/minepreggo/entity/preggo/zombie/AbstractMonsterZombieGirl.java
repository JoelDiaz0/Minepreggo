package dev.dixmk.minepreggo.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.entity.preggo.ISimplePregnancy;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class AbstractMonsterZombieGirl extends AbstractZombieGirl implements ISimplePregnancy {

	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState attackAnimationState = new AnimationState();

	protected AbstractMonsterZombieGirl(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_);
	}
	
	@Override
	public boolean canBeTamedByPlayer() {
		return false;
	}
		
	public boolean canBreakDoors() {
		return false;
	}
	
	@Override
	public void die(DamageSource source) {
		super.die(source);
		/*
		if (getCurrentStage() != PregnancyStage.P0) {		
			PreggoMobHelper.simpleSpawnFetusesAndBabiesZombie(this);		
		}		
		*/
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "preggo_death"));
	}
	
	public SoundEvent getDefaultSoundEvent() {
		return super.getDeathSound();
	}
	
	@Override
	public void tick() {
	    super.tick();
	    if (this.level().isClientSide && !this.idleAnimationState.isStarted()) {
	    	this.idleAnimationState.start(this.tickCount);
	    }  
	}
	
    @Override
    public void handleEntityEvent(byte id) {
        if (id == 4) { // vanilla "swing/attack" animation event
            this.attackAnimationState.start(this.tickCount); 
        }
        else {
            super.handleEntityEvent(id);
        }
    }
	
	@Override
	protected void registerGoals() {
		super.registerGoals();	
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
			}
		});	
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers(AbstractMonsterZombieGirl.class));	
		this.goalSelector.addGoal(2, new RestrictSunGoal(this));
		this.goalSelector.addGoal(2, new FleeSunGoal(this, 1.1D));	
	    this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false, false));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, IronGolem.class, false, false));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));	
		this.goalSelector.addGoal(7, new AbstractZombieGirl.ZombieGirlAttackTurtleEggGoal(this, 1.0, 3));
		this.goalSelector.addGoal(9, new WaterAvoidingRandomStrollGoal(this, 1.0));
		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
	}
	
	
	protected static AttributeSupplier.Builder getBasicAttributes(double movementSpeed) {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 20)
				.add(Attributes.ARMOR, 1D)
				.add(Attributes.ATTACK_DAMAGE, 2D)
				.add(Attributes.FOLLOW_RANGE, 35D)
				.add(Attributes.MOVEMENT_SPEED, movementSpeed);
	}
	
}
