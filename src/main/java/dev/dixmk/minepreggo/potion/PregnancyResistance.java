package dev.dixmk.minepreggo.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class PregnancyResistance extends MobEffect {

	public PregnancyResistance() {
		super(MobEffectCategory.BENEFICIAL, -6710785);
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
	}
	
	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
