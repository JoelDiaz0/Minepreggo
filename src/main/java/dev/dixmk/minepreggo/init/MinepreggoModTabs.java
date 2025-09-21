package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoModTabs {
	
	private MinepreggoModTabs() {}
	
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MinepreggoMod.MODID);

    public static final RegistryObject<CreativeModeTab> MINEPREGGO_TAB = REGISTRY.register("minepreggo_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> MinepreggoModItems.LEMON.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(MinepreggoModItems.LEMON.get());
                output.accept(MinepreggoModItems.ACTIVATED_GUNPOWDER.get());
                output.accept(MinepreggoModItems.VILLAGER_BRAIN.get());                 
                output.accept(MinepreggoModItems.MONSTER_ZOMBIE_GIRL_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_ZOMBIE_GIRL_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.MONSTER_CREEPER_GIRL_P0_SPAWN_EGG.get());
                output.accept(MinepreggoModItems.TAMABLE_CREEPER_GIRL_P0_SPAWN_EGG.get());
                
            }).build());
}
