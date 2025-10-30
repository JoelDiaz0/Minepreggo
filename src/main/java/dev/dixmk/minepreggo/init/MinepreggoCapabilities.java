package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.network.capability.PlayerDataImpl;
import dev.dixmk.minepreggo.network.capability.PregnancyEffectsImpl;
import dev.dixmk.minepreggo.network.capability.PregnancySystemImpl;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class MinepreggoCapabilities {

	private MinepreggoCapabilities() {}
	
	public static final Capability<PregnancySystemImpl> PLAYER_PREGNANCY_SYSTEM = CapabilityManager.get(new CapabilityToken<PregnancySystemImpl>() {});
	public static final Capability<PlayerDataImpl> PLAYER_DATA = CapabilityManager.get(new CapabilityToken<PlayerDataImpl>() {});
	public static final Capability<PregnancyEffectsImpl> PLAYER_PREGNANCY_EFFECTS = CapabilityManager.get(new CapabilityToken<PregnancyEffectsImpl>() {});

}
