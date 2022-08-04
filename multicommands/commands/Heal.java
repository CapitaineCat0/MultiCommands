package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(Perms.HEAL_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            sender.sendMessage(Messenger.CMD_NO_PERM.getMessage().replace("%cmd%", command.getName()));
            return true;
        }
        else{
            if(args.length == 0){
                if(sender instanceof Player){
                    if(((Player) sender).getHealth() != 20){
                        ((Player) sender).setHealth(20);
                        sender.sendMessage(Messenger.HEAL_SELF.getMessage());
                    }else{
                        sender.sendMessage(Messenger.HEAL_ALREADY.getMessage());
                    }
                }else if(sender instanceof ConsoleCommandSender){
                    sender.sendMessage(Messenger.NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("%cmd%", command.getName()));
                }
            }
            if(args.length == 1){
                Player target = Bukkit.getPlayerExact(args[0]);
                if(target != null){
                    if(target.getHealth() != 20){
                        target.setHealth(20);
                        target.sendMessage(Messenger.HEAL_OTHER.getMessage());
                        sender.sendMessage(Messenger.HEAL_OTHER_SENDER.getMessage().replace("%p", target.getName()));
                    }else{
                        sender.sendMessage(Messenger.HEAL_ALREADY_SENDER.getMessage().replace("%p", target.getName()));
                    }
                }else{
                    sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                }
            }
        }
        return false;
    }
}
