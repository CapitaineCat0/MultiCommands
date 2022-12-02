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
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;

public class PrivateMessager implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length >= 2){
            Player target = Bukkit.getPlayerExact(args[0]);
            Player player = (Player) sender;
            PrivateMessengerHandler pmh = new PrivateMessengerHandler();
            if(target != null){
                String message = Joiner.on(" ").join(args).replace(target.getName(), "&dDe &b"+player.getName()+"&d :&7");
                String rmessage = Joiner.on(" ").join(args).replace(target.getName(), "&dA &b"+target.getName()+"&d :&7");
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
                        getMsgSendConfig(sender, command.getName(), "&cVous n'avez pas ouvert de discutions avec d'autres joueurs!");
                    }
                }
            }else{
                getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("%player", args[0]));
            }
        }else{
            getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "<player> <message>"));
        }

        return false;
    }
}
