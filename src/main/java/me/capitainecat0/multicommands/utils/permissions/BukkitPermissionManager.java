package me.capitainecat0.multicommands.utils.permissions;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BukkitPermissionManager implements PermissionManager{
    @Override
    public boolean hasPermission(@NotNull OfflinePlayer player, String permission) {
        return Objects.requireNonNull(player.getPlayer()).hasPermission(permission);
    }

    @Override
    public boolean hasPermission(@NotNull Player player, String permission) {
        return Objects.requireNonNull(player.getPlayer()).hasPermission(permission);
    }
}
