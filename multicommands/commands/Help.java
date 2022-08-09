package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.help.HelpTopic;
import org.jetbrains.annotations.NotNull;

public class Help implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
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
