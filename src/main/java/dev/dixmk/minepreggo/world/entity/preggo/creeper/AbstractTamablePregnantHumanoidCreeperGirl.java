package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.entity.preggo.PregnancySystemP1;
import dev.dixmk.minepreggo.entity.preggo.PregnantPreggoMobSystem;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractTamablePregnantHumanoidCreeperGirl<S extends PregnantPreggoMobSystem<?>, P extends PregnancySystemP1<?>> extends AbstractTamablePregnantCreeperGirl<S, P> {

	protected AbstractTamablePregnantHumanoidCreeperGirl(EntityType<? extends AbstractTamableCreeperGirl<?>> p_21803_,
			Level p_21804_) {
		super(p_21803_, p_21804_);
	}

	@Override
	protected boolean canReplaceCurrentItem(ItemStack p_21428_, ItemStack p_21429_) {	
		return AbstractCreeperGirl.canReplaceCurrentItem(this, p_21428_, p_21429_, this.getCurrentPregnancyStage());
	}
	
	@Override
	protected void pickUpItem(ItemEntity p_21471_) {
		AbstractTamableCreeperGirl.pickUpItem(this, p_21471_);
	}
}
