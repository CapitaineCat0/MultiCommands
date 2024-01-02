package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.Fly;
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

public class FlyCMD implements CommandExecutor {

    /**
     *
     * The Fly command can toggle fly-mode for player
     * <br>If args isn't null, it will toggle fly-mode for targeted player
     * <br>If args is null, it will toggle fly-mode for command sender
     *
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        try {
            if(sender instanceof Player) {
                if(args.length == 0){
                    if(perms.hasPermission((Player) sender, FLY_PERM_SELF.getPermission())
                            || perms.hasPermission((Player) sender, FLY_PERM_ALL.getPermission())
                            || perms.hasPermission((Player) sender, ALL_PERMS.getPermission())){
                        Player player = (Player)sender;
                        new Fly(player, null).execute();
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }else{
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }
                }else if(args.length == 1){
                    if(perms.hasPermission((Player) sender, FLY_PERM_SELF.getPermission())
                            || perms.hasPermission((Player) sender, FLY_PERM_ALL.getPermission())
                            || perms.hasPermission((Player) sender, ALL_PERMS.getPermission())){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            new Fly((Player) sender, target).execute();
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        }else{
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                        }
                    }else{
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM_TO_OTHER.getMessage());
                    }
                }
            }else if(sender instanceof ConsoleCommandSender) {
                if (args.length == 0) {
                    sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("<command>", command.getName()));
                }
                if (args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        if (!target.getAllowFlight()) {
                            target.setAllowFlight(true);
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            getMsgSendConfig(target, command.getName(), FLY_TOGGLE_ON_BY_ADMIN.getMessage());
                            sendConsoleMessage(FLY_TOGGLE_ON_SENDER.getMessage().replace("{0}", target.getName()));
                        } else if (target.getAllowFlight()) {
                            target.setAllowFlight(false);
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), FLY_TOGGLE_OFF_BY_ADMIN.getMessage());
                            sendConsoleMessage(FLY_TOGGLE_OFF_SENDER.getMessage().replace("{0}", target.getName()));
                        }
                    } else {
                        sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                    }
                }
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return false;
    }
}
