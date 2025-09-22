package dev.dixmk.minepreggo.utils;

import java.util.Comparator;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.entity.preggo.IPreggoMob;

import net.minecraft.core.BlockPos;
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
	
	public static<E extends TamableAnimal & IPreggoMob> void evaluatePreggoMobHungryTimer(E preggoMob, Level level) {
	    if (preggoMob == null)
	        return;
   
	    final var currentHungry = preggoMob.getHungry();
	    final var currentHungryTimer = preggoMob.getHungryTimer();
	    
	    final var x = preggoMob.getX();
	    final var y = preggoMob.getY();
	    final var z = preggoMob.getZ();
	    
	    if (!preggoMob.isSavage()) {
	        if (currentHungry > 0) {
	            int timerIncrement = 1;
	            if (preggoMob.getDeltaMovement().x() != 0 || preggoMob.getDeltaMovement().z() != 0) {
	                timerIncrement += 1;              
		            if (preggoMob.isInWater()) {
		                timerIncrement += 1;
		            }
	            }
	            
	            if (currentHungryTimer < MinepreggoModConfig.getTotalTicksOfHungry()) {
	            	preggoMob.setHungryTimer(currentHungryTimer + timerIncrement);
	            } else {
	            	preggoMob.setHungryTimer(0);
	            	preggoMob.setHungry(currentHungry + 1);
	            }

	            if (currentHungry >= MinepreggoModConfig.getMinHungryToHeal() && preggoMob.getHealth() < preggoMob.getMaxHealth()) {
	            	preggoMob.heal(1F);
	                preggoMob.setHungry(currentHungry - 1);
	            }
	            
	        } else {
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
	        		
	            Player player = level.getEntitiesOfClass(Player.class, new AABB(center, center).inflate(24), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null);
	        	
	        	if (player != null)
	        		preggoMob.setTarget(player);
	        }

	        if (!level.isClientSide() && currentHungry >= MinepreggoModConfig.getMinHungryToTameAgain()) {
	        	preggoMob.setSavage(false);
	        	preggoMob.setTarget(null);
	        }
	    }
	}
	
	public static<E extends TamableAnimal & IPreggoMob> void evaluatePreggoMobHungryTimer(E preggoMob) {
		evaluatePreggoMobHungryTimer(preggoMob, preggoMob.level());
	}
	
	
	public static<E extends TamableAnimal & IPreggoMob> boolean evaluatePreggoMobPregnancyBeginning(E preggoMob) {		
	    	
		if (preggoMob.isPregnant()) {
			
			var world = preggoMob.level();
			
	        if (!world.isClientSide() && preggoMob.getPregnancyTimer() >= MinepreggoModConfig.getTicksToStartPregnancy()) {
	            return true;
	        } else {

	        	preggoMob.setPregnancyTimer(preggoMob.getPregnancyTimer() + 1);

                if (!world.isClientSide()
                		&& world.getRandom().nextFloat() < MinepreggoModConfig.getMorningSicknessProbability()
                		&& !preggoMob.hasEffect(MobEffects.CONFUSION)) {
                	preggoMob.addEffect(new MobEffectInstance(MobEffects.CONFUSION, MinepreggoModConfig.getTotalTicksOfMorningSickness(), 0, false, true));                 
                }
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

