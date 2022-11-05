package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.AFKHandler;
import me.capitainecat0.multicommands.utils.MessengerUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static me.capitainecat0.multicommands.utils.MessengerUtils.hideActiveBossBar;

public class Chat implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        hideActiveBossBar();

        Player sender = e.getPlayer();
        String senderNickName = sender.getCustomName();
        String msg = MultiCommands.colored(e.getMessage());
        if(msg.startsWith("!") || msg.startsWith("<") || msg.startsWith("#") || msg.startsWith("%") || msg.startsWith(">")){
            e.setCancelled(true);
        }else{
            if(senderNickName != null){
                if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
                    String chat = "§7[AFK] %luckperms_prefix% §b"+senderNickName;
                    chat = PlaceholderAPI.setPlaceholders(e.getPlayer(), chat);
                    e.setFormat(MultiCommands.colored(chat+ "§7 > §f" + msg));
                    if(AFKHandler.getInstance().isAFK(sender)){
                        String AFKchat = "%luckperms_prefix% §b"+senderNickName;
                        AFKchat = PlaceholderAPI.setPlaceholders(e.getPlayer(), AFKchat);
                        e.setFormat(MultiCommands.colored(AFKchat+ "§7 > §f" + msg));
                    }
                }else{
                    e.setFormat(MultiCommands.colored("§7[AFK] §b"+senderNickName+" §7> §f" + msg));
                    if(AFKHandler.getInstance().isAFK(sender)){
                        e.setFormat(MultiCommands.colored("§b"+senderNickName+ "§7 > §f" + msg));
                    }
                }

            }else{
                if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
                    String defaultchat = "%luckperms_prefix% §b%player_name%";
                    defaultchat = PlaceholderAPI.setPlaceholders(e.getPlayer(), defaultchat);
                    e.setFormat(MultiCommands.colored(defaultchat+ "§7 > §f" + msg));
                    if(AFKHandler.getInstance().isAFK(sender)){
                        String defaultAFKchat = "%luckperms_prefix% §b%player_name%";
                        defaultAFKchat = PlaceholderAPI.setPlaceholders(e.getPlayer(), defaultAFKchat);
                        e.setFormat(MultiCommands.colored(defaultAFKchat+ "§7 > §f" + msg));
                    }
                }else{
                    e.setFormat(MultiCommands.colored(""+sender+"§7 > §f"+msg));
                    if(AFKHandler.getInstance().isAFK(sender)){
                        e.setFormat(MultiCommands.colored("§7[AFK] §b"+sender+"§7 > §f"+msg));
                    }
                }
            }
        }
    }
}
