package dev.dixmk.minepreggo.utils;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP1;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP2;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP3;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP4;
import dev.dixmk.minepreggo.entity.preggo.IPregnancySystem;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP1;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP2;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP3;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP4;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP5;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP6;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP7;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP1;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP2;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP3;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP4;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP5;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP6;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP7;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;

public class PreggoMobDebugHelper {

	private PreggoMobDebugHelper() {}
	
	public static<E extends PreggoMob & ITamablePreggoMob> void showBasicInfo(E preggoMob) {
		MinepreggoMod.LOGGER.debug("BASIC INFO: id={} class={}, hungry={}, hungryTimer={}, isAngry={}",
				preggoMob.getId(), preggoMob.getClass().getSimpleName(), preggoMob.getHungry(), preggoMob.getHungryTimer(), 
				preggoMob.isAngry());		
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem> void showPregnancyInfo(E preggoMob) {
		MinepreggoMod.LOGGER.debug("BASIC PREGNANCY INFO: pregnancyTimer={}, daysByStage={}, daysByStage={}, daysToGiveBirth={}, pregnancyPain={}, "
				+ "pregnancyPainTimer={}, pregnanctSymptom={}, isIncapacitated={}",
				preggoMob.getPregnancyTimer(), preggoMob.getDaysByStage(), preggoMob.getDaysByStage(),
				preggoMob.getDaysToGiveBirth(), preggoMob.getPregnancyPain(), preggoMob.getPregnancyPainTimer(),
				preggoMob.getPregnancySymptom(), preggoMob.isIncapacitated());
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem & IPregnancyP1> void showPregnancyP1Info(E preggoMob) {
		MinepreggoMod.LOGGER.debug("PREGNANCY P1 INFO: craving={}, cravingTimer={}, cravingChosen={}",
				preggoMob.getCraving(), preggoMob.getCravingTimer(), preggoMob.getTypeOfCraving());
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem & IPregnancyP2> void showPregnancyP2Info(E preggoMob) {
		MinepreggoMod.LOGGER.debug("PREGNANCY P2 INFO: milking={}, milkingTimer={}",
				preggoMob.getMilking(), preggoMob.getMilkingTimer());
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem & IPregnancyP3> void showPregnancyP3Info(E preggoMob) {
		MinepreggoMod.LOGGER.debug("PREGNANCY P3 INFO: bellyRubs={}, bellyRubsTimer={}",
				preggoMob.getBellyRubs(), preggoMob.getBellyRubsTimer());
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob & IPregnancySystem & IPregnancyP4> void showPregnancyP4Info(E preggoMob) {
		MinepreggoMod.LOGGER.debug("PREGNANCY P4 INFO: horny={}, hornyTimer={}",
				preggoMob.getHorny(), preggoMob.getHornyTimer());
	}
	
	public static<E extends PreggoMob> void check(E entity) {
		
		if (entity.level().isClientSide()) {
			return;
		}
		
		if (entity instanceof AbstractTamablePregnantCreeperGirl<?,?> c) {
			PreggoMobDebugHelper.showBasicInfo(c);	
			PreggoMobDebugHelper.showPregnancyInfo(c);			
			if (c instanceof TamableCreeperGirlP7 c7) {
				PreggoMobDebugHelper.showPregnancyP1Info(c7);
				PreggoMobDebugHelper.showPregnancyP3Info(c7);
				PreggoMobDebugHelper.showPregnancyP2Info(c7);
				PreggoMobDebugHelper.showPregnancyP4Info(c7);	
			}
			else if (c instanceof TamableCreeperGirlP6 c6) {
				PreggoMobDebugHelper.showPregnancyP1Info(c6);
				PreggoMobDebugHelper.showPregnancyP3Info(c6);
				PreggoMobDebugHelper.showPregnancyP2Info(c6);
				PreggoMobDebugHelper.showPregnancyP4Info(c6);	
			}
			else if (c instanceof TamableCreeperGirlP5 c5) {
				PreggoMobDebugHelper.showPregnancyP1Info(c5);
				PreggoMobDebugHelper.showPregnancyP2Info(c5);
				PreggoMobDebugHelper.showPregnancyP3Info(c5);
				PreggoMobDebugHelper.showPregnancyP4Info(c5);	
			}
			else if (c instanceof TamableCreeperGirlP4 c4) {
				PreggoMobDebugHelper.showPregnancyP1Info(c4);
				PreggoMobDebugHelper.showPregnancyP2Info(c4);
				PreggoMobDebugHelper.showPregnancyP3Info(c4);
				PreggoMobDebugHelper.showPregnancyP4Info(c4);	
			}
			else if (c instanceof TamableCreeperGirlP3 c3) {
				PreggoMobDebugHelper.showPregnancyP1Info(c3);
				PreggoMobDebugHelper.showPregnancyP2Info(c3);
				PreggoMobDebugHelper.showPregnancyP3Info(c3);	
			}
			else if (c instanceof TamableCreeperGirlP2 c2) {
				PreggoMobDebugHelper.showPregnancyP1Info(c2);
				PreggoMobDebugHelper.showPregnancyP2Info(c2);	
			}
			else if (c instanceof TamableCreeperGirlP1 c1) {
				PreggoMobDebugHelper.showPregnancyP1Info(c1);	
			}
		}
		else if (entity instanceof AbstractTamablePregnantZombieGirl<?,?> z) {
			PreggoMobDebugHelper.showBasicInfo(z);	
			PreggoMobDebugHelper.showPregnancyInfo(z);	
			
			if (z instanceof TamableZombieGirlP7 c7) {
				PreggoMobDebugHelper.showPregnancyP1Info(c7);
				PreggoMobDebugHelper.showPregnancyP2Info(c7);
				PreggoMobDebugHelper.showPregnancyP3Info(c7);
				PreggoMobDebugHelper.showPregnancyP4Info(c7);	
			}
			else if (z instanceof TamableZombieGirlP6 c6) {
				PreggoMobDebugHelper.showPregnancyP1Info(c6);
				PreggoMobDebugHelper.showPregnancyP2Info(c6);
				PreggoMobDebugHelper.showPregnancyP3Info(c6);
				PreggoMobDebugHelper.showPregnancyP4Info(c6);	
			}
			else if (z instanceof TamableZombieGirlP5 c5) {
				PreggoMobDebugHelper.showPregnancyP1Info(c5);
				PreggoMobDebugHelper.showPregnancyP2Info(c5);
				PreggoMobDebugHelper.showPregnancyP3Info(c5);
				PreggoMobDebugHelper.showPregnancyP4Info(c5);	
			}
			else if (z instanceof TamableZombieGirlP4 c4) {
				PreggoMobDebugHelper.showPregnancyP1Info(c4);
				PreggoMobDebugHelper.showPregnancyP2Info(c4);
				PreggoMobDebugHelper.showPregnancyP3Info(c4);
				PreggoMobDebugHelper.showPregnancyP4Info(c4);	
			}
			else if (z instanceof TamableZombieGirlP3 c3) {
				PreggoMobDebugHelper.showPregnancyP1Info(c3);
				PreggoMobDebugHelper.showPregnancyP2Info(c3);
				PreggoMobDebugHelper.showPregnancyP3Info(c3);	
			}
			else if (z instanceof TamableZombieGirlP2 c2) {
				PreggoMobDebugHelper.showPregnancyP1Info(c2);
				PreggoMobDebugHelper.showPregnancyP2Info(c2);	
			}
			else if (z instanceof TamableZombieGirlP1 c1) {
				PreggoMobDebugHelper.showPregnancyP1Info(c1);	
			}
		}			
		else if (entity instanceof AbstractTamableCreeperGirl<?> c) {
			PreggoMobDebugHelper.showBasicInfo(c);	
		}
		else if (entity instanceof AbstractTamableZombieGirl<?> z) {
			PreggoMobDebugHelper.showBasicInfo(z);	
		}	
	}
}
