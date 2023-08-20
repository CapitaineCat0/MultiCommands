package me.capitainecat0.multicommands.events;


import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.*;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class Chat implements Listener {
    /**
     * The onChat function is a function that handles the chat event.
     * It cancels the default chat message and replaces it with a custom one.
     * The onChat function also checks if the player has permission to use certain chats, such as staffchat or adminchat.
     *
     * @param event Get the message that was sent,
     */
    @EventHandler
    public void onChat(@NotNull AsyncPlayerChatEvent event){
        event.setCancelled(true);
        hideActiveBossBar();
        Player sender = event.getPlayer();
        String senderNickName = sender.getCustomName();
        String msg = colored(event.getMessage().toString());
        if(msg.startsWith(STAFFCHAT_PREFIX.getMessage())){
            if (msg.length() >= 1) {
                try {
                    CharSequence args = (msg.replace(STAFFCHAT_PREFIX.getMessage(), ""));
                    String s = String.join(" ", args);
                    String format = STAFFCHAT.getMessage().replace("{0}", STAFFCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);
                    List<Player> onlinePlayers = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
                    boolean hasPermission = sender.hasPermission(STAFFCHAT_PERM.getPermission()) || sender.hasPermission(ALL_CHAT_PERM.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission());
                    for (Player player : onlinePlayers) {
                        if (hasPermission) {
                            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            sendMessage(player, format);
                        } else {
                            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(player, "chat staff", CMD_NO_PERM.getMessage());
                        }
                    }
                    sendConsoleMessage(format);
                }catch (Exception e){
                    sendEventExceptionMessage(e, event.getEventName());
                }
            } else{
                getMsgSendConfig(sender, "chat staff", CMD_NO_ARGS.getMessage().replace("<command>", "chat staff").replace("{0}", "<message>"));
            }
        }else if(msg.startsWith(ADMINCHAT_PREFIX.getMessage())){
            if (msg.length() >= 1) {
                try {
                    CharSequence args = (msg.replace(ADMINCHAT_PREFIX.getMessage(), ""));
                    String s = String.join(" ", args);
                    String format = ADMINCHAT.getMessage().replace("{0}", ADMINCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);
                    List<Player> onlinePlayers = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
                    boolean hasPermission = sender.hasPermission(ADMINCHAT_PERM.getPermission()) || sender.hasPermission(ALL_CHAT_PERM.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission());
                    for (Player player : onlinePlayers) {
                        if (hasPermission) {
                            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            sendMessage(player, format);
                        } else {
                            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(player, "chat staff", CMD_NO_PERM.getMessage());
                        }
                    }
                    sendConsoleMessage(format);
                }catch (Exception e){
                    sendEventExceptionMessage(e, event.getEventName());
                }
            } else{
                getMsgSendConfig(sender, "chat admin", CMD_NO_ARGS.getMessage().replace("<command>", "chat admin").replace("{0}", "<message>"));
            }
        }else if(msg.startsWith(BUILDERCHAT_PREFIX.getMessage())){
            if (msg.length() >= 1) {
                try {
                    CharSequence args = (msg.replace(BUILDERCHAT_PREFIX.getMessage(), ""));
                    String s = String.join(" ", args);
                    String format = BUILDERCHAT.getMessage().replace("{0}", BUILDERCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);
                    List<Player> onlinePlayers = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
                    boolean hasPermission = sender.hasPermission(BUILDERCHAT_PERM.getPermission()) || sender.hasPermission(ALL_CHAT_PERM.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission());
                    for (Player player : onlinePlayers) {
                        if (hasPermission) {
                            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            sendMessage(player, format);
                        } else {
                            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(player, "chat staff", CMD_NO_PERM.getMessage());
                        }
                    }
                    sendConsoleMessage(format);
                }catch (Exception e){
                    sendEventExceptionMessage(e, event.getEventName());
                }
            } else{
                getMsgSendConfig(sender, "chat builder", CMD_NO_ARGS.getMessage().replace("<command>", "chat builder").replace("{0}", "<message>"));
            }
        }else if(msg.startsWith(DEVCHAT_PREFIX.getMessage())){
            if (msg.length() >= 1) {
                try {
                    CharSequence args = (msg.replace(DEVCHAT_PREFIX.getMessage(), ""));
                    String s = String.join(" ", args);
                    String format = DEVCHAT.getMessage().replace("{0}", DEVCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);
                    List<Player> onlinePlayers = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
                    boolean hasPermission = sender.hasPermission(DEVCHAT_PERM.getPermission()) || sender.hasPermission(ALL_CHAT_PERM.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission());
                    for (Player player : onlinePlayers) {
                        if (hasPermission) {
                            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            sendMessage(player, format);
                        } else {
                            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(player, "chat dev", CMD_NO_PERM.getMessage());
                        }
                    }
                    sendConsoleMessage(format);
                }catch (Exception e){
                    sendEventExceptionMessage(e, event.getEventName());
                }
            } else{
                getMsgSendConfig(sender, "chat dev", CMD_NO_ARGS.getMessage().replace("<command>", "chat dev").replace("{0}", "<message>"));
            }
        }else if(msg.startsWith(MODOCHAT_PREFIX.getMessage())){
            if (msg.length() >= 1) {
                try {
                    CharSequence args = (msg.replace(MODOCHAT_PREFIX.getMessage(), ""));
                    String s = String.join(" ", args);
                    String format = MODOCHAT.getMessage().replace("{0}", MODOCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);
                    List<Player> onlinePlayers = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
                    boolean hasPermission = sender.hasPermission(MODOCHAT_PERM.getPermission()) || sender.hasPermission(ALL_CHAT_PERM.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission());
                    for (Player player : onlinePlayers) {
                        if (hasPermission) {
                            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            sendMessage(player, format);
                        } else {
                            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(player, "chat modo", CMD_NO_PERM.getMessage());
                        }
                    }
                    sendConsoleMessage(format);
                }catch (Exception e){
                    sendEventExceptionMessage(e, event.getEventName());
                }
            } else{
                getMsgSendConfig(sender, "chat modo", CMD_NO_ARGS.getMessage().replace("<command>", "chat modo").replace("{0}", "<message>"));
            }
        }else{
            if(!MuteHandler.getInstance().isMuted(sender) || !FreezeHandler.getInstance().isFreeze(sender)){
                if(ChatHandler.getInstance().isEnabled()){
                    if(senderNickName != null){
                        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
                            String chat = "&7[AFK] %luckperms_prefix% &b"+senderNickName;
                            chat = PlaceholderAPI.setPlaceholders(event.getPlayer(), chat);
                            sendBroadcastMessage(CHAT_FORMAT.getMessage().replace("{0}", chat).replace("{1}", msg));
                            //event.setMessage(colored(chat+ "&7 > &f" + msg.replace("CraftPlayer{name="+sender.getName()+"}", " ")));
                            if(!AFKHandler.getInstance().isAFK(sender)){
                                String AFKchat = "%luckperms_prefix% &b"+senderNickName;
                                AFKchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), AFKchat);
                                sendBroadcastMessage(CHAT_FORMAT.getMessage().replace("{0}", AFKchat).replace("{1}", msg));
                                //event.setMessage(colored(AFKchat+ "&7 > &f" + msg.replace("CraftPlayer{name="+sender.getName()+"}", " ")));
                            }
                        }else{
                            sendBroadcastMessage(CHAT_FORMAT.getMessage().replace("{0}", "[AFK] "+senderNickName).replace("{1}", msg));
                            //event.setMessage(colored("&7[AFK] &b"+senderNickName+" &7> &f" + msg.replace("CraftPlayer{name="+sender.getName()+"}", " ")));
                            if(!AFKHandler.getInstance().isAFK(sender)){
                                sendBroadcastMessage(CHAT_FORMAT.getMessage().replace("{0}", senderNickName).replace("{1}", msg));
                                //event.setMessage(colored("&b"+senderNickName+" &7> &f" + msg.replace("CraftPlayer{name="+sender.getName()+"}", " ")));
                            }
                        }

                    }else{
                        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
                            String defaultchat = "%luckperms_prefix% &b%player_name%";
                            defaultchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), defaultchat);
                            sendBroadcastMessage(CHAT_FORMAT.getMessage().replace("{0}", defaultchat).replace("{1}", msg));
                            //event.setMessage(colored(defaultchat+" &7> &f" + msg.replace("CraftPlayer{name="+sender.getName()+"}", " ")));
                            if(!AFKHandler.getInstance().isAFK(sender)){
                                String defaultAFKchat = "%luckperms_prefix% &b%player_name%";
                                defaultAFKchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), defaultAFKchat);
                                sendBroadcastMessage(CHAT_FORMAT.getMessage().replace("{0}", defaultAFKchat).replace("{1}", msg));
                                //event.setMessage(colored(defaultAFKchat+" &7> &f" + msg.replace("CraftPlayer{name="+sender.getName()+"}", " ")));
                            }
                        }else{
                            if(AFKHandler.getInstance().isAFK(sender)){
                                sendBroadcastMessage(CHAT_FORMAT.getMessage().replace("{0}", "[AFK] "+sender.getName()).replace("{1}", msg));
                                //event.setMessage(colored("&b"+senderNickName+" &7> &f" + msg.replace("CraftPlayer{name="+sender.getName()+"}", " ")));
                            }else{
                                sendBroadcastMessage(CHAT_FORMAT.getMessage().replace("{0}", sender.getName()).replace("{1}", msg));
                                //event.setMessage(colored("&b"+senderNickName+" &7> &f" + msg.replace("CraftPlayer{name="+sender.getName()+"}", " ")));
                            }
                        }
                    }
                }else{
                    event.setCancelled(true);
                    getMsgSendConfig(sender, event.getEventName(), "&cChat disabled by an administrator!");
                }
            }else{
                event.setCancelled(true);
                if(FreezeHandler.getFreeze().contains(sender)){
                    getMsgSendConfig(sender, event.getEventName(), FREEZE_CHAT.getMessage());
                }else if(MuteHandler.getMuted().contains(sender)){
                    getMsgSendConfig(sender, event.getEventName(), MUTE_CHAT.getMessage().replace("{0}", MuteHandler.getReason().toString()));
                }
            }
        }
    }
}
