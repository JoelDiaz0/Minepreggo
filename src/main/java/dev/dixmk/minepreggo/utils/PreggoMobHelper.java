package dev.dixmk.minepreggo.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.entity.preggo.IPreggoMob;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP1;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP2;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP3;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP4;
import dev.dixmk.minepreggo.entity.preggo.IPregnancySystem;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class PreggoMobHelper {
			
	private PreggoMobHelper() {}
	
	
	public static void main(String[] args) {
		System.out.println(EquipmentSlot.FEET.getFilterFlag());
		System.out.println(EquipmentSlot.LEGS.getFilterFlag());
		System.out.println(EquipmentSlot.CHEST.getFilterFlag());
		System.out.println(EquipmentSlot.HEAD.getFilterFlag());
		System.out.println(EquipmentSlot.MAINHAND.getFilterFlag());
		System.out.println(EquipmentSlot.OFFHAND.getFilterFlag());
	}
	
	
	
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

	public static <E extends TamableAnimal & IPreggoMob> void transferPreggoMobInventary(E source, E target) {
		transferSlots(source, target);
		target.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
			if (capability instanceof IItemHandlerModifiable modHandlerEntSetSlot) {
				modHandlerEntSetSlot.setStackInSlot(IPreggoMob.INVENTARY_HEAD, target.getItemBySlot(EquipmentSlot.HEAD));
				modHandlerEntSetSlot.setStackInSlot(IPreggoMob.INVENTARY_CHEST, target.getItemBySlot(EquipmentSlot.CHEST));
				modHandlerEntSetSlot.setStackInSlot(IPreggoMob.INVENTARY_LEGS, target.getItemBySlot(EquipmentSlot.LEGS));
				modHandlerEntSetSlot.setStackInSlot(IPreggoMob.INVENTARY_FEET, target.getItemBySlot(EquipmentSlot.FEET));
				modHandlerEntSetSlot.setStackInSlot(IPreggoMob.INVENTARY_MAINHAND, target.getItemBySlot(EquipmentSlot.MAINHAND));
				modHandlerEntSetSlot.setStackInSlot(IPreggoMob.INVENTARY_OFFHAND, target.getSlot(IPreggoMob.INVENTARY_OFFHAND).get());
			}
		});
		
		AtomicReference<IItemHandler> itemhandlerref = new AtomicReference<>();
		source.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(itemhandlerref::set);		
		if (itemhandlerref.get() != null) {	
			for (int idx = IPreggoMob.INVENTARY_FOOD + 1; idx < itemhandlerref.get().getSlots(); idx++) {			
				final int slotid = idx;			
				ItemStack itemstackiterator = itemhandlerref.get().getStackInSlot(slotid).copy();		
				target.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable modHandlerEntSetSlot)
						modHandlerEntSetSlot.setStackInSlot(slotid, itemstackiterator.copy());
				});
			}
		}	
	}
	
	private static <E extends TamableAnimal> void transferSlots(E source, E target) {
		target.setItemInHand(InteractionHand.MAIN_HAND, source.getMainHandItem().copy());
		target.setItemInHand(InteractionHand.OFF_HAND, source.getOffhandItem().copy());
		target.setItemSlot(EquipmentSlot.HEAD, source.getItemBySlot(EquipmentSlot.HEAD).copy());
		target.setItemSlot(EquipmentSlot.CHEST, source.getItemBySlot(EquipmentSlot.CHEST).copy());
		target.setItemSlot(EquipmentSlot.LEGS, source.getItemBySlot(EquipmentSlot.LEGS).copy());
		target.setItemSlot(EquipmentSlot.FEET, source.getItemBySlot(EquipmentSlot.FEET).copy());
	}

	
	public static void transferAttackTarget(Mob source, Mob target) {
		if (source == null || target == null)
			return;
		
		final Vec3 center = new Vec3(source.getX(), source.getY(), source.getZ());
		List<LivingEntity> entfound = Collections.unmodifiableList(source.level().getEntitiesOfClass(LivingEntity.class, new AABB(center, center).inflate(16), e -> true)
				.stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(center))).toList());
			
		for (var ent : entfound) {
			if (ent instanceof Mob mob && mob.getTarget() == source) {
				mob.setTarget(target);
			}
			if (source.getTarget() == ent) {
				target.setTarget(ent);
			}	
		}
	}


	public static void defaultDoHurtTarget(LivingEntity entity, Entity target) {
		      	    
		var world = entity.level();		
		ItemStack mainHandItemStack = entity.getMainHandItem();
	    Item mainHand = mainHandItemStack.getItem();
		        
	    if (mainHand == ItemStack.EMPTY.getItem() || !mainHandItemStack.isDamageableItem()) {
	    	return;
	    }
	    
	        
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
						modHandlerEntSetSlot.setStackInSlot(IPreggoMob.INVENTARY_MAINHAND, setstack);
				});
			}
		}
	}

	@Nonnegative
	public static int getNumberOfChildrens(PregnancyStage maxPregnancyStage) {
		return 0;
	}
	
	
	@Nonnegative
	public static int getNumberOfDaysByMaxPregnancyStage(PregnancyStage maxPregnancyStage) {
		return 0;
	}
	

	
	public static<E extends TamableAnimal & IPreggoMob & IPregnancySystem & IPregnancyP1> void startPregnancy(E preggoMob, PregnancyStage maxPregnancyStage) {	
		final int daysByStage = getNumberOfDaysByMaxPregnancyStage(maxPregnancyStage);
		final int daysToBirth = (maxStage - currentStage + 1) * daysByStage;
		
		preggoMob.setDaysPassed(0);
		preggoMob.setMaxPregnancyStage(maxPregnancyStage);
		preggoMob.setDaysByStage(daysByStage);
		preggoMob.setDaysToGiveBirth(daysToBirth);
		preggoMob.setPregnancyHealth(100);
	}
	
	
	
	public static <E extends TamableAnimal & IPreggoMob & IPregnancySystem> void defaultDamagePregnancyHealth(E preggoMob, DamageSource damagesource) {
		
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
	
	public static void spawnFetusesAndBabiesZombie(AbstractTamablePregnantZombieGirl zombieGirl) {	
		
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
		}
	} 
	
	
	public static void spawnFetusesAndBabiesCreeper(AbstractTamablePregnantCreeperGirl creeperGirl) {
		
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
		}	
	}
	
	
	public static void defaultHurt(LivingEntity entity, DamageSource damagesource, float amount) {
			
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
					
			if (armorItem == ItemStack.EMPTY.getItem()) {
				return;
			}
					
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
							if (((i == 3 || i == 0) && Math.random() < 0.7 || ((i == 2 || i == 1) && Math.random() < 0.85))) {
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
						if (((i == 3 || i == 0) && Math.random() < 0.7) || ((i == 2 || i == 1) && Math.random() < 0.85)) {
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
					world.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.item.break")), SoundSource.NEUTRAL, 1, 1);				
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
}

