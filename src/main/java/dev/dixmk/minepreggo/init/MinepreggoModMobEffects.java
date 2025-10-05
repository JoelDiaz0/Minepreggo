package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.potion.PregnancyResistance;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoModMobEffects {
	
	private MinepreggoModMobEffects() {}
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MinepreggoMod.MODID);

	public static final RegistryObject<MobEffect> PREGNANCY_RESISTANCE_EFFECT = REGISTRY.register("pregnancy_resistance_effect", PregnancyResistance::new);
	
}
