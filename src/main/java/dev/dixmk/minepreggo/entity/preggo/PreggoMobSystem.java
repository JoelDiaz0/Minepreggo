package dev.dixmk.minepreggo.entity.preggo;

import java.util.Comparator;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public abstract class PreggoMobSystem<E extends TamableAnimal & IPreggoMob> {
	
	public static final int MIN_HUNGRY_TO_HEAL = 16;
	public static final int MIN_HUNGRY_TO_TAME_AGAIN = 12;
	
	protected final RandomSource randomSource;
	
	protected final E preggoMob;
	
	
	protected PreggoMobSystem(@Nonnull E preggopreggoMob) {
		this.preggoMob = preggopreggoMob;	
		this.randomSource = preggopreggoMob.getRandom();
	}
	
	protected void evaluateHungryTimer(Level level, double x, double y, double z, final int totalTicksOfHungry) {

	    final var currentHungry = preggoMob.getHungry();
	    final var currentHungryTimer = preggoMob.getHungryTimer();
	    
	    if (!preggoMob.isSavage()) {
	        if (currentHungry > 0) {
	            int timerIncrement = 1;
	            if (preggoMob.getDeltaMovement().x() != 0 || preggoMob.getDeltaMovement().z() != 0) {
	                timerIncrement += 1;              
		            if (preggoMob.isInWater()) {
		                timerIncrement += 2;
		            }
	            }
	            
	            if (currentHungryTimer < totalTicksOfHungry) {
	            	preggoMob.setHungryTimer(currentHungryTimer + timerIncrement);
	            } else {
	            	preggoMob.setHungryTimer(0);
	            	preggoMob.setHungry(currentHungry + 1);
	            }

	            if (currentHungry >= MIN_HUNGRY_TO_HEAL && preggoMob.getHealth() < preggoMob.getMaxHealth()) {
	            	preggoMob.heal(1F);
	            	preggoMob.setHungry(currentHungry - 1);
	            }
	            
	        } 
	        else {
	            if (!level.isClientSide()) {
	            	preggoMob.setSavage(true);
	            }
	        }
	    } else {
	        if (preggoMob.isTame() && Math.random() < 0.0065) {
	            // SpawnBrainParticlesProcedure.execute(world, zombieGirl, 0);
	        }

	        if (preggoMob.isTame() && preggoMob.getTarget() == null) {
	            final var center = new Vec3(x, y, z);
	        		
	            Player player = level.getEntitiesOfClass(Player.class, new AABB(center, center).inflate(12), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(entcnd -> entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null);
	        	
	        	if (player != null)
	        		preggoMob.setTarget(player);
	        }
	    }
	}
		
	private final Result evaluatePregnancyBeginnigTimer(Level level) {			    	
		if (preggoMob.isPregnant()) {
					
	        if (!level.isClientSide() && preggoMob.getPregnancyTimer() >= MinepreggoModConfig.getTicksToStartPregnancy()) {
	        	startPregnancy();
	        	return Result.SUCCESS;
	        } else {
	        	preggoMob.setPregnancyTimer(preggoMob.getPregnancyTimer() + 1);
                if (!level.isClientSide()
                		&& randomSource.nextFloat() < 0.0001F
                		&& !preggoMob.hasEffect(MobEffects.CONFUSION)) {
                	preggoMob.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0, false, true));                 
                }        
                return Result.PROCESS;
	        }  
	    }
        return Result.NOTHING;
	}
	
	
	protected abstract void startPregnancy();
	

	public void evaluate() {
		
		final var level = preggoMob.level();
		final var x = preggoMob.getX();
		final var y = preggoMob.getY();
		final var z = preggoMob.getZ();
		
		if (evaluatePregnancyBeginnigTimer(level) == Result.SUCCESS) {
			return;
		}
		
		evaluateHungryTimer(level, x, y, z, MinepreggoModConfig.getTotalTicksOfHungryP0());
	}
	
	protected enum Result {
		PROCESS,
		NOTHING,
		SUCCESS
	}
}

