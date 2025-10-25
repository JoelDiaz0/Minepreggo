package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.PreggoMobSystem;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractTamableHumanoidCreeperGirl<S extends PreggoMobSystem<?>> extends AbstractTamableCreeperGirl<S> {

	protected AbstractTamableHumanoidCreeperGirl(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_);
	}

	@Override
	protected boolean canReplaceCurrentItem(ItemStack p_21428_, ItemStack p_21429_) {	
		return AbstractCreeperGirl.canReplaceCurrentItem(this, p_21428_, p_21429_, PregnancyStage.getNonPregnancyStage());
	}
	
	@Override
	protected void pickUpItem(ItemEntity p_21471_) {
		AbstractTamableCreeperGirl.pickUpItem(this, p_21471_);
	}
	

}
