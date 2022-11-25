package me.capitainecat0.multicommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.KICK_PERM;
import static me.capitainecat0.multicommands.utils.PluginCore.colored;

public class Kick implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(KICK_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            return true;
        }
        else{
            if(args.length == 0){
                getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "<player>"));
            }
            // /kick joueur raison
            // /kick args[0] args[1]
            else if(args.length == 1){
                Player target = Bukkit.getPlayerExact(args[0]);
                assert target != null; // ! =
                target.kickPlayer(colored(KICK_PREFIX.getMessage()));
            }else {
                    String message = "";
                    for (int i = 0; i < args.length; i++) {
                        message = message + args[i] + " ";
                    }
                Player target = Bukkit.getPlayerExact(args[0]);
                assert target != null; // ! =
                if (message.length() == 0){
                    target.kickPlayer(colored(KICK_PREFIX.getMessage()));
                    }else{
                    target.kickPlayer(colored(KICK_PREFIX.getMessage().replace("%reason%", message.replace(target.getName(), ""))));
                    }
            }
        }
        return false;
    }
}
