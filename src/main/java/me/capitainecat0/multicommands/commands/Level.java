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
import static me.capitainecat0.multicommands.utils.MessengerUtils.playSound;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class Level implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(sender instanceof Player){
            if(args.length <= 1){
                if(soundEnabled()){
                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()).replace("<command>", command.getName()).replace("{0}", "<add | set | remove> <value> [player]"));
            }else if(args.length == 2){
                int level = Integer.parseInt(args[1]);
                if(args[0].equalsIgnoreCase("add")){
                    if(!sender.hasPermission(LEVEL_ADD_PERM.getPermission()) || !sender.hasPermission(LEVEL_ALL_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }else{
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), LEVEL_ADD.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()).replace("{0}", String.valueOf(level)));
                        ((Player) sender).setLevel(((Player)sender).getLevel() + level);
                    }
                }else if(args[0].equalsIgnoreCase("set")){
                    if(!sender.hasPermission(LEVEL_SET_PERM.getPermission()) || !sender.hasPermission(LEVEL_ALL_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }else{
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), LEVEL_SET.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()).replace("{0}", String.valueOf(level)));
                        ((Player) sender).setLevel(level);
                    }
                } else if(args[0].equalsIgnoreCase("remove")) {
                    if(!sender.hasPermission(LEVEL_REMOVE_PERM.getPermission()) || !sender.hasPermission(LEVEL_ALL_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }else{
                        if(level <= ((Player)sender).getLevel()){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), LEVEL_REMOVE.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()).replace("{0}", String.valueOf(level)));
                            ((Player) sender).setLevel(((Player)sender).getLevel() - level);
                        }else{
                            ((Player) sender).setLevel(0);
                        }
                    }
                }
            }else if(args.length == 3){
                Player target = Bukkit.getPlayerExact(args[2]);
                int level = Integer.parseInt(args[1]);
                if(args[0].equalsIgnoreCase("add")){
                    if(!sender.hasPermission(LEVEL_ADD_PERM.getPermission()) || !sender.hasPermission(LEVEL_ALL_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }else{
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), LEVEL_ADD_OTHER.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()).replace("{0}", String.valueOf(level)).replace("{1}", target.getName()));
                        target.setLevel(target.getLevel() + level);
                    }
                }else if(args[0].equalsIgnoreCase("set")){
                    if(!sender.hasPermission(LEVEL_SET_PERM.getPermission()) || !sender.hasPermission(LEVEL_ALL_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }else{
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), LEVEL_SET_OTHER.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()).replace("{0}", String.valueOf(level)).replace("{1}", target.getName()));
                        target.setLevel(level);
                    }
                } else if(args[0].equalsIgnoreCase("remove")) {
                    if(!sender.hasPermission(LEVEL_REMOVE_PERM.getPermission()) || !sender.hasPermission(LEVEL_ALL_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }else{
                        if(level <= target.getLevel()){
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), LEVEL_REMOVE_OTHER.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()).replace("{0}", String.valueOf(level)).replace("{1}", target.getName()));
                            target.setLevel(target.getLevel() - level);
                        }else{
                            target.setLevel(0);
                        }
                    }
                }
            }

        }else if(sender instanceof ConsoleCommandSender){
            if(args.length >= 2){
                sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("{0}", command.getName()).replace("{1}", "<add | set | remove> <value> <player>"));
            }else if(args.length == 3){
                Player target = Bukkit.getPlayerExact(args[2]);
                int level = Integer.parseInt(args[1]);
                if(args[0].equalsIgnoreCase("add")){
                    if(soundEnabled()){
                        playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    target.setLevel(target.getLevel() + level);
                }else if(args[0].equalsIgnoreCase("set")){
                    if(soundEnabled()){
                        playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    target.setLevel(level);
                }else if(args[0].equalsIgnoreCase("remove")) {
                    if(soundEnabled()){
                        playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    target.setLevel(target.getLevel() - level);
                }
            }
        }
        return false;
    }
}
