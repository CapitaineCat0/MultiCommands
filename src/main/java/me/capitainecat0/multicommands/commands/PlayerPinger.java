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
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.PLAYERPINGER_PERM;

public class PlayerPinger implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(!sender.hasPermission(PLAYERPINGER_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
            if(soundEnabled()){
                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            }
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            return true;
        }
        else{
            if(args.length == 0){
                if(sender instanceof Player){
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    int ping = 0;//((Player) sender).getPing();
                    if(ping < 50){
                        sendMessage(sender, PING_SELF_MSG.getMessage().replace("{0}", colored("&a" + ping + " ms")).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }
                    if(ping > 50){
                        sendMessage(sender, PING_SELF_MSG.getMessage().replace("{0}", colored("&e" + ping + " ms")).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }
                    if(ping > 300){
                        sendMessage(sender, PING_SELF_MSG.getMessage().replace("{0}", colored("&c" + ping + " ms")).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }
                }else if(sender instanceof ConsoleCommandSender){
                    sendConsoleMessage(NO_CONSOLE_COMMAND_WITHOUT_ARGS.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                }
            }else if(args.length == 1) {
                Player target = Bukkit.getPlayerExact(args[0]);
                int ping = 0;
                if (sender instanceof Player) {
                    if (soundEnabled()) {
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    assert target != null;
                    ping = 0;//target.getPing();
                    if (ping < 50) {
                        sendMessage(sender, PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", colored("&a" + ping + " ms")).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }
                    if (ping > 50) {
                        sendMessage(sender, PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", colored("&e" + ping + " ms")).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }
                    if (ping > 300) {
                        sendMessage(sender, PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", colored("&c" + ping + " ms")).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }
                } else if (sender instanceof ConsoleCommandSender) {
                    if (ping < 50) {
                        sendConsoleMessage(PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", colored("&a" + ping + " ms")).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }
                    if (ping > 50) {
                        sendConsoleMessage(PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", colored("&e" + ping + " ms")).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }
                    if (ping > 300) {
                        sendConsoleMessage(PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", colored("&c" + ping + " ms")).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }
                }

            }
        }
        return false;
    }
}
