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
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class ModoChat implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if (args.length >= 1) {
            String s = Joiner.on(" ").join(args);
            String format = MODOCHAT.getMessage().replace("{0}", MODOCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);

            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                if (player.hasPermission(MODOCHAT_PERM.getPermission()) || player.hasPermission(ALL_CHAT_PERM.getPermission()) || player.hasPermission(ALL_PERMS.getPermission())) {
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    sendMessage(player, format);
                } else {
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, command.getName(), CMD_NO_PERM.getMessage());
                }
            }
            Bukkit.getServer().getConsoleSender().sendMessage(colored(format));
        } else{
            getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<message>"));
        }
        return false;
    }
    @EventHandler
    public boolean onChat(AsyncPlayerChatEvent event){
        hideActiveBossBar();
        if(event.getMessage().startsWith(MODOCHAT_PREFIX.getMessage())){
            if (event.getMessage().length() >= 1) {
                String s = Joiner.on(" ").join(Collections.singleton(event.getMessage()));
                String format = MODOCHAT.getMessage().replace("{1}", event.getPlayer().getName()).replace("{2}", s).replace(MODOCHAT_PREFIX.getMessage(), " ");

                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    if (player.hasPermission(MODOCHAT_PERM.getPermission())) {
                        if(soundEnabled()){
                            playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        }
                        sendMessage(player, format.replace("{0}", MODOCHAT_PREFIX.getMessage()));
                    } else {
                        if(soundEnabled()){
                            playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(player, "ModoChat", CMD_NO_PERM.getMessage());
                    }
                }
                Bukkit.getServer().getConsoleSender().sendMessage(colored(format));
            } else{
                getMsgSendConfig(event.getPlayer(), "ModoChat", CMD_NO_ARGS.getMessage().replace("<command>", "ModoChat"));
            }
        }return false;
    }
}

