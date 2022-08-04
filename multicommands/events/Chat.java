package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.AFKHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){

        Player sender = e.getPlayer();
        String senderNickName = sender.getCustomName();
        String msg = MultiCommands.colored(e.getMessage());
        if(senderNickName != null){
            e.setFormat("§b" + senderNickName + "§7 > §f" + msg);
            if(AFKHandler.getInstance().isAFK(sender)){
                e.setFormat("§7[AFK] §b" + senderNickName + "§7 > §f" + msg);
            }
        }else{
            e.setFormat("§b" + sender.getName() + "§7 > §f" + msg);
            if(AFKHandler.getInstance().isAFK(sender)){
                e.setFormat("§7[AFK] §b" + senderNickName + "§7 > §f" + msg);
        }
        }
    }
}
