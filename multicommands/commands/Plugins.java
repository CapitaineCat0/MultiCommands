package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

public class Plugins implements Listener {
    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onCommandEvent(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equalsIgnoreCase("/plugins") || event.getMessage().equalsIgnoreCase("/pl") ||
                event.getMessage().equalsIgnoreCase("/bukkit:pl") || event.getMessage().equalsIgnoreCase("/bukkit:plugins")) {
            event.setCancelled(true);
            if (!event.getPlayer().isOp()) {
                MultiCommands.getInstance().getMsgSendConfig(event.getPlayer(), "plugins", Messenger.CMD_NO_PERM.getMessage());
                return;
            }
            event.getPlayer().sendMessage("§6Plugins chargés §7(§c" + Bukkit.getPluginManager().getPlugins().length + "§7)§8:");
            Plugin[] pm = Bukkit.getPluginManager().getPlugins();
            for (Plugin p : pm) {
                try{
                    if(p.isEnabled()){
                        event.getPlayer().sendMessage("  §3- §b" + p.getName() + " §8" + p.getDescription().getVersion() + " §a(enabled)");
                    }else{
                        event.getPlayer().sendMessage("  §3- §b" + p.getName() + " §8" + p.getDescription().getVersion() + " §c(disabled)");
                    }
                }catch(Error e){
                    event.getPlayer().sendMessage("§cUne erreur est survenue :§e" + e);
                }
            }
        }
    }
}
