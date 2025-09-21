package dev.dixmk.minepreggo.entity.preggo.zombie;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import dev.dixmk.minepreggo.entity.preggo.BabyType;
import dev.dixmk.minepreggo.entity.preggo.IImpregnable;
import dev.dixmk.minepreggo.entity.preggo.PreggoMobAnimationState;
import dev.dixmk.minepreggo.entity.preggo.PregnancyIllness;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.utils.PreggoMobHelper;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.wrapper.EntityArmorInvWrapper;
import net.minecraftforge.items.wrapper.EntityHandsInvWrapper;

public abstract class AbstractTamableZombieGirl extends AbstractZombieGirl implements IImpregnable {

	public static final EntityDataAccessor<Integer> DATA_HUNGRY = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_HUNGRY_TIMER = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.INT);
	
	public static final EntityDataAccessor<Integer> DATA_PREGNANCY_TIMER = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.INT);	
	public static final EntityDataAccessor<PregnancyStage> DATA_MAX_PREGNANCY_STAGE = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, MinepreggoModEntityDataSerializers.PREGNANCY_STAGE);
	public static final EntityDataAccessor<PregnancyIllness> DATA_PREGNANCY_ILLNESS = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, MinepreggoModEntityDataSerializers.PREGNANCY_ILLNESS);
	public static final EntityDataAccessor<Integer> DATA_PREGNANCY_ILLNESS_TIMER = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.INT);
	
	public static final EntityDataAccessor<Boolean> DATA_IS_PREGNANT = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> DATA_IS_SAVAGE = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> DATA_IS_ANGRY = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> DATA_IS_WAITING = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.BOOLEAN);

	public static final EntityDataAccessor<PreggoMobAnimationState> DATA_ANIMATION_STATE = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, MinepreggoModEntityDataSerializers.ANIMATION_STATE);
	
	/*
	public static final EntityDataAccessor<Integer> DATA_PREGNANCY_HEALTH = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.INT);	
	public static final EntityDataAccessor<Integer> DATA_DAYS_BY_STAGE = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_DAYS_PASSED = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_DAYS_TO_GIVE_BIRTH = SynchedEntityData.defineId(AbstractTamableZombieGirl.class, EntityDataSerializers.INT);
	*/

	protected final ItemStackHandler inventory;
	protected final CombinedInvWrapper combined;
	public static final int INVENTORY_SIZE = 14;
	
	protected AbstractTamableZombieGirl(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
	      super(p_21803_, p_21804_);
	      this.reassessTameGoals();	     
	      this.inventory = new ItemStackHandler(INVENTORY_SIZE);
	      this.combined = new CombinedInvWrapper(inventory, new EntityHandsInvWrapper(this), new EntityArmorInvWrapper(this));
	}
		
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_HUNGRY, 10);
		this.entityData.define(DATA_HUNGRY_TIMER, 0);
			
		this.entityData.define(DATA_IS_PREGNANT, false);
		this.entityData.define(DATA_IS_WAITING, false);	
		this.entityData.define(DATA_IS_SAVAGE, false);
		this.entityData.define(DATA_IS_ANGRY, false);
		
		this.entityData.define(DATA_PREGNANCY_TIMER, 0);
		this.entityData.define(DATA_MAX_PREGNANCY_STAGE, PregnancyStage.P0);
		this.entityData.define(DATA_PREGNANCY_ILLNESS, PregnancyIllness.NONE);
		this.entityData.define(DATA_PREGNANCY_ILLNESS_TIMER, 0);
		
		this.entityData.define(DATA_ANIMATION_STATE, PreggoMobAnimationState.IDLE);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.put("InventoryCustom", inventory.serializeNBT());
		compound.putInt("DataHungry", this.entityData.get(DATA_HUNGRY));
		compound.putInt("DataHungryTimer", this.entityData.get(DATA_HUNGRY_TIMER));
		
		compound.putBoolean("DataIsWaiting", this.entityData.get(DATA_IS_WAITING));
		compound.putBoolean("DataIsPregnant", this.entityData.get(DATA_IS_PREGNANT));
		compound.putBoolean("DataIsSavage", this.entityData.get(DATA_IS_SAVAGE));
		compound.putBoolean("DataIsAngry", this.entityData.get(DATA_IS_ANGRY));
		
		/*
		compound.putInt("DataPregnancyHealth", this.entityData.get(DATA_PREGNANCY_HEALTH));
		compound.putInt("DataDaysByStage", this.entityData.get(DATA_DAYS_BY_STAGE));	
		compound.putInt("DataDaysPassed", this.entityData.get(DATA_DAYS_PASSED));	
		compound.putInt("DataDaysToGiveBirth", this.entityData.get(DATA_DAYS_TO_GIVE_BIRTH));	
		*/
		
		compound.putInt("DataPregnancyTimer", this.entityData.get(DATA_PREGNANCY_TIMER));
		compound.putInt("DataMaxPregnancyStage", this.entityData.get(DATA_MAX_PREGNANCY_STAGE).ordinal());
		compound.putInt("DataPregnancyIllness", this.entityData.get(DATA_PREGNANCY_ILLNESS).ordinal());	
		compound.putInt("DataPregnancyIllnessTimer", this.entityData.get(DATA_PREGNANCY_ILLNESS_TIMER));	
	
		compound.putInt("DataAnimationStage", this.entityData.get(DATA_ANIMATION_STATE).ordinal());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		Tag inventoryCustom = compound.get("InventoryCustom");
		if (inventoryCustom instanceof CompoundTag inventoryTag)
			inventory.deserializeNBT(inventoryTag);
		if (compound.contains("DataHungry"))
			this.entityData.set(DATA_HUNGRY, compound.getInt("DataHungry"));
		if (compound.contains("DataHungryTimer"))
			this.entityData.set(DATA_HUNGRY_TIMER, compound.getInt("DataHungryTimer"));
	
		if (compound.contains("DataIsWaiting"))
			this.entityData.set(DATA_IS_WAITING, compound.getBoolean("DataIsWaiting"));
		if (compound.contains("DataIsPregnant"))
			this.entityData.set(DATA_IS_PREGNANT, compound.getBoolean("DataIsPregnant"));
		if (compound.contains("DataIsSavage"))
			this.entityData.set(DATA_IS_SAVAGE, compound.getBoolean("DataIsSavage"));
		if (compound.contains("DataIsAngry"))
			this.entityData.set(DATA_IS_ANGRY, compound.getBoolean("DataIsAngry"));
				
		if (compound.contains("DataPregnancyTimer"))
			this.entityData.set(DATA_PREGNANCY_TIMER, compound.getInt("DataPregnancyTimer"));
		if (compound.contains("DataPregnancyIllness"))
			this.entityData.set(DATA_PREGNANCY_ILLNESS, PregnancyIllness.values()[compound.getInt("DataPregnancyIllness")]);
		if (compound.contains("DataPregnancyIllnessTimer"))
			this.entityData.set(DATA_PREGNANCY_ILLNESS_TIMER, compound.getInt("DataPregnancyIllnessTimer"));
		if (compound.contains("DataMaxPregnancyStage"))
			this.entityData.set(DATA_MAX_PREGNANCY_STAGE, PregnancyStage.values()[compound.getInt("DataMaxPregnancyStage")]);
	
		if (compound.contains("DataAnimationStage"))
			this.entityData.set(DATA_ANIMATION_STATE, PreggoMobAnimationState.values()[compound.getInt("DataAnimationStage")]);
	
	}

	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
		if (this.isAlive() && capability == ForgeCapabilities.ITEM_HANDLER && side == null)
			return LazyOptional.of(() -> combined).cast();
		return super.getCapability(capability, side);
	}

	@Override
	protected void dropEquipment() {
		super.dropEquipment();
		for (int i = 0; i < inventory.getSlots(); ++i) {
			ItemStack itemstack = inventory.getStackInSlot(i);
			if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
				this.spawnAtLocation(itemstack);
			}
		}
	}	
   
	@Override
	protected void populateDefaultEquipmentSlots(RandomSource p_219165_, DifficultyInstance p_219166_) {
		super.populateDefaultEquipmentSlots(p_219165_, p_219166_);
		if (p_219165_.nextFloat() < (this.level().getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
			int i = p_219165_.nextInt(3);
			if (i == 0) {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
			} else {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SHOVEL));
			}
		}
	}
	
	@Override
	public boolean hurt(DamageSource damagesource, float amount) {
		return PreggoMobHelper.defaultHurt(super.hurt(damagesource, amount), this, damagesource, amount);	
	}
	
	@Override
	public boolean doHurtTarget(Entity target) {	
		return PreggoMobHelper.defaultDoHurtTarget(super.doHurtTarget(target), this, target);
	}

	@Override
	public String getPreggoName() {
		return this.hasCustomName() ? this.getDisplayName().getString() : "Zombie Girl";
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(8, new AbstractZombieGirl.ZombieGirlAttackTurtleEggGoal(this, 1.0D, 3){
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !isIncapacitated()
				&& !isWaiting();
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !isIncapacitated();
			}	
		});	
	}
	
	@Override
	public ItemStackHandler getInventary() {
		return this.inventory;
	}
	
	@Override
	public CombinedInvWrapper getCombined() {
		return this.combined;
	}
	
	@Override
	public BabyType getBabyType() {
		return BabyType.ZOMBIE;
	}
	
	@Override
	public boolean wantsToAttack(LivingEntity target, LivingEntity owner) {		
		if ((target instanceof TamableAnimal tamableTarget && tamableTarget.isOwnedBy(owner))
				|| (target instanceof AbstractHorse houseTarget && houseTarget.isTamed())
				|| (target instanceof Player pTarget && owner instanceof Player pOwmer && !(pOwmer).canHarmPlayer(pTarget)))
			return false;
	
		return true;
	}
		
	protected static AttributeSupplier.Builder getBasicAttributes(double movementSpeed) {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 26D)
				.add(Attributes.ARMOR, 1D)
				.add(Attributes.ATTACK_DAMAGE, 3D)
				.add(Attributes.FOLLOW_RANGE, 35D)
				.add(Attributes.MOVEMENT_SPEED, movementSpeed);
	}
	
	@Override
	public int getHungry() {
	    return this.entityData.get(DATA_HUNGRY);
	}

	@Override
	public void setHungry(int hungry) {
	    this.entityData.set(DATA_HUNGRY, hungry);
	}

	@Override
	public int getHungryTimer() {
	    return this.entityData.get(DATA_HUNGRY_TIMER);
	}

	@Override
	public void setHungryTimer(int ticks) {
	    this.entityData.set(DATA_HUNGRY_TIMER, ticks);
	}

	@Override
	public boolean isWaiting() {
	    return this.entityData.get(DATA_IS_WAITING);
	}

	@Override
	public void setWaiting(boolean waiting) {
	    this.entityData.set(DATA_IS_WAITING, waiting);
	}
		
	@Override
	public boolean isPregnant() {
	    return this.entityData.get(DATA_IS_PREGNANT);
	}
	
	@Override
	public boolean isSavage() {
	    return this.entityData.get(DATA_IS_SAVAGE);
	}
	
	@Override
	public void setSavage(boolean savage) {
	    this.entityData.set(DATA_IS_SAVAGE, savage);
	}
	
	@Override
	public int getPregnancyTimer() {
	    return this.entityData.get(DATA_PREGNANCY_TIMER);
	}
	
	@Override
	public void setPregnancyTimer(int ticks) {
	    this.entityData.set(DATA_PREGNANCY_TIMER, ticks);
	}
	
	@Override
	public PregnancyStage getMaxPregnancyStage() {
		return this.entityData.get(DATA_MAX_PREGNANCY_STAGE); 
	}
	
	@Override
	public void setMaxPregnancyStage(PregnancyStage stage) {
		this.entityData.set(DATA_MAX_PREGNANCY_STAGE, stage);
	}
	
	@Override
	public PregnancyIllness getPregnancyIllness() {
		return this.entityData.get(DATA_PREGNANCY_ILLNESS);
	}
	
	@Override
	public void setPregnancyIllness(PregnancyIllness illness) {
		this.entityData.set(DATA_PREGNANCY_ILLNESS, illness);
	}
	
	@Override
	public int getPregnancyIllnessTimer() {
		return this.entityData.get(DATA_PREGNANCY_ILLNESS_TIMER);
	}
	
	@Override
	public void setPregnancyIllnessTimer(int ticks) {
		this.entityData.set(DATA_PREGNANCY_ILLNESS_TIMER, ticks);
	}
	
	@Override
	public boolean isIncapacitated() {
		return getPregnancyIllness() != PregnancyIllness.NONE;
	}
	
	@Override
	public PreggoMobAnimationState getAnimationState() {
		return this.entityData.get(DATA_ANIMATION_STATE);
	}

	@Override
	public void setAnimationState(PreggoMobAnimationState state) {
		this.entityData.set(DATA_ANIMATION_STATE, state);
	}
	
	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		return null;
	}
}
