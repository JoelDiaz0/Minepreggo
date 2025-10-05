package dev.dixmk.minepreggo.entity.preggo.creeper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import dev.dixmk.minepreggo.entity.preggo.IPreggoMob;
import dev.dixmk.minepreggo.entity.preggo.PreggoMobState;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.PregnancySymptom;
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
import net.minecraft.world.damagesource.DamageTypes;
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

public abstract class AbstractTamableCreeperGirl extends AbstractCreeperGirl implements IPreggoMob {

	protected static final EntityDataAccessor<Integer> DATA_HUNGRY = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_HUNGRY_TIMER = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_PREGNANCY_TIMER = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.INT);	
	protected static final EntityDataAccessor<PregnancyStage> DATA_MAX_PREGNANCY_STAGE = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, MinepreggoModEntityDataSerializers.PREGNANCY_STAGE);
	protected static final EntityDataAccessor<PregnancySymptom> DATA_PREGNANCY_SYMPTOM = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, MinepreggoModEntityDataSerializers.PREGNANCY_SYMPTOM);
	protected static final EntityDataAccessor<Integer> DATA_PREGNANCY_ILLNESS_TIMER = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Boolean> DATA_PREGNANT = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> DATA_SAVAGE = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> DATA_ANGRY = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> DATA_WAITING = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> DATA_PANIC = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<PreggoMobState> DATA_STATE = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, MinepreggoModEntityDataSerializers.STATE);
	protected static final EntityDataAccessor<CombatMode> DATA_COMBAT_MODE = SynchedEntityData.defineId(AbstractTamableCreeperGirl.class, MinepreggoModEntityDataSerializers.COMBAT_MODE);

	public static final int INVENTARY_SIZE = 13;
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
		this.entityData.define(DATA_HUNGRY, 4);
		this.entityData.define(DATA_HUNGRY_TIMER, 0);
			
		this.entityData.define(DATA_PREGNANT, false);
		this.entityData.define(DATA_SAVAGE, true);
		this.entityData.define(DATA_ANGRY, false);
		this.entityData.define(DATA_WAITING, false);
		this.entityData.define(DATA_PANIC, false);
		
		this.entityData.define(DATA_PREGNANCY_TIMER, 0);
		this.entityData.define(DATA_MAX_PREGNANCY_STAGE, PregnancyStage.P0);
		this.entityData.define(DATA_PREGNANCY_SYMPTOM, PregnancySymptom.NONE);
		this.entityData.define(DATA_PREGNANCY_ILLNESS_TIMER, 0);
		this.entityData.define(DATA_STATE, PreggoMobState.IDLE);
		this.entityData.define(DATA_COMBAT_MODE, CombatMode.FIGHT_AND_EXPLODE);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.put("InventoryCustom", inventory.serializeNBT());
		compound.putInt("DataHungry", this.entityData.get(DATA_HUNGRY));
		compound.putInt("DataHungryTimer", this.entityData.get(DATA_HUNGRY_TIMER));
		
		compound.putBoolean("DataPregnant", this.entityData.get(DATA_PREGNANT));
		compound.putBoolean("DataSavage", this.entityData.get(DATA_SAVAGE));
		compound.putBoolean("DataWaiting", this.entityData.get(DATA_WAITING));
		compound.putBoolean("DataAngry", this.entityData.get(DATA_ANGRY));
		compound.putBoolean("DataPanic", this.entityData.get(DATA_PANIC));
		
		compound.putInt("DataPregnancyTimer", this.entityData.get(DATA_PREGNANCY_TIMER));
		compound.putInt("DataMaxPregnancyStage", this.entityData.get(DATA_MAX_PREGNANCY_STAGE).ordinal());
		compound.putInt("DataPregnancySymptom", this.entityData.get(DATA_PREGNANCY_SYMPTOM).ordinal());	
		compound.putInt("DataPregnancySymptomTimer", this.entityData.get(DATA_PREGNANCY_ILLNESS_TIMER));	
	
		compound.putInt("DataStage", this.entityData.get(DATA_STATE).ordinal());
		
		compound.putInt("DataCombatMode", this.entityData.get(DATA_COMBAT_MODE).ordinal());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		Tag inventoryCustom = compound.get("InventoryCustom");
		if (inventoryCustom instanceof CompoundTag inventoryTag)
			inventory.deserializeNBT(inventoryTag);	
		this.entityData.set(DATA_HUNGRY, compound.getInt("DataHungry"));
		this.entityData.set(DATA_HUNGRY_TIMER, compound.getInt("DataHungryTimer"));
		this.entityData.set(DATA_PREGNANT, compound.getBoolean("DataPregnant"));		
		this.entityData.set(DATA_SAVAGE, compound.getBoolean("DataSavage"));		
		this.entityData.set(DATA_WAITING, compound.getBoolean("DataWaiting"));		
		this.entityData.set(DATA_ANGRY, compound.getBoolean("DataAngry"));	
		this.entityData.set(DATA_PANIC, compound.getBoolean("DataPanic"));	
		this.entityData.set(DATA_PREGNANCY_TIMER, compound.getInt("DataPregnancyTimer"));
		this.entityData.set(DATA_PREGNANCY_SYMPTOM, PregnancySymptom.values()[compound.getInt("DataPregnancySymptom")]);
		this.entityData.set(DATA_PREGNANCY_ILLNESS_TIMER, compound.getInt("DataPregnancySymptomTimer"));
		this.entityData.set(DATA_MAX_PREGNANCY_STAGE, PregnancyStage.values()[compound.getInt("DataMaxPregnancyStage")]);
		this.entityData.set(DATA_STATE, PreggoMobState.values()[compound.getInt("DataStage")]);
		this.entityData.set(DATA_COMBAT_MODE, CombatMode.values()[compound.getInt("DataCombatMode")]);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new AbstractCreeperGirl.SwellGoal<>(this) {
			@Override
			public boolean canUse() {				
				return super.canUse() 
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
		boolean result = super.hurt(damagesource, amount);	
		if (result) {
			PreggoMobHelper.defaultHurt(this, damagesource, amount);			
			if (this.isWaiting() 
					&& !this.isPanic()
					&& damagesource.is(DamageTypes.GENERIC)
					&& !this.isOwnedBy(this.getLastHurtByMob())) {			
					this.setTarget(this.getLastHurtByMob());							
					this.setPanic(true);
			}										
		}		
		return result;
	}
	
	@Override
	public boolean doHurtTarget(Entity target) {		
		boolean result = super.doHurtTarget(target);	
		if (result) {
			PreggoMobHelper.defaultDoHurtTarget(this, target);
		}
		return result;
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
		
		if (this.isIgnited()) {
			return InteractionResult.SUCCESS;
		}
		
		if (this.isTame()) {
			this.setSavage(false);
		}	

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
	public boolean hasCustomHeadAnimation() {
		return isWaiting();
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
	public boolean isPregnant() {
	    return this.entityData.get(DATA_PREGNANT);
	}
	
	@Override
	public boolean isSavage() {
	    return this.entityData.get(DATA_SAVAGE);
	}
	
	@Override
	public void setSavage(boolean savage) {
	    this.entityData.set(DATA_SAVAGE, savage);
	}
	
	@Override
	public boolean isWaiting() {
	    return this.entityData.get(DATA_WAITING);
	}
	
	@Override
	public void setWaiting(boolean waiting) {
	    this.entityData.set(DATA_WAITING, waiting);
	}
	
	@Override
	public boolean isAngry() {
	    return this.entityData.get(DATA_ANGRY);
	}
	
	@Override
	public void setAngry(boolean angry) {
	    this.entityData.set(DATA_ANGRY, angry);
	}
	
	@Override
	public boolean isPanic() {
		return this.entityData.get(DATA_PANIC);
	}

	@Override
	public void setPanic(boolean panic) {
	    this.entityData.set(DATA_PANIC, panic);
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
	public PreggoMobState getState() {
		return this.entityData.get(DATA_STATE);
	}

	@Override
	public void setState(PreggoMobState state) {
		this.entityData.set(DATA_STATE, state);
	}
	
	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		return null;
	}
}
