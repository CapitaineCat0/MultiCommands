package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.EnderChest;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.permissions.Perms.*;

public class EnderChestCMD implements CommandExecutor {

    @Override
public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
    hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        try {
            if (sender instanceof Player player) {
                if (args.length == 0) {
                    if(perms.hasPermission(player, ENDERCHEST_PERM_SELF.getPermission())
                            || perms.hasPermission(player, ENDERCHEST_PERM_ALL.getPermission())
                            || perms.hasPermission(player, ALL_PERMS.getPermission())){
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        new EnderChest(player, player).execute();
                    }else{
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(player, command.getName(), CMD_NO_PERM.getMessage());
                        return true;
                    }
                } else if (args.length == 1) {
                    if(perms.hasPermission(player, ENDERCHEST_PERM_OTHER.getPermission())
                            || perms.hasPermission(player, ENDERCHEST_PERM_ALL.getPermission())
                            || perms.hasPermission(player, ALL_PERMS.getPermission())){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            sendMessage(target, command.getName(), ENDERCHEST_ADMIN_OPEN.getMessage());
                            new EnderChest(player, target).execute();
                        }else{
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(player, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                        }
                    }else{
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(player, command.getName(), CMD_NO_PERM_TO_OTHER.getMessage());
                    }
                }
            } else if (sender instanceof ConsoleCommandSender) {
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
    return false;
    }
}
