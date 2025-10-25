package dev.dixmk.minepreggo.world.entity;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModPotions;
import dev.dixmk.minepreggo.init.MinepreggoModVillagerProfessions;
import dev.dixmk.minepreggo.item.alchemy.PotionItemFactory;
import dev.dixmk.minepreggo.world.entity.ScientificIllagerTrades.PotionsForEmeralds;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

import net.minecraft.Util;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;

public class CustomVillagerTrades {

	private CustomVillagerTrades() {}
	
	public static final Map<VillagerProfession, Int2ObjectMap<VillagerTrades.ItemListing[]>> TRADES = Util.make(Maps.newHashMap(), (p_35633_) -> {
		   
		p_35633_.put(VillagerProfession.FARMER, new Int2ObjectOpenHashMap<>(ImmutableMap.of(
				1, new VillagerTrades.ItemListing[]
				{ new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.CHILI_PEPPER.get(), 5, 15, 20),
				new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.LEMON.get(), 5, 20, 20),
				new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.CUCUMBER.get(), 7, 15, 20)}, 
				2, new VillagerTrades.ItemListing[]
				{ new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.CHILI_PEPPER.get(), 10, 35, 20),
				new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.LEMON.get(), 10, 45, 20),
				new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.CUCUMBER.get(), 14, 35, 20)},
				3, new VillagerTrades.ItemListing[] { 
				new VillagerTrades.ItemsForEmeralds(Items.COCOA_BEANS, 20, 10, 20),
				new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.LEMON.get(), 10, 45, 20),
				new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.PICKLE.get(), 10, 8, 20)},
				4, new VillagerTrades.ItemListing[] { 
				new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.CHILI_PEPPER.get(), 20, 64, 20),
				new VillagerTrades.ItemsForEmeralds(Items.COCOA_BEANS, 30, 15, 20),
				new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.PICKLE.get(), 20, 18, 20)}, 
				5, new VillagerTrades.ItemListing[] { 
				new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.CHOCOLATE_BAR.get(), 30, 10, 20),
				new VillagerTrades.ItemsForEmeralds(Items.COCOA_BEANS, 40, 20, 20),
				new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.PICKLE.get(), 30, 64, 20)})));	
			
		p_35633_.put(MinepreggoModVillagerProfessions.VILLAGER_DOCTOR.get(), new Int2ObjectOpenHashMap<>(ImmutableMap.of(
				1, new VillagerTrades.ItemListing[]
				{ new PotionsForEmeralds(PotionItemFactory.createPotion(Potions.HEALING), 15, 1, 20, 5)},
				2, new VillagerTrades.ItemListing[]
				{ new PotionsForEmeralds(PotionItemFactory.createPotion(Potions.STRONG_HEALING), 17, 1, 20, 25),
					new PotionsForEmeralds(PotionItemFactory.createSplashPotion(Potions.HEALING), 16, 1, 20, 20)},
				3, new VillagerTrades.ItemListing[]
				{ new PotionsForEmeralds(PotionItemFactory.createSplashPotion(MinepreggoModPotions.IMPREGNATION_POTION_0.get()), 30, 1, 20, 27)},
				4, new VillagerTrades.ItemListing[]
				{ new PotionsForEmeralds(PotionItemFactory.createSplashPotion(MinepreggoModPotions.PREGNANCY_HEALING.get()), 35, 1, 20, 30)},
				5, new VillagerTrades.ItemListing[]
				{ new PotionsForEmeralds(PotionItemFactory.createSplashPotion(MinepreggoModPotions.PREGNANCY_RESISTANCE.get()), 40, 1, 20, 33)}))); 
	});
	
	
	
}
