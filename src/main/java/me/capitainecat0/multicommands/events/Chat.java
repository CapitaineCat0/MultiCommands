package me.capitainecat0.multicommands.events;


import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.AFKHandler;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class Chat implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        hideActiveBossBar();
        Player sender = event.getPlayer();
        String senderNickName = sender.getCustomName();
        String msg = MultiCommands.colored(event.getMessage());
        if(msg.startsWith("!") || msg.startsWith("<") || msg.startsWith("#") || msg.startsWith("%") || msg.startsWith(">")){
            event.setCancelled(true);
        }else{
                if(senderNickName != null){
                    if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
                        String chat = "&7[AFK] %luckperms_prefix% &b"+senderNickName;
                        chat = PlaceholderAPI.setPlaceholders(event.getPlayer(), chat);
                        event.setFormat(chat+ "&7 > &f" + msg);
                        if(!AFKHandler.getInstance().isAFK(sender)){
                            String AFKchat = "%luckperms_prefix% &b"+senderNickName;
                            AFKchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), AFKchat);
                            event.setFormat(AFKchat+ "&7 > &f" + msg);
                        }
                    }else{
                        event.setFormat("&7[AFK] &b"+senderNickName+" &7> &f" + msg);
                        if(!AFKHandler.getInstance().isAFK(sender)){
                            event.setFormat("&b"+senderNickName+" &7> &f" + msg);
                        }
                    }

                }else{
                    if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
                        String defaultchat = "%luckperms_prefix% &b%player_name%";
                        defaultchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), defaultchat);
                        event.setFormat(defaultchat+" &7> &f" + msg);
                        if(!AFKHandler.getInstance().isAFK(sender)){
                            String defaultAFKchat = "%luckperms_prefix% &b%player_name%";
                            defaultAFKchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), defaultAFKchat);
                            event.setFormat(defaultAFKchat+" &7> &f" + msg);
                        }
                    }else{
                        event.setFormat(sender+" &7> &f" + msg);
                        if(AFKHandler.getInstance().isAFK(sender)){
                            event.setFormat("&7[AFK] &b"+sender+" &7> &f" + msg);
                        }
                    }
                }

        }
    }
}
