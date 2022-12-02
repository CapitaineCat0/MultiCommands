package me.capitainecat0.multicommands.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class LevelViewer implements Listener {

    @EventHandler
    public void playerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        double locY = player.getLocation().getY();
       player.setLevel((int) locY);
    }
}
