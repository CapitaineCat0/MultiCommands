package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class Title implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(!sender.hasPermission(Perms.TITLE_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            if(soundEnabled()){
                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            }
            getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
        }else{
            if(args.length == 0){
                if(soundEnabled()){
                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "<message>"));
            }else if(args.length > 1){
                Player target = Bukkit.getPlayerExact(args[0]);
                if(target != null){
                    StringBuilder bc = new StringBuilder();
                    for(String part : args) {
                        bc.append(part).append(" ");
                    }
                    sendMessage(sender, "&aVotre message à bien été envoyé à &e"+target.getName()+"&a!");
                    sendTitle(target, args[1], bc.toString().replace(args[0], "").replace(args[1], ""), Duration.ofSeconds(1),Duration.ofSeconds(3),Duration.ofSeconds(1));
                }else{
                    StringBuilder bc = new StringBuilder();
                    for(String part : args) {
                        bc.append(part).append(" ");
                    }
                    sendMessage(sender, "&aVotre message à été envoyé à tout le monde!");
                    sendTitle(args[0], bc.toString().replace(args[0], ""), Duration.ofSeconds(1),Duration.ofSeconds(3),Duration.ofSeconds(1));
                }
            }
        }
        return true;
    }
}
