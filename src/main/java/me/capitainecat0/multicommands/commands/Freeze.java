package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.data.BannedData;
import me.capitainecat0.multicommands.data.FreezeData;
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
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.FREEZE_PERM;

public class Freeze implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        hideActiveBossBar();
        if(!sender.hasPermission(FREEZE_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
            if(soundEnabled()){
                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            }
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            return true;
        }
        else{
            if(sender instanceof Player){
                if(args.length == 0){
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "<joueur>"));
                    return true;
                }else if(args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null){
                        final FreezeData data = new FreezeData(target);
                        final boolean isFrozen = data.isFrozen();
                        if (isFrozen) {
                            data.setFrozen(false);
                            FreezeData.save();
                            if(soundEnabled()){
                                playSound(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(target, command.getName(), FREEZE_TOGGLE_OFF.getMessage());
                            getMsgSendConfig(sender, command.getName(), FREEZE_TOGGLE_OFF_ADMIN.getMessage().replace("%player%", target.getName()));
                        } else {
                            data.setFrozen(true);
                            FreezeData.save();
                            if(soundEnabled()){
                                playSound(target, Sound.BLOCK_ANVIL_PLACE, 1f, 1f);
                                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            }
                            getMsgSendConfig(target, command.getName(), FREEZE_TOGGLE_ON.getMessage());
                            getMsgSendConfig(sender, command.getName(), FREEZE_TOGGLE_ON_ADMIN.getMessage().replace("%player%", target.getName()));
                        }
                    }else{
                        if(soundEnabled()){
                            playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("%player%", args[0]));
                    }
                }
            }else if(sender instanceof ConsoleCommandSender){
                if(args.length == 0){
                    sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "<joueur>"));
                    return true;
                }else if(args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null){
                        final FreezeData data = new FreezeData(target);
                        final boolean isFrozen = data.isFrozen();
                        if (isFrozen) {
                            data.setFrozen(false);
                            FreezeData.save();
                            getMsgSendConfig(target, command.getName(), FREEZE_TOGGLE_OFF.getMessage());
                            sendConsoleMessage(FREEZE_TOGGLE_OFF_ADMIN.getMessage().replace("%player%", target.getName()));
                        } else {
                            data.setFrozen(true);
                            FreezeData.save();
                            getMsgSendConfig(target, command.getName(), FREEZE_TOGGLE_ON.getMessage());
                            sendConsoleMessage(FREEZE_TOGGLE_ON_ADMIN.getMessage().replace("%player%", target.getName()));
                        }
                    }else{
                        sendConsoleMessage(NOT_A_PLAYER.getMessage().replace("%player%", args[0]));
                    }
                }
            }

        }
        return false;
    }
}