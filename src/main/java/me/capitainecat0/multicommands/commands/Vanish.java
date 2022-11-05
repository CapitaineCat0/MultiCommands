package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.VanishHandler;
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

public class Vanish implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
            if(sender instanceof Player){
                if(args.length == 0){
                    if(sender.hasPermission(VANISH_PERM_SELF.getPermission()) || sender.hasPermission(VANISH_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                        Player player = (Player) sender;
                        VanishHandler handler = VanishHandler.getInstance();
                        if(!handler.isVanished(player)){
                            handler.toggleVanish(player);
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), VANISH_TOGGLE_SELF.getMessage().replace("%status%", "§a§lactivé"));
                        }else if(handler.isVanished(player)){
                            handler.toggleVanish(player);
                            if(soundEnabled()){
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(sender, command.getName(), VANISH_TOGGLE_SELF.getMessage().replace("%status%", "§c§ldésactivé"));
                        }
                    }else{
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                        return true;
                    }
                }else if (args.length == 1){
                    if(sender.hasPermission(VANISH_PERM_OTHER.getPermission()) || sender.hasPermission(VANISH_PERM_ALL.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                        Player target = Bukkit.getPlayerExact(args[0]);
                        VanishHandler handler = VanishHandler.getInstance();
                        if(target != null){
                            if(!handler.isVanished(target)){
                                handler.toggleVanish(target);
                                if(soundEnabled()){
                                    playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), VANISH_TOGGLE_OTHER.getMessage().replace("%status%", "§a§lactiver"));
                                getMsgSendConfig(sender, command.getName(), VANISH_TOGGLE_ADMIN.getMessage().replace("%status%", "§a§lactivé").replace("%p", target.getName()));
                            }else if(handler.isVanished(target)){
                                handler.toggleVanish(target);
                                if(soundEnabled()){
                                    playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                if(soundEnabled()){
                                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                }
                                getMsgSendConfig(target, command.getName(), VANISH_TOGGLE_OTHER.getMessage().replace("%status%", "§a§ldésactiver"));
                                getMsgSendConfig(sender, command.getName(), VANISH_TOGGLE_ADMIN.getMessage().replace("%status%", "§c§ldésactivé").replace("%p", target.getName()));
                            }
                        }else{
                            getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                        }
                    }
                }

            }else if(sender instanceof ConsoleCommandSender){
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("%cmd%", command.getName()));
            }
        return false;
    }
}
