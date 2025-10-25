package dev.dixmk.minepreggo.world.entity;

import java.util.List;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.entity.preggo.BabyType;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModPotions;
import dev.dixmk.minepreggo.item.alchemy.PotionItemFactory;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantment.Rarity;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.registries.ForgeRegistries;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;

public class ScientificIllagerTrades {

	private ScientificIllagerTrades() {}
	
	public static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRADES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(
			0, new VillagerTrades.ItemListing[]{ 
			new ItemsForItems(MinepreggoModItems.BABY_HUMAN.get(), 1, Items.GOLD_INGOT, 36, 20),
			new ItemsForItems(MinepreggoModItems.BABY_HUMAN.get(), 1, Items.IRON_INGOT, 48, 20),
			new ItemsForItems(MinepreggoModItems.BABY_HUMAN.get(), 1, Items.DIAMOND, 8, 10),
			new ItemsForItems(MinepreggoModItems.BABY_HUMAN.get(), 1, Items.EMERALD, 64, 20),
			new ItemsForItems(MinepreggoModItems.BABY_ZOMBIE.get(), 1, Items.GOLD_INGOT, 48, 15),
			new ItemsForItems(MinepreggoModItems.BABY_ZOMBIE.get(), 1, Items.DIAMOND, 12, 15),
			new ItemsForItems(MinepreggoModItems.BABY_ZOMBIE.get(), 1, Items.IRON_BLOCK, 9, 15),
			new ItemsForItems(MinepreggoModItems.BABY_HUMANOID_CREEPER.get(), 1, Items.GOLD_INGOT, 64, 10),
			new ItemsForItems(MinepreggoModItems.BABY_QUADRUPED_CREEPER.get(), 1, Items.DIAMOND, 24, 10),
			new EnchantBookForBaby(BabyType.HUMANOID_CREEPER),
			new EnchantBookForBaby(BabyType.HUMANOID_CREEPER),
			new EnchantBookForBaby(BabyType.ZOMBIE),
			new EnchantBookForBaby(BabyType.HUMAN),
			new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.PREGNANCY_ACCELERATION.get()), 25, 1, 20, 0),
			new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.IMPREGNATION_POTION_0.get()), 30, 1, 15, 0),
			new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.PREGNANCY_RESISTANCE.get()), 27, 1, 20, 0),
			new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.PREGNANCY_HEALING.get()), 27, 1, 25, 0),
			new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.VILLAGER_BRAIN.get(), 16, 24, 30, 0)}, 
			1, new VillagerTrades.ItemListing[]{
			new ItemsForItems(MinepreggoModItems.BABY_HUMAN.get(), 1, Items.GOLD_INGOT, 36, 20),
			new ItemsForItems(MinepreggoModItems.BABY_HUMAN.get(), 1, Items.DIAMOND, 8, 20),
			new ItemsForItems(MinepreggoModItems.BREAST_MILK_BOTTLE.get(), 12, Items.GOLD_INGOT, 20, 10),
			new ItemsForItems(MinepreggoModItems.EXPLOSIVE_BREAST_MILK_BOTTLE.get(), 12, Items.DIAMOND, 14, 10),
			new ItemsForItems(MinepreggoModItems.ROTTEN_BREAST_MILK_BOTTLE.get(), 12, Items.GOLD_INGOT, 6, 10),
			new ItemsForItems(MinepreggoModItems.BABY_HUMAN.get(), 1, Items.EMERALD, 64, 20),
			new ItemsForItems(MinepreggoModItems.BABY_ZOMBIE.get(), 1, Items.GOLD_INGOT, 48, 15),
			new ItemsForItems(MinepreggoModItems.BABY_ZOMBIE.get(), 1, Items.DIAMOND, 12, 15),
			new ItemsForItems(MinepreggoModItems.BABY_QUADRUPED_CREEPER.get(), 1, Items.IRON_BLOCK, 12, 15),
			new ItemsForItems(MinepreggoModItems.BABY_HUMANOID_CREEPER.get(), 1, Items.GOLD_INGOT, 64, 10),
			new ItemsForItems(MinepreggoModItems.BABY_HUMANOID_CREEPER.get(), 1, Items.DIAMOND, 24, 10),
			new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.CREEPER_IMPREGNATION_0.get()), 35, 1, 15, 0),
			new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.PREGNANCY_ACCELERATION.get()), 27, 1, 20, 0),
			new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.PREGNANCY_HEALING.get()), 27, 1, 25, 0),
			new EnchantBookForBaby(BabyType.HUMANOID_CREEPER),
			new EnchantBookForBaby(BabyType.HUMAN),
			new EnchantBookForBaby(BabyType.QUADRUPED_CREEPER),
			new EnchantBookForBaby(BabyType.ZOMBIE)},
			2, new VillagerTrades.ItemListing[]{
			new ItemsForItems(MinepreggoModItems.BREAST_MILK_BOTTLE.get(), 12, Items.GOLD_INGOT, 20, 10),
			new ItemsForItems(MinepreggoModItems.EXPLOSIVE_BREAST_MILK_BOTTLE.get(), 12, Items.DIAMOND, 14, 10),
			new ItemsForItems(MinepreggoModItems.ROTTEN_BREAST_MILK_BOTTLE.get(), 12, Items.GOLD_INGOT, 6, 10),
			new ItemsForItems(MinepreggoModItems.DEAD_ZOMBIE_FETUS.get(), 40, MinepreggoModItems.ZOMBIE_LIFE_SUBSTANCE.get(), 5, 15),
			new ItemsForItems(MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get(), 30, MinepreggoModItems.HUMANOID_CREEPER_LIFE_SUBSTANCE.get(), 3, 15),
			new ItemsForItems(MinepreggoModItems.BABY_ZOMBIE.get(), 1, Items.GOLD_INGOT, 48, 15),
			new ItemsForItems(MinepreggoModItems.BABY_ZOMBIE.get(), 1, Items.DIAMOND, 12, 15),
			new ItemsForItems(MinepreggoModItems.BABY_ZOMBIE.get(), 1, Items.IRON_BLOCK, 10, 15),
			new ItemsForItems(MinepreggoModItems.BABY_HUMANOID_CREEPER.get(), 1, Items.GOLD_INGOT, 64, 10),
			new ItemsForItems(MinepreggoModItems.BABY_HUMANOID_CREEPER.get(), 1, Items.DIAMOND, 30, 10),		
			new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.ZOMBIE_IMPREGNATION_0.get()), 40, 1, 15, 0),
			new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.PREGNANCY_ACCELERATION.get()), 27, 1, 20, 0),
			new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.PREGNANCY_RESISTANCE.get()), 27, 1, 25, 0),
			new EnchantBookForBaby(BabyType.HUMANOID_CREEPER),
			new EnchantBookForBaby(BabyType.QUADRUPED_CREEPER),
			new EnchantBookForBaby(BabyType.ZOMBIE),
			new EnchantBookForBaby(BabyType.HUMAN)}
	));


	static class ItemsForItems implements VillagerTrades.ItemListing {
	      protected final ItemStack sourceItemStack;
	      protected final ItemStack targetItemStack;     
	      protected final int numberOfTargetItems;
	      protected final int numberOfSourceItems;      
	      protected final int maxUses;
	      protected final int villagerXp;
	      protected final float priceMultiplier;

	      public ItemsForItems(ItemStack sourceItemStack, int numberOfSourceItems, ItemStack targetItemStack, int numberOfTargetItems, int maxUses, int villagerXp, float priceMultiplier) {
	         this.sourceItemStack = sourceItemStack;
	         this.targetItemStack = targetItemStack;
	         this.numberOfTargetItems = numberOfTargetItems;
	         this.numberOfSourceItems = numberOfSourceItems;
	         this.maxUses = maxUses;
	         this.villagerXp = villagerXp;
	         this.priceMultiplier = priceMultiplier;
	      }

	      public ItemsForItems(Item sourceItem, int numberOfSourceItems, Item targetItem, int numberOfTargetItems, int maxUses, int villagerXp) {
	    	  this(new ItemStack(sourceItem), numberOfSourceItems, new ItemStack(targetItem), numberOfTargetItems, maxUses, villagerXp, 0.05F);
	      }
	      
	      public ItemsForItems(Item sourceItem, int numberOfSourceItems, Item targetItem, int numberOfTargetItems, int maxUses) {
	    	  this(new ItemStack(sourceItem), numberOfSourceItems, new ItemStack(targetItem), numberOfTargetItems, maxUses, 0, 0.05F);
	      }
	       
		  public MerchantOffer getOffer(Entity p_219699_, RandomSource p_219700_) {
	         return new MerchantOffer(
	        		 new ItemStack(sourceItemStack.getItem(), numberOfSourceItems),
	        		 new ItemStack(targetItemStack.getItem(), numberOfTargetItems),
	        		 this.maxUses, this.villagerXp, this.priceMultiplier);
	      }
	  }
	

	static class PotionsForEmeralds extends VillagerTrades.ItemsForEmeralds {
		public PotionsForEmeralds(ItemStack p_35765_, int p_35766_, int p_35767_, int p_35768_, int p_35769_) {
			super(p_35765_, p_35766_, p_35767_, p_35768_, p_35769_);
		}
		
		@Override
		public MerchantOffer getOffer(Entity p_219699_, RandomSource p_219700_) {
			ItemStack sellingStack = this.itemStack.copy();
			sellingStack.setCount(1);
			return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), sellingStack, this.maxUses, this.villagerXp, this.priceMultiplier);
		}
	}

	
	static class EnchantBookForBaby implements VillagerTrades.ItemListing {
		private Item babyItem;
		private Enchantment.Rarity rarity;
		private float enchantmentMaxLevelProbability;
		
		public EnchantBookForBaby(BabyType babyType) {		    	
			switch (babyType) {	
			case QUADRUPED_CREEPER: {
				babyItem = MinepreggoModItems.BABY_QUADRUPED_CREEPER.get();
				rarity = Rarity.VERY_RARE;
				enchantmentMaxLevelProbability = 0.9F;
				break;
			}
			case HUMANOID_CREEPER: {
				babyItem = MinepreggoModItems.BABY_HUMANOID_CREEPER.get();
				rarity = Rarity.RARE;
				enchantmentMaxLevelProbability = 0.7F;
				break;
			}
			case ZOMBIE: {
				babyItem = MinepreggoModItems.BABY_ZOMBIE.get();
				rarity = Rarity.UNCOMMON;
				enchantmentMaxLevelProbability = 0.5F;
				break;
			}
			default:
				babyItem = MinepreggoModItems.BABY_HUMAN.get();
				rarity = Rarity.COMMON;
				enchantmentMaxLevelProbability = 0.3F;
			}    	
		}


		public MerchantOffer getOffer(Entity p_219688_, RandomSource p_219689_) {
			List<Enchantment> list = ForgeRegistries.ENCHANTMENTS.getValues().stream()
					.filter(e -> e.isTradeable() && !e.isCurse() && e.getRarity() == rarity)
					.toList();
			
			Enchantment enchantment = list.get(p_219689_.nextInt(list.size()));
			int i;	
			
			if (p_219689_.nextFloat() < enchantmentMaxLevelProbability) {
				i = enchantment.getMaxLevel();
			}
			else {
				i = p_219689_.nextInt(enchantment.getMinLevel(), enchantment.getMaxLevel() + 1);
			}
	 
			ItemStack itemstack = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, i));	       
			return new MerchantOffer(new ItemStack(babyItem), new ItemStack(Items.BOOK), itemstack, 12, 0, 0.2F);
		}
	}
}
