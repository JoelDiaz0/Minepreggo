package dev.dixmk.minepreggo.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.entity.preggo.IPreggoMob;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP1;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP2;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP3;
import dev.dixmk.minepreggo.entity.preggo.IPregnancyP4;
import dev.dixmk.minepreggo.entity.preggo.IPregnancySystem;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.PregnancySystemConstants;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractMonsterPregnantCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractMonsterPregnantZombieGirl;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.entity.preggo.zombie.AbstractZombieGirl;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
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

	public static <E extends TamableAnimal & IPreggoMob> void transferPreggoMobInventary(E source, E target) {
		transferSlots(source, target);
	
		AtomicReference<IItemHandler> itemhandlerref = new AtomicReference<>();
		source.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(itemhandlerref::set);		
		if (itemhandlerref.get() != null) {	
			for (int idx = 0; idx < itemhandlerref.get().getSlots(); idx++) {			
				final int slotid = idx;			
				target.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable modHandlerEntSetSlot)
						modHandlerEntSetSlot.setStackInSlot(slotid, itemhandlerref.get().getStackInSlot(slotid).copy());
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
				.stream().sorted(Comparator.comparingDouble(entcnd -> entcnd.distanceToSqr(center))).toList());
			
		for (var ent : entfound) {
			if (ent instanceof Mob mob && mob.getTarget() == source) {
				mob.setTarget(target);
			}
			if (source.getTarget() == ent) {
				target.setTarget(ent);
			}	
		}
	}

	@Nonnegative
	public static int getNumberOfChildrens(PregnancyStage maxPregnancyStage) {			
		switch (maxPregnancyStage) {
		case P5: {		
			return 3;
		}
		case P6: {		
			return 4;
		}
		case P7: {		
			return 5;
		}
		default:
			return 2;
		}
	}
		
	@Nonnegative
	public static int getNumberOfDaysByMaxPregnancyStage(PregnancyStage maxPregnancyStage, RandomSource randomSource) {
		return (60 + randomSource.nextInt(10)) / maxPregnancyStage.ordinal();
	}
	
	public static<E extends TamableAnimal & IPreggoMob & IPregnancySystem & IPregnancyP1> void startPregnancy(E preggoMob, PregnancyStage maxPregnancyStage) {	
		final int daysByStage = getNumberOfDaysByMaxPregnancyStage(maxPregnancyStage, preggoMob.getRandom());
		final int daysToBirth = (maxPregnancyStage.ordinal() - preggoMob.getCurrentPregnancyStage().ordinal() + 1) * daysByStage;
		
		preggoMob.setDaysPassed(0);
		preggoMob.setMaxPregnancyStage(maxPregnancyStage);
		preggoMob.setDaysByStage(daysByStage);
		preggoMob.setDaysToGiveBirth(daysToBirth);
		preggoMob.setPregnancyHealth(100);
	}
		
	public static<E extends TamableAnimal & IPreggoMob & IPregnancySystem & IPregnancyP1> void startPregnancy(E preggoMob) {	
		startPregnancy(preggoMob, PregnancyStage.P4);
	}
	
	public static <E extends TamableAnimal & IPreggoMob & IPregnancySystem> void defaultDamagePregnancyHealth(E preggoMob, DamageSource damagesource) {
		
		var randomSource = preggoMob.getRandom();
		
		if ((preggoMob.hasEffect(MinepreggoModMobEffects.PREGNANCY_RESISTANCE_EFFECT.get()) && randomSource.nextFloat() < 0.9F)
				|| (!damagesource.is(DamageTypes.FALL) && preggoMob.getItemBySlot(EquipmentSlot.CHEST).getItem() != ItemStack.EMPTY.getItem() && randomSource.nextFloat() < 0.5)) {
			return;
		}
		
		boolean showMessage = false;
		
		if (preggoMob.getHealth() < preggoMob.getMaxHealth() / 2F) {
			int damage = 0;
			
			if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION) || damagesource.is(DamageTypes.FALL)) {
				damage *= 2;
			}
			
			preggoMob.setPregnancyHealth(Math.max(0, preggoMob.getPregnancyHealth() - damage));			
			showMessage = true;
		} 
		else if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION) || damagesource.is(DamageTypes.FALL)) {
			preggoMob.setPregnancyHealth(Math.max(0, preggoMob.getPregnancyHealth() - 5));
			showMessage = true;
		}
		
		if (showMessage && preggoMob.getPregnancyHealth() < 40) {
			PreggoMessageHelper.warningOwnerPossibleMiscarriageEvent(preggoMob);
		}
	}
	
	private static<E extends TamableAnimal & IPreggoMob & IPregnancySystem> float getSpawnProbabilityBasedPregnancy(E preggoMob, float t0, float k, float pMin, float pMax) {
		final int totalDays = preggoMob.getMaxPregnancyStage().ordinal() * preggoMob.getDaysByStage();
		final int totalDaysPassed = Math.max(0, preggoMob.getCurrentPregnancyStage().ordinal() - 1) * preggoMob.getDaysByStage() + preggoMob.getDaysPassed();  				
		final float t = Mth.clamp(totalDaysPassed / (float) totalDays, 0, 1);	
		return PreggoMathHelper.sigmoid(pMin, pMax, k, t, t0);
	}
	
	private static void spawnBabiesOrFetusesZombie(float p, int numOfBabies, AbstractZombieGirl zombieGirl) {
		
		if (!(zombieGirl.level() instanceof ServerLevel serverLevel)) {
			return;
		}
		
		final var MIN_HEALTH = (int) Math.floor(zombieGirl.getMaxHealth() * 0.2);
		final var MAX_HEALTH = (int) Math.floor(zombieGirl.getMaxHealth() * 0.6);
		var randomSource = zombieGirl.getRandom();
			
		for (int i = 0; i < numOfBabies; ++i) {
			
			if (randomSource.nextFloat() < p) {	
				Mob entityToSpawn;
				if (randomSource.nextBoolean()) {
					entityToSpawn = EntityType.ZOMBIE.spawn(serverLevel, BlockPos.containing(zombieGirl.getX(), zombieGirl.getY() + zombieGirl.getBbHeight() / 2F, zombieGirl.getZ()), MobSpawnType.MOB_SUMMONED);
				}
				else {
					entityToSpawn = MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(zombieGirl.getX(), zombieGirl.getY() + zombieGirl.getBbHeight() / 2F, zombieGirl.getZ()), MobSpawnType.MOB_SUMMONED);
				}				
				entityToSpawn.setBaby(true);
				entityToSpawn.setYRot(randomSource.nextFloat() * 360F);
				entityToSpawn.setTarget(zombieGirl.getLastHurtByMob());
				entityToSpawn.setHealth(randomSource.nextInt(MIN_HEALTH, MAX_HEALTH));				
			}	
			else {
				ItemEntity entityToSpawn = new ItemEntity(serverLevel, zombieGirl.getX(), zombieGirl.getY(), zombieGirl.getZ(), new ItemStack(MinepreggoModItems.DEAD_ZOMBIE_FETUS.get()));
				entityToSpawn.setPickUpDelay(10);
				serverLevel.addFreshEntity(entityToSpawn);
			}
		}	
	}
	
	private static void spawnBabiesOrFetusesCreeper(float p, int numOfBabies, AbstractCreeperGirl creeperGirl) {
		
		if (!(creeperGirl.level() instanceof ServerLevel serverLevel)) {
			return;
		}
		
		final var MIN_HEALTH = (int) Math.floor(creeperGirl.getMaxHealth() * 0.2);
		final var MAX_HEALTH = (int) Math.floor(creeperGirl.getMaxHealth() * 0.6);
		var randomSource = creeperGirl.getRandom();
		
		for (int i = 0; i < numOfBabies; ++i) {				
			if (randomSource.nextFloat() < p) {								
				var entityToSpawn = MinepreggoModEntities.MONSTER_CREEPER_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(creeperGirl.getX(), creeperGirl.getY() + creeperGirl.getBbHeight() / 2F, creeperGirl.getZ()), MobSpawnType.MOB_SUMMONED);
				entityToSpawn.setBaby(true);
				entityToSpawn.setYRot(randomSource.nextFloat() * 360F);
				entityToSpawn.setTarget(creeperGirl.getLastHurtByMob());
				entityToSpawn.setHealth(randomSource.nextInt(MIN_HEALTH, MAX_HEALTH));			
			}	
			else {
				ItemEntity entityToSpawn = new ItemEntity(serverLevel, creeperGirl.getX(), creeperGirl.getY(), creeperGirl.getZ(), new ItemStack(MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get()));
				entityToSpawn.setPickUpDelay(10);
				serverLevel.addFreshEntity(entityToSpawn);
			}
		}	
	}

	private static void spawnZombieFetuses(float p, int numOfBabies, AbstractZombieGirl zombieGirl) {
		if (!(zombieGirl.level() instanceof ServerLevel serverLevel)) {
			return;
		}	
		var randomSource = zombieGirl.getRandom();
		for (int i = 0; i < numOfBabies; ++i) {	
			if (p < randomSource.nextFloat()) {
				ItemEntity entityToSpawn = new ItemEntity(serverLevel, zombieGirl.getX(), zombieGirl.getY(), zombieGirl.getZ(), new ItemStack(MinepreggoModItems.DEAD_ZOMBIE_FETUS.get()));
				entityToSpawn.setPickUpDelay(10);
				serverLevel.addFreshEntity(entityToSpawn);
			}
		}
	}
	
	private static void spawnCreeperFetuses(float p, int numOfBabies, AbstractCreeperGirl creeperGirl) {
		if (!(creeperGirl.level() instanceof ServerLevel serverLevel)) {
			return;
		}	
		var randomSource = creeperGirl.getRandom();
		for (int i = 0; i < numOfBabies; ++i) {	
			if (p < randomSource.nextFloat()) {
				ItemEntity entityToSpawn = new ItemEntity(serverLevel, creeperGirl.getX(), creeperGirl.getY(), creeperGirl.getZ(), new ItemStack(MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get()));
				entityToSpawn.setPickUpDelay(10);
				serverLevel.addFreshEntity(entityToSpawn);
			}
		}
	}
		
	public static void spawnFetusesAndBabiesZombie(AbstractTamablePregnantZombieGirl<?> zombieGirl) {		

		final var numOfBabies = getNumberOfChildrens(zombieGirl.getMaxPregnancyStage());
		
		if (zombieGirl instanceof IPregnancyP3) {
			final var alive = getSpawnProbabilityBasedPregnancy(zombieGirl, 0.6F, 0.5F, 0.2F, 0.95F);
			spawnBabiesOrFetusesZombie(alive, numOfBabies, zombieGirl);
		}	
		else {		
			final var fetusSpawn = getSpawnProbabilityBasedPregnancy(zombieGirl, 0.7F, 0.8F, 0.1F, 0.8F);
			spawnZombieFetuses(fetusSpawn, numOfBabies, zombieGirl);
		}
	}
	
	public static void spawnFetusesAndBabiesCreeper(AbstractTamablePregnantCreeperGirl<?> creeperGirl) {
		
		final var numOfBabies = getNumberOfChildrens(creeperGirl.getMaxPregnancyStage());
	
		if (creeperGirl instanceof IPregnancyP3) {
			final var alive = getSpawnProbabilityBasedPregnancy(creeperGirl, 0.7F, 0.6F, 0.15F, 0.8F);
			spawnBabiesOrFetusesCreeper(alive, numOfBabies, creeperGirl);
		}	
		else {		
			final var fetusSpawn = getSpawnProbabilityBasedPregnancy(creeperGirl, 0.75F, 0.85F, 0.075F, 0.7F);
			spawnCreeperFetuses(fetusSpawn, numOfBabies, creeperGirl);
		}
	}
	
	
	public static void spawnFetusesAndBabiesCreeper(AbstractMonsterPregnantCreeperGirl creeperGirl) {	
		
		final var currentPregnancyStage = creeperGirl.getCurrentPregnancyStage();
		
		if (currentPregnancyStage == PregnancyStage.P0) {
			return;
		}
			
		final var numOfBabies = getNumberOfChildrens(creeperGirl.getCurrentPregnancyStage());
		final var t = Mth.clamp(PregnancySystemConstants.TOTAL_PREGNANCY_DAYS / (float) creeperGirl.getTotalDaysPassed(), 0, 1);
		
		if (currentPregnancyStage.ordinal() > 2) {
			spawnBabiesOrFetusesCreeper(PreggoMathHelper.sigmoid(0.2F, 0.9F, 0.5F, t, 0.6F), numOfBabies, creeperGirl);
		}
		else {
			spawnCreeperFetuses(PreggoMathHelper.sigmoid(0.2F, 0.9F, 0.5F, t, 0.6F), numOfBabies, creeperGirl);
		}
	}
	
	public static void spawnFetusesAndBabiesZombie(AbstractMonsterPregnantZombieGirl zombie) {	
		
		final var currentPregnancyStage = zombie.getCurrentPregnancyStage();
		
		if (currentPregnancyStage == PregnancyStage.P0) {
			return;
		}
		
		final var numOfBabies = getNumberOfChildrens(zombie.getCurrentPregnancyStage());
		final var t = Mth.clamp(PregnancySystemConstants.TOTAL_PREGNANCY_DAYS / (float) zombie.getTotalDaysPassed(), 0, 1);
		
		if (currentPregnancyStage.ordinal() > 2) {
			spawnBabiesOrFetusesZombie(PreggoMathHelper.sigmoid(0.2F, 0.9F, 0.5F, t, 0.6F), numOfBabies, zombie);
		}
		else {
			spawnZombieFetuses(PreggoMathHelper.sigmoid(0.2F, 0.9F, 0.5F, t, 0.6F), numOfBabies, zombie);
		}
	}
		
	public static<E extends TamableAnimal & IPreggoMob> void defaultDoHurtTarget(E entity, Entity target) {
  	    
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
				entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
					if (capability instanceof IItemHandlerModifiable modHandlerEntSetSlot)
						modHandlerEntSetSlot.setStackInSlot(IPreggoMob.MAINHAND_INVENTARY_SLOT, mainHandItemStack);
				});
			}
		}
	}
	
	public static<E extends TamableAnimal & IPreggoMob> void defaultHurt(E entity, DamageSource damagesource, float amount) {
			
		var world = entity.level();
		
		int numOfArmors = 0;
		
		for (int i = 0; i < 4; i++) {
			if (entity.getItemBySlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, i)).getItem() != ItemStack.EMPTY.getItem()) {
				++numOfArmors;
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
						if (numOfArmors > 1) {							
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
					if (numOfArmors > 1) {
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
					final var slotId = i + 1;
					entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {	
						if (capability instanceof IItemHandlerModifiable modHandlerEntSetSlot) {
							modHandlerEntSetSlot.setStackInSlot(slotId, armorItemStack);
						}		
					});
				}
			}
		}
	}
}

