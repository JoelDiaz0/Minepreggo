package dev.dixmk.minepreggo.server;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import net.minecraft.server.level.ServerPlayer;

public class ServerSexCinematicManager {
	
	private ServerSexCinematicManager() {}
	
    private static final Set<UUID> playersInCinematic = new HashSet<>();

    public static void startCinematic(ServerPlayer player) {
        playersInCinematic.add(player.getUUID());
    }

    public static void endCinematic(ServerPlayer player) {
        playersInCinematic.remove(player.getUUID());
        // Optional: trigger post-cinematic logic (e.g., dialogue, mob action)
    }

    public static boolean isInCinematic(ServerPlayer player) {
        return playersInCinematic.contains(player.getUUID());
    }
}
