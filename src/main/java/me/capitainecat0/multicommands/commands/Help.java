package me.capitainecat0.multicommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.help.HelpTopic;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.CMD_NO_PERM;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.MessengerUtils.hideActiveBossBar;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;

public class Help implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(!sender.hasPermission(ALL_PERMS.getPermission())){
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            return true;
        }
        if(args.length == 0){
            sender.sendMessage("§6Aide des différentes commandes§8:");
            for(HelpTopic help : Bukkit.getHelpMap().getHelpTopics()){
                sender.sendMessage(" §e-" + help.getName() + " §7" + help.getShortText());
            }
            /*for(Plugin plugin : Bukkit.getPluginManager().getPlugins()){
                sender.sendMessage("§6 -§e" + plugin.getName() + "§8: §b/help " + plugin.getName());
            }
        }else if(args.length == 1){
            for(Plugin plugin : Bukkit.getPluginManager().getPlugins()){
                if(args[0].equalsIgnoreCase(plugin.getName())){
                    sender.sendMessage("§e" + plugin.getDescription());
                }
            }*/
        }


        return false;
    }
}
