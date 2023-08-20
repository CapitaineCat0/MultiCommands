package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerKickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static me.capitainecat0.multicommands.MultiCommands.getInstance;
import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class KickAll implements CommandExecutor {

    /**
     *
     * The KickAll command can kick all players connected except operators
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(sender instanceof Player){
            try{
                if(!sender.hasPermission(KICKALL_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                    return true;
                }
                else{
                    if(args.length <= 1){
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player> <reason>"));
                    }
                    else if(args.length == 2){
                        StringBuilder bc = new StringBuilder();
                        for(String part : args) {
                            bc.append(part).append(" ");
                        }
                        String kickReason = bc.toString().replace(args[0], "");
                        for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                            if (!(sender instanceof Player) || !onlinePlayer.getName().equalsIgnoreCase(sender.getName())) {
                                if (!onlinePlayer.hasPermission(KICKALL_EXEMPT_PERM.getPermission())) {
                                    onlinePlayer.kickPlayer(kickReason);
                                }
                            }
                        }
                    }
                }
            }catch(Exception e){
                sendCommandExceptionMessage(e, command.getName());
            }
        }else if(sender instanceof ConsoleCommandSender){
            try{
                if(args.length <= 1){
                    sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player> <reason>"));
                }
                else if(args.length == 2){
                    StringBuilder bc = new StringBuilder();
                    for(String part : args) {
                        bc.append(part).append(" ");
                    }
                    String kickReason = bc.toString().replace(args[0], "");
                    for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        if (!onlinePlayer.hasPermission(KICKALL_EXEMPT_PERM.getPermission())) {
                            onlinePlayer.kickPlayer(kickReason);}
                    }
                }
            }catch(Exception e){
                sendCommandExceptionMessage(e, command.getName());
            }
        }
        return false;
    }
}
