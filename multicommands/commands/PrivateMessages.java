package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.PrivateMessenger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PrivateMessages implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player && args.length > 0) {
            Player msender = (Player)sender;
            Player reciever = PrivateMessenger.getReplyTarget(msender);
            Player target =Bukkit.getPlayerExact(args[0]);
            StringBuilder msg = new StringBuilder();
            int i;
            if (command.getName().equalsIgnoreCase("msg")) {
                if (command.getName().equalsIgnoreCase("r")) {
                    if (PrivateMessenger.getReplyTarget(msender) == null) {
                        msender.sendMessage(Messenger.PRIVATE_MSG_RECIEVER_NULL.getMessage());
                        return true;
                    }else if (target != null) {
                        reciever = target;
                        PrivateMessenger.setReplyTarget(msender, reciever);
                        args[0] = "";
                        msg = new StringBuilder();
                        for(i = 0; i < args.length; ++i) {
                            msg.append(" ").append(args[i]);
                        }
                        msender.sendMessage(Messenger.PRIVATE_MSG_SENDER.getMessage().replace("%p", reciever.getName()).replace("%msg%", msg.toString()));
                        reciever.sendMessage(Messenger.PRIVATE_MSG_RECIEVER.getMessage().replace("%p", msender.getName()).replace("%msg%", msg.toString()));
                    } else {
                        msender = (Player)sender;
                        msender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                    }
                    for(i = 0; i < args.length; ++i) {
                        msg.append(" ").append(args[i]);
                    }
                    msender.sendMessage(Messenger.PRIVATE_MSG_SENDER.getMessage().replace("%p", reciever.getName()).replace("%msg%", msg.toString()));
                    reciever.sendMessage(Messenger.PRIVATE_MSG_RECIEVER.getMessage().replace("%p", msender.getName()).replace("%msg%", msg.toString()));
                    return true;
                }
            } else{
                sender.sendMessage(Messenger.PRIVATE_MSG_ERROR.getMessage());
            }
        }
        return false;
    }
}

