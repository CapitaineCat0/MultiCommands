package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
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
        if(sender instanceof Player){
            if(!sender.hasPermission(KICK_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                if(soundEnabled()){
                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                return true;
            }
            else{
                if(args.length <= 1){
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
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
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    sendMessage(KICK_ALERT.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    if (event.isCancelled()) {
                        return false;
                    }
                }
            }
        }else if(sender instanceof ConsoleCommandSender){
            if(args.length <= 1){
               sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player> <reason>").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
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
                sendConsoleMessage(KICK_ALERT.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                if (event.isCancelled()) {
                    return false;
                }
            }
        }

        return false;
    }
}
