package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.data.BannedData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class Ban implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(BAN_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            return true;
        }
        else{
            if(args.length <= 1){
                getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player> <message>"));
            }
            else if(args.length == 2){
                String message = "";
                for (int i = 0; i < args.length; i++) {
                    message = message + args[i] + " ";
                }
                if (message.length() == 0){
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player> <message>"));
                    return false;
                }else{
                    Player target = Bukkit.getPlayerExact(args[0]);
                    assert target != null;
                    UUID uuid = target.getUniqueId();
                    OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(uuid);
                    final BannedData data = new BannedData(target);
                    if(!BannedData.isBanned()){
                        data.setBanned(target,true);
                        data.setReason(target, message);
                    }else{
                        data.setBanned(offlineTarget, false);
                    }
                    target.kickPlayer(colored(BAN_PREFIX.getMessage().replace("{0}", message.replace(target.getName(), ""))));
                }


            }
        }
        return false;
    }
}
