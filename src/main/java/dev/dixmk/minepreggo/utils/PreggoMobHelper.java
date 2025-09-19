package dev.dixmk.minepreggo.utils;

import java.util.function.BiConsumer;

import dev.dixmk.minepreggo.entity.preggo.IPregnancyP1;

/*
import net.mcreator.minepreggo.MinepreggoMod;
import net.mcreator.minepreggo.entity.CreeperGirlEntity;
import net.mcreator.minepreggo.entity.CreeperGirlP0Entity;
import net.mcreator.minepreggo.entity.CreeperGirlP0PostMiscarriageEntity;
import net.mcreator.minepreggo.entity.CreeperGirlP0PostpartumEntity;
import net.mcreator.minepreggo.entity.CreeperGirlP1Entity;
import net.mcreator.minepreggo.entity.CreeperGirlP2Entity;
import net.mcreator.minepreggo.entity.CreeperGirlP3Entity;
import net.mcreator.minepreggo.entity.CreeperGirlP4Entity;
import net.mcreator.minepreggo.entity.CreeperGirlP5Entity;
import net.mcreator.minepreggo.entity.CreeperGirlP6Entity;
import net.mcreator.minepreggo.entity.CreeperGirlP7Entity;
import net.mcreator.minepreggo.entity.ZombieGirlEntity;
import net.mcreator.minepreggo.entity.ZombieGirlP0Entity;
import net.mcreator.minepreggo.entity.ZombieGirlP0PostMiscarriageEntity;
import net.mcreator.minepreggo.entity.ZombieGirlP0PostpartumEntity;
import net.mcreator.minepreggo.entity.ZombieGirlP1Entity;
import net.mcreator.minepreggo.entity.ZombieGirlP2Entity;
import net.mcreator.minepreggo.entity.ZombieGirlP3Entity;
import net.mcreator.minepreggo.entity.ZombieGirlP4Entity;
import net.mcreator.minepreggo.entity.ZombieGirlP5Entity;
import net.mcreator.minepreggo.entity.ZombieGirlP6Entity;
import net.mcreator.minepreggo.entity.ZombieGirlP7Entity;
import net.mcreator.minepreggo.entity.impregnable.girl.PreggoP0;
import net.mcreator.minepreggo.entity.impregnable.girl.PreggoP0.BabyType;
import net.mcreator.minepreggo.entity.impregnable.girl.PreggoP1;
import net.mcreator.minepreggo.entity.impregnable.girl.PreggoP2;
import net.mcreator.minepreggo.entity.impregnable.girl.PreggoP3;
import net.mcreator.minepreggo.entity.impregnable.girl.PreggoP4;
import net.mcreator.minepreggo.entity.impregnable.girl.PreggoP5;
import net.mcreator.minepreggo.entity.impregnable.girl.PreggoP6;
import net.mcreator.minepreggo.entity.impregnable.girl.PreggoP7;
import net.mcreator.minepreggo.entity.impregnable.girl.creeper.AbstractCreeperGirl;
import net.mcreator.minepreggo.entity.impregnable.girl.creeper.AbstractCreeperGirlP0Entity;
import net.mcreator.minepreggo.entity.impregnable.girl.creeper.AbstractMonsterCreeperGirl;
import net.mcreator.minepreggo.entity.impregnable.girl.zombie.AbstractMonsterZombieGirl;
import net.mcreator.minepreggo.entity.impregnable.girl.zombie.AbstractZombieGirl;
import net.mcreator.minepreggo.entity.impregnable.girl.zombie.AbstractZombieGirlP0Entity;
import net.mcreator.minepreggo.init.MinepreggoModEntities;
import net.mcreator.minepreggo.init.MinepreggoModItems;
import net.mcreator.minepreggo.init.MinepreggoModMobEffects;
import net.mcreator.minepreggo.network.MinepreggoModVariables;
import net.mcreator.minepreggo.procedures.MaxStageToNumberOfChildrenProcedure;
import net.mcreator.minepreggo.procedures.NumberOfStageDaysByMaxStageProcedure;
import net.mcreator.minepreggo.procedures.SetEntityAttackTargetProcedure;
import net.mcreator.minepreggo.procedures.TranferInventaryProcedure;
import net.mcreator.minepreggo.procedures.TransferInventaryFromSavageProcedure;
*/

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class PreggoMobHelper {
			
	private PreggoMobHelper() {}
	
	public static boolean hasValidTarget(Mob entity) {
	    LivingEntity target = entity.getTarget();
	    return target != null && target.isAlive() && entity.canAttack(target);
	}
	
	public static boolean isTargetStillValid(Mob entity) {
	    LivingEntity target = entity.getTarget();
	    return target != null && target.isAlive();
	}
		
	/*
	public static <T extends TamableAnimal & IPregnancyP1> void defaultDamagePregnancyHealth(T preggoMob, DamageSource damagesource) {
			
		if (preggoMob.hasEffect(MinepreggoModMobEffects.PREGNANCY_RESISTANCE_EFFECT.get()) && preggoMob.getRandom().nextFloat() < 0.9F) return;
			
		if (!damagesource.is(DamageTypes.FALL)
				&& preggoMob.getItemBySlot(EquipmentSlot.CHEST).getItem() != ItemStack.EMPTY.getItem()
				&& preggoMob.getRandom().nextFloat() < 0.5) return;
		
		boolean showMessage = false;
		
		if (preggoMob.getHealth() < preggoMob.getMaxHealth() / 2F) {
			int damage = 0;
			
			if (preggoMob.getCurrentStage() == 1) {
				damage = Constant.PREGNANCY_P1_HEALTH_DAMAGE_VALUE;
			}
			else if (preggoMob.getCurrentStage() == 2) {
				damage = Constant.PREGNANCY_P2_HEALTH_DAMAGE_VALUE;
			}
			else if (preggoMob.getCurrentStage() == 3) {
				damage = Constant.PREGNANCY_P3_HEALTH_DAMAGE_VALUE;
			}
			else if (preggoMob.getCurrentStage() == 4) {
				damage = Constant.PREGNANCY_P4_HEALTH_DAMAGE_VALUE;
			}
			else if (preggoMob.getCurrentStage() == 5) {
				damage = Constant.PREGNANCY_P5_HEALTH_DAMAGE_VALUE;
			}
			else if (preggoMob.getCurrentStage() == 6) {
				damage = Constant.PREGNANCY_P6_HEALTH_DAMAGE_VALUE;
			}
			else if (preggoMob.getCurrentStage() == 7) {
				damage = Constant.PREGNANCY_P7_HEALTH_DAMAGE_VALUE;
			}
			
			if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION) || damagesource.is(DamageTypes.FALL)) {
				damage *= 2;
			}
			
			preggoMob.setPregnancyHealth(Math.max(0, preggoMob.getPregnancyHealth() - damage));			
			showMessage = true;
		} 
		else if (damagesource.is(DamageTypes.EXPLOSION)
				|| damagesource.is(DamageTypes.PLAYER_EXPLOSION)
				|| damagesource.is(DamageTypes.FALL)) {
			preggoMob.setPregnancyHealth(Math.max(0, preggoMob.getPregnancyHealth() - 5));
			showMessage = true;
		}
		
		if (showMessage && preggoMob.getPregnancyHealth() < 40) {
			PreggoMobMessageHelper.warningOwnerPossibleMiscarriageEvent(preggoMob);
		}
	}
	
	public static void initPreggoMob(IPregnancyP1 preggoP1, int currentStage) {
		initPreggoMob(preggoP1, currentStage, 4);
	}
	
	
	public static void initPreggoMob(PreggoP1 preggoP1, int currentStage, int maxStage) {	
		final int daysByStage = (int) NumberOfStageDaysByMaxStageProcedure.execute(maxStage);
		final int daysToBirth = (maxStage - currentStage + 1) * daysByStage;
		
		preggoP1.setDaysPassed(0);
		preggoP1.setMaxStage(maxStage);
		preggoP1.setDaysByStage(daysByStage);
		preggoP1.setDaysToBirth(daysToBirth);
		preggoP1.setPregnancyHealth(100);
		preggoP1.setHungry(10);
		preggoP1.setSavage(true);

		MinepreggoMod.LOGGER.log(org.apache.logging.log4j.Level.DEBUG, "MaxStage = {} DaysByStage = {} DaysToGiveBirth = {}", maxStage, daysByStage, daysToBirth);		
	}
	*/
	
	public static void putItemStackInOffHand(LivingEntity entity, Item item, int num) {
		if (entity.getOffhandItem() != ItemStack.EMPTY
				&& entity.level() instanceof ServerLevel level) {
			ItemEntity entityToSpawn = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), entity.getOffhandItem());
			entityToSpawn.setPickUpDelay(10);
			level.addFreshEntity(entityToSpawn);
		}
		entity.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(item, num).copy());
	}

	/*
	public static void passZombieGirlPregnancyStage(Entity entity, double x, double y, double z) {
			
		if (!(entity instanceof AbstractZombieGirlP0Entity abstractZombieGirl)) return;
		
		BiConsumer<TamableAnimal, TamableAnimal> basicTransferConsumer = (source, target) -> {			
			target.setYRot(source.getYRot());
			target.setYBodyRot(source.getYRot());
			target.setYHeadRot(source.getYRot());
			target.setXRot(source.getXRot());
			target.setHealth(source.getHealth());
			
			if (source.isTame() && source.getOwner() != null)
				target.tame((Player) source.getOwner());
			
			if (source.hasCustomName()) target.setCustomName(source.getCustomName()); 	
		};
		
		Level world = abstractZombieGirl.level();
		
		if (abstractZombieGirl instanceof ZombieGirlP0Entity source) {				
			if (world instanceof ServerLevel level) {
				ZombieGirlP1Entity target = MinepreggoModEntities.ZOMBIE_GIRL_P_1.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (target != null) {
					basicTransferConsumer.accept(source, target);					
					changeStage(source, target, false, true);
					TranferInventaryProcedure.execute(world, x, y, z, source, target, target.getCurrentStage());
					SetEntityAttackTargetProcedure.execute(world, x, y, z, target, source, 24);
					target.updateTextureAndAnim();
					source.discard();
				}		
			}
		}
		else if (abstractZombieGirl instanceof ZombieGirlP1Entity source) {				
			if (world instanceof ServerLevel level) {
				ZombieGirlP2Entity target = MinepreggoModEntities.ZOMBIE_GIRL_P_2.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (target != null) {
					basicTransferConsumer.accept(source, target);					
					changeStage(source, target, false);
					TranferInventaryProcedure.execute(world, x, y, z, source, target, target.getCurrentStage());
					SetEntityAttackTargetProcedure.execute(world, x, y, z, target, source, 24);
					target.updateTextureAndAnim();
					source.discard();
				}		
			}
		}
		else if (abstractZombieGirl instanceof ZombieGirlP2Entity source) {
			if (world instanceof ServerLevel level) {
				ZombieGirlP3Entity target = MinepreggoModEntities.ZOMBIE_GIRL_P_3.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (target != null) {
					basicTransferConsumer.accept(source, target);					
					changeStage(source, target, false);
					TranferInventaryProcedure.execute(world, x, y, z, source, target, target.getCurrentStage());
					SetEntityAttackTargetProcedure.execute(world, x, y, z, target, source, 24);
					target.updateTextureAndAnim();
					source.discard();
				}		
			}
		}
		else if (abstractZombieGirl instanceof ZombieGirlP3Entity source) {
			if (world instanceof ServerLevel level) {
				ZombieGirlP4Entity target = MinepreggoModEntities.ZOMBIE_GIRL_P_4.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (target != null) {
					basicTransferConsumer.accept(source, target);					
					changeStage(source, target, false);
					TranferInventaryProcedure.execute(world, x, y, z, source, target, target.getCurrentStage());
					SetEntityAttackTargetProcedure.execute(world, x, y, z, target, source, 24);
					target.updateTextureAndAnim();
					source.discard();
				}		
			}
		}
		else if (abstractZombieGirl instanceof ZombieGirlP4Entity source) {
			if (world instanceof ServerLevel level) {
				ZombieGirlP5Entity target = MinepreggoModEntities.ZOMBIE_GIRL_P_5.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (target != null) {
					basicTransferConsumer.accept(source, target);					
					changeStage(source, target);
					TranferInventaryProcedure.execute(world, x, y, z, source, target, target.getCurrentStage());
					SetEntityAttackTargetProcedure.execute(world, x, y, z, target, source, 24);
					target.updateTextureAndAnim();
					source.discard();
				}		
			}
		}
		else if (abstractZombieGirl instanceof ZombieGirlP5Entity source) {
			if (world instanceof ServerLevel level) {
				ZombieGirlP6Entity target = MinepreggoModEntities.ZOMBIE_GIRL_P_6.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (target != null) {
					basicTransferConsumer.accept(source, target);					
					changeStage(source, target);
					TranferInventaryProcedure.execute(world, x, y, z, source, target, target.getCurrentStage());
					SetEntityAttackTargetProcedure.execute(world, x, y, z, target, source, 24);
					target.updateTextureAndAnim();
					source.discard();
				}		
			}
		}	

		else if (abstractZombieGirl instanceof ZombieGirlP6Entity source) {
			if (world instanceof ServerLevel level) {
				ZombieGirlP7Entity target = MinepreggoModEntities.ZOMBIE_GIRL_P_7.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (target != null) {
					basicTransferConsumer.accept(source, target);					
					changeStage(source, target);
					TranferInventaryProcedure.execute(world, x, y, z, source, target, target.getCurrentStage());
					SetEntityAttackTargetProcedure.execute(world, x, y, z, target, source, 24);
					target.updateTextureAndAnim();
					source.discard();
				}		
			}
		}	
	}
	
	public static void passCreeperGirlPregnancyStage(Entity entity, double x, double y, double z) {
			
		if (!(entity instanceof AbstractCreeperGirlP0Entity abstractCreeperGirl)) return;
		
		BiConsumer<TamableAnimal, TamableAnimal> basicTransferConsumer = (source, target) -> {			
			target.setYRot(source.getYRot());
			target.setYBodyRot(source.getYRot());
			target.setYHeadRot(source.getYRot());
			target.setXRot(source.getXRot());
			target.setHealth(source.getHealth());
			
			if (source.isTame() && source.getOwner() != null) 
				target.tame((Player) source.getOwner());
				
			
			if (source.hasCustomName()) target.setCustomName(source.getCustomName()); 	
		};
		
		Level world = abstractCreeperGirl.level();
		
		if (abstractCreeperGirl instanceof CreeperGirlP0Entity source) {				
			if (world instanceof ServerLevel level) {
				CreeperGirlP1Entity target = MinepreggoModEntities.CREEPER_GIRL_P_1.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (target != null) {
					target.setcombatMode(source.getcombatMode());
					basicTransferConsumer.accept(source, target);					
					changeStage(source, target, false, true);
					TranferInventaryProcedure.execute(world, x, y, z, source, target, target.getCurrentStage());
					SetEntityAttackTargetProcedure.execute(world, x, y, z, target, source, 24);
					target.updateTextureAndAnim();
					source.discard();
				}		
			}
		}
		else if (abstractCreeperGirl instanceof CreeperGirlP1Entity source) {				
			if (world instanceof ServerLevel level) {
				CreeperGirlP2Entity target = MinepreggoModEntities.CREEPER_GIRL_P_2.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (target != null) {
					basicTransferConsumer.accept(source, target);					
					changeStage(source, target, false);
					TranferInventaryProcedure.execute(world, x, y, z, source, target, target.getCurrentStage());
					SetEntityAttackTargetProcedure.execute(world, x, y, z, target, source, 24);
					target.updateTextureAndAnim();
					source.discard();
				}		
			}
		}
		else if (abstractCreeperGirl instanceof CreeperGirlP2Entity source) {
			if (world instanceof ServerLevel level) {
				CreeperGirlP3Entity target = MinepreggoModEntities.CREEPER_GIRL_P_3.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (target != null) {
					target.setcombatMode(source.getcombatMode());
					basicTransferConsumer.accept(source, target);					
					changeStage(source, target, false);
					TranferInventaryProcedure.execute(world, x, y, z, source, target, target.getCurrentStage());
					SetEntityAttackTargetProcedure.execute(world, x, y, z, target, source, 24);
					target.updateTextureAndAnim();
					source.discard();
				}		
			}
		}
		else if (abstractCreeperGirl instanceof CreeperGirlP3Entity source) {
			if (world instanceof ServerLevel level) {
				CreeperGirlP4Entity target = MinepreggoModEntities.CREEPER_GIRL_P_4.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (target != null) {
					target.setcombatMode(source.getcombatMode());
					basicTransferConsumer.accept(source, target);					
					changeStage(source, target, false);
					TranferInventaryProcedure.execute(world, x, y, z, source, target, target.getCurrentStage());
					SetEntityAttackTargetProcedure.execute(world, x, y, z, target, source, 24);
					target.updateTextureAndAnim();
					source.discard();
				}		
			}
		}
		else if (abstractCreeperGirl instanceof CreeperGirlP4Entity source) {
			if (world instanceof ServerLevel level) {
				CreeperGirlP5Entity target = MinepreggoModEntities.CREEPER_GIRL_P_5.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (target != null) {
					target.setcombatMode(source.getcombatMode());
					basicTransferConsumer.accept(source, target);					
					changeStage(source, target);
					TranferInventaryProcedure.execute(world, x, y, z, source, target, target.getCurrentStage());
					SetEntityAttackTargetProcedure.execute(world, x, y, z, target, source, 24);
					target.updateTextureAndAnim();
					source.discard();
				}		
			}
		}
		else if (abstractCreeperGirl instanceof CreeperGirlP5Entity source) {
			if (world instanceof ServerLevel level) {
				CreeperGirlP6Entity target = MinepreggoModEntities.CREEPER_GIRL_P_6.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (target != null) {
					target.setcombatMode(source.getcombatMode());
					basicTransferConsumer.accept(source, target);					
					changeStage(source, target);
					TranferInventaryProcedure.execute(world, x, y, z, source, target, target.getCurrentStage());
					SetEntityAttackTargetProcedure.execute(world, x, y, z, target, source, 24);
					target.updateTextureAndAnim();
					source.discard();
				}		
			}
		}	

		else if (abstractCreeperGirl instanceof CreeperGirlP6Entity source) {
			if (world instanceof ServerLevel level) {
				CreeperGirlP7Entity target = MinepreggoModEntities.CREEPER_GIRL_P_7.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
				if (target != null) {
					target.setcombatMode(source.getcombatMode());
					basicTransferConsumer.accept(source, target);					
					changeStage(source, target);
					TranferInventaryProcedure.execute(world, x, y, z, source, target, target.getCurrentStage());
					SetEntityAttackTargetProcedure.execute(world, x, y, z, target, source, 24);
					target.updateTextureAndAnim();
					source.discard();
				}		
			}
		}	
	}	
	
	public static void startPregnancy(LivingEntity entity, int amplifier) {
		startPregnancy(entity, amplifier, BabyType.HUMAN);
	}
	
	public static void startPregnancy(LivingEntity entity, int amplifier, BabyType babyType) {
		
		int s = 4;
	
		if (amplifier == 1) s = 5;
		else if (amplifier == 2) s = 6;
		else if (amplifier >= 3) s = 7;
				
		final int maxStage = s;
		final int daysByStage = (int) NumberOfStageDaysByMaxStageProcedure.execute(maxStage);
		final int numOfTotalDays = maxStage * daysByStage;
		final int numOfBabies = (int) MaxStageToNumberOfChildrenProcedure.execute(maxStage);
				
		if (entity instanceof Player player
				&& player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinepreggoModVariables.PlayerVariables()).playerSex
				&& !player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinepreggoModVariables.PlayerVariables()).isPlayerPregnant) {

			player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.isPlayerPregnant = true;
				capability.playerMaxStage = maxStage;
				capability.playerCurrentStage = 1;
				capability.playerDaysByStage = daysByStage;
				capability.playerDaysToGiveBirth = numOfTotalDays;
				capability.playerNumberOfChildren = numOfBabies;
				capability.playerPregnancyHealth = 100;
				capability.playerBabyType = babyType.getId();		
				capability.syncPlayerVariables(player);
			});					
			
			if (!player.level().isClientSide())
				player.addEffect(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_P_1.get(), -1, 0, false, false));
		}
		
		else if (entity instanceof ZombieGirlP0Entity zombieGirl) {
			zombieGirl.getEntityData().set(ZombieGirlP0Entity.DATA_isPregnant, true);
			zombieGirl.getEntityData().set(ZombieGirlP0Entity.DATA_maxStage, maxStage);
			zombieGirl.getEntityData().set(ZombieGirlP0Entity.DATA_daysByStage, daysByStage);			
		}
		else if (entity instanceof CreeperGirlP0Entity creeperGirl) {
			creeperGirl.getEntityData().set(CreeperGirlP0Entity.DATA_isPregnant, true);
			creeperGirl.getEntityData().set(CreeperGirlP0Entity.DATA_maxStage, maxStage);
			creeperGirl.getEntityData().set(CreeperGirlP0Entity.DATA_daysByStage, daysByStage);
		}
		else if (entity instanceof ZombieGirlEntity zombieGirl
				&& !zombieGirl.isBaby()
				&& entity.level() instanceof ServerLevel level) {
				
				double x = zombieGirl.getX();
				double y = zombieGirl.getY();
				double z = zombieGirl.getZ();
			ZombieGirlP0Entity entityToSpawn = MinepreggoModEntities.ZOMBIE_GIRL_P_0.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
			if (entityToSpawn != null) {		
				entityToSpawn.setYRot(zombieGirl.getYRot());
				entityToSpawn.setYBodyRot(zombieGirl.getYRot());
				entityToSpawn.setYHeadRot(zombieGirl.getYRot());
				entityToSpawn.setXRot(zombieGirl.getXRot());
				
				entityToSpawn.getEntityData().set(ZombieGirlP0Entity.DATA_isPregnant, true);
				entityToSpawn.getEntityData().set(ZombieGirlP0Entity.DATA_maxStage, maxStage);
				entityToSpawn.getEntityData().set(ZombieGirlP0Entity.DATA_daysByStage, daysByStage);
				TransferInventaryFromSavageProcedure.execute(level, x, y, z, zombieGirl, entityToSpawn, 0);
				SetEntityAttackTargetProcedure.execute(level, x, y, z, entityToSpawn, zombieGirl, 36D);
			}
			zombieGirl.discard();
		
		}
		else if (entity instanceof CreeperGirlEntity creeperGirl
				&& !creeperGirl.isBaby()
				&& entity.level() instanceof ServerLevel level) {
			double x = creeperGirl.getX();
			double y = creeperGirl.getY();
			double z = creeperGirl.getZ();
			CreeperGirlP0Entity entityToSpawn = MinepreggoModEntities.CREEPER_GIRL_P_0.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
			if (entityToSpawn != null) {
				entityToSpawn.setYRot(creeperGirl.getYRot());
				entityToSpawn.setYBodyRot(creeperGirl.getYRot());
				entityToSpawn.setYHeadRot(creeperGirl.getYRot());
				entityToSpawn.setXRot(creeperGirl.getXRot());

				entityToSpawn.getEntityData().set(CreeperGirlP0Entity.DATA_isPregnant, true);
				entityToSpawn.getEntityData().set(CreeperGirlP0Entity.DATA_maxStage, maxStage);
				entityToSpawn.getEntityData().set(CreeperGirlP0Entity.DATA_daysByStage, daysByStage);
				TransferInventaryFromSavageProcedure.execute(level, x, y, z, creeperGirl, entityToSpawn, 0);
				SetEntityAttackTargetProcedure.execute(level, x, y, z, entityToSpawn, creeperGirl, 36D);		
			}
			creeperGirl.discard();
		}
		else {
			entity.hurt(new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC)), 2);
		}
	}
	

	private static<T extends TamableAnimal & PreggoP0> void basicTransfer(T source, T target, ServerLevel level, double x, double y, double z) {
		if (target != null && source != null) {		
			target.setYRot(source.getYRot());
			target.setYBodyRot(source.getYRot());
			target.setYHeadRot(source.getYRot());
			target.setXRot(source.getXRot());
			
			if (source.hasCustomName())
				target.setCustomName(source.getCustomName());
			
			if (source.isTame() && source.getOwner() != null)
				target.tame((Player) source.getOwner());
			
			target.setHungry(source.getHungry());
			target.setHungryTimer(source.getHungryTimer());
			target.setSavage(source.isSavage());
			target.setIncapacitated(source.isIncapacitated());
			target.setHealth(source.getHealth());
			target.setWaiting(source.isWaiting());
			
			target.updateTextureAndAnim();
			TranferInventaryProcedure.execute(level, x, y, z, source, target, target.getCurrentStage());
			SetEntityAttackTargetProcedure.execute(level, x, y, z, target, source, 36D);
			source.discard();
		}
	}
	
	
	public static void passZombieGirlPost(Entity entity, double x, double y, double z) {	
		if (entity.level() instanceof ServerLevel level) {		
			if (entity instanceof ZombieGirlP0PostMiscarriageEntity source) {
				ZombieGirlP0Entity target = MinepreggoModEntities.ZOMBIE_GIRL_P_0.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);			
				basicTransfer(source, target, level, x, y, z);
			}
			else if (entity instanceof ZombieGirlP0PostpartumEntity source) {
				ZombieGirlP0Entity target = MinepreggoModEntities.ZOMBIE_GIRL_P_0.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);			
				basicTransfer(source, target, level, x, y, z);
			}		
		}
	}
	
	
	public static void passCreeperGirlPost(Entity entity, double x, double y, double z) {
		
		if (entity.level() instanceof ServerLevel level) {		
			if (entity instanceof CreeperGirlP0PostMiscarriageEntity source) {
				CreeperGirlP0Entity target = MinepreggoModEntities.CREEPER_GIRL_P_0.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);			
				target.setcombatMode(source.getcombatMode());
				basicTransfer(source, target, level, x, y, z);
			}
			else if (entity instanceof CreeperGirlP0PostpartumEntity source) {
				CreeperGirlP0Entity target = MinepreggoModEntities.CREEPER_GIRL_P_0.get().spawn(level, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);			
				target.setcombatMode(source.getcombatMode());
				basicTransfer(source, target, level, x, y, z);
			}		
		}
	}
	
	

	public static void changeStage(PreggoP0 source, PreggoP1 target, boolean init, boolean first) {		
	    if (source == null || target == null) return;    	
	    target.setHungry(source.getHungry());
	    target.setWaiting(source.isWaiting());
	    target.setHungryTimer(source.getHungryTimer());
	    target.setMorningSicknessTimer(source.getMorningSicknessTimer());
	    target.setMorningSicknessEventActive(source.isMorningSicknessEventActive());
	    target.setIncapacitated(source.isIncapacitated());
	    target.setSavage(source.isSavage());    
	    target.setDaysByStage(source.getDaysByStage());
	    target.setMaxStage(source.getMaxStage()); 
	    target.setDaysPassed(0);
	    
	    if (first ) {
	    	target.setDaysToBirth(source.getDaysByStage() * source.getMaxStage());
	    }
	    
	    if (init) {
		    target.setPregnancyHealth(0);
		    target.setPregnancyHealthTimer(0);
		    target.setMiscarriageEventActive(false);
		    target.setMiscarriageTimer(0);

		    target.setDaysPassed(0);
		    target.setDaysToBirth(0);

		    target.setCravingTimer(0);
		    target.setCravingChosen(0);
		    target.setFirstCravingEventActive(false);
		    target.setSecondCravingEventActive(false);
		    target.setCraving(0);

		    target.setFatigue(0);
		    target.setFatigueTimer(0);
		    target.setRestTimer(0);  	
	    }
	}
	
	
	public static void changeStage(PreggoP1 source, PreggoP2 target, boolean init) {
	    if (source == null || target == null) return;   
	    changeStage((PreggoP0) source, (PreggoP1) target, false, false);

	    target.setPregnancyHealth(source.getPregnancyHealth());
	    target.setPregnancyHealthTimer(source.getPregnancyHealthTimer());
	    target.setMiscarriageEventActive(source.isMiscarriageEventActive());
	    target.setMiscarriageTimer(source.getMiscarriageTimer());

	    target.setDaysToBirth(source.getDaysToBirth());

	    target.setCravingTimer(source.getCravingTimer());
	    target.setCravingChosen(source.getCravingChosen());
	    target.setFirstCravingEventActive(source.isFirstCravingEventActive());
	    target.setSecondCravingEventActive(source.isSecondCravingEventActive());
	    target.setCraving(source.getCraving());

	    target.setFatigue(source.getFatigue());
	    target.setFatigueTimer(source.getFatigueTimer());
	    target.setRestTimer(source.getRestTimer());

	    target.setAngerEventActive(source.isAngerEventActive());  
	    
	    if (init) {
	    	target.setMilking(0);
	    	target.setMilkingTimer(0);
	    	target.setMilkingFullEventActive(false);
	    	target.setPregnancyPainTimer(0);
	    	target.setPregnancyPainEventActive(false);
	    }
	}
		
	public static void changeStage(PreggoP2 source, PreggoP3 target, boolean init) {
	    if (source == null || target == null) return;   
	    changeStage((PreggoP1) source,(PreggoP2)  target, false);    
	    
	    target.setMilking(source.getMilking());
	    target.setMilkingTimer(source.getMilkingTimer());
	    target.setMilkingFullEventActive(source.isMilkingFullEventActive());
	    target.setPregnancyPainTimer(source.getPregnancyPainTimer());
	    target.setPregnancyPainEventActive(source.isPregnancyPainEventActive());
	    
	    if (init) {
	    	target.setBellyRubs(0);
	    	target.setBellyRubsTimer(0);
	    	target.setFullBellyRubsEventActive(false);
	    }
	}
	
	public static void changeStage(PreggoP3 source, PreggoP4 target, boolean init) {
	    if (source == null || target == null) return;   
	    changeStage((PreggoP2) source, (PreggoP3) target, false);      
	    target.setBellyRubs(source.getBellyRubs());
	    target.setBellyRubsTimer(source.getBellyRubsTimer());
	    target.setFullBellyRubsEventActive(source.isFullBellyRubsEventActive());
	    
	    if (init) {
	    	target.setHorny(0);
	    	target.setHornyTimer(0);
	    	target.setFullHornyEventActive(false);
	    	target.setSexEventActive(false);
	    }
	}
	
	public static void changeStage(PreggoP4 source, PreggoP5 target) {
	    if (source == null || target == null) return;   
	    changeStage((PreggoP3) source, (PreggoP4) target, false); 

		target.setHorny(source.getHorny());
		target.setHornyTimer(source.getHornyTimer());
		target.setFullHornyEventActive(source.isFullHornyEventActive());
		target.setSexEventActive(source.isSexEventActive());

	}
	
	public static void changeStage(PreggoP5 source, PreggoP6 target) {
	    if (source == null || target == null) return;   
	    changeStage((PreggoP4) source, (PreggoP5) target);  
	}
	
	public static void changeStage(PreggoP6 source, PreggoP7 target) {
	    if (source == null || target == null) return;   
	    changeStage((PreggoP5) source, (PreggoP6) target);  
	}
	
	
	public static void spawnFetusesAndBabiesZombie(AbstractZombieGirlP0Entity zombieGirl) {	
				
		var randomSource = zombieGirl.getRandom();
			
		if (zombieGirl instanceof PreggoP2 p2) {
			
			int numOfBabies = p2.getNumberOfBabies();
			
			int totalDays = p2.getMaxStage() * p2.getDaysByStage();
			int totalDaysPassed = Math.max(0, p2.getCurrentStage() - 1) * p2.getDaysByStage() + p2.getDaysPassed();  
				
			float prog = Math.max(0, Math.min(totalDaysPassed / (float) totalDays, 1));
			float min = 0.15F;
			float max = 0.95F;
			float alive = (min + (max - min) / (float) (1 + Math.exp(-3 * (prog - 0.5F))));

			
			MinepreggoMod.LOGGER.log(org.apache.logging.log4j.Level.DEBUG, "numOfBabies = {} totalDays = {} totalDaysPassed = {} daysToBirth = {} alive = {}",
						numOfBabies, totalDays, totalDaysPassed, p2.getDaysToBirth(), alive);
			
			spawnFetusesAndBabiesZombie(alive, alive, numOfBabies, zombieGirl);			

		}
	
		else if (zombieGirl instanceof PreggoP1 p1) {
			
			int numOfBabies = p1.getNumberOfBabies();
			
			int daysPassed = p1.getDaysPassed();
			int totalDays = p1.getMaxStage() * p1.getDaysByStage();

			float prog = Math.max(0F, Math.min(daysPassed / (float) totalDays, 1F));
			
			float min = 0.15F;
			float max = 0.75F;
			float k = 2;
			float fetusSpawn = min + (max - min) * (float) Math.pow(prog, k);	
			
			for (int i = 0; i < numOfBabies; ++i) {
				if (randomSource.nextFloat() < fetusSpawn
						&& zombieGirl.level() instanceof ServerLevel level) {					
					ItemEntity entityToSpawn = new ItemEntity(level, zombieGirl.getX(), zombieGirl.getY(), zombieGirl.getZ(), new ItemStack(MinepreggoModItems.DEAD_ZOMBIE_FETUS.get()));
					entityToSpawn.setPickUpDelay(10);
					level.addFreshEntity(entityToSpawn);			
				}
			}		
			
			MinepreggoMod.LOGGER.log(org.apache.logging.log4j.Level.DEBUG, "numOfBabies = {} daysPassed = {} totalDays = {} fetusSpawn = {}", numOfBabies, daysPassed, totalDays, fetusSpawn);
		}
	} 
	
	
	public static void spawnFetusesAndBabiesCreeper(AbstractCreeperGirlP0Entity creeperGirl) {
		
		var randomSource = creeperGirl.getRandom();
					
		if (creeperGirl instanceof PreggoP2 p2) {
				
			int numOfBabies = p2.getNumberOfBabies();
			
			int totalDays = p2.getMaxStage() * p2.getDaysByStage();
			int totalDaysPassed = Math.max(0, p2.getCurrentStage() - 1) * p2.getDaysByStage() + p2.getDaysPassed();  
				
			float prog = Math.max(0F, Math.min(totalDaysPassed / (float) totalDays, 1F));
			float min = 0.05F;
			float max = 0.99F;	
			float alive = (min + (max - min) / (float) (1 + Math.exp(-5F * (prog - 0.5F))));
			
			MinepreggoMod.LOGGER.log(org.apache.logging.log4j.Level.DEBUG, "numOfBabies = {} totalDays = {} totalDaysPassed = {} daysToBirth = {} alive = {}",
					numOfBabies, totalDays, totalDaysPassed, p2.getDaysToBirth(), alive);
		
			spawnFetusesAndBabiesCreeper(alive, 0.8F, numOfBabies, creeperGirl);			
		}
		
		else if (creeperGirl instanceof PreggoP1 p1) {
			
			int numOfBabies = p1.getNumberOfBabies();
			
			int daysPassed = p1.getDaysPassed();
			int totalDays = p1.getMaxStage() * p1.getDaysByStage();

			float prog = Math.max(0F, Math.min(daysPassed / (float) totalDays, 1F));
			
			float min = 0.10F;
			float max = 0.65F;
			float k = 2;
			float fetusSpawn = min + (max - min) * (float) Math.pow(prog, k);
			
			for (int i = 0; i < numOfBabies; ++i) {
					
				if (randomSource.nextFloat() < fetusSpawn
						&& creeperGirl.level() instanceof ServerLevel level) {					
					ItemEntity entityToSpawn = new ItemEntity(level, creeperGirl.getX(), creeperGirl.getY(), creeperGirl.getZ(), new ItemStack(MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get()));
					entityToSpawn.setPickUpDelay(10);
					level.addFreshEntity(entityToSpawn);			
				}
			}	
			
			MinepreggoMod.LOGGER.log(org.apache.logging.log4j.Level.DEBUG, "numOfBabies = {} daysPassed = {} totalDays = {} fetusSpawn = {}", numOfBabies, daysPassed, totalDays, fetusSpawn);
		}	
	}
	

	public static<T extends AbstractMonsterZombieGirl> void simpleSpawnFetusesAndBabiesZombie(T zombieGirl) {
		final var numOfBabies = zombieGirl.getBasicPreggoData().numOfBabies();
		final var totalDaysPassed = zombieGirl.getBasicPreggoData().totalDaysPassed();
		final var totalDays = zombieGirl.getBasicPreggoData().totalDays();
		
		float prog = Math.max(0, Math.min(totalDaysPassed / (float) totalDays, 1));
		float min = 0.1F;
		float max = 0.65F;
		float alive = (min + (max - min) / (float) (1 + Math.exp(-2 * (prog - 0.6F))));
	
		spawnFetusesAndBabiesZombie(alive, 0.85F, numOfBabies, zombieGirl);
	}
	
	public static<T extends AbstractMonsterCreeperGirl> void simpleSpawnFetusesAndBabiesCreeper(T creeperGirl) {	
		final var numOfBabies = creeperGirl.getBasicPreggoData().numOfBabies();
		final var totalDaysPassed = creeperGirl.getBasicPreggoData().totalDaysPassed();
		final var totalDays = creeperGirl.getBasicPreggoData().totalDays();
		
		float prog = Math.max(0F, Math.min(totalDaysPassed / (float) totalDays, 1F));
		float min = 0.1F;
		float max = 0.55F;
		float alive = (min + (max - min) / (float) (1 + Math.exp(-2 * (prog - 0.65F))));
	
		spawnFetusesAndBabiesCreeper(alive, 0.7F, numOfBabies, creeperGirl);
	}

	
	private static void spawnFetusesAndBabiesCreeper (float alive, float fetus, int numOfBabies, AbstractCreeperGirl creeperGirl) {	
		var randomSource = creeperGirl.getRandom();
			
		if (creeperGirl.level() instanceof ServerLevel level) {
			
			final var MIN_HEALTH = (int) Math.floor(creeperGirl.getMaxHealth() * 0.25);
			final var MAX_HEALTH = (int) Math.floor(creeperGirl.getMaxHealth() * 0.75);
			
			for (int i = 0; i < numOfBabies; ++i) {				
				if (randomSource.nextFloat() < alive) {								
					CreeperGirlEntity entityToSpawn = MinepreggoModEntities.CREEPER_GIRL.get().spawn(level, BlockPos.containing(creeperGirl.getX(), creeperGirl.getY() + creeperGirl.getBbHeight() / 2F, creeperGirl.getZ()), MobSpawnType.MOB_SUMMONED);
					if (entityToSpawn != null) {
						entityToSpawn.setBaby(true);
						entityToSpawn.setYRot(level.getRandom().nextFloat() * 360F);
						entityToSpawn.setTarget(creeperGirl.getLastHurtByMob());
						entityToSpawn.setHealth(randomSource.nextInt(MIN_HEALTH, MAX_HEALTH));
					}			
				}	
				else {
					if (randomSource.nextFloat() < fetus) {
						ItemEntity entityToSpawn = new ItemEntity(level, creeperGirl.getX(), creeperGirl.getY(), creeperGirl.getZ(), new ItemStack(MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get()));
						entityToSpawn.setPickUpDelay(10);
						level.addFreshEntity(entityToSpawn);
					}
				}
			}
		}
	}
	
	private static void spawnFetusesAndBabiesZombie (float alive, float fetus, int numOfBabies, AbstractZombieGirl zombieGirl) {	
		var randomSource = zombieGirl.getRandom();
			
		if (zombieGirl.level() instanceof ServerLevel level) {
			
			final var MIN_HEALTH = (int) Math.floor(zombieGirl.getMaxHealth() * 0.25);
			final var MAX_HEALTH = (int) Math.floor(zombieGirl.getMaxHealth() * 0.75);
			
			for (int i = 0; i < numOfBabies; ++i) {
				
				if (randomSource.nextFloat() < alive) {	
					Mob entityToSpawn;
					if (randomSource.nextBoolean()) {
						entityToSpawn = EntityType.ZOMBIE.spawn(level, BlockPos.containing(zombieGirl.getX(), zombieGirl.getY() + zombieGirl.getBbHeight() / 2F, zombieGirl.getZ()), MobSpawnType.MOB_SUMMONED);
					}
					else {
						entityToSpawn = MinepreggoModEntities.ZOMBIE_GIRL.get().spawn(level, BlockPos.containing(zombieGirl.getX(), zombieGirl.getY() + zombieGirl.getBbHeight() / 2F, zombieGirl.getZ()), MobSpawnType.MOB_SUMMONED);
					}	
					
					if (entityToSpawn != null) {
						entityToSpawn.setBaby(true);
						entityToSpawn.setYRot(level.getRandom().nextFloat() * 360F);
						entityToSpawn.setTarget(zombieGirl.getLastHurtByMob());
						entityToSpawn.setHealth(randomSource.nextInt(MIN_HEALTH, MAX_HEALTH));
					}				
				}	
				else {
					if (randomSource.nextFloat() < fetus) {
						ItemEntity entityToSpawn = new ItemEntity(level, zombieGirl.getX(), zombieGirl.getY(), zombieGirl.getZ(), new ItemStack(MinepreggoModItems.DEAD_ZOMBIE_FETUS.get()));
						entityToSpawn.setPickUpDelay(10);
						level.addFreshEntity(entityToSpawn);
					}
				}
			}		
		}
	}
	
	public static void setPlayerSpecialPregnancyEffect(Player player) {
		
		final var babyType = (int) player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinepreggoModVariables.PlayerVariables()).playerBabyType;
		
		if (player.level() != null && !player.level().isClientSide) {
			if (babyType == BabyType.ZOMBIE.getId()) {
				player.addEffect(new MobEffectInstance(MinepreggoModMobEffects.FULL_OF_ZOMBIES.get(), -1, 0));			
			}
			else if (babyType == BabyType.HUMANOID_CREEPER.getId()) {
				player.addEffect(new MobEffectInstance(MinepreggoModMobEffects.FULL_OF_CREEPERS.get(), -1, 0));			
			}
		}
	}
	*/
	
	
	public static boolean defaultDoHurtTarget(boolean success, LivingEntity entity, Entity target) {
		      
	    if (!success) return false;
	   	    
		var world = entity.level();
		
		ItemStack mainHandItemStack = entity.getMainHandItem();
	    Item mainHand = mainHandItemStack.getItem();
		    
		if (mainHand != ItemStack.EMPTY.getItem() 
				&& mainHandItemStack.isDamageableItem()) {		

				if (mainHand instanceof AxeItem
						|| mainHand instanceof PickaxeItem
						|| mainHand instanceof ShovelItem
						|| mainHand instanceof HoeItem) {
					
					if (!mainHandItemStack.isEnchanted()
							&& mainHandItemStack.hurt(2, RandomSource.create(), null)) {
						mainHandItemStack.shrink(1);
						mainHandItemStack.setDamageValue(0);
					} else {
						if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.UNBREAKING, mainHandItemStack) != 0) {
							if (Math.random() > ((100 - 100 / (entity.getMainHandItem().getEnchantmentLevel(Enchantments.UNBREAKING) + 1D)) * 0.65) / 100
									&& mainHandItemStack.hurt(2, RandomSource.create(), null)) {
								mainHandItemStack.shrink(1);
								mainHandItemStack.setDamageValue(0);
							}
						} else {
							if (mainHandItemStack.hurt(2, RandomSource.create(), null)) {
								mainHandItemStack.shrink(1);
								mainHandItemStack.setDamageValue(0);
							}
						}
					}
				} else if (mainHand instanceof SwordItem
						|| mainHand instanceof TridentItem) {
					if (!(entity.getMainHandItem().isEnchanted())) {
						if (mainHandItemStack.hurt(1, RandomSource.create(), null)) {
							mainHandItemStack.shrink(1);
							mainHandItemStack.setDamageValue(0);
						}
					} else {									
						if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.UNBREAKING, mainHandItemStack) != 0) {
							if (Math.random() > ((100 - 100 / (entity.getMainHandItem().getEnchantmentLevel(Enchantments.UNBREAKING) + 1D)) * 0.65) / 100
									&& mainHandItemStack.hurt(1, RandomSource.create(), null)) {
								mainHandItemStack.shrink(1);
								mainHandItemStack.setDamageValue(0);
							}
						} else {
							if (mainHandItemStack.hurt(1, RandomSource.create(), null)) {
								mainHandItemStack.shrink(1);
								mainHandItemStack.setDamageValue(0);
							}
						}
					}
				}
				
				if (!world.isClientSide()) {
					if (mainHandItemStack.getDamageValue() >= mainHandItemStack.getMaxDamage()) {			
						world.playSound((Player) null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.item.break")), SoundSource.NEUTRAL, 1, 1);
					} else {
						final ItemStack setstack = mainHandItemStack.copy();
						setstack.setCount(entity.getMainHandItem().getCount());
						entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable modHandlerEntSetSlot)
								modHandlerEntSetSlot.setStackInSlot(4, setstack);
						});
					}
				}
		}
		
		return success;
	}


	public static boolean defaultHurt(boolean success, LivingEntity entity, DamageSource damagesource, float amount) {
		
		if (!success) return false;
		
		LevelAccessor world = entity.level();
		
		int lNumberOfArmors = 0;
		
		for (int i = 0; i < 4; i++) {
			if (entity.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, i)).getItem() != ItemStack.EMPTY.getItem()) {
				lNumberOfArmors = lNumberOfArmors + 1;
			}
		}
				
		for (int i = 0; i < 4; i++) {
			
			ItemStack armorItemStack = entity.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, i));
			Item armorItem = armorItemStack.getItem();
					
			if (armorItem != ItemStack.EMPTY.getItem()) {
				if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.UNBREAKING, armorItemStack) != 0) {
					if (Math.random() > (100 - (60
							+ 40D / (1 + armorItem.getEnchantmentLevel(armorItemStack, Enchantments.UNBREAKING))))
							* 0.01) {
						if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION)) {
							if (armorItemStack.hurt(2, RandomSource.create(), null)) {
								armorItemStack.shrink(1);
								armorItemStack.setDamageValue(0);
							}
						} else {
							if (lNumberOfArmors > 1) {
									
								if ((i == 3 || i == 0) && Math.random() < 0.7) {
									if (armorItemStack.hurt(1, RandomSource.create(), null)) {
										armorItemStack.shrink(1);
										armorItemStack.setDamageValue(0);
									}
								} else if ((i == 2 || i == 1) && Math.random() < 0.85) {
									if (armorItemStack.hurt(1, RandomSource.create(), null)) {
										armorItemStack.shrink(1);
										armorItemStack.setDamageValue(0);
									}
								}  
							} else {
								if (armorItemStack.hurt(1, RandomSource.create(), null)) {
									armorItemStack.shrink(1);
									armorItemStack.setDamageValue(0);
								}
							}
						}
					}
				} else {
					if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION)) {
						if (armorItemStack.hurt(2, RandomSource.create(), null)) {
							armorItemStack.shrink(1);
							armorItemStack.setDamageValue(0);
						}
					} else {
						if (lNumberOfArmors > 1) {
							if ((i == 3 || i == 0) && Math.random() < 0.7) {
								if (armorItemStack.hurt(1, RandomSource.create(), null)) {
									armorItemStack.shrink(1);
									armorItemStack.setDamageValue(0);
								}
							} else if ((i == 2 || i == 1) && Math.random() < 0.85) {
								if (armorItemStack.hurt(1, RandomSource.create(), null)) {
									armorItemStack.shrink(1);
									armorItemStack.setDamageValue(0);
								}
							} 
						} else {
							if (armorItemStack.hurt(1, RandomSource.create(), null)) {
								armorItemStack.shrink(1);
								armorItemStack.setDamageValue(0);
							}
						}
					}
				}
				if (!world.isClientSide()) {
					if (armorItemStack.getDamageValue() >= armorItemStack.getMaxDamage()) {
						
						if (world instanceof Level level) {
							if (!level.isClientSide()) {
								level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.item.break")), SoundSource.NEUTRAL, 1, 1);
							} else {
								level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.item.break")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
						
					} else {
						final int slotid = i;
						final ItemStack setstack = armorItemStack.copy();
						setstack.setCount(armorItemStack.getCount());
						entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable modHandlerEntSetSlot)
								modHandlerEntSetSlot.setStackInSlot(slotid, setstack);
						});
					}
				}
			}
		}
	
		return true;
	}
	

}

