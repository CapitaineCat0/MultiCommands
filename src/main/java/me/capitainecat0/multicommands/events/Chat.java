package me.capitainecat0.multicommands.events;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.AFKHandler;
import me.capitainecat0.multicommands.utils.MessengerUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


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
                    Component chatComponent = Component.text(chat+ "&7 > &f" + msg);
                    event.message(chatComponent);
                    if(!AFKHandler.getInstance().isAFK(sender)){
                        String AFKchat = "%luckperms_prefix% &b"+senderNickName;
                        AFKchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), AFKchat);
                        Component afkChatComponent = Component.text(AFKchat+ "&7 > &f" + msg);
                        event.message(afkChatComponent);
                    }
                }else{
                    Component nickAfkChatComponent = Component.text("&7[AFK] &b"+senderNickName+" &7> &f" + msg);
                    event.message(nickAfkChatComponent);
                    if(!AFKHandler.getInstance().isAFK(sender)){
                        Component nickChatComponent = Component.text("&b"+senderNickName+" &7> &f" + msg);
                        event.message(nickChatComponent);
                    }
                }

            }else{
                if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
                    String defaultchat = "%luckperms_prefix% &b%player_name%";
                    defaultchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), defaultchat);
                    Component defaultChatComponent = Component.text(defaultchat+" &7> &f" + msg);
                    event.message(defaultChatComponent);
                    if(!AFKHandler.getInstance().isAFK(sender)){
                        String defaultAFKchat = "%luckperms_prefix% &b%player_name%";
                        defaultAFKchat = PlaceholderAPI.setPlaceholders(event.getPlayer(), defaultAFKchat);
                        Component defaultAfkChatComponent = Component.text(defaultAFKchat+" &7> &f" + msg);
                        event.message(defaultAfkChatComponent);
                    }
                }else{
                    Component defaultComponent = Component.text(sender+" &7> &f" + msg);
                    event.message(defaultComponent);
                    if(AFKHandler.getInstance().isAFK(sender)){
                        Component defaultAfkComponent = Component.text("&7[AFK] &b"+sender+" &7> &f" + msg);
                        event.message(defaultAfkComponent);
                    }
                }
            }
        }
    }
}
