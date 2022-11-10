package me.capitainecat0.multicommands.events;

import io.papermc.paper.event.player.AsyncChatEvent;
import io.papermc.paper.event.player.ChatEvent;
import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.AFKHandler;
import me.capitainecat0.multicommands.utils.MessengerUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class Chat implements Listener {
    @EventHandler
    public void onChat(PlayerChatEvent event){
        hideActiveBossBar();
        Player sender = event.getPlayer();
        String senderNickName = sender.getCustomName();
        String msg = MultiCommands.colored(event.getMessage().toString());
        if(msg.startsWith("!") || msg.startsWith("<") || msg.startsWith("#") || msg.startsWith("%") || msg.startsWith(">")){
            event.setCancelled(true);
        }else{
                if(senderNickName != null){
                    if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
                        String chat = "&7[AFK] %luckperms_prefix% &b"+senderNickName;
                        chat = PlaceholderAPI.setPlaceholders(event.getPlayer(), chat);
                        event.setCancelled(true);
                        sendMessage(chat+ "&7 > &f" + msg);
                        if(!AFKHandler.getInstance().isAFK(sender)){
                            String AFKchat = "%luckperms_prefix% &b"+senderNickName;
                            AFKchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), AFKchat);
                            event.setCancelled(true);
                            sendMessage(AFKchat+ "&7 > &f" + msg);
                        }
                    }else{
                        event.setCancelled(true);
                        sendMessage("&7[AFK] &b"+senderNickName+" &7> &f" + msg);
                        if(!AFKHandler.getInstance().isAFK(sender)){
                            event.setCancelled(true);
                            sendMessage("&b"+senderNickName+" &7> &f" + msg);
                        }
                    }

                }else{
                    if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
                        String defaultchat = "%luckperms_prefix% &b%player_name%";
                        defaultchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), defaultchat);
                        event.setCancelled(true);
                        sendMessage(defaultchat+" &7> &f" + msg);
                        if(!AFKHandler.getInstance().isAFK(sender)){
                            String defaultAFKchat = "%luckperms_prefix% &b%player_name%";
                            defaultAFKchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), defaultAFKchat);
                            event.setCancelled(true);
                            sendMessage(defaultAFKchat+" &7> &f" + msg);
                        }
                    }else{
                        event.setCancelled(true);
                        sendMessage(sender+" &7> &f" + msg);
                        if(AFKHandler.getInstance().isAFK(sender)){
                            event.setCancelled(true);
                            sendMessage("&7[AFK] &b"+sender+" &7> &f" + msg);
                        }
                    }
                }

        }
    }
}
