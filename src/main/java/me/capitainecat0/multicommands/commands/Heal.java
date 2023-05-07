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

public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
            if(sender instanceof Player){
                if(args.length == 0){
                    if(sender.hasPermission(HEAL_PERM_SELF.getPermission()) || sender.hasPermission(HEAL_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                        if(((Player) sender).getHealth() != 20){
                            ((Player) sender).setHealth(20);
                            if(soundEnabled()){
                                playSound(sender, Sound.ENTITY_GENERIC_DRINK, 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), HEAL_SELF.getMessage());
                        }else{
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), HEAL_ALREADY.getMessage());
                        }
                    }else{
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                        return true;
                    }
                }
                if(args.length == 1){
                    if(sender.hasPermission(HEAL_PERM_OTHER.getPermission()) || sender.hasPermission(HEAL_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if(target != null){
                            if(target.getHealth() != 20){
                                target.setHealth(20);
                                if(soundFeedHealEnabled()){
                                    playSound(target, Sound.ENTITY_GENERIC_DRINK, 1f, 1f);
                                }
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), HEAL_OTHER.getMessage());
                                getMsgSendConfig(sender, command.getName(), HEAL_OTHER_SENDER.getMessage().replace("{0}", target.getName()));
                            }else{
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(sender, command.getName(), HEAL_ALREADY_SENDER.getMessage().replace("{0}", target.getName()));
                            }
                        }else{
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                        }
                    }else{
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                        return true;
                    }
                }if(args.length == 1 && args[0].equalsIgnoreCase("all")){
                    if(sender.hasPermission(HEAL_PERM_OTHER.getPermission()) || sender.hasPermission(HEAL_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                        Player target = (Player) Bukkit.getOnlinePlayers();
                        if(target != null){
                            if(target.getHealth() != 20){
                                target.setHealth(20);
                                if(soundFeedHealEnabled()){
                                    playSound(target, Sound.ENTITY_GENERIC_DRINK, 1f, 1f);
                                }
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), HEAL_OTHER.getMessage());
                                getMsgSendConfig(sender, command.getName(), HEAL_OTHER_SENDER.getMessage().replace("{0}", target.getName()));
                            }else{
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(sender, command.getName(), HEAL_ALREADY_SENDER.getMessage().replace("{0}", target.getName()));
                            }
                        }else{
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
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
                sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("<command>", command.getName()));
            }
            if(args.length == 1){
                Player target = Bukkit.getPlayerExact(args[0]);
                if(target != null){
                    if(target.getHealth() != 20){
                        target.setHealth(20);
                        if(soundFeedHealEnabled()){
                            playSound(target, Sound.ENTITY_GENERIC_DRINK, 1f, 1f);
                        }
                        getMsgSendConfig(target, command.getName(), HEAL_OTHER.getMessage());
                        sendConsoleMessage(HEAL_OTHER_SENDER.getMessage().replace("{0}", target.getName()));
                    }else{
                        sendConsoleMessage(HEAL_ALREADY_SENDER.getMessage().replace("{0}", target.getName()));
                    }
                }else{
                    sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                }
            }
            if(args.length == 1 && args[0].equalsIgnoreCase("all")){
            Player target = (Player) Bukkit.getOnlinePlayers();
            if(target != null){
                if(target.getHealth() != 20){
                    target.setHealth(20);
                    if(soundFeedHealEnabled()){
                        playSound(target, Sound.ENTITY_GENERIC_DRINK, 1f, 1f);
                    }
                    getMsgSendConfig(target, command.getName(), HEAL_OTHER.getMessage());
                    sendConsoleMessage(HEAL_OTHER_SENDER.getMessage().replace("{0}", target.getName()));
                }else{
                    sendConsoleMessage(HEAL_ALREADY_SENDER.getMessage().replace("{0}", target.getName()));
                }
            }else{
                sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
            }
        }
        return false;
    }
}
