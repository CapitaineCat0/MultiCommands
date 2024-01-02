package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.Plugins;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import me.capitainecat0.multicommands.utils.permissions.Perms;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class PluginsCMD implements Listener {
    /**
     *
     * La commande &quot;/plugins&quot; requiert la permission &quot;multicommands.plugins&quot; pour fonctionner.
     * <br>Cette commande permet d'afficher la liste des plugins chargés par le serveur.
     */
    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onCommandEvent(@NotNull PlayerCommandPreprocessEvent event) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        try{
            if (event.getMessage().equalsIgnoreCase("/plugins") || event.getMessage().equalsIgnoreCase("/pl") ||
                    event.getMessage().equalsIgnoreCase("/bukkit:pl") || event.getMessage().equalsIgnoreCase("/bukkit:plugins")) {
                event.setCancelled(true);
                if (!perms.hasPermission(event.getPlayer(), Perms.PLUGIN_PERM.getPermission())
                        || !perms.hasPermission(event.getPlayer(), Perms.ALL_PERMS.getPermission())) {
                    playSoundIfEnabled(event.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(event.getPlayer(), "plugins", CMD_NO_PERM.getMessage());
                    return;
                }
                new Plugins(event.getPlayer()).execute();
            }
        }catch(Exception e){
            sendEventExceptionMessage(e, "/plugins");
            sendMessage(event.getPlayer(), CMD_ERROR.getMessage().replace("<command>", event.getEventName()).replace("{e}", e.getMessage()));
            sendSuggestCommandMessage(event.getPlayer(), CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", event.getEventName()).replace("{e}", e.getMessage()));
        }
    }
}
