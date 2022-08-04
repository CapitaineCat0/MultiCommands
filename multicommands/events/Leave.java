package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.utils.Messenger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        e.setQuitMessage(Messenger.ONLEAVE.getMessage().replace("%p", player.getName()));
    }
}
