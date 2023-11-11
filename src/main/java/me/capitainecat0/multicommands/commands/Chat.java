package me.capitainecat0.multicommands.commands;

import com.google.common.base.Joiner;
import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.ChatHandler;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.MessengerUtils;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.Messenger.BUILDERCHAT_PREFIX;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class Chat implements CommandExecutor {
    /**
     *
     * The Chat command can manage chat features
     * <br>
     * <br>If args isn't null and contains at first:
     * @params:
     * <br>admin - Send messages on admin channel
     * <br>builder - Send messages on builder channel
     * <br>clear - Clear the entire chat
     * <br>dev - Send messages on dev channel
     * <br>modo - Send messages on modo channel
     * <br>staff - Send messages on staff channel
     */
    @Override
public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    hideActiveBossBar();
    try {
        if (!sender.hasPermission(ALL_CHAT_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())) {
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
        } else {
            if (args.length < 1) {
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "\n< admin | builder | clear | dev | modo | staff >"));
            } else if (args.length == 1) {
                String role = args[0].toLowerCase();
                if (Arrays.asList("admin", "builder", "dev", "modo", "staff").contains(role)) {
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "[" + role + "] <message>"));
                }if (args[0].equalsIgnoreCase("toggle")) {
                    ChatHandler.getInstance().toggleChat(sender);
                } else if (args[0].equalsIgnoreCase("clear")) {
                    try{
                        if (sender.hasPermission(CLEARCHAT_PERM.getPermission())) {
                            for (int i = 0; i < 100; i++) {
                                sendBroadcastMessage(" ");
                            }
                            sendBroadcastMessage(" &a&m+----------------------------------------+");
                            sendBroadcastMessage(" ");
                            sendBroadcastMessage("                   "+CLEARCHAT.getMessage());
                            sendBroadcastMessage(" ");
                            sendBroadcastMessage(" &a&m+----------------------------------------+");
                            sendBroadcastMessage(" ");
                            sendBroadcastMessage(" ");
                            sendBroadcastMessage(" ");
                        } else {
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            sendMessage(sender, CMD_NO_PERM.getMessage());
                        }
                    }catch (Exception e){
                        sendCommandExceptionMessage(e, command.getName()+" clear");
                        sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                        sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                    }
                }
            }
                String role = args[0].toLowerCase();
                String s = Joiner.on(" ").join(args).replace(role, " ");
                if(role != null){
                    String format = switch (role) {
                        case "admin" ->
                                ADMINCHAT.getMessage().replace("{0}", ADMINCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);
                        case "builder" ->
                                BUILDERCHAT.getMessage().replace("{0}", BUILDERCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);
                        case "dev" ->
                                DEVCHAT.getMessage().replace("{0}", DEVCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);
                        case "modo" ->
                                MODOCHAT.getMessage().replace("{0}", MODOCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);
                        case "staff" ->
                                STAFFCHAT.getMessage().replace("{0}", STAFFCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);
                        default -> "";
                    };

                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        if (player.hasPermission(Perms.valueOf(role.toUpperCase() + "CHAT_PERM").getPermission()) || player.hasPermission(ALL_CHAT_PERM.getPermission()) || player.hasPermission(ALL_PERMS.getPermission())) {
                            sendBroadcastMessage(format);
                        } else {
                            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(player, command.getName(), CMD_NO_PERM.getMessage());
                        }
                    }
                    sendConsoleMessage(format);
                } else if (!role.contains(args[0])) {
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "\n< admin | builder | clear | dev | modo | staff >"));
                }
        }
    }catch (Exception e){
        sendCommandExceptionMessage(e, command.getName());
        sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
    }
    return false;
}
}



