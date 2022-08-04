package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.VanishHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
       Player player = e.getPlayer();
       if(player.isOp()){
           e.setJoinMessage(Messenger.ONJOIN_ADMIN.getMessage().replace("%p", player.getName()));
           VanishHandler.getVanished().add(player);
       }else{
           e.setJoinMessage(Messenger.ONJOIN.getMessage().replace("%p", player.getName()));
       }
    }
}
