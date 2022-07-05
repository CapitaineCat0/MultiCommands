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
import org.jetbrains.annotations.NotNull;

public class Debug implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(Perms.DEBUG_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
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
            if(sender instanceof Player){
                if(args.length < 1){
                    if(MultiCommands.getInstance().DEBUG_ENABLED){
                        MultiCommands.getInstance().setDebug(false);
                        sender.sendMessage(Messenger.DEBUG_DISABLED.getMessage());
                    }else{
                        MultiCommands.getInstance().setDebug(true);
                        sender.sendMessage(Messenger.DEBUG_ENABLED.getMessage());
                    }
                }
                else if(args[0].equalsIgnoreCase("true") || args[0].equalsIgnoreCase("on")){
                    if(!MultiCommands.getInstance().DEBUG_ENABLED){
                        MultiCommands.getInstance().setDebug(true);
                        sender.sendMessage(Messenger.DEBUG_ENABLED.getMessage());
                    }else{
                        sender.sendMessage(Messenger.DEBUG_ALREADY_ENABLED.getMessage());
                    }

                }else if(args[0].equalsIgnoreCase("false") || args[0].equalsIgnoreCase("off")){
                    if(MultiCommands.getInstance().DEBUG_ENABLED){
                        MultiCommands.getInstance().setDebug(false);
                        sender.sendMessage(Messenger.DEBUG_DISABLED.getMessage());
                    }else{
                        sender.sendMessage(Messenger.DEBUG_ALREADY_DISABLED.getMessage());
                    }
                }
        }else if(sender instanceof ConsoleCommandSender){
                sender.sendMessage(Messenger.NO_CONSOLE_COMMAND.getMessage().replace("%cmd%", command.getName()));
            }
        }
        return false;
    }
}
