package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.world.entity.CustomVillagerTrades;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MinepreggoModTrades {

	private MinepreggoModTrades() {}

	@SubscribeEvent
	public static void registerTrades(VillagerTradesEvent event) {
		if (event.getType() == MinepreggoModVillagerProfessions.VILLAGER_DOCTOR.get()) {			
			final var trades = CustomVillagerTrades.TRADES.get(MinepreggoModVillagerProfessions.VILLAGER_DOCTOR.get());		
			
			for (var entry : trades.int2ObjectEntrySet()) {
				var list = entry.getValue();
				if (list != null) {		
					var key = entry.getIntKey();
					for (var trade : list) {
						event.getTrades().get(key).add(trade);
					}			
				}
			}					
		}
		
		
		else if (event.getType() == VillagerProfession.FARMER) {
			
			final var trades = CustomVillagerTrades.TRADES.get(VillagerProfession.FARMER);		
			
			for (var entry : trades.int2ObjectEntrySet()) {
				var list = entry.getValue();
				if (list != null) {		
					var key = entry.getIntKey();
					for (var trade : list) {
						event.getTrades().get(key).add(trade);
					}			
				}
			}	
		}	
	}
}
