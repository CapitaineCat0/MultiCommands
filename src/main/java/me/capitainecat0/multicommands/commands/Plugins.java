package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class Plugins implements Listener {
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
        try{
            if (event.getMessage().equalsIgnoreCase("/plugins") || event.getMessage().equalsIgnoreCase("/pl") ||
                    event.getMessage().equalsIgnoreCase("/bukkit:pl") || event.getMessage().equalsIgnoreCase("/bukkit:plugins")) {
                event.setCancelled(true);
                if (!event.getPlayer().hasPermission(Perms.ALL_PERMS.getPermission())) {
                    playSoundIfEnabled(event.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(event.getPlayer(), "plugins", CMD_NO_PERM.getMessage());
                    return;
                }
                playSoundIfEnabled(event.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                sendMessage(event.getPlayer(), "&6Plugins loaded &7(&c" + Bukkit.getPluginManager().getPlugins().length + "&7)&8:");
                Plugin[] pm = Bukkit.getPluginManager().getPlugins();
                for (Plugin p : pm) {
                    try{
                        if(p.isEnabled()){
                            sendMessage(event.getPlayer(), "  &3- &b" + p.getName() + " &8" + p.getDescription().getVersion() + " &a(enabled)");
                        }else{
                            sendMessage(event.getPlayer(), "  &3- &b" + p.getName() + " &8" + p.getDescription().getVersion() + " &c(disabled)");
                        }
                    }catch(Error e){
                        sendMessage(event.getPlayer(), "&cAn error occurred :&e" + e);
                    }
                }
            }
        }catch(Exception e){
            sendEventExceptionMessage(e, "/plugins");
            sendMessage(event.getPlayer(), CMD_ERROR.getMessage().replace("<command>", event.getEventName()).replace("{e}", e.getMessage()));
            sendSuggestCommandMessage(event.getPlayer(), CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", event.getEventName()).replace("{e}", e.getMessage()));
        }
    }
}
