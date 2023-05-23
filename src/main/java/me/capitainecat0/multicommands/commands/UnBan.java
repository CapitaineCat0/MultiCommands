package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.data.BannedData;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

import static me.capitainecat0.multicommands.MultiCommands.getInstance;
import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.BAN_PERM;

public class UnBan implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(BAN_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            return true;
        }
        else{
            if(args.length <= 1){
                getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player>").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            }
            else if(args.length == 2){
                UUID uuid = Objects.requireNonNull(Bukkit.getPlayerExact(args[0]).getUniqueId());
                OfflinePlayer target = Bukkit.getOfflinePlayer(uuid);
                String banReason = args[1];
                new BannedData(target);
                if(target.isBanned()){
                    new BannedData(target).setBanned(target, false);
                    new BannedData(target).setReason(target, "[]");
                    getInstance().getServer().getBanList(BanList.Type.NAME).pardon(target.getName());
                    sendMessage(sender, UNBAN_DONE.getMessage().replace("{0}", target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                }else{
                    sendMessage(sender, UNBAN_ERROR.getMessage().replace("{0}", Objects.requireNonNull(target.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage())));
                }
            }
        }
        return false;
    }
}
