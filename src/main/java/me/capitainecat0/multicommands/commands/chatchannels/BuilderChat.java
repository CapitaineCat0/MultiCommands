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

public class BuilderChat implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if (args.length >= 1) {
            String s = Joiner.on(" ").join(args);
            String format = BUILDERCHAT.getMessage().replace("{0}", BUILDERCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s).replace("{prefix}", PLUGIN_PREFIX.getMessage());

            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                if (player.hasPermission(BUILDERCHAT_PERM.getPermission()) || player.hasPermission(ALL_CHAT_PERM.getPermission()) || player.hasPermission(ALL_PERMS.getPermission())) {
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    sendMessage(player, format);
                } else {
                    if(soundEnabled()){
                        playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    getMsgSendConfig(player, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                }
            }
            Bukkit.getServer().getConsoleSender().sendMessage(colored(format));
        } else{
            getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<message>").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        }
        return false;
    }
    @EventHandler
    public boolean onChat(AsyncPlayerChatEvent event){
        hideActiveBossBar();
        if(event.getMessage().startsWith(BUILDERCHAT_PREFIX.getMessage())){
            if (event.getMessage().length() >= 1) {
                String s = Joiner.on(" ").join(Collections.singleton(event.getMessage()));
                String format = BUILDERCHAT.getMessage().replace("{1}", event.getPlayer().getName()).replace("{2}", s).replace(BUILDERCHAT_PREFIX.getMessage(), " ").replace("{prefix}", PLUGIN_PREFIX.getMessage());

                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    if (player.hasPermission(BUILDERCHAT_PERM.getPermission())) {
                        if(soundEnabled()){
                            playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        }
                        sendMessage(player, format.replace("{0}", BUILDERCHAT_PREFIX.getMessage()));
                    } else {
                        if(soundEnabled()){
                            playSound(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        getMsgSendConfig(player, "BuilderChat", CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                    }
                }
                Bukkit.getServer().getConsoleSender().sendMessage(colored(format));
            } else{
                getMsgSendConfig(event.getPlayer(), "BuilderChat", CMD_NO_ARGS.getMessage().replace("<command>", "BuilderChat").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
            }
        }return false;
    }
}

