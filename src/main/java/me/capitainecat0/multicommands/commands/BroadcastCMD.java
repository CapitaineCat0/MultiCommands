package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.Broadcast;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.permissions.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.permissions.Perms.BROADCAST_PERM;

public class BroadcastCMD implements CommandExecutor {

    /**
     *
     * The Broadcast command sends messages to all online players
     */
    @Override
public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
    hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
    try {
        if(sender instanceof Player){
            if (!perms.hasPermission((Player) sender, BROADCAST_PERM.getPermission())
                    || !perms.hasPermission((Player) sender, ALL_PERMS.getPermission())) {
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                return true;
            } else {
                if (args.length == 0) {
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(),CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<message>"));
                    return true;
                } else {
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), BROADCAST_CMD.getMessage());
                    new Broadcast(args).execute();
                }
            }
        }else if(sender instanceof ConsoleCommandSender){
            if(args.length == 0){
                sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("<command>", command.getName()));
            }else{
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), BROADCAST_CMD.getMessage());
                new Broadcast(args).execute();
            }
        }

    }catch (Exception e){
        sendCommandExceptionMessage(e, command.getName());
        sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
    }
     return false;
}
}
