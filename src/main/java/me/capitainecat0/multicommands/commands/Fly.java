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

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        hideActiveBossBar();
            if(sender instanceof Player) {
                if(args.length == 0){
                    if(sender.hasPermission(FLY_PERM_SELF.getPermission()) || sender.hasPermission(FLY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                        if(!((Player)sender).getAllowFlight()){
                            ((Player)sender).setAllowFlight(true);
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), FLY_TOGGLE_ON.getMessage());
                        }else if(((Player)sender).getAllowFlight()){
                            ((Player)sender).setAllowFlight(false);
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), FLY_TOGGLE_OFF.getMessage());
                        }
                    }else{
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                        return true;
                    }
                }else if(args.length == 1){
                    if(sender.hasPermission(FLY_PERM_OTHER.getPermission()) || sender.hasPermission(FLY_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            if(!target.getAllowFlight()){
                                target.setAllowFlight(true);
                                if(soundEnabled()){
                                    playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), FLY_TOGGLE_ON_BY_ADMIN.getMessage());
                                getMsgSendConfig(sender, command.getName(), FLY_TOGGLE_ON_SENDER.getMessage().replace("%player%", target.getName()));
                            }else if(target.getAllowFlight()){
                                target.setAllowFlight(false);
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), FLY_TOGGLE_OFF_BY_ADMIN.getMessage());
                                getMsgSendConfig(sender, command.getName(), FLY_TOGGLE_OFF_SENDER.getMessage().replace("%player%", target.getName()));
                            }
                        }else{
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("%player%", args[0]));
                        }
                    }else{
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM_TO_OTHER.getMessage());
                    }
                }
            }else if(sender instanceof ConsoleCommandSender) {
                if (args.length == 0) {
                    sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("%cmd%", command.getName()));
                }
                if (args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        if (!target.getAllowFlight()) {
                            target.setAllowFlight(true);
                            if(soundEnabled()){
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(target, command.getName(), FLY_TOGGLE_ON_BY_ADMIN.getMessage());
                            sendConsoleMessage(FLY_TOGGLE_ON_SENDER.getMessage().replace("%player%", target.getName()));
                        } else if (target.getAllowFlight()) {
                            target.setAllowFlight(false);
                            if(soundEnabled()){
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), FLY_TOGGLE_OFF_BY_ADMIN.getMessage());
                            sendConsoleMessage(FLY_TOGGLE_OFF_SENDER.getMessage().replace("%player%", target.getName()));
                        }
                    } else {
                        sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("%player%", args[0]));
                    }
                }
            }
        return false;
    }
}
