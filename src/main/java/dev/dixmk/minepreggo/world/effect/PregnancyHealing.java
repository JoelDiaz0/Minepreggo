package dev.dixmk.minepreggo.world.effect;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.entity.preggo.IPregnancySystem;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;

public class PregnancyHealing extends MobEffect {
	
	public PregnancyHealing() {
		super(MobEffectCategory.BENEFICIAL, -3407821);
	}
	
	@Override
	public void applyInstantenousEffect(@Nullable Entity p_19462_, @Nullable Entity p_19463_, LivingEntity p_19464_, int p_19465_, double p_19466_) { 
		if (p_19464_ instanceof TamableAnimal t && t instanceof IPregnancySystem p) {		
			p.setPregnancyHealth(100);
			t.heal(2F);
		}
		
		/*
		else if (p_19464_ instanceof Player player
				&& player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MinepreggoModVariables.PlayerVariables()).isPlayerPregnant) {				
			player.getCapability(MinepreggoModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.playerPregnancyHealth = 100;
				capability.syncPlayerVariables(player);
			});		
			player.heal(4F);
		}	
		*/
	}
	
	
	@Override
	public boolean isInstantenous() {
		return true;
	}

}
