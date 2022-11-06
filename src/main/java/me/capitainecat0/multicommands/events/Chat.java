package me.capitainecat0.multicommands.events;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.AFKHandler;
import me.capitainecat0.multicommands.utils.MessengerUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import static me.capitainecat0.multicommands.utils.MessengerUtils.hideActiveBossBar;

public class Chat implements Listener {
    @EventHandler
    public void onChat(AsyncChatEvent event){
        hideActiveBossBar();

        Player sender = event.getPlayer();
        String senderNickName = sender.getCustomName();
        String msg = MultiCommands.colored(event.message().toString());
        if(msg.startsWith("!") || msg.startsWith("<") || msg.startsWith("#") || msg.startsWith("%") || msg.startsWith(">")){
            event.setCancelled(true);
        }else{
            if(senderNickName != null){
                if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
                    String chat = "&7[AFK] %luckperms_prefix% &b"+senderNickName;
                    chat = PlaceholderAPI.setPlaceholders(event.getPlayer(), chat);
                    MessengerUtils.sendBroadcastMessage(chat+ "&7 > &f" + msg);
                    event.message(null);
                    if(AFKHandler.getInstance().isAFK(sender)){
                        String AFKchat = "%luckperms_prefix% &b"+senderNickName;
                        AFKchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), AFKchat);
                        MessengerUtils.sendBroadcastMessage(AFKchat+ "&7 > &f" + msg);
                        event.message(null);
                    }
                }else{
                    MessengerUtils.sendBroadcastMessage("&7[AFK] &b"+senderNickName+" &7> &f" + msg);
                    event.message(null);
                    if(AFKHandler.getInstance().isAFK(sender)){
                        MessengerUtils.sendBroadcastMessage("&b"+senderNickName+" &7> &f" + msg);
                        event.message(null);
                    }
                }

            }else{
                if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
                    String defaultchat = "%luckperms_prefix% &b%player_name%";
                    defaultchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), defaultchat);
                    MessengerUtils.sendBroadcastMessage(defaultchat+" &7> &f" + msg);
                    event.message(null);
                    if(AFKHandler.getInstance().isAFK(sender)){
                        String defaultAFKchat = "%luckperms_prefix% &b%player_name%";
                        defaultAFKchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), defaultAFKchat);
                        MessengerUtils.sendBroadcastMessage(defaultAFKchat+" &7> &f" + msg);
                        event.message(null);
                    }
                }else{
                    MessengerUtils.sendBroadcastMessage(sender+" &7> &f" + msg);
                    event.message(null);
                    if(AFKHandler.getInstance().isAFK(sender)){
                        MessengerUtils.sendBroadcastMessage("&7[AFK] &b"+sender+" &7> &f" + msg);
                        event.message(null);
                    }
                }
            }
        }
    }
}
