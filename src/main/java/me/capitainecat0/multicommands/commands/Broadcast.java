package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.BROADCAST_PERM;

public class Broadcast implements CommandExecutor {

    /**
     *
     * The Broadcast command sends messages to all online players
     */
    @Override
public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
    hideActiveBossBar();
    try {
        boolean hasBroadcastPerm = sender.hasPermission(BROADCAST_PERM.getPermission());
        boolean hasAllPerms = sender.hasPermission(ALL_PERMS.getPermission());
        if (!hasBroadcastPerm || !hasAllPerms) {
            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            return true;
        } else {
            if (args.length == 0) {
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(),CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<message>"));
                return true;
            } else {
                StringBuilder bc = new StringBuilder();
                for (String part : args) {
                    bc.append(part).append(" ");
                }
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), BROADCAST_CMD.getMessage());
                sendBroadcastMessage(BROADCAST_PREFIX.getMessage() + "&r " + bc);
            }
        }
    }catch (Exception e){
        sendCommandExceptionMessage(e, command.getName());
    }
     return false;
}
}
