package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.MuteHandler;
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

public class Mute implements CommandExecutor {

    /**
     *
     * The Mute command can toggle mute for targeted player
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
            if(sender instanceof Player){
                try{
                    if(args.length == 0){
                        if(sender.hasPermission(MUTE_PERM.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission())){
                            Player target = Bukkit.getPlayerExact(args[0]);
                            StringBuilder bc = new StringBuilder();
                            String reason = bc.toString().replace(target.getName(), "");
                            MuteHandler handler = MuteHandler.getInstance();
                            for(String part : args) {
                                bc.append(part).append(" ");
                            }
                            if(args.length < 2){
                                if(args.length == 0){
                                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player> [reason]"));
                                }else{
                                    if(!handler.isMuted(target)){
                                        handler.toggleMute(target);
                                        playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                        getMsgSendConfig(sender, command.getName(), MUTE_ENABLED_ADMIN.getMessage().replace("{0}", target.getName()));
                                        getMsgSendConfig(target, command.getName(), MUTE_ENABLED.getMessage());
                                    }else if(handler.isMuted(target)){
                                        MuteHandler.getMuted().remove(target);
                                        handler.toggleMute(target);
                                        playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                        getMsgSendConfig(sender, command.getName(), MUTE_DISABLED_ADMIN.getMessage().replace("{0}", target.getName()));
                                        getMsgSendConfig(target, command.getName(), MUTE_DISABLED.getMessage());
                                    }
                                }
                            }else if(args.length >= 2){
                                if(!handler.isMuted(target)){
                                    handler.toggleMute(target, reason);
                                    playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                    getMsgSendConfig(sender, command.getName(), MUTE_ENABLED_REASON_ADMIN.getMessage().replace("{0}", target.getName()).replace("{1}", reason));
                                    getMsgSendConfig(target, command.getName(), MUTE_ENABLED_REASON.getMessage().replace("{0}", reason));
                                }else if(handler.isMuted(target)){
                                    handler.toggleMute(target, reason);
                                    playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                                    getMsgSendConfig(sender, command.getName(), MUTE_DISABLED_ADMIN.getMessage().replace("{0}", target.getName()));
                                    getMsgSendConfig(target, command.getName(), MUTE_DISABLED.getMessage());
                                }
                            }
                        }else{
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                            return true;
                        }
                    }
                }catch(Exception e){
                    sendCommandExceptionMessage(e, command.getName());
                    sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                    sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                }
            }else if(sender instanceof ConsoleCommandSender){
                try{
                    Player target = Bukkit.getPlayerExact(args[0]);
                    StringBuilder bc = new StringBuilder();
                    for(String part : args) {
                        bc.append(part).append(" ");
                    }
                    assert target != null;
                    String reason = bc.toString().replace(target.getName(), "");
                    MuteHandler handler = MuteHandler.getInstance();
                    if(args.length < 2){
                        if(!handler.isMuted(target)){
                            handler.toggleMute(target);
                            playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            sendConsoleMessage(MUTE_ENABLED_ADMIN.getMessage().replace("{0}", target.getName()));
                            getMsgSendConfig(target, command.getName(), MUTE_ENABLED.getMessage());
                        }else if(handler.isMuted(target)){
                            handler.toggleMute(target);
                            playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            sendConsoleMessage(MUTE_DISABLED_ADMIN.getMessage().replace("{0}", target.getName()));
                            getMsgSendConfig(target, command.getName(), MUTE_DISABLED.getMessage());
                        }
                    }else if(args.length >= 2){
                        if(!handler.isMuted(target)){
                            handler.toggleMute(target, reason);
                            playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            sendConsoleMessage(MUTE_ENABLED_REASON_ADMIN.getMessage().replace("{0}", target.getName()).replace("{1}", reason));
                            getMsgSendConfig(target, command.getName(), MUTE_ENABLED_REASON.getMessage().replace("{0}", reason));
                        }else if(handler.isMuted(target)){
                            handler.toggleMute(target, reason);
                            playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            sendConsoleMessage(MUTE_DISABLED_ADMIN.getMessage().replace("{0}", target.getName()));
                            getMsgSendConfig(target, command.getName(), MUTE_DISABLED.getMessage());
                        }
                    }
                }catch(Exception e){
                    sendCommandExceptionMessage(e, command.getName());
                    sendMessage(sender, CMD_ERROR.getMessage().replace("{0}", command.getName()).replace("{e}", e.getMessage()));}
            }
        return false;
    }
}
