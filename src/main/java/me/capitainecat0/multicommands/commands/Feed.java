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

public class Feed implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
            if(sender instanceof Player){
                if(args.length == 0){
                    if(sender.hasPermission(FEED_PERM_SELF.getPermission()) || sender.hasPermission(FEED_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                        if(((Player) sender).getFoodLevel() != 20){
                            ((Player) sender).setFoodLevel(20);
                            if(soundFeedHealEnabled()){
                                playSound(sender, Sound.ENTITY_GENERIC_EAT, 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), FEED_SELF.getMessage());
                        }else{
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), FEED_ALREADY.getMessage());
                        }
                    }else{
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                        return true;
                    }
                }if(args.length == 1){
                    if(sender.hasPermission(FEED_PERM_OTHER.getPermission()) || sender.hasPermission(FEED_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if(target != null){
                            if(target.getFoodLevel() != 20){
                                target.setFoodLevel(20);
                                if(soundFeedHealEnabled()){
                                    playSound(target, Sound.ENTITY_GENERIC_EAT, 1f, 1f);
                                }
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(sender, command.getName(), FEED_OTHER_SENDER.getMessage().replace("%p", target.getName()));
                                getMsgSendConfig(target, command.getName(), FEED_OTHER.getMessage());
                            }else{
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(sender, command.getName(), FEED_ALREADY_SENDER.getMessage());
                            }
                        }else{
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                        }
                    }
                }
            }
            else if(sender instanceof ConsoleCommandSender){
                sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("%cmd%", command.getName()));
            }
            if(args.length == 1){
                Player target = Bukkit.getPlayerExact(args[0]);
                if(target != null){
                    if(target.getFoodLevel() != 20){
                        target.setFoodLevel(20);
                        if(soundFeedHealEnabled()){
                            playSound(target, Sound.ENTITY_GENERIC_EAT, 1f, 1f);
                        }
                        sendConsoleMessage(FEED_OTHER_SENDER.getMessage().replace("%p", target.getName()));
                        getMsgSendConfig(target, command.getName(), FEED_OTHER.getMessage());
                    }else{
                        sendConsoleMessage(FEED_ALREADY_SENDER.getMessage().replace("%p", target.getName()));
                    }
                }else{
                    sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                }
            }
        return false;
    }
}
