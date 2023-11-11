package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.data.FreezeData;
import me.capitainecat0.multicommands.utils.FreezeHandler;
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
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.FREEZE_PERM;

public class Freeze implements CommandExecutor {

    /**
     *
     * The Freeze command can freeze player targeted
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        hideActiveBossBar();
        try {
            if(!sender.hasPermission(FREEZE_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                return true;
            }
            else{
                if(sender instanceof Player){
                    if(args.length == 0){
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("{0}", "<player>"));
                        return true;
                    }else if(args.length == 1) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if(target != null){
                            final FreezeData data = new FreezeData(target);
                            final boolean isFrozen = FreezeData.isFrozen();
                            if (isFrozen && FreezeHandler.getInstance().isFreeze(target)) {
                                FreezeHandler.getInstance().toggleFreeze(target);
                                data.setFrozen(target,false);
                                playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                getMsgSendConfig(target, command.getName(), FREEZE_TOGGLE_OFF.getMessage());
                                getMsgSendConfig(sender, command.getName(), FREEZE_TOGGLE_OFF_ADMIN.getMessage().replace("{0}", target.getName()));
                            } else {
                                FreezeHandler.getInstance().toggleFreeze(target);
                                data.setFrozen(target,true);
                                playSoundIfEnabled(target, Sound.BLOCK_ANVIL_PLACE, 1f, 1f);
                                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                getMsgSendConfig(target, command.getName(), FREEZE_TOGGLE_ON.getMessage());
                                getMsgSendConfig(sender, command.getName(), FREEZE_TOGGLE_ON_ADMIN.getMessage().replace("{0}", target.getName()));
                            }
                        }else{
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                        }
                    }
                }else if(sender instanceof ConsoleCommandSender){
                    if(args.length == 0){
                        sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player>"));
                        return true;
                    }else if(args.length == 1) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if(target != null){
                            final FreezeData data = new FreezeData(target);
                            final boolean isFrozen = FreezeData.isFrozen();
                            if (isFrozen && FreezeHandler.getInstance().isFreeze(target)) {
                                FreezeHandler.getInstance().toggleFreeze(target);
                                data.setFrozen(target,false);
                                getMsgSendConfig(target, command.getName(), FREEZE_TOGGLE_OFF.getMessage());
                                sendConsoleMessage(FREEZE_TOGGLE_OFF_ADMIN.getMessage().replace("{0}", target.getName()));
                            } else {
                                FreezeHandler.getInstance().toggleFreeze(target);
                                data.setFrozen(target,true);
                                getMsgSendConfig(target, command.getName(), FREEZE_TOGGLE_ON.getMessage());
                                sendConsoleMessage(FREEZE_TOGGLE_ON_ADMIN.getMessage().replace("{0}", target.getName()));
                            }
                        }else{
                            sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                        }
                    }
                }
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return false;
    }
}