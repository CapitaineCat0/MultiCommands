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
import static me.capitainecat0.multicommands.utils.Perms.ALERT_PERM;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;

public class Alert implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        hideActiveBossBar();
        if(sender instanceof Player){
            if(!sender.hasPermission(ALERT_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                if(soundEnabled()){
                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                return true;
            }
            else{
                if(args.length == 0){
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "<message>"));
                    return true;
                }else {
                    StringBuilder bc = new StringBuilder();
                    for(String part : args) {
                        bc.append(part).append(" ");
                    }
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(sender, command.getName(), ALERT_CMD.getMessage());
                    sendBroadcastMessage(ALERT_PREFIX.getMessage() + "&r " + bc);
                }
            }
        }else if(sender instanceof ConsoleCommandSender){
            sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("%cmd%", command.getName()));
        }

        return false;
    }
}
