package dev.dixmk.minepreggo.entity.preggo;

import java.util.Comparator;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

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
	            
	            if (currentHungryTimer >= totalTicksOfHungry) {
	            	preggoMob.setHungryTimer(0);
	            	preggoMob.setHungry(currentHungry + 1);
	            }
	            else {
	            	preggoMob.setHungryTimer(currentHungryTimer + timerIncrement);
	            }
	                    
	            if (currentHungry >= MIN_HUNGRY_TO_HEAL
	            		&& preggoMob.getHealth() < preggoMob.getMaxHealth()) {     	
	            	if (preggoMob.getHealingTimer() >= IPreggoMob.HEALING_COOLDOWN_DURATION) {
		            	preggoMob.heal(1F);
		            	preggoMob.setHungry(currentHungry - 1);
		            	preggoMob.setHealingTimer(0);
	            	}
	            	else {
		            	preggoMob.setHealingTimer(preggoMob.getHealingTimer() + 1);
	            	}
	            }            
	        } 
	        else {
	        	preggoMob.setSavage(true);
	        }
	    } else {
	    	/*
	    	if (preggoMob.isTame() && Math.random() < 0.0065) {
	        }
	    	 */
	    	
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
		
	private final Result evaluatePregnancyBeginnigTimer() {			    	
		if (preggoMob.isPregnant()) {
					
	        if (preggoMob.getPregnancyTimer() >= MinepreggoModConfig.getTicksToStartPregnancy()) {
	        	startPregnancy();
	        	return Result.SUCCESS;
	        } else {
	        	preggoMob.setPregnancyTimer(preggoMob.getPregnancyTimer() + 1);
                if (randomSource.nextFloat() < 0.0001F
                		&& !preggoMob.hasEffect(MobEffects.CONFUSION)) {
                	preggoMob.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0, false, true));                 
                }        
                return Result.PROCESS;
	        }  
	    }
        return Result.NOTHING;
	}
	
	
	protected abstract void startPregnancy();
	
	public void evaluateOnTick() {
		final var level = preggoMob.level();
		
		if (level.isClientSide()) {
			return;
		}
		
		final var x = preggoMob.getX();
		final var y = preggoMob.getY();
		final var z = preggoMob.getZ();
		
		if (evaluatePregnancyBeginnigTimer() == Result.SUCCESS) {
			return;
		}
		
		evaluateHungryTimer(level, x, y, z, MinepreggoModConfig.getTotalTicksOfHungryP0());
	}
	
	public void evaluateRightClick(Player source) {		
		final var level = preggoMob.level();	
		if (!preggoMob.isOwnedBy(source) || level.isClientSide()) {
			return;
		}	
		
		Result result;
		
		if ((result = evaluateHungry(level, source)) != Result.NOTHING) {
			spawnParticles(level, result);
		}
	}
	
	
	public boolean canOwnerAccessGUI(Player source) {			
		return preggoMob.isOwnedBy(source)
				&& !preggoMob.isSavage()
				&& !preggoMob.isFood(source.getMainHandItem());
	}
	
	protected Result evaluateHungry(Level level, Player source) {		
	    var mainHandItem = source.getMainHandItem();
	    var currentHunger = preggoMob.getHungry();
    
	    if (currentHunger < 20) {
	        int foodValue = 0;

	        if (preggoMob.isFood(mainHandItem)) {      	           	
	        	var foodProperties = mainHandItem.getItem().getFoodProperties(mainHandItem, preggoMob);
	        	foodValue = foodProperties.getNutrition();      
	        }
         
	        if (foodValue > 0) {        	
                source.getInventory().clearOrCountMatchingItems(p -> mainHandItem.getItem() == p.getItem(), 1, source.inventoryMenu.getCraftSlots());
                currentHunger += foodValue;          
                preggoMob.setHungry(currentHunger);
	        	        	
                level.playSound(null, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.generic.eat")), SoundSource.NEUTRAL, 0.75f, 1);	          	
      
                if (preggoMob.isSavage() && preggoMob.isTame() && currentHunger >= MIN_HUNGRY_TO_TAME_AGAIN) {
                	preggoMob.setSavage(false);
                } 
                
	            return Result.SUCCESS;
	        }
	    }
	    
	    return Result.NOTHING;		
	}
	
	protected void spawnParticles(Level level, Result result) {
		
		ParticleOptions particleoptions;
			
		if (result == Result.SUCCESS)
			particleoptions = ParticleTypes.HEART;
		else if (result == Result.FAIL)
			particleoptions = ParticleTypes.SMOKE;
		else if (result == Result.ANGRY)
			particleoptions = ParticleTypes.ANGRY_VILLAGER;
		else 
			return;
					
		for(int i = 0; i < 7; ++i) {
			double d0 = randomSource.nextGaussian() * 0.02D;
			double d1 = randomSource.nextGaussian() * 0.02D;
			double d2 = randomSource.nextGaussian() * 0.02D;
			level.addParticle(particleoptions, preggoMob.getRandomX(1.0D), preggoMob.getRandomY() + 0.5D, preggoMob.getRandomZ(1.0D), d0, d1, d2);
		}
	}
	
	
	protected enum Result {
		ANGRY,
		FAIL,
		PROCESS,
		NOTHING,
		SUCCESS
	}
}

