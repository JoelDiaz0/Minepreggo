package dev.dixmk.minepreggo.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class PregnancySystemP2<E extends TamableAnimal
	& IPreggoMob & IPregnancySystem & IPregnancyP2> extends PregnancySystemP1<E> {
	
	protected PregnancySystemP2(@Nonnull E preggoMob) {
		super(preggoMob);
	}
	
	protected void evaluateMilkingTimer(final int totalTicksOfMilking) {
		if (preggoMob.getMilking() < 20) {
	        if (preggoMob.getMilkingTimer() >= totalTicksOfMilking) {
	        	preggoMob.setMilking(preggoMob.getMilking() + 1);
	        	preggoMob.setMilkingTimer(0);
	        }
	        else {
	        	preggoMob.setMilkingTimer(preggoMob.getMilkingTimer() + 1);
	        }
		}	
	}
	
	@Override
	protected void evaluatePregnancySymptoms(Level level) {		
		if (preggoMob.getPregnancySymptom() == PregnancySymptom.NONE) {		
			if (!level.isClientSide()) {			
				if (preggoMob.getCraving() >= PregnancySystemConstants.ACTIVATE_CRAVING_SYMPTOM) {		
					preggoMob.setPregnancySymptom(PregnancySymptom.CRAVING);	
				}
				else if (preggoMob.getMilking() >= PregnancySystemConstants.ACTIVATE_MILKING_SYMPTOM) {
					preggoMob.setPregnancySymptom(PregnancySymptom.MILKING);
				}
			}
		}
		else {
			if (preggoMob.getPregnancySymptom() == PregnancySymptom.CRAVING
					&& preggoMob.getCravingChosen() == Craving.NONE) {
				preggoMob.setCravingChosen(CravingFactory.getRandomCraving(randomSource));
			}
		}
	}
	
	@Override
	protected void evaluatePregnancyPains(Level level) {
		if (preggoMob.getPregnancyPain() == PregnancyPain.NONE) {		
			if (!level.isClientSide()
				&& randomSource.nextFloat() < PregnancySystemConstants.MEDIUM_MORNING_SICKNESS_PROBABILITY) {
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
	
	@Override
	protected boolean activateAngry() {
		return super.activateAngry() || preggoMob.getMilking() >= 20;
	}
	
	@Override
	public void evaluateBaseTick() {
		if (evaluatePregnancyStageChange() == Result.SUCCESS) {
			return;
		}
		
		final var level = preggoMob.level();
		final var x =  preggoMob.getX();
		final var y = preggoMob.getY();
		final var z = preggoMob.getZ();
		
		if (evaluateMiscarriage(level, x, y, z, PregnancySystemConstants.TOTAL_TICKS_MISCARRIAGE) == Result.SUCCESS) {
			return;
		}
		
		this.evaluatePregnancyTimer();
		this.evaluateHungryTimer(level, x, y, z, MinepreggoModConfig.getTotalTicksOfHungryP2());
		this.evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP2());
		this.evaluateMilkingTimer(MinepreggoModConfig.getTotalTicksOfMilkingP2());
		this.evaluateAngry(level, x, y, z, PregnancySystemConstants.LOW_ANGER_PROBABILITY);
		
		this.evaluatePregnancySymptoms(level);
		this.evaluatePregnancyPains(level);
	}

	@Override
	public void evaluateRightClick(Player source) {	
		super.evaluateRightClick(source);
		spawnParticles(evaluateMilking(source));
	}
	
	public Result evaluateMilking(Player source) {
			
	    var mainHandItem = source.getMainHandItem().getItem();
	    var currentMilking = preggoMob.getMilking();
		
	    if (currentMilking >= PregnancySystemConstants.MILKING_VALUE
	    		&& mainHandItem == Items.GLASS_BOTTLE) {    	
        
		    var level = preggoMob.level();
    		
            source.getInventory().clearOrCountMatchingItems(p -> mainHandItem == p.getItem(), 1, source.inventoryMenu.getCraftSlots());
            currentMilking = Math.max(0, currentMilking - PregnancySystemConstants.MILKING_VALUE);
            preggoMob.setMilking(currentMilking);
                
            if (!level.isClientSide()) {
            	level.playSound(null, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.cow.milk")), SoundSource.NEUTRAL, 0.75f, 1);	

                if (preggoMob.getPregnancySymptom() == PregnancySymptom.MILKING
                		&& currentMilking <= PregnancySystemConstants.DESACTIVATE_MILKING_SYMPTOM) {
        	    	preggoMob.setPregnancySymptom(PregnancySymptom.NONE);
                }
            }      

            return Result.SUCCESS;   
	    }
		
	    return Result.NOTHING;
	}
}
