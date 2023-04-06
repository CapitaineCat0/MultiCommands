package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
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
import static me.capitainecat0.multicommands.utils.Perms.BOSSBAR_PERM;

public class BossBar implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(sender instanceof Player){
            if(!sender.hasPermission(BOSSBAR_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                if(soundEnabled()){
                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            }else{
                if(args.length == 0){
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<message>"));
                }else if(args.length > 1){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null){
                        StringBuilder bc = new StringBuilder();
                        for(String part : args) {
                            bc.append(part).append(" ");
                        }
                        sendMessage(sender, ACTIONBAR_SENT_TO_OTHER.getMessage().replace("{0}", target.getName()));
                        sendBossBar(target,1, net.kyori.adventure.bossbar.BossBar.Color.GREEN, net.kyori.adventure.bossbar.BossBar.Overlay.NOTCHED_20, MultiCommands.colored(bc.toString().replace(args[0], "")));
                    }else{
                        StringBuilder bc = new StringBuilder();
                        for(String part : args) {
                            bc.append(part).append(" ");
                        }
                        sendMessage(sender, ACTIONBAR_SENT_TO_ALL.getMessage());
                        sendBossBar(1, net.kyori.adventure.bossbar.BossBar.Color.GREEN, net.kyori.adventure.bossbar.BossBar.Overlay.NOTCHED_20, MultiCommands.colored(bc.toString().replace(args[0], "")));
                    }

                }
            }
        }else if(sender instanceof ConsoleCommandSender){
            sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
        }
        return true;
    }
}
