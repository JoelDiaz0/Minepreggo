package dev.dixmk.minepreggo.entity.preggo;

import java.util.Comparator;
import java.util.List;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.utils.PreggoMobHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class PregnancySystemP1<
	E extends TamableAnimal & IPreggoMob & IPregnancySystem & IPregnancyP1> extends PreggoMobSystem<E> {

	protected PregnancySystemP1(@Nonnull E preggoMob) {
		super(preggoMob);
	}
	
	protected Result evaluatePregnancyStageChange() {
	    if (preggoMob.getDaysPassed() >= preggoMob.getDaysByStage()) {
	    	this.changePregnancyStage();
	    	MinepreggoMod.LOGGER.debug("pregnancy stage change={}, preggomob={}, currentPregnancyStage={}",
	    			true, preggoMob.getClass().getName(), preggoMob.getCurrentPregnancyStage());
	        return Result.SUCCESS;
	    }
	    return Result.NOTHING;
	}
	
	protected void evaluatePregnancyTimer() {
        if (preggoMob.getPregnancyTimer() >= MinepreggoModConfig.getTotalTicksByDay()) {
        	preggoMob.setPregnancyTimer(0);
        	preggoMob.setDaysPassed(preggoMob.getDaysPassed() + 1);
        	preggoMob.setDaysToGiveBirth(preggoMob.getDaysToGiveBirth() - 1);
        } else {
        	preggoMob.setPregnancyTimer(preggoMob.getPregnancyTimer() + 1);
        }
	}
	
	protected Result evaluateMiscarriage(ServerLevel serverLevel, double x, double y, double z, final int totalTicksOfMiscarriage) {
   	    
	    if (preggoMob.getPregnancyPain() == PregnancyPain.MISCARRIAGE) {
	        if (preggoMob.getPregnancyPainTimer() < totalTicksOfMiscarriage) {
	        	preggoMob.setPregnancyPainTimer(preggoMob.getPregnancyPainTimer() + 1);
	        	
	        	serverLevel.addParticle(ParticleTypes.FALLING_DRIPSTONE_LAVA, x, (y + preggoMob.getBbHeight() * 0.35), z, 0, 1, 0);	       
	        
	        } else {
	        	return Result.SUCCESS;
	        }
	        return Result.PROCESS; 
	    }
	    
	    return Result.NOTHING;
	}
	
	protected void evaluateCravingTimer(final int totalTicksOfCraving) {   				
		if (preggoMob.getCraving() < 20) {
	        if (preggoMob.getCravingTimer() >= totalTicksOfCraving) {
	        	preggoMob.setCraving(preggoMob.getCraving() + 1);
	        	preggoMob.setCravingTimer(0);
	        }
	        else {
	        	preggoMob.setCravingTimer(preggoMob.getCravingTimer() + 1);
	        }
		}	
	}
	
	protected void evaluateAngry(Level level, double x, double y, double z, final float angerProbability) {

	    if (!preggoMob.isAngry()) {
	        if (activateAngry()) {
	        	preggoMob.setAngry(true);
	        }
	    } else {
	        if (!PreggoMobHelper.hasValidTarget(preggoMob) && randomSource.nextFloat() < angerProbability) {
	            final Vec3 center = new Vec3(x, y, z);      
	            List<Player> owner = level.getEntitiesOfClass(Player.class, new AABB(center, center).inflate(12), preggoMob::isOwnedBy)
	                    .stream()
	                    .sorted(Comparator.comparingDouble(entcnd -> entcnd.distanceToSqr(center)))
	                    .toList();	           
	            if (!owner.isEmpty()) {
	            	preggoMob.setTarget(owner.get(0));
	            }
	        }
	    }
	}

	protected void evaluatePregnancyPains() {
		if (preggoMob.getPregnancyPain() == PregnancyPain.NONE) {		
			if (randomSource.nextFloat() < PregnancySystemConstants.LOW_MORNING_SICKNESS_PROBABILITY) {
				preggoMob.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);		
			}		
		} 
		else {
			if (preggoMob.getPregnancyPainTimer() >= PregnancySystemConstants.TOTAL_TICKS_MORNING_SICKNESS) {
				preggoMob.setPregnancyPainTimer(0);
				preggoMob.setPregnancyPain(PregnancyPain.NONE);
			}
			else {
				preggoMob.setPregnancyPainTimer(preggoMob.getPregnancyPainTimer() + 1);
			}
		}
	}
	
	protected void evaluatePregnancySymptoms() {		
		if (preggoMob.getPregnancySymptom() == PregnancySymptom.NONE) {
			if (preggoMob.getCraving() >= PregnancySystemConstants.ACTIVATE_CRAVING_SYMPTOM) {		
				preggoMob.setPregnancySymptom(PregnancySymptom.CRAVING);	
			}
		}
		else {
			if (preggoMob.getCravingChosen() == Craving.NONE) {
				preggoMob.setCravingChosen(CravingFactory.getRandomCraving(randomSource));
			}
		}
	}
	
	@Override
	public void evaluateOnTick() {		
		
		final var level = preggoMob.level();
		
		if (level.isClientSide()) {			
			return;
		}
		
		if (evaluatePregnancyStageChange() == Result.SUCCESS) {
			return;
		}
		
		final var x =  preggoMob.getX();
		final var y = preggoMob.getY();
		final var z = preggoMob.getZ();
				
		if (level instanceof ServerLevel serverLevel
				&& evaluateMiscarriage(serverLevel, x, y, z, PregnancySystemConstants.TOTAL_TICKS_MISCARRIAGE) == Result.SUCCESS) {
			return; 
		}
		
		this.evaluatePregnancyTimer();
		this.evaluateHungryTimer(level, x, y, z, MinepreggoModConfig.getTotalTicksOfHungryP1());
		
		
		this.evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP1());
		this.evaluateAngry(level, x, y, z, PregnancySystemConstants.LOW_ANGER_PROBABILITY);
		
		this.evaluatePregnancySymptoms();
		this.evaluatePregnancyPains();
	}
	
	protected boolean activateAngry() {
		return preggoMob.getCraving() >= 20 || preggoMob.getHungry() <= 2;
	}

	@Override
	public InteractionResult evaluateRightClick(Player source) {	
		final var level = preggoMob.level();
		
		if (!preggoMob.isOwnedBy(source) || level.isClientSide() || !(level instanceof ServerLevel serverLevel)) {
			return InteractionResult.PASS;
		}			
		
		Result result;
		
		if ((result = evaluateHungry(serverLevel, source)) != Result.NOTHING
				|| (result = evaluateCraving(serverLevel, source)) != Result.NOTHING) {
			spawnParticles(serverLevel, result);
		}
		
		return onRightClickResult(result);	
	}
	
	
	@Override
	public boolean canOwnerAccessGUI(Player source) {	
		return super.canOwnerAccessGUI(source) && !preggoMob.isIncapacitated();
	}
	
	protected Result evaluateCraving(Level level, Player source) {
		if (preggoMob.getPregnancySymptom() != PregnancySymptom.CRAVING) {
			return Result.NOTHING;
		}
		
	    var mainHandItem = source.getMainHandItem().getItem();
	    var currentCraving = preggoMob.getCraving();
	    
	    if (currentCraving > PregnancySystemConstants.DESACTIVATE_CRAVING_SYMPTOM) {    	

	    	if (!(mainHandItem instanceof ICraving craving)) {
	    		return Result.ANGRY;
	    	}
	    	    	
	    	if (preggoMob.isValidCraving(preggoMob.getCravingChosen(), mainHandItem)) {
	           
	    		source.getInventory().clearOrCountMatchingItems(p -> mainHandItem == p.getItem(), 1, source.inventoryMenu.getCraftSlots());
	            currentCraving = Math.max(0, currentCraving - craving.getGratification());
                preggoMob.setCraving(currentCraving);
	            preggoMob.setHungry(Math.min(preggoMob.getHungry() + 2, 20));
                
            	level.playSound(null, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.generic.eat")), SoundSource.NEUTRAL, 0.75f, 1);	
                
	            if (currentCraving <= PregnancySystemConstants.DESACTIVATE_CRAVING_SYMPTOM) {
	    	    	preggoMob.setPregnancySymptom(PregnancySymptom.NONE);
	            }
	    		     
	            return Result.SUCCESS; 
	    	} else {
	    		return Result.FAIL;
	    	}
	    }
	    
	    return Result.NOTHING;
	}
	
	protected abstract void changePregnancyStage();
	
	protected abstract void finishMiscarriage();
	
	@Override
	protected final void startPregnancy() {}
}

