package dev.dixmk.minepreggo.network.capability;

public interface IPlayerData {
	void setGender(Gender gender);
	Gender getGender();
	
	boolean canGetPregnant();	
	boolean isUsingCustomSkin();	
	boolean isPregnant();	
	
	void impregnate();
	void startPregnancy();
	
	float getFertilityPercentage();
	void setFertilityPercentage(float percentage);
	void incrementFertilityPercentage();
	void reduceFertilityPercentage();	
	void incrementFertilityTimer();
}