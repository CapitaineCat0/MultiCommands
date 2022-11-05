package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.ACTIONBAR_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;

public class ActionBar implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(!sender.hasPermission(ACTIONBAR_PERMS.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
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
                    sendActionBar(target, bc.toString().replace(args[0], ""));
                }else{
                    StringBuilder bc = new StringBuilder();
                    for(String part : args) {
                        bc.append(part).append(" ");
                    }
                    sendMessage(sender, "&aVotre message à été envoyé à tout le monde!");
                    sendActionBar(bc.toString().replace(args[0], ""));
                }
            }
        }
        return true;
    }
}
