package dev.dixmk.minepreggo.utils;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.entity.preggo.IPreggoMob;
import dev.dixmk.minepreggo.entity.preggo.IPregnancySystem;
import dev.dixmk.minepreggo.entity.preggo.PregnancyStage;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;

public class PreggoMessageHelper {

	private PreggoMessageHelper() {}
	
	private static final Int2ObjectMap<String> PLAYER_ARMOR_MESSAGES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(
			0, "chat.minepreggo.player.armor.message.chestplate_p0_does_not_fit",
			1, "chat.minepreggo.player.armor.message.chestplate_p1_does_not_fit",
			2, "chat.minepreggo.player.armor.message.chestplate_p2_does_not_fit",
			3, "chat.minepreggo.player.armor.message.chestplate_p3_does_not_fit",
			4, "chat.minepreggo.player.armor.message.chestplate_p4_does_not_fit",
			5, "chat.minepreggo.player.armor.message.chestplate_p5_does_not_fit",
			6, "chat.minepreggo.player.armor.message.chestplate_p6_does_not_fit",
			7, "chat.minepreggo.player.armor.message.chestplate_p7_does_not_fit"
	));
	
	private static final Int2ObjectMap<String> PREGGO_MOB_ARMOR_MESSAGES = new Int2ObjectOpenHashMap<>(ImmutableMap.of(
			0, "chat.minepreggo.preggo_mob.armor.message.chestplate_p0_does_not_fit",
			1, "chat.minepreggo.preggo_mob.armor.message.chestplate_p1_does_not_fit",
			2, "chat.minepreggo.preggo_mob.armor.message.chestplate_p2_does_not_fit",
			3, "chat.minepreggo.preggo_mob.armor.message.chestplate_p3_does_not_fit",
			4, "chat.minepreggo.preggo_mob.armor.message.chestplate_p4_does_not_fit",
			5, "chat.minepreggo.preggo_mob.armor.message.chestplate_p5_does_not_fit",
			6, "chat.minepreggo.preggo_mob.armor.message.chestplate_p6_does_not_fit",
			7, "chat.minepreggo.preggo_mob.armor.message.chestplate_p7_does_not_fit"
	));
	
	
	public static Component getPlayerArmorChestMessage(PregnancyStage stage) {
		return Component.translatable(PLAYER_ARMOR_MESSAGES.get(stage.ordinal()));
	}
	
	public static Component getPreggoMobArmorChestMessage(PregnancyStage stage, String name) {
		return Component.translatable(PREGGO_MOB_ARMOR_MESSAGES.get(stage.ordinal()), name);
	}

	public static Component getPreggoMobArmorLeggingsMessage(String name) {
		return Component.translatable("chat.minepreggo.preggo_mob.armor.message.leggings_p1_does_not_fit", name);
	}
	
	public static<T extends TamableAnimal & IPreggoMob & IPregnancySystem> void warningOwnerPossibleMiscarriageEvent(T preggoMob) {	
		if (preggoMob.isTame() && preggoMob.getOwner() != null  && !preggoMob.level().isClientSide()) {		
			sendMessagePlayer((Player) preggoMob.getOwner(), Component.translatable("chat.minepreggo.preggo_mob.pregnancy.message.miscarriage_warning", preggoMob.getName()));		
		}			
	}
	
	public static<T extends TamableAnimal & IPreggoMob & IPregnancySystem> void warningOwnerPospartumEvent(T preggoMob) {	
		if (preggoMob.isTame() && preggoMob.getOwner() != null && !preggoMob.level().isClientSide()) {		
			final var owner = (Player) preggoMob.getOwner();
			sendMessagePlayer(owner, Component.translatable("chat.minepreggo.preggo_mob.message.post_partum", preggoMob.getPreggoName(), owner.getDisplayName().getString()));	
		}			
	}
	
	public static<T extends TamableAnimal & IPreggoMob & IPregnancySystem> void warningOwnerMiscarriageEvent(T preggoMob) {	
		if (preggoMob.isTame() && preggoMob.getOwner() != null && !preggoMob.level().isClientSide()) {		
			sendMessagePlayer((Player) preggoMob.getOwner(), Component.translatable("chat.minepreggo.preggo_mob.message.post_miscarriage", preggoMob.getPreggoName()));	
		}			
	}
	
	public static void sendMessagePlayer(Player player, String message) {
		player.displayClientMessage(Component.literal(message), true);	
	}
	
	public static void sendMessagePlayer(Player player, Component component) {
		player.displayClientMessage(component, true);	
	}
}
