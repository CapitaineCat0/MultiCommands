package me.capitainecat0.multicommands.commands;

import com.google.common.base.Joiner;
import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.Messenger.ADMINCHAT_PREFIX;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class Chat implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(!sender.hasPermission(ALL_CHAT_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
        }else{
            if(args.length < 1){
                getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "< admin | builder | clear | dev | modo | staff >"));
            }else if(args.length == 1){
                if(args[0].equalsIgnoreCase("admin")){
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "[admin] <message>"));
                }
                else if(args[0].equalsIgnoreCase("builder")){
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "[builder] <message>"));
                }
                else if(args[0].equalsIgnoreCase("dev")){
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "[dev] <message>"));
                }
                else if(args[0].equalsIgnoreCase("modo")){
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "[modo] <message>"));
                }
                else if(args[0].equalsIgnoreCase("staff")){
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "[staff] <message>"));
                }
                if(args[0].equalsIgnoreCase("toggle")){

                }else if(args[0].equalsIgnoreCase("clear")){
                    if(sender.hasPermission(CLEARCHAT_PERM.getPermission())){
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" &a&m+----------------------------------------+");
                        sendMessage(" ");
                        sendMessage("                   &6Le chat à été nettoyé");
                        sendMessage("                  &6par un &c&ladministrateur &6!");
                        sendMessage(" ");
                        sendMessage(" &a&m+----------------------------------------+");
                        sendMessage(" ");
                        sendMessage(" ");
                        sendMessage(" ");
                    }
                }
            }else if(args.length >= 2){
                if(args[0].equalsIgnoreCase("admin")){
                    String s = Joiner.on(" ").join(args).replace("admin", " ");
                    String format = ADMINCHAT.getMessage().replace("{0}", ADMINCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        if (player.hasPermission(ADMINCHAT_PERM.getPermission()) || player.hasPermission(ALL_CHAT_PERM.getPermission()) || player.hasPermission(ALL_PERMS.getPermission())) {
                            if(soundEnabled()){
                                playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            sendMessage(player, format);
                        } else {
                            if(soundEnabled()){
                                playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(player, command.getName(), CMD_NO_PERM.getMessage());
                        }
                    }
                    sendConsoleMessage(colored(format));
                }
                else if(args[0].equalsIgnoreCase("builder")){
                    String s = Joiner.on(" ").join(args).replace("builder", " ");
                    String format = BUILDERCHAT.getMessage().replace("{0}", BUILDERCHAT.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        if (player.hasPermission(BUILDERCHAT_PERM.getPermission()) || player.hasPermission(ALL_CHAT_PERM.getPermission()) || player.hasPermission(ALL_PERMS.getPermission())) {
                            if(soundEnabled()){
                                playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            sendMessage(player, format);
                        } else {
                            if(soundEnabled()){
                                playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(player, command.getName(), CMD_NO_PERM.getMessage());
                        }
                    }
                    sendConsoleMessage(colored(format));
                }
                else if(args[0].equalsIgnoreCase("dev")){
                    String s = Joiner.on(" ").join(args).replace("dev", " ");
                    String format = DEVCHAT.getMessage().replace("{0}", DEVCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        if (player.hasPermission(DEVCHAT_PERM.getPermission()) || player.hasPermission(ALL_CHAT_PERM.getPermission()) || player.hasPermission(ALL_PERMS.getPermission())) {
                            if(soundEnabled()){
                                playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            sendMessage(player, format);
                        } else {
                            if(soundEnabled()){
                                playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(player, command.getName(), CMD_NO_PERM.getMessage());
                        }
                    }
                    sendConsoleMessage(colored(format));
                }
                else if(args[0].equalsIgnoreCase("modo")){
                    String s = Joiner.on(" ").join(args).replace("modo", " ");
                    String format = MODOCHAT.getMessage().replace("{0}", MODOCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        if (player.hasPermission(MODOCHAT_PERM.getPermission()) || player.hasPermission(ALL_CHAT_PERM.getPermission()) || player.hasPermission(ALL_PERMS.getPermission())) {
                            if(soundEnabled()){
                                playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            sendMessage(player, format);
                        } else {
                            if(soundEnabled()){
                                playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(player, command.getName(), CMD_NO_PERM.getMessage());
                        }
                    }
                    sendConsoleMessage(colored(format));
                }
                else if(args[0].equalsIgnoreCase("staff")){
                    String s = Joiner.on(" ").join(args).replace("staff", " ");
                    String format = STAFFCHAT.getMessage().replace("{0}", STAFFCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        if (player.hasPermission(STAFFCHAT_PERM.getPermission()) || player.hasPermission(ALL_CHAT_PERM.getPermission()) || player.hasPermission(ALL_PERMS.getPermission())) {
                            if(soundEnabled()){
                                playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            sendMessage(player, format);
                        } else {
                            if(soundEnabled()){
                                playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(player, command.getName(), CMD_NO_PERM.getMessage());
                        }
                    }
                    sendConsoleMessage(colored(format));
                }
            }
        }
        return false;
    }
}
