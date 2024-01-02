package me.capitainecat0.multicommands.utils.permissions;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface PermissionManager {
    boolean hasPermission(OfflinePlayer player, String permission);
    boolean hasPermission(Player player, String permission);
}
