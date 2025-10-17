package dev.dixmk.minepreggo.entity.preggo.zombie;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.entity.preggo.BabyType;
import dev.dixmk.minepreggo.entity.preggo.Craving;
import dev.dixmk.minepreggo.entity.preggo.IPregnancySystem;
import dev.dixmk.minepreggo.entity.preggo.PregnancyPain;
import dev.dixmk.minepreggo.entity.preggo.PregnancySymptom;
import dev.dixmk.minepreggo.entity.preggo.PregnancySystemP1;
import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.utils.PreggoAIHelper;
import dev.dixmk.minepreggo.utils.PreggoMobHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class AbstractTamablePregnantZombieGirl<S extends PregnancySystemP1<?>> extends AbstractTamableZombieGirl<S> implements IPregnancySystem {
	protected static final EntityDataAccessor<Integer> DATA_PREGNANCY_HEALTH = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_DAYS_PASSED = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_DAYS_BY_STAGE = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_DAYS_TO_GIVE_BIRTH = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_CRAVING = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_MILKING = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_BELLY_RUBS = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_HORNY = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<PregnancySymptom> DATA_PREGNANCY_SYMPTOM = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, MinepreggoModEntityDataSerializers.PREGNANCY_SYMPTOM);
	protected static final EntityDataAccessor<PregnancyPain> DATA_PREGNANCY_PAIN = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, MinepreggoModEntityDataSerializers.PREGNANCY_PAIN);
	protected static final EntityDataAccessor<Craving> DATA_CRAVING_CHOSEN = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, MinepreggoModEntityDataSerializers.CRAVING);

	protected int cravingTimer = 0;
	protected int milkingTimer = 0;
	protected int bellyRubsTimer = 0;
	protected int hornyTimer = 0;
	protected int pregnancyPainTimer = 0;
	
	protected static final ImmutableMap<Craving, Item> CRAVING_ENUM_MAP = ImmutableMap.of(
			Craving.SALTY, MinepreggoModItems.BRAIN_WITH_SALT.get(), 
			Craving.SWEET, MinepreggoModItems.BRAIN_WITH_CHOCOLATE.get(), 
			Craving.SOUR, MinepreggoModItems.SOUR_BRAIN.get(),
			Craving.SPICY, MinepreggoModItems.BRAIN_WITH_HOT_SAUCE.get());	
	
	protected AbstractTamablePregnantZombieGirl(EntityType<? extends AbstractTamablePregnantZombieGirl<?>> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_);
	}
		
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();	
		this.entityData.define(DATA_PREGNANCY_HEALTH, 100);
		this.entityData.define(DATA_DAYS_PASSED, 0);
		this.entityData.define(DATA_DAYS_BY_STAGE, 0);
		this.entityData.define(DATA_DAYS_TO_GIVE_BIRTH, 0);	
		this.entityData.define(DATA_CRAVING, 0);
		this.entityData.define(DATA_MILKING, 0);
		this.entityData.define(DATA_BELLY_RUBS, 0);
		this.entityData.define(DATA_HORNY, 0);
		this.entityData.define(DATA_PREGNANCY_SYMPTOM, PregnancySymptom.NONE);
		this.entityData.define(DATA_PREGNANCY_PAIN, PregnancyPain.NONE);
		this.entityData.define(DATA_CRAVING_CHOSEN, Craving.NONE);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		compoundTag.putInt("DataPregnancyHealth", this.entityData.get(DATA_PREGNANCY_HEALTH));
		compoundTag.putInt("DataDaysPassed", this.entityData.get(DATA_DAYS_PASSED));
		compoundTag.putInt("DataDaysByStage", this.entityData.get(DATA_DAYS_BY_STAGE));
		compoundTag.putInt("DataDaysToGiveBirth", this.entityData.get(DATA_DAYS_TO_GIVE_BIRTH));
		compoundTag.putInt("DataCraving", this.entityData.get(DATA_CRAVING));
		compoundTag.putInt("DataCravingTimer", this.cravingTimer);
		compoundTag.putInt("DataMilking", this.entityData.get(DATA_MILKING));
		compoundTag.putInt("DataMilkingTimer", this.milkingTimer);
		compoundTag.putInt("DataBellyRubs", this.entityData.get(DATA_BELLY_RUBS));
		compoundTag.putInt("DataBellyRubsTimer", this.bellyRubsTimer);
		compoundTag.putInt("DataHorny", this.entityData.get(DATA_HORNY));
		compoundTag.putInt("DataHornyTimer", this.hornyTimer);
		compoundTag.putInt("DataPregnancySymptom", this.entityData.get(DATA_PREGNANCY_SYMPTOM).ordinal());
		compoundTag.putInt("DataPregnancyPain", this.entityData.get(DATA_PREGNANCY_PAIN).ordinal());
		compoundTag.putInt("DataPregnancyPainTimer", this.pregnancyPainTimer);
		compoundTag.putInt("DataCravingChosen", this.entityData.get(DATA_CRAVING_CHOSEN).ordinal());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);	
		this.entityData.set(DATA_PREGNANCY_HEALTH, compoundTag.getInt("DataPregnancyHealth"));
		this.entityData.set(DATA_DAYS_PASSED, compoundTag.getInt("DataDaysPassed"));
		this.entityData.set(DATA_DAYS_BY_STAGE, compoundTag.getInt("DataDaysByStage"));
		this.entityData.set(DATA_DAYS_TO_GIVE_BIRTH, compoundTag.getInt("DataDaysToGiveBirth"));
		this.entityData.set(DATA_CRAVING, compoundTag.getInt("DataCraving"));
		this.cravingTimer = compoundTag.getInt("DataCravingTimer");
		this.entityData.set(DATA_MILKING, compoundTag.getInt("DataMilking"));
		this.milkingTimer = compoundTag.getInt("DataMilkingTimer");
		this.entityData.set(DATA_BELLY_RUBS, compoundTag.getInt("DataBellyRubs"));
		this.bellyRubsTimer = compoundTag.getInt("DataBellyRubsTimer");
		this.entityData.set(DATA_HORNY, compoundTag.getInt("DataHorny"));
		this.hornyTimer = compoundTag.getInt("DataHornyTimer");
		this.entityData.set(DATA_PREGNANCY_SYMPTOM, PregnancySymptom.values()[compoundTag.getInt("DataPregnancySymptom")]);
		this.entityData.set(DATA_PREGNANCY_PAIN, PregnancyPain.values()[compoundTag.getInt("DataPregnancyPain")]);
		this.entityData.set(DATA_CRAVING_CHOSEN, Craving.values()[compoundTag.getInt("DataCravingChosen")]);
		this.pregnancyPainTimer = compoundTag.getInt("DataPregnancyPainTimer");
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(8, new AbstractZombieGirl.ZombieGirlAttackTurtleEggGoal(this, 1.0D, 3){
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !isWaiting() && !isIncapacitated();
			}
		});	
		PreggoAIHelper.setTamablePregnantZombieGirlGoals(this);
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "preggo_death"));
	}
	
	@Override
	public boolean isValidCraving(Craving kindOfCraving, Item item) {
		return item == CRAVING_ENUM_MAP.get(kindOfCraving);
	}
	
	@Override
	public boolean hasCustomHeadAnimation() {
		return super.hasCustomHeadAnimation() || this.getPregnancyPain() != PregnancyPain.NONE;
	}
	
	@Override
	public void die(DamageSource source) {
		super.die(source);			
		PreggoMobHelper.spawnBabyAndFetusZombies(this);
	}
	
	@Override
	public boolean hurt(DamageSource damagesource, float amount) {
		boolean result = super.hurt(damagesource, amount);	
		
		if (result) {
			preggoMobSystem.evaluateOnSuccessfulHurt(damagesource);
		}
		
		return result;
	}
	
	
	@Override
	public int getDaysByStage() {
		return this.entityData.get(DATA_DAYS_BY_STAGE);
	}

	@Override
	public void setDaysByStage(int days) {
		this.entityData.set(DATA_DAYS_BY_STAGE, days);
	}
	
	@Override
	public int getPregnancyHealth() {
		return this.entityData.get(DATA_PREGNANCY_HEALTH);
	}
	
	@Override
	public void setPregnancyHealth(int health) {
		this.entityData.set(DATA_PREGNANCY_HEALTH, health);
	}
	
	@Override
	public int getDaysPassed() {
		return this.entityData.get(DATA_DAYS_PASSED);
	}
	
	@Override
	public void setDaysPassed(int days) {
		this.entityData.set(DATA_DAYS_PASSED, days);
	}
	
	@Override
	public int getDaysToGiveBirth() {
		return this.entityData.get(DATA_DAYS_TO_GIVE_BIRTH);
	}
	
	@Override
	public void setDaysToGiveBirth(int days) {
		this.entityData.set(DATA_DAYS_TO_GIVE_BIRTH, days);
	}
	
	@Override
	public PregnancySymptom getPregnancySymptom() {
		return this.entityData.get(DATA_PREGNANCY_SYMPTOM);
	}
	
	@Override
	public void setPregnancySymptom(PregnancySymptom symptom) {
		this.entityData.set(DATA_PREGNANCY_SYMPTOM, symptom);
	}
	
	@Override
	public PregnancyPain getPregnancyPain() {
		return this.entityData.get(DATA_PREGNANCY_PAIN);
	}
	
	@Override
	public void setPregnancyPain(PregnancyPain pain) {
		this.entityData.set(DATA_PREGNANCY_PAIN, pain);
	}
	
	@Override
	public int getPregnancyPainTimer() {
		return this.pregnancyPainTimer;
	}
	
	@Override
	public void setPregnancyPainTimer(int ticks) {
		this.pregnancyPainTimer = ticks;
	}
	
	@Override
	public BabyType getBabyType() {
		return BabyType.ZOMBIE;
	}
	
	@Override
	public boolean isIncapacitated() {
		return this.getPregnancyPain() != PregnancyPain.NONE;
	}
}
