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

import static me.capitainecat0.multicommands.utils.Messenger.CMD_NO_PERM;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class Plugins implements Listener {
    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onCommandEvent(PlayerCommandPreprocessEvent event) {
        hideActiveBossBar();
        if (event.getMessage().equalsIgnoreCase("/plugins") || event.getMessage().equalsIgnoreCase("/pl") ||
                event.getMessage().equalsIgnoreCase("/bukkit:pl") || event.getMessage().equalsIgnoreCase("/bukkit:plugins")) {
            event.setCancelled(true);
            if (!event.getPlayer().hasPermission(Perms.ALL_PERMS.getPermission())) {
                if(soundEnabled()){
                    playSound(event.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                getMsgSendConfig(event.getPlayer(), "plugins", CMD_NO_PERM.getMessage());
                return;
            }
            if(soundEnabled()){
                playSound(event.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            }
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
    }
}
