package dev.dixmk.minepreggo.entity.preggo.creeper;

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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.wrapper.EntityArmorInvWrapper;
import net.minecraftforge.items.wrapper.EntityHandsInvWrapper;

public abstract class AbstractTamableCreeperGirl extends AbstractCreeperGirl implements IImpregnable {

	public static final EntityDataAccessor<Integer> DATA_HUNGRY = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_HUNGRY_TIMER = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.INT);
	
	public static final EntityDataAccessor<Integer> DATA_PREGNANCY_TIMER = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.INT);	
	public static final EntityDataAccessor<PregnancyStage> DATA_MAX_PREGNANCY_STAGE = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, MinepreggoModEntityDataSerializers.PREGNANCY_STAGE);
	public static final EntityDataAccessor<PregnancyIllness> DATA_PREGNANCY_ILLNESS = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, MinepreggoModEntityDataSerializers.PREGNANCY_ILLNESS);
	public static final EntityDataAccessor<Integer> DATA_PREGNANCY_ILLNESS_TIMER = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.INT);
	
	public static final EntityDataAccessor<Boolean> DATA_IS_PREGNANT = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> DATA_IS_SAVAGE = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> DATA_IS_ANGRY = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> DATA_IS_WAITING = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	
	private static final EntityDataAccessor<CombatMode> DATA_COMBAT_MODE = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, MinepreggoModEntityDataSerializers.COMBAT_MODE);
	
	public static final EntityDataAccessor<PreggoMobAnimationState> DATA_ANIMATION_STATE = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, MinepreggoModEntityDataSerializers.ANIMATION_STATE);
	
	public static final int INVENTARY_SIZE = 12;
	protected final ItemStackHandler inventory;
	protected final CombinedInvWrapper combined;
	
	protected AbstractTamableCreeperGirl(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
	      super(p_21803_, p_21804_);
	      this.reassessTameGoals();	   
	      this.inventory = new ItemStackHandler(INVENTARY_SIZE);
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
			
		this.entityData.define(DATA_COMBAT_MODE, CombatMode.FIGHT_AND_EXPLODE);
		
		this.entityData.define(DATA_ANIMATION_STATE, PreggoMobAnimationState.IDLE);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		compoundTag.put("InventoryCustom", inventory.serializeNBT());
		compoundTag.putInt("DataModeCombat", this.entityData.get(DATA_COMBAT_MODE).ordinal());
		compoundTag.putInt("DataHungry", this.entityData.get(DATA_HUNGRY));
		compoundTag.putInt("DataHungryTimer", this.entityData.get(DATA_HUNGRY_TIMER));
		
		compoundTag.putBoolean("DataIsWaiting", this.entityData.get(DATA_IS_WAITING));
		compoundTag.putBoolean("DataIsPregnant", this.entityData.get(DATA_IS_PREGNANT));
		compoundTag.putBoolean("DataIsSavage", this.entityData.get(DATA_IS_SAVAGE));
		compoundTag.putBoolean("DataIsAngry", this.entityData.get(DATA_IS_ANGRY));

		compoundTag.putInt("DataPregnancyTimer", this.entityData.get(DATA_PREGNANCY_TIMER));
		compoundTag.putInt("DataMaxPregnancyStage", this.entityData.get(DATA_MAX_PREGNANCY_STAGE).ordinal());
		compoundTag.putInt("DataPregnancyIllness", this.entityData.get(DATA_PREGNANCY_ILLNESS).ordinal());	
		compoundTag.putInt("DataPregnancyIllnessTimer", this.entityData.get(DATA_PREGNANCY_ILLNESS_TIMER));	
	
		compoundTag.putInt("DataAnimationStage", this.entityData.get(DATA_ANIMATION_STATE).ordinal());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);	
		Tag inventoryCustom = compoundTag.get("InventoryCustom");
		if (inventoryCustom instanceof CompoundTag inventoryTag)
			inventory.deserializeNBT(inventoryTag);
		if (compoundTag.contains("DataModeCombat"))
			this.entityData.set(DATA_COMBAT_MODE, CombatMode.values()[compoundTag.getInt("DataModeCombat")]);
		if (compoundTag.contains("DataHungry"))
			this.entityData.set(DATA_HUNGRY, compoundTag.getInt("DataHungry"));
		if (compoundTag.contains("DataHungryTimer"))
			this.entityData.set(DATA_HUNGRY_TIMER, compoundTag.getInt("DataHungryTimer"));
	
		if (compoundTag.contains("DataIsWaiting"))
			this.entityData.set(DATA_IS_WAITING, compoundTag.getBoolean("DataIsWaiting"));
		if (compoundTag.contains("DataIsPregnant"))
			this.entityData.set(DATA_IS_PREGNANT, compoundTag.getBoolean("DataIsPregnant"));
		if (compoundTag.contains("DataIsSavage"))
			this.entityData.set(DATA_IS_SAVAGE, compoundTag.getBoolean("DataIsSavage"));
		if (compoundTag.contains("DataIsAngry"))
			this.entityData.set(DATA_IS_ANGRY, compoundTag.getBoolean("DataIsAngry"));
				
		if (compoundTag.contains("DataPregnancyTimer"))
			this.entityData.set(DATA_PREGNANCY_TIMER, compoundTag.getInt("DataPregnancyTimer"));
		if (compoundTag.contains("DataPregnancyIllness"))
			this.entityData.set(DATA_PREGNANCY_ILLNESS, PregnancyIllness.values()[compoundTag.getInt("DataPregnancyIllness")]);
		if (compoundTag.contains("DataPregnancyIllnessTimer"))
			this.entityData.set(DATA_PREGNANCY_ILLNESS_TIMER, compoundTag.getInt("DataPregnancyIllnessTimer"));
		if (compoundTag.contains("DataMaxPregnancyStage"))
			this.entityData.set(DATA_MAX_PREGNANCY_STAGE, PregnancyStage.values()[compoundTag.getInt("DataMaxPregnancyStage")]);
	
		if (compoundTag.contains("DataAnimationStage"))
			this.entityData.set(DATA_ANIMATION_STATE, PreggoMobAnimationState.values()[compoundTag.getInt("DataAnimationStage")]);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new AbstractCreeperGirl.SwellGoal<>(this) {
			@Override
			public boolean canUse() {				
				return super.canUse() 
				&& !isIncapacitated()
				&& getCanExplote();								
			}
		});
	}
	
	public void setcombatMode(CombatMode value) {
		this.entityData.set(DATA_COMBAT_MODE, value);
		if (value == CombatMode.EXPLODE)
			this.maxDistance = 1D;
	}
	
	public CombatMode getcombatMode() {
		return this.entityData.get(DATA_COMBAT_MODE);
	}
	
	@Override
	public boolean getCanExplote() {
		switch (this.getcombatMode()) {
		case FIGHT_AND_EXPLODE: {
			return this.getHealth() <= this.getMaxHealth() / 2F;
		}
		case DONT_EXPLODE: {
			return false;
		}	
		default:
			return true;
		}
	}
	
	@Override
   	public void aiStep() {
      super.aiStep();
      
      if (this.isAlive()
    		  && this.isLeashed()) {
    	  this.dropLeash(true, true);
      }
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
	public boolean wantsToAttack(LivingEntity target, LivingEntity owner) {	
		if ((target instanceof TamableAnimal tamableTarget && tamableTarget.isOwnedBy(owner))
				|| (target instanceof AbstractHorse houseTarget && houseTarget.isTamed())
				|| (target instanceof Player pTarget && owner instanceof Player pOwmer && !(pOwmer).canHarmPlayer(pTarget)))
			return false;	
		return true;
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
		return this.hasCustomName() ? this.getDisplayName().getString() : "Creeper Girl";
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
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {				
		super.mobInteract(sourceentity, hand);		
		if (isIgnited()) return InteractionResult.SUCCESS;
			
		return InteractionResult.CONSUME;
	}

	protected static AttributeSupplier.Builder getBasicAttributes(double movementSpeed) {
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 26D)
				.add(Attributes.ATTACK_DAMAGE, 2D)
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
	public BabyType getBabyType() {
		return BabyType.HUMANOID_CREEPER;
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
