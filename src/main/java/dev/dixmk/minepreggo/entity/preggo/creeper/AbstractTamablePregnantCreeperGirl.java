package dev.dixmk.minepreggo.entity.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.BabyType;
import dev.dixmk.minepreggo.entity.preggo.Craving;
import dev.dixmk.minepreggo.entity.preggo.IPregnancySystem;
import dev.dixmk.minepreggo.entity.preggo.PregnancyState;
import dev.dixmk.minepreggo.entity.preggo.PregnancySymptom;
import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public abstract class AbstractTamablePregnantCreeperGirl extends AbstractTamableCreeperGirl implements IPregnancySystem {

	protected static final EntityDataAccessor<Integer> DATA_PREGNANCY_HEALTH = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_DAYS_PASSED = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_DAYS_BY_STAGE = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_DAYS_TO_GIVE_BIRTH = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_CRAVING = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_CRAVING_TIMER = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_MILKING = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_MILKING_TIMER = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_BELLY_RUBS = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_BELLY_RUBS_TIMER = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_HORNY = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_HORNY_TIMER = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<PregnancySymptom> DATA_PREGNANCY_SYMPTOM = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, MinepreggoModEntityDataSerializers.PREGNANCY_SYMPTOM);
	protected static final EntityDataAccessor<PregnancyState> DATA_PREGNANCY_STATE = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, MinepreggoModEntityDataSerializers.PREGNANCY_STATE);
	protected static final EntityDataAccessor<Craving> DATA_CRAVING_CHOSEN = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, MinepreggoModEntityDataSerializers.CRAVING);
	protected static final EntityDataAccessor<Integer> DATA_PREGNANCY_STATE_TIMER = SynchedEntityData.defineId(AbstractTamablePregnantCreeperGirl.class, EntityDataSerializers.INT);

	protected AbstractTamablePregnantCreeperGirl(EntityType<? extends AbstractTamableCreeperGirl> p_21803_, Level p_21804_) {
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
		this.entityData.define(DATA_CRAVING_TIMER, 0);	
		this.entityData.define(DATA_MILKING, 0);
		this.entityData.define(DATA_MILKING_TIMER, 0);
		this.entityData.define(DATA_BELLY_RUBS, 0);
		this.entityData.define(DATA_BELLY_RUBS_TIMER, 0);	
		this.entityData.define(DATA_HORNY, 0);
		this.entityData.define(DATA_HORNY_TIMER, 0);
		this.entityData.define(DATA_PREGNANCY_SYMPTOM, PregnancySymptom.NONE);
		this.entityData.define(DATA_PREGNANCY_STATE, PregnancyState.NONE);
		this.entityData.define(DATA_CRAVING_CHOSEN, Craving.NONE);
		this.entityData.define(DATA_PREGNANCY_STATE_TIMER, 0);		
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		compoundTag.putInt("DataPregnancyHealth", this.entityData.get(DATA_PREGNANCY_HEALTH));
		compoundTag.putInt("DataDaysPassed", this.entityData.get(DATA_DAYS_PASSED));
		compoundTag.putInt("DataDaysByStage", this.entityData.get(DATA_DAYS_BY_STAGE));
		compoundTag.putInt("DataDaysToGiveBirth", this.entityData.get(DATA_DAYS_TO_GIVE_BIRTH));
		compoundTag.putInt("DataCraving", this.entityData.get(DATA_CRAVING));
		compoundTag.putInt("DataCravingTimer", this.entityData.get(DATA_CRAVING_TIMER));
		compoundTag.putInt("DataMilking", this.entityData.get(DATA_MILKING));
		compoundTag.putInt("DataMilkingTimer", this.entityData.get(DATA_MILKING_TIMER));
		compoundTag.putInt("DataBellyRubs", this.entityData.get(DATA_BELLY_RUBS));
		compoundTag.putInt("DataBellyRubsTimer", this.entityData.get(DATA_BELLY_RUBS_TIMER));
		compoundTag.putInt("DataHorny", this.entityData.get(DATA_HORNY));
		compoundTag.putInt("DataHornyTimer", this.entityData.get(DATA_HORNY_TIMER));
		compoundTag.putInt("DataPregnancySymptom", this.entityData.get(DATA_PREGNANCY_SYMPTOM).ordinal());
		compoundTag.putInt("DataPregnancyState", this.entityData.get(DATA_PREGNANCY_STATE).ordinal());
		compoundTag.putInt("DataPregnancyStateTimer", this.entityData.get(DATA_PREGNANCY_STATE_TIMER));
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
		this.entityData.set(DATA_CRAVING_TIMER, compoundTag.getInt("DataCravingTimer"));
		this.entityData.set(DATA_MILKING, compoundTag.getInt("DataMilking"));
		this.entityData.set(DATA_MILKING_TIMER, compoundTag.getInt("DataMilkingTimer"));
		this.entityData.set(DATA_BELLY_RUBS, compoundTag.getInt("DataBellyRubs"));
		this.entityData.set(DATA_BELLY_RUBS_TIMER, compoundTag.getInt("DataBellyRubsTimer"));
		this.entityData.set(DATA_HORNY, compoundTag.getInt("DataHorny"));
		this.entityData.set(DATA_HORNY_TIMER, compoundTag.getInt("DataHornyTimer"));
		this.entityData.set(DATA_PREGNANCY_SYMPTOM, PregnancySymptom.values()[compoundTag.getInt("DataPregnancySymptom")]);
		this.entityData.set(DATA_PREGNANCY_STATE, PregnancyState.values()[compoundTag.getInt("DataPregnancyState")]);
		this.entityData.set(DATA_CRAVING_CHOSEN, Craving.values()[compoundTag.getInt("DataCravingChosen")]);
		this.entityData.set(DATA_PREGNANCY_STATE_TIMER, compoundTag.getInt("DataPregnancyStateTimer"));
	}
	

	/*
	@Override
	public int getCraving() {
		return this.entityData.get(DATA_CRAVING);
	}

	@Override
	public void setCraving(int craving) {
		this.entityData.set(DATA_CRAVING, craving);
	}
	
	@Override
	public int getCravingTimer() {
		return this.entityData.get(DATA_CRAVING_TIMER);
	}

	@Override
	public void setCravingTimer(int timer) {
		this.entityData.set(DATA_CRAVING_TIMER, timer);
	}
	
	@Override
	public Craving getCravingChosen() {
		return this.entityData.get(DATA_CRAVING_CHOSEN);
	}

	@Override
	public void setCravingChosen(Craving craving) {
		this.entityData.set(DATA_CRAVING_CHOSEN, craving);
	}
	
	@Override
	public int getMilking() {
	    return this.entityData.get(DATA_MILKING);
	}
	
	@Override
	public void setMilking(int milking) {
	    this.entityData.set(DATA_MILKING, milking);
	}
	
	@Override
	public int getMilkingTimer() {
	    return this.entityData.get(DATA_MILKING_TIMER);
	}
	
	@Override
	public void setMilkingTimer(int timer) {
	    this.entityData.set(DATA_MILKING_TIMER, timer);
	}
	
	@Override
	public int getBellyRubs() {
	    return this.entityData.get(DATA_BELLY_RUBS);
	}
	
	@Override
	public void setBellyRubs(int bellyRubs) {
	    this.entityData.set(DATA_BELLY_RUBS, bellyRubs);
	}
	
	@Override
	public int getBellyRubsTimer() {
	    return this.entityData.get(DATA_BELLY_RUBS_TIMER);
	}
	
	@Override
	public void setBellyRubsTimer(int timer) {
        this.entityData.set(DATA_BELLY_RUBS_TIMER, timer);
    }
	
	@Override
	public int getHorny() {
        return this.entityData.get(DATA_HORNY);
    }
	
	@Override
	public void setHorny(int horny) {
        this.entityData.set(DATA_HORNY, horny);
    }
	
	@Override
	public int getHornyTimer() {
        return this.entityData.get(DATA_HORNY_TIMER);
    }
	
	@Override
	public void setHornyTimer(int timer) {
        this.entityData.set(DATA_HORNY_TIMER, timer);
    }
	 */
	
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
	public PregnancyState getPregnancyState() {
		return this.entityData.get(DATA_PREGNANCY_STATE);
	}
	
	@Override
	public void setPregnancyState(PregnancyState symptom) {
		this.entityData.set(DATA_PREGNANCY_STATE, symptom);
	}
	
	@Override
	public int getPregnancyStateTimer() {
		return this.entityData.get(DATA_PREGNANCY_STATE_TIMER);
	}
	
	@Override
	public void setPregnancyStateTimer(int ticks) {
		this.entityData.set(DATA_PREGNANCY_STATE_TIMER, ticks);
	}
	
	@Override
	public BabyType getBabyType() {
		return BabyType.HUMANOID_CREEPER;
	}
	
	@Override
	public boolean isIncapacitated() {
		return getPregnancySymptom() != PregnancySymptom.NONE;
	}
	
	
}
