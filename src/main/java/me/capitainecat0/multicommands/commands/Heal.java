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

    /**
     *
     * The Heal command can heal player
     * <br>If args isn't null, it will heal targeted player
     * <br>If args is null, it will heal command sender
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
            if(sender instanceof Player){
                try{
                    if(args.length == 0){
                        if(MultiCommands.getPermissions().has(sender, HEAL_PERM_SELF.getPermission()) || MultiCommands.getPermissions().has(sender, HEAL_PERM_ALL.getPermission()) || MultiCommands.getPermissions().has(sender, ALL_PERMS.getPermission())){
                            if(((Player) sender).getHealth() != 20){
                                ((Player) sender).setHealth(20);
                                playSoundIfEnabled(sender, Sound.ENTITY_GENERIC_DRINK, 1f, 1f);
                                getMsgSendConfig(sender, command.getName(), HEAL_SELF.getMessage());
                            }else{
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                getMsgSendConfig(sender, command.getName(), HEAL_ALREADY.getMessage());
                            }
                        }else{
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                            return true;
                        }
                    }
                }catch (Exception e){
                    sendCommandExceptionMessage(e, command.getName());
                    sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                }
                if(args.length == 1){
                    try{
                        if(MultiCommands.getPermissions().has(sender, HEAL_PERM_OTHER.getPermission()) || MultiCommands.getPermissions().has(sender, HEAL_PERM_ALL.getPermission()) || MultiCommands.getPermissions().has(sender, ALL_PERMS.getPermission())){
                            Player target = Bukkit.getPlayerExact(args[0]);
                            if(target != null){
                                if(target.getHealth() != 20){
                                    target.setHealth(20);
                                    playSoundIfEnabled(target, Sound.ENTITY_GENERIC_DRINK, 1f, 1f);
                                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    getMsgSendConfig(target, command.getName(), HEAL_OTHER.getMessage());
                                    getMsgSendConfig(sender, command.getName(), HEAL_OTHER_SENDER.getMessage().replace("{0}", target.getName()));
                                }else{
                                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                    getMsgSendConfig(sender, command.getName(), HEAL_ALREADY_SENDER.getMessage().replace("{0}", target.getName()));
                                }
                            }else{
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                            }
                        }else{
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                            return true;
                        }
                    }catch (Exception e){
                        sendCommandExceptionMessage(e, command.getName()+" "+args[0]);
                        sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                    }
                }if(args.length == 1 && args[0].equalsIgnoreCase("all")){
                    try{
                        if(MultiCommands.getPermissions().has(sender, HEAL_PERM_OTHER.getPermission()) || MultiCommands.getPermissions().has(sender, HEAL_PERM_ALL.getPermission()) || MultiCommands.getPermissions().has(sender, ALL_PERMS.getPermission())){
                            Player target = (Player) Bukkit.getOnlinePlayers();
                            if(target != null){
                                if(target.getHealth() != 20){
                                    target.setHealth(20);
                                    playSoundIfEnabled(target, Sound.ENTITY_GENERIC_DRINK, 1f, 1f);
                                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    getMsgSendConfig(target, command.getName(), HEAL_OTHER.getMessage());
                                    getMsgSendConfig(sender, command.getName(), HEAL_OTHER_SENDER.getMessage().replace("{0}", target.getName()));
                                }else{
                                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                    getMsgSendConfig(sender, command.getName(), HEAL_ALREADY_SENDER.getMessage().replace("{0}", target.getName()));
                                }
                            }else{
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                            }
                        }else{
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                            return true;
                        }
                    }catch (Exception e){
                        sendCommandExceptionMessage(e, command.getName());
                        sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                    }
                }
            }else if(sender instanceof ConsoleCommandSender){
                sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("<command>", command.getName()));
            }else{
                if(args.length == 1){
                    try{
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if(target != null){
                            if(target.getHealth() != 20){
                                target.setHealth(20);
                                playSoundIfEnabled(target, Sound.ENTITY_GENERIC_DRINK, 1f, 1f);
                                getMsgSendConfig(target, command.getName(), HEAL_OTHER.getMessage());
                                sendConsoleMessage(HEAL_OTHER_SENDER.getMessage().replace("{0}", target.getName()));
                            }else{
                                sendConsoleMessage(HEAL_ALREADY_SENDER.getMessage().replace("{0}", target.getName()));
                            }
                        }else{
                            sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                        }
                    }catch (Exception e){
                        sendCommandExceptionMessage(e, command.getName());
                        sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                    }
                }
                if(args.length == 1 && args[0].equalsIgnoreCase("all")){
                    try{
                        Player target = (Player) Bukkit.getOnlinePlayers();
                        target.setHealth(20);
                        playSoundIfEnabled(target, Sound.ENTITY_GENERIC_DRINK, 1f, 1f);
                        getMsgSendConfig(target, command.getName(), HEAL_OTHER.getMessage());
                        sendConsoleMessage(HEAL_OTHER_SENDER.getMessage().replace("{0}", "@everyone"));
                    }catch (Exception e){
                        sendCommandExceptionMessage(e, command.getName());
                        sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                    }
                }
            }
        return false;
    }
}
