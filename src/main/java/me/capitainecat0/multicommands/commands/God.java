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

public class God implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        hideActiveBossBar();
            if(sender instanceof Player){
                if(sender.hasPermission(GOD_PERM_SELF.getPermission()) || sender.hasPermission(GOD_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                    Player player = (Player) sender;
                    if(args.length == 0){
                        if(player.isInvulnerable()){
                            player.setInvulnerable(false);
                            player.setGlowing(false);
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), GOD_SELF_OFF.getMessage());
                        }else if(!player.isInvulnerable()){
                            player.setInvulnerable(true);
                            player.setGlowing(true);
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), GOD_SELF_ON.getMessage());
                        }
                    }
                } else{
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                    return true;
                }
                if(args.length == 1){
                    if(sender.hasPermission(GOD_PERM_OTHER.getPermission()) || sender.hasPermission(GOD_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if(target != null){
                            if(target.isInvulnerable()){
                                target.setInvulnerable(false);
                                target.setGlowing(false);
                                if(soundEnabled()){
                                    playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), GOD_OTHER_OFF.getMessage());
                                getMsgSendConfig(sender, command.getName(), GOD_OTHER_ADMIN_OFF.getMessage().replace("%p", target.getName()));
                            }else if(!target.isInvulnerable()){
                                target.setInvulnerable(true);
                                target.setGlowing(true);
                                if(soundEnabled()){
                                    playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), GOD_OTHER_ON.getMessage());
                                getMsgSendConfig(sender, command.getName(), GOD_OTHER_ADMIN_ON.getMessage().replace("%p", target.getName()));
                            }
                        }else{
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                        }
                    }else{
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                        return true;
                    }
                }
            }else if(sender instanceof ConsoleCommandSender){
                if(args.length == 0){
                    sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("%cmd%", command.getName()));
                }
                if(args.length == 1){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null){
                        if(target.isInvulnerable()){
                            target.setInvulnerable(false);
                            target.setGlowing(false);
                            if(soundEnabled()){
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(target, command.getName(), GOD_OTHER_OFF.getMessage());
                            sendConsoleMessage(GOD_OTHER_ADMIN_OFF.getMessage().replace("%p", target.getName()));
                        }else if(!target.isInvulnerable()){
                            target.setInvulnerable(true);
                            target.setGlowing(true);
                            if(soundEnabled()){
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), GOD_OTHER_ON.getMessage());
                            sendConsoleMessage(GOD_OTHER_ADMIN_ON.getMessage().replace("%p", target.getName()));
                        }
                    }else{
                        sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                    }
                }
            }
        return false;
    }
}
