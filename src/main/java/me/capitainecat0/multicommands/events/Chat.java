package me.capitainecat0.multicommands.events;


import io.papermc.paper.event.player.AsyncChatEvent;
import me.capitainecat0.multicommands.utils.AFKHandler;
import me.capitainecat0.multicommands.utils.FreezeHandler;
import me.capitainecat0.multicommands.utils.MuteHandler;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class Chat implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        hideActiveBossBar();
        Player sender = event.getPlayer();
        String senderNickName = sender.getCustomName();
        String msg = colored(event.getMessage().toString());
        if(msg.startsWith(STAFFCHAT_PREFIX.getMessage())
                || msg.startsWith(ADMINCHAT_PREFIX.getMessage())
                || msg.startsWith(BUILDERCHAT_PREFIX.getMessage())
                || msg.startsWith(DEVCHAT_PREFIX.getMessage())
                || msg.startsWith(MODOCHAT_PREFIX.getMessage())){
            event.setCancelled(true);
        }else{
            if(!MuteHandler.getInstance().isMuted(sender) || !FreezeHandler.getInstance().isFreeze(sender)){
                if(senderNickName != null){
                    if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
                        String chat = "&7[AFK] %luckperms_prefix% &b"+senderNickName;
                        chat = PlaceholderAPI.setPlaceholders(event.getPlayer(), chat);
                        event.setMessage(colored(chat+ "&7 > &f" + msg.replace("CraftPlayer{name="+sender+"}", event.getPlayer().getName())));
                        if(!AFKHandler.getInstance().isAFK(sender)){
                            String AFKchat = "%luckperms_prefix% &b"+senderNickName;
                            AFKchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), AFKchat);
                            event.setMessage(colored(AFKchat+ "&7 > &f" + msg.replace("CraftPlayer{name="+sender+"}", event.getPlayer().getName())));
                        }
                    }else{
                        event.setMessage(colored("&7[AFK] &b"+senderNickName+" &7> &f" + msg.replace("CraftPlayer{name="+sender+"}", event.getPlayer().getName())));
                        if(!AFKHandler.getInstance().isAFK(sender)){
                            event.setMessage(colored("&b"+senderNickName+" &7> &f" + msg.replace("CraftPlayer{name="+sender+"}", event.getPlayer().getName())));
                        }
                    }

                }else{
                    if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
                        String defaultchat = "%luckperms_prefix% &b%player_name%";
                        defaultchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), defaultchat);
                        event.setMessage(colored(defaultchat+" &7> &f" + msg.replace("CraftPlayer{name="+sender+"}", event.getPlayer().getName())));
                        if(!AFKHandler.getInstance().isAFK(sender)){
                            String defaultAFKchat = "%luckperms_prefix% &b%player_name%";
                            defaultAFKchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), defaultAFKchat);
                            event.setMessage(colored(defaultAFKchat+" &7> &f" + msg.replace("CraftPlayer{name="+sender+"}", event.getPlayer().getName())));
                        }
                    }else{
                        event.setMessage(colored(sender+" &7> &f" + msg.replace("CraftPlayer{name="+sender+"}", event.getPlayer().getName())));
                        if(AFKHandler.getInstance().isAFK(sender)){
                            event.setMessage(colored("&7[AFK] &b"+sender+" &7> &f" + msg.replace("CraftPlayer{name="+sender+"}", event.getPlayer().getName())));
                        }
                    }
                }
            }else{
                event.setCancelled(true);
             if(FreezeHandler.getFreeze().contains(sender)){
                 getMsgSendConfig(sender, event.getEventName(), FREEZE_CHAT.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
             }else if(MuteHandler.getMuted().contains(sender)){
                 getMsgSendConfig(sender, event.getEventName(), MUTE_CHAT.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()).replace("{0}", MuteHandler.getReason().toString()));
             }
            }
        }
    }
}
