package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.data.BannedData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.Perms.*;
import static me.capitainecat0.multicommands.utils.PluginCore.colored;

public class Ban implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(BAN_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            return true;
        }
        else{
            if(args.length <= 1){
                getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "<player> <message>"));
            }
            else if(args.length == 2){
                StringBuilder bc = new StringBuilder();
                for(String part : args) {
                    bc.append(part).append(" ");
                }
                Player target = Bukkit.getPlayerExact(args[0]);
                assert target != null;
                final BannedData data = new BannedData(target);
                target.kickPlayer(colored(BAN_PREFIX.getMessage().replace("%reason%", bc.toString().replace(target.getName(), ""))));
                if(!BannedData.isBanned()){
                    data.setBanned(true);
                    data.setReason(args[1]);
                    BannedData.save();
                }else{
                    data.setBanned(false);
                    BannedData.save();
                }

            }
        }
        return false;
    }
}
