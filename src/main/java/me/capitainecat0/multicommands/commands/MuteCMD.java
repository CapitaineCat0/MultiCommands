package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.Mute;
import me.capitainecat0.multicommands.utils.MuteHandler;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
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
import static me.capitainecat0.multicommands.utils.permissions.Perms.*;

public class MuteCMD implements CommandExecutor {

    /**
     *
     * The Mute command can toggle mute for targeted player
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        Player target = Bukkit.getPlayerExact(args[0]);
            if(sender instanceof Player){
                try{
                    if(args.length == 0){
                        if(perms.hasPermission((Player) sender, MUTE_PERM.getPermission())
                                || perms.hasPermission((Player) sender, ALL_PERMS.getPermission())){
                            new Mute((Player) sender, target, args).execute();
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
                    StringBuilder bc = new StringBuilder();
                    for(String part : args) {
                        bc.append(part).append(" ");
                    }
                    assert target != null;
                    String reason = bc.toString().replace(target.getName(), "");
                    MuteHandler handler = MuteHandler.getInstance();
                    if(args.length < 2){
                        if(!handler.isMuted(target)){
                            MuteHandler.toggleMute(target);
                            playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            sendConsoleMessage(MUTE_ENABLED_ADMIN.getMessage().replace("{0}", target.getName()));
                            getMsgSendConfig(target, command.getName(), MUTE_ENABLED.getMessage());
                        }else if(handler.isMuted(target)){
                            MuteHandler.toggleMute(target);
                            playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            sendConsoleMessage(MUTE_DISABLED_ADMIN.getMessage().replace("{0}", target.getName()));
                            getMsgSendConfig(target, command.getName(), MUTE_DISABLED.getMessage());
                        }
                    }else {
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
