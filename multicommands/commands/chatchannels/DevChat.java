package me.capitainecat0.multicommands.commands.chatchannels;

import com.google.common.base.Joiner;
import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.playSound;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class DevChat implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if (args.length >= 1) {
            String s = Joiner.on(" ").join(args);
            String format = DEVCHAT.getMessage().replace("%p", sender.getName()).replace("%msg%", s);

            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                if (player.hasPermission(DEVCHAT_PERM.getPermission()) || player.hasPermission(ALL_CHAT_PERM.getPermission()) || player.hasPermission(ALL_PERMS.getPermission())) {
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    sendMessage(player, format);
                } else {
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, command.getName(), CMD_NO_PERM.getMessage());
                }
            }
            Bukkit.getServer().getConsoleSender().sendMessage(MultiCommands.colored(format));
        } else{
            getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "<message>"));
        }
        return false;
    }
    @EventHandler
    public boolean onChat(PlayerChatEvent event){
        hideActiveBossBar();
        if(event.getMessage().startsWith("%")){
            if (event.getMessage().length() >= 1) {
                String s = Joiner.on(" ").join(Collections.singleton(event.getMessage()));
                String format = DEVCHAT.getMessage().replace("%p", event.getPlayer().getName()).replace("%msg%", s).replace("%","");

                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    if (player.hasPermission(DEVCHAT_PERM.getPermission())) {
                        if(soundEnabled()){
                            playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        }
                        sendMessage(player, format);
                    } else {
                        if(soundEnabled()){
                            playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(player, "DevChat", CMD_NO_PERM.getMessage());
                    }
                }
                Bukkit.getServer().getConsoleSender().sendMessage(MultiCommands.colored(format));
            } else{
                getMsgSendConfig(event.getPlayer(), "DevChat", CMD_NO_ARGS.getMessage().replace("%cmd%", "DevChat"));
            }
        }return false;
    }
}

