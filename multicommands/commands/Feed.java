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

public class Feed implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(Perms.FEED_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
            return true;
        }
        else{
            if(args.length == 0){
                if(sender instanceof Player){
                    if(((Player) sender).getFoodLevel() != 20){
                        ((Player) sender).setFoodLevel(20);
                        MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.FEED_SELF.getMessage());
                    }else{
                        MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.FEED_ALREADY.getMessage());
                    }

                }else if(sender instanceof ConsoleCommandSender){
                    sender.sendMessage(Messenger.NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("%cmd%", command.getName()));
                }
            }if(args.length == 1){
                Player target = Bukkit.getPlayerExact(args[0]);
                if(target != null){
                    if(target.getFoodLevel() != 20){
                        target.setFoodLevel(20);
                        MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.FEED_OTHER_SENDER.getMessage().replace("%p", target.getName()));
                        MultiCommands.getInstance().getMsgSendConfig(target, command.getName(), Messenger.FEED_OTHER.getMessage());
                    }else{
                        MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.FEED_ALREADY_SENDER.getMessage());
                    }
                }else{
                    MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                }
            }
        }
        return false;
    }
}
