package dev.dixmk.minepreggo.world.entity.monster;

import java.util.List;

import dev.dixmk.minepreggo.utils.PreggoMobHelper;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.phys.AABB;

import java.util.EnumSet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.level.GameRules;

public interface Ill {

	void tameByIllager(ScientificIllager illagerScientific);
	
	void removeIllagerOwner();
		
	static <E extends TamableAnimal & Ill> void addBehaviourGoals(E ill) {	
		ill.goalSelector.addGoal(1, new OwnerHurtByTargetGoal(ill));
		ill.targetSelector.addGoal(2, new OwnerHurtTargetGoal(ill));
		ill.goalSelector.addGoal(6, new FollowOwnerGoal(ill, 1.2D, 8F, 3F, false) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !PreggoMobHelper.hasValidTarget(this.tamable);
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse()
				&& !PreggoMobHelper.isTargetStillValid(this.tamable);
			}		
		});	
		ill.targetSelector.addGoal(3, new Ill.IllMobHurtByTargetGoal(ill, 12D));
		ill.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(ill, 1.0D, 0.0F) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& ill.getOwner() == null || !ill.isTame();
			}
		});
		
		ill.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(ill, AbstractVillager.class, false, false));
		ill.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(ill, IronGolem.class, false, false));
	}
	
	class IllMobHurtByTargetGoal extends TargetGoal {
	    private static final TargetingConditions HURT_BY_TARGETING = TargetingConditions.forCombat().ignoreLineOfSight().ignoreInvisibilityTesting();
	    private final double alertRangeY;
	    private int timestamp;
	    
	    public IllMobHurtByTargetGoal(PathfinderMob p_26039_, double alertRangeY) {
			super(p_26039_, true);
	        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
	        this.alertRangeY = alertRangeY;
	    }
	    
	    @Override
	    public boolean canUse() {
	        int i = this.mob.getLastHurtByMobTimestamp();
	        LivingEntity livingentity = this.mob.getLastHurtByMob();
	        if (i != this.timestamp && livingentity != null) {
	            if (livingentity.getType() == EntityType.PLAYER && this.mob.level().getGameRules().getBoolean(GameRules.RULE_UNIVERSAL_ANGER)) {
	                return false;
	            }
	            else {
                    if (livingentity instanceof Ill || livingentity instanceof AbstractIllager) {
                        return false;
                    }
	                return this.canAttack(livingentity, HURT_BY_TARGETING);
	            }
	        }
	        else {
	            return false;
	        }
	    }

	    @Override
	    public void start() {
	        this.mob.setTarget(this.mob.getLastHurtByMob());
	        this.targetMob = this.mob.getTarget();
	        this.timestamp = this.mob.getLastHurtByMobTimestamp();
	        this.unseenMemoryTicks = 300;
	        this.alertOthers();


	        super.start();
	    }

	    
	    protected void alertOthers() {
	        double d0 = this.getFollowDistance();
	        AABB aabb = AABB.unitCubeFromLowerCorner(this.mob.position()).inflate(d0, alertRangeY, d0);
	        List<Mob> list = this.mob.level().getEntitiesOfClass(Mob.class, aabb, EntitySelector.NO_SPECTATORS);
	        
	        for (var mob : list) {
                if (this.mob != mob
                		&& !(mob.isAlliedTo(this.mob.getLastHurtByMob()))
                		&& (mob instanceof Ill || mob instanceof AbstractIllager)) {
                		this.alertOther(mob, this.mob.getLastHurtByMob());
                }   	
	        }
	    }

	    protected void alertOther(Mob p_26042_, LivingEntity p_26043_) {
	        p_26042_.setTarget(p_26043_);
	    }
	}
}
