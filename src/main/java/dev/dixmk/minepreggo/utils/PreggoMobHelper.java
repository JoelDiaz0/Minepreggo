package dev.dixmk.minepreggo.utils;

import java.awt.CardLayout;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.entity.preggo.IPreggoMob;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP1;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP2;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP3;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP4;
import dev.dixmk.minepreggo.entity.preggo.IPregnancySystem;
import dev.dixmk.minepreggo.entity.preggo.PreggoMobState;
import dev.dixmk.minepreggo.entity.preggo.PreggoMobSystem;
import dev.dixmk.minepreggo.entity.preggo.PregnancyPain;
import dev.dixmk.minepreggo.entity.preggo.PregnancySymptom;
import dev.dixmk.minepreggo.entity.preggo.PregnancySystemP1;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP0;
import dev.dixmk.minepreggo.entity.preggo.creeper.TamableCreeperGirlP1;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.entity.preggo.zombie.TamableZombieGirlP0;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.item.ItemEntity;
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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.registries.ForgeRegistries;

public class PreggoMobHelper {
			
	private PreggoMobHelper() {}
	
	public static<E extends TamableAnimal & IPreggoMob> void transferPreggoMobBasicData(E source, E target) {		
		target.setYRot(source.getYRot());
		target.setYBodyRot(source.getYRot());
		target.setYHeadRot(source.getYRot());
		target.setXRot(source.getXRot());
		target.setHealth(source.getHealth());
			
		if (source.hasCustomName()) target.setCustomName(source.getCustomName());
		if (source.isTame() && source.getOwner() != null) target.tame((Player) source.getOwner());
				
		target.setHungry(source.getHungry());
		target.setHungryTimer(source.getHungryTimer());
		target.setWaiting(source.isWaiting());
		target.setAngry(source.isAngry());
		target.setSavage(source.isSavage());
		target.setPregnancyTimer(0);
		target.setMaxPregnancyStage(source.getMaxPregnancyStage());
	}
	
	public static<E extends TamableAnimal & IPreggoMob & IPregnancySystem & IPregnancyP1> void transferPregnancyP1Data(E source, E target) {
		transferPreggoMobBasicData(source, target);
		target.setCraving(source.getCraving());
		target.setCravingTimer(source.getCravingTimer());
		target.setCravingChosen(source.getCravingChosen());
		
		target.setDaysByStage(source.getDaysByStage());
		target.setDaysPassed(0);
		target.setDaysToGiveBirth(source.getDaysToGiveBirth());
		target.setPregnancyHealth(source.getPregnancyHealth());
		target.setPregnancyPain(source.getPregnancyPain());
		target.setPregnancySymptom(source.getPregnancySymptom());
		target.setPregnancyPainTimer(source.getPregnancyPainTimer());
	}
	
	
	public static<E extends TamableAnimal & IPreggoMob & IPregnancySystem & IPregnancyP2> void transferPregnancyP2Data(E source, E target) {
		transferPregnancyP1Data(source, target);
		target.setMilking(source.getMilking());
		target.setMilkingTimer(source.getMilkingTimer());
	}

	public static<E extends TamableAnimal & IPreggoMob & IPregnancySystem & IPregnancyP3> void transferPregnancyP3Data(E source, E target) {
		transferPregnancyP2Data(source, target);
		target.setBellyRubs(source.getBellyRubs());
		target.setBellyRubsTimer(source.getBellyRubsTimer());
	}
	
	public static<E extends TamableAnimal & IPreggoMob & IPregnancySystem & IPregnancyP4> void transferPregnancyP4Data(E source, E target) {
		transferPregnancyP3Data(source, target);
		target.setHorny(source.getHorny());
		target.setHornyTimer(source.getHornyTimer());
	}
	
	
	
	
	
	
	public static boolean hasValidTarget(Mob entity) {
	    LivingEntity target = entity.getTarget();
	    return target != null && target.isAlive() && entity.canAttack(target);
	}
	
	public static boolean isTargetStillValid(Mob entity) {
	    LivingEntity target = entity.getTarget();
	    return target != null && target.isAlive();
	}

	public static void putItemStackInOffHand(LivingEntity entity, Item item, int num) {
		if (entity.getOffhandItem() != ItemStack.EMPTY
				&& entity.level() instanceof ServerLevel level) {
			ItemEntity entityToSpawn = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), entity.getOffhandItem());
			entityToSpawn.setPickUpDelay(10);
			level.addFreshEntity(entityToSpawn);
		}
		entity.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(item, num).copy());
	}


	public static<E extends TamableAnimal & IPreggoMob> boolean evaluatePreggoMobHungry(E preggoMob, ServerPlayer owner, TagKey<Item> foods) {
	    var mainHandItem = owner.getMainHandItem();
	    var currentHunger = preggoMob.getHungry();
	    var world = preggoMob.level();
	    
	    if (currentHunger < 20) {
	        int foodValue = 0;

	        if (mainHandItem.is(foods)) {      	           	
	        	var foodProperties = mainHandItem.getItem().getFoodProperties(mainHandItem, preggoMob);
	        	foodValue = foodProperties.getNutrition();      
	        }
         
	        if (foodValue > 0) {
                if (!world.isClientSide()) {
                	world.playSound(null, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.generic.eat")), SoundSource.NEUTRAL, 0.75f, 1);	
                }
                
	            owner.getInventory().clearOrCountMatchingItems(p -> mainHandItem.getItem() == p.getItem(), 1, owner.inventoryMenu.getCraftSlots());
	            preggoMob.setHungry(currentHunger + foodValue);
	            
	            return true;
	        }
	    }
	    
	    return false;
	}


	

	
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
		
		var world = entity.level();
		
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
						if (!world.isClientSide()) {
							world.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.item.break")), SoundSource.NEUTRAL, 1, 1);
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

