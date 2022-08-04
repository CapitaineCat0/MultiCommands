package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class EnderChest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(Perms.ENDERCHEST_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            sender.sendMessage(Messenger.CMD_NO_PERM.getMessage().replace("%cmd%", command.getName()));
            return true;
        }
        else{
            if (sender instanceof Player) {
                if (args.length == 0) {
                    Player player = (Player) sender;
                    player.openInventory(player.getEnderChest());
                } else if (args.length == 1) {
                    if(sender.hasPermission(Perms.ALL_PERMS.getPermission())){
                        Player player = (Player) sender;
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            target.sendMessage(Messenger.ENDERCHEST_ADMIN_OPEN.getMessage().replace("%cmd%", command.getName()));
                            player.openInventory(target.getEnderChest());
                        }else{
                            player.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                        }
                    }else{
                        sender.sendMessage(Messenger.CMD_NO_PERM_TO_OTHER.getMessage().replace("%cmd%", command.getName()));
                    }
                }
            } else if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(Messenger.NO_CONSOLE_COMMAND.getMessage().replace("%cmd%", command.getName()));
            }
        }
        return false;
    }
}
