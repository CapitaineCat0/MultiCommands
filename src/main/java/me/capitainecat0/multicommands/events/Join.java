package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import me.capitainecat0.multicommands.utils.permissions.Perms;
import me.capitainecat0.multicommands.utils.VanishHandler;
import me.capitainecat0.multicommands.utils.storage.BannedData;
import me.capitainecat0.multicommands.utils.storage.FreezeData;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static me.capitainecat0.multicommands.MultiCommands.getInstance;
import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
public class Join implements Listener {

    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent event) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        try {
            Player player = event.getPlayer();
            FreezeData.frozenData.put(player.getUniqueId(), false);
            if(getInstance().getServer().getBanList(BanList.Type.NAME).isBanned(player.getName())){
                if(!BannedData.isBanned(player)) {
                    BannedData.bannedData.put(player.getUniqueId(), true);
                }
                event.joinMessage(null);
                player.kickPlayer(BAN_PREFIX.getMessage().replace("{0}", Objects.requireNonNull(getInstance().getServer().getBanList(BanList.Type.NAME).getBanEntry(player.getName()).getReason())));
                return;
            }
            if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                if (perms.hasPermission(player, Perms.VANISH_PERM_SELF.getPermission())
                        || perms.hasPermission(player, Perms.VANISH_PERM_ALL.getPermission())
                        || perms.hasPermission(player, Perms.ALL_PERMS.getPermission())) {
                    VanishHandler.getVanished().add(player);
                }
                String joinText = "<dark_gray>{ <green>+ <dark_gray>} <yellow>- <green>><reset>%luckperms_prefix% <aqua>%player_name%";
                joinText = PlaceholderAPI.setPlaceholders(event.getPlayer(), joinText);
                //Component join = Component.text(joinText);
                event.joinMessage(null);
                sendBroadcastMessage(joinText);
            } else {
                //Component join = Component.text(ONJOIN.getMessage().replace("{0}", player.getName()));
                event.joinMessage(null);
                sendBroadcastMessage(ONJOIN.getMessage().replace("{0}", player.getName()));
                if (perms.hasPermission(player, Perms.VANISH_PERM_SELF.getPermission())
                        || perms.hasPermission(player, Perms.VANISH_PERM_ALL.getPermission())
                        || perms.hasPermission(player, Perms.ALL_PERMS.getPermission())) {
                    VanishHandler.getVanished().add(player);
                }
            }
            //sendTablist(player, Component.text(colored(Objects.requireNonNull(getInstance().getConfig().getString("tablist.header")))), Component.text(colored(Objects.requireNonNull(getInstance().getConfig().getString("tablist.footer")))));
        } catch (Exception e) {
            sendEventExceptionMessage(e, "Join.onJoin");
        }
    }
}
