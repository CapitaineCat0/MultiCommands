package me.capitainecat0.multicommands.commands;

import com.google.common.base.Joiner;
import me.capitainecat0.multicommands.utils.PrivateMessengerHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class PrivateMessager implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length >= 2){
            Player target = Bukkit.getPlayerExact(args[0]);
            Player player = (Player) sender;
            PrivateMessengerHandler pmh = new PrivateMessengerHandler();
            if(target != null){
                String message = Joiner.on(" ").join(args).replace(target.getName(), "&dFrom &b"+player.getName()+"&d :&7");
                String rmessage = Joiner.on(" ").join(args).replace(target.getName(), "&dFor &b"+target.getName()+"&d :&7");
                if(command.getName().equalsIgnoreCase("msg")){
                    pmh.setMessager(player);
                    sendMessage(target, message);
                    sendMessage(sender, rmessage);
                }else if(command.getName().equalsIgnoreCase("r")){
                    if(pmh.isMessager(player) == true){
                        pmh.setMessager(player);
                        sendMessage(player, rmessage);
                        sendMessage(target, message);
                    }else{
                        getMsgSendConfig(sender, command.getName(), "&cYou doesn't start discussion with any player");
                    }
                }
            }else{
                getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[0]).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            }
        }else{
            getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player> <message>").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        }

        return false;
    }
}
