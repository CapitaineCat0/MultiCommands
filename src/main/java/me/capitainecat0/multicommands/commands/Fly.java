package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
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
import static me.capitainecat0.multicommands.utils.Perms.*;

public class Fly implements CommandExecutor {

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
        try {
            if(sender instanceof Player) {
                if(args.length == 0){
                    if(sender.hasPermission(FLY_PERM_SELF.getPermission())|| sender.hasPermission(FLY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                        Player player = (Player)sender;
                        boolean allowFlight = player.getAllowFlight();
                        player.setAllowFlight(!allowFlight);
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        String message = allowFlight ? FLY_TOGGLE_OFF.getMessage() : FLY_TOGGLE_ON.getMessage();
                        getMsgSendConfig(sender, command.getName(), message.replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }else{
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }
                }else if(args.length == 1){
                    if(sender.hasPermission(FLY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            boolean allowFlight = target.getAllowFlight();
                            target.setAllowFlight(!allowFlight);
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            String messageTarget = allowFlight ? FLY_TOGGLE_OFF_BY_ADMIN.getMessage() : FLY_TOGGLE_ON_BY_ADMIN.getMessage();
                            String messageSender = allowFlight ? FLY_TOGGLE_OFF_SENDER.getMessage() : FLY_TOGGLE_ON_SENDER.getMessage();
                            getMsgSendConfig(target, command.getName(), messageTarget);
                            getMsgSendConfig(sender, command.getName(), messageSender.replace("{0}", target.getName()));
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
