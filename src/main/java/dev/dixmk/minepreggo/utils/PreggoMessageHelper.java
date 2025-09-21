package dev.dixmk.minepreggo.utils;

import com.google.common.collect.ImmutableMap;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class PreggoMessageHelper {

	private PreggoMessageHelper() {}
	

	public static final Int2ObjectMap<String[]> ARMOR_MESSAGES = customToIntMap(ImmutableMap.of(
			0, new String[] {"Your boobs don't fit in this armor!", "%s's boobs don't fit in this armor!"},
			1, new String[] {"Your slightly swollen belly doesn't fit in this armor!", "%s's slightly swollen belly doesn't fit in this armor!"},
			2, new String[] {"Your swollen belly can't fit in this armor...", "%s's swollen belly couldn't fit in this armor..."},
			3, new String[] {"Your belly is too big to wear this armor!", "%s's belly is too big to wear this armor..."},
			4, new String[] {"A belly that huge couldn't fit this armor!", "%s's belly is too huge to wear this armor..."},
			5, new String[] {"Do you wanna kill your babies?", "%s's belly is too huge to wear this armor..."},
			6, new String[] {"A belly that heavy couldn't fit this armor!", "%s's belly is too heavy to wear this armor..."},
			7, new String[] {"Do really you think this armor could fit this belly?", "%s's belly is out of bounds..."},
			-1, new String[] {"Your butt and hips are too big to wear this armor", "%s's hips are too big to wear this armor..."},
			-2, new String[] {"Your lactating boobs don't fit in this armor", "%s's hips are too big to wear this armor..."}
	));
	

	private static Int2ObjectMap<String[]> customToIntMap(ImmutableMap<Integer, String[]> p_35631_) {
		return new Int2ObjectOpenHashMap<>(p_35631_);
	}
	
	/*
	public static<T extends TamableAnimal & PreggoP1> void warningOwnerPossibleMiscarriageEvent(T preggoMob) {	
		if (preggoMob.isTame() && !preggoMob.level().isClientSide) {		
			sendMessagePlayer((Player) preggoMob.getOwner(), String.format("%s feels pain below her pregnant belly, something isn't right...", preggoMob.getPreggoName()));
			
		}			
	}
	
	public static<T extends TamableAnimal & PostPregnancy> void warningOwnerPospartumEvent(T preggoMob) {	
		if (preggoMob.isTame() && !preggoMob.level().isClientSide) {		
			var owner = (Player) preggoMob.getOwner();  
			sendMessagePlayer(owner, String.format("%s just gave birth your babies, congratulation %s!", preggoMob.getPreggoName(), owner.getDisplayName().getString()));	
		}			
	}
	
	public static<T extends TamableAnimal & PostPregnancy> void warningOwnerMiscarriageEvent(T preggoMob) {	
		if (preggoMob.isTame() && !preggoMob.level().isClientSide) {		
			sendMessagePlayer((Player) preggoMob.getOwner(), String.format(String.format("%s just lose the baby...", preggoMob.getPreggoName())));	
		}			
	}
	 */
	
	public static void sendMessagePlayer(Player player, String message) {
		if (!player.level().isClientSide) {		
			player.displayClientMessage(Component.literal(message), true);
		}	
	}
	
	
}
