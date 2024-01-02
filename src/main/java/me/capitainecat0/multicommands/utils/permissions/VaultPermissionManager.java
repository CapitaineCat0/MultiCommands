package me.capitainecat0.multicommands.utils.permissions;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Objects;

public class VaultPermissionManager implements PermissionManager{
    private final Permission permission;

    public VaultPermissionManager() {
        this.permission = Objects.requireNonNull(Bukkit.getServer().getServicesManager().getRegistration(Permission.class)).getProvider();
    }
    @Override
    public boolean hasPermission(OfflinePlayer player, String permission) {
        return this.permission.playerHas(null, player, permission);
    }

    @Override
    public boolean hasPermission(Player player, String permission) {
        return this.permission.playerHas(null, player, permission);
    }
}
