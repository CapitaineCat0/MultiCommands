package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.CRAFT_PERM;

public class Craft implements CommandExecutor {

    /**
     *
     * The Craft command opens a workbench GUI without any workbench
     */
    @Override
public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        hideActiveBossBar();
        try {
            if (sender instanceof Player) {
                if (!sender.hasPermission(CRAFT_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())) {
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                    return true;
                } else {
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    ((Player) sender).openWorkbench(((Player) sender).getLocation(), true);
                }
            } else if (sender instanceof ConsoleCommandSender) {
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, command.getName());
        }
    return false;
}
}
