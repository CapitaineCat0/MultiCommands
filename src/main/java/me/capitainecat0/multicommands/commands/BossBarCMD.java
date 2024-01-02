package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.BossBar;
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
import static me.capitainecat0.multicommands.utils.permissions.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.permissions.Perms.BOSSBAR_PERM;

public class BossBarCMD implements CommandExecutor {

    /**
     *
     * The BossBar command sends messages on boss bar overlay
     */
    @Override
public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
    try {
        String commandName = command.getName();
        if(sender instanceof Player){
            if(!perms.hasPermission((Player) sender, BOSSBAR_PERM.getPermission())
                    || !perms.hasPermission((Player) sender, ALL_PERMS.getPermission())){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, commandName, CMD_NO_PERM.getMessage());
            }else{
                if(args.length == 0){
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, commandName, CMD_NO_ARGS.getMessage().replace("<command>", commandName).replace("{0}", "<player> <message>"));
                }else if(args.length > 1){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null){
                        new BossBar(target, args).execute();
                        sendMessage(sender, ACTIONBAR_SENT_TO_OTHER.getMessage().replace("{0}", target.getName()));
                    }else{
                        sendMessage(sender, ACTIONBAR_SENT_TO_ALL.getMessage());
                        new BossBar((Player) sender, args).execute();
                    }
                }
            }
        }else if(sender instanceof ConsoleCommandSender){
            sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", commandName));
        }
    }catch (Exception e){
        sendCommandExceptionMessage(e, command.getName());
        sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
    }
     return true;
}
}
