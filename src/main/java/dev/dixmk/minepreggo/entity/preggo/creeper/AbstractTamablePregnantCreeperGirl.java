package dev.dixmk.minepreggo.entity.preggo.creeper;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.entity.preggo.BabyType;
import dev.dixmk.minepreggo.entity.preggo.Craving;
import dev.dixmk.minepreggo.entity.preggo.IPregnancySystem;
import dev.dixmk.minepreggo.entity.preggo.PregnancyPain;
import dev.dixmk.minepreggo.entity.preggo.PregnancySymptom;
import dev.dixmk.minepreggo.entity.preggo.PregnancySystemP1;
import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.utils.PreggoAIHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public abstract class AbstractTamablePregnantCreeperGirl<S extends PregnancySystemP1<?>> extends AbstractTamableCreeperGirl<S> implements IPregnancySystem {

	protected static final EntityDataAccessor<Integer> DATA_PREGNANCY_HEALTH = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_DAYS_PASSED = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_DAYS_BY_STAGE = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_DAYS_TO_GIVE_BIRTH = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_CRAVING = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_MILKING = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_BELLY_RUBS = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_HORNY = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<PregnancySymptom> DATA_PREGNANCY_SYMPTOM = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, MinepreggoModEntityDataSerializers.PREGNANCY_SYMPTOM);
	protected static final EntityDataAccessor<PregnancyPain> DATA_PREGNANCY_PAIN = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, MinepreggoModEntityDataSerializers.PREGNANCY_PAIN);
	protected static final EntityDataAccessor<Craving> DATA_CRAVING_CHOSEN = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, MinepreggoModEntityDataSerializers.CRAVING);
	
	protected int cravingTimer = 0;
	protected int milkingTimer = 0;
	protected int bellyRubsTimer = 0;
	protected int hornyTimer = 0;
	protected int pregnancyPainTimer = 0;
	
	protected static final ImmutableMap<Craving, Item> CRAVING_ENUM_MAP = ImmutableMap.of(
			Craving.SALTY, MinepreggoModItems.ACTIVATED_GUNPOWDER_WITH_SALT.get(), 
			Craving.SWEET, MinepreggoModItems.ACTIVATED_GUNPOWDER_WITH_CHOCOLATE.get(), 
			Craving.SOUR, MinepreggoModItems.SOUR_ACTIVATED_GUNPOWDER.get(),
			Craving.SPICY, MinepreggoModItems.ACTIVATED_GUNPOWDER_WITH_HOT_SAUCE.get());	
	
	protected AbstractTamablePregnantCreeperGirl(EntityType<? extends AbstractTamableCreeperGirl<?>> p_21803_, Level p_21804_) {
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
		this.bellyRubsTimer =  compoundTag.getInt("DataBellyRubsTimer");
		this.entityData.set(DATA_HORNY, compoundTag.getInt("DataHorny"));
		this.hornyTimer = compoundTag.getInt("DataHornyTimer");
		this.entityData.set(DATA_PREGNANCY_SYMPTOM, PregnancySymptom.values()[compoundTag.getInt("DataPregnancySymptom")]);
		this.entityData.set(DATA_PREGNANCY_PAIN, PregnancyPain.values()[compoundTag.getInt("DataPregnancyPain")]);
		this.entityData.set(DATA_CRAVING_CHOSEN, Craving.values()[compoundTag.getInt("DataCravingChosen")]);
		this.pregnancyPainTimer = compoundTag.getInt("DataPregnancyPainTimer");
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new AbstractCreeperGirl.SwellGoal<>(this) {
			@Override
			public boolean canUse() {				
				return super.canUse() 
				&& canExplode()
				&& !isIncapacitated();								
			}
		});
		PreggoAIHelper.setTamablePregnantCreeperGirlGoals(this);
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
	public void setPregnancyPain(PregnancyPain symptom) {
		this.entityData.set(DATA_PREGNANCY_PAIN, symptom);
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
		return BabyType.HUMANOID_CREEPER;
	}
	
	@Override
	public boolean isIncapacitated() {
		return this.getPregnancyPain() != PregnancyPain.NONE;
	}
	
	
}
