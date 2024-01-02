package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.MultiInfos;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.permissions.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.permissions.Perms.MULTIINFOS_PERM;

public class MultiInfosCMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        if(!perms.hasPermission((Player) sender, MULTIINFOS_PERM.getPermission())
                || !perms.hasPermission((Player) sender, ALL_PERMS.getPermission())){
            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            return true;
        }
        else{
            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            try{
                new MultiInfos((Player) sender).execute();
            } catch(Exception e) {
                sendCommandExceptionMessage(e, command.getName());
            }
        }
        return false;
    }
}
