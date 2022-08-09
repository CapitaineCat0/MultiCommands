package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerPinger implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(Perms.PLAYERPINGER_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
            return true;
        }
        else{
            if(args.length == 0){
                if(sender instanceof Player){
                    int ping = ((Player) sender).getPing();
                    if(ping < 50){
                        sender.sendMessage(Messenger.PING_SELF_MSG.getMessage().replace("%ping%", MultiCommands.colored("&a" + ping) + " ms"));
                    }
                    if(ping > 50){
                        sender.sendMessage(Messenger.PING_SELF_MSG.getMessage().replace("%ping%", MultiCommands.colored("&e" + ping) + " ms"));
                    }
                    if(ping > 300){
                        sender.sendMessage(Messenger.PING_SELF_MSG.getMessage().replace("%ping%", MultiCommands.colored("&c" + ping) + " ms"));
                    }
                }else if(sender instanceof ConsoleCommandSender){
                    sender.sendMessage(Messenger.NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("%cmd%", command.getName()));
                }
            }else if(args.length == 1){
                Player target = Bukkit.getPlayerExact(args[0]);
                assert target != null;
                int ping = target.getPing();
                if(ping < 50){
                    sender.sendMessage(Messenger.PING_OTHER_MSG.getMessage().replace("%target", target.getName()).replace("%ping%", MultiCommands.colored("&a" + ping) + " ms"));
                }
                if(ping > 50){
                    sender.sendMessage(Messenger.PING_OTHER_MSG.getMessage().replace("%target", target.getName()).replace("%ping%", MultiCommands.colored("&e" + ping) + " ms"));
                }
                if(ping > 300){
                    sender.sendMessage(Messenger.PING_OTHER_MSG.getMessage().replace("%target", target.getName()).replace("%ping%", MultiCommands.colored("&c" + ping) + " ms"));
                }
            }
        }
        return false;
    }
}
