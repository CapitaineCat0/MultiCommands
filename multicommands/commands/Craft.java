package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Craft implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(!sender.hasPermission(Perms.CRAFT_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
                sender.sendMessage(Messenger.CMD_NO_PERM.getMessage().replace("%cmd%", command.getName()));
                if(MultiCommands.getInstance().DEBUG_ENABLED) {
                    for (OfflinePlayer operators : Bukkit.getOperators()) {
                        if (operators.getPlayer().hasPermission(Perms.ALL_PERMS.getPermission()) || operators.getPlayer().hasPermission(Perms.DEBUG_PERM.getPermission())) {
                            operators.getPlayer().sendMessage(Messenger.CMD_DEBUG_NO_PERM.getMessage()
                                    .replace("%p", sender.getName())
                                    .replace("%cmd%", command.getName()));
                        }
                    }
                }
                return true;
            }
            else{
                if(MultiCommands.getInstance().DEBUG_ENABLED) {
                    for (OfflinePlayer operators : Bukkit.getOperators()) {
                        if (operators.getPlayer().hasPermission(Perms.ALL_PERMS.getPermission()) || operators.getPlayer().hasPermission(Perms.DEBUG_PERM.getPermission())) {
                            operators.getPlayer().sendMessage(Messenger.CMD_DEBUG_SUCCES.getMessage()
                                    .replace("%p", sender.getName())
                                    .replace("%cmd%", command.getName()));
                        }
                    }
                }
                ((Player)sender).openWorkbench(((Player)sender).getLocation(), true);
            }
        }else if(sender instanceof ConsoleCommandSender){
            sender.sendMessage(Messenger.NO_CONSOLE_COMMAND.getMessage().replace("%cmd%", command.getName()));
        }
        return false;
    }
}
