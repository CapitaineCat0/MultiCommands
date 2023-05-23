package me.capitainecat0.multicommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerKickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static me.capitainecat0.multicommands.MultiCommands.getInstance;
import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.Messenger.PLUGIN_PREFIX;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class Kick implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(!sender.hasPermission(KICK_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            return true;
        }
        else{
            if(args.length <= 1){
                getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player> <reason>").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            }
            else if(args.length == 2){
                Player target = Objects.requireNonNull(Bukkit.getPlayerExact(args[0]));
                StringBuilder bc = new StringBuilder();
                for(String part : args) {
                    bc.append(part).append(" ");
                }
                String kickReason = bc.toString().replace(args[0], "");
                final PlayerKickEvent event = new PlayerKickEvent(target, kickReason, KICK_PREFIX.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                getInstance().getServer().getPluginManager().callEvent(event);
                sendMessage(KICK_ALERT.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                if (event.isCancelled()) {
                    return false;
                }
            }
        }
        return false;
    }
}
