package me.capitainecat0.multicommands.commands.chatchannels;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;

public class ChatChannels {
    // Représente la permission nécessaire pour accéder à ce canal de chat.
    private static String perm = "";
    private static final PermissionManager perms = MultiCommands.getPermissionManager();
    // Représente les joueurs qui sont dans ce canal de chat.
    private static final Set<Player> players = new HashSet<>();

    public ChatChannels(String permission) {
        perm = permission;
    }

    /**
     * Met à jour la liste des joueurs qui ont accès à ce canal de chat
     * par rapport à la permission.
     */
    public static void updatePlayerList() {
        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            if(perms.hasPermission(player, perm)){
                players.add(player);
            }
        }
    }
    public void broadcast(String message) {
        for (Player player : players) {
            sendMessage(player, message);
        }
    }
}
