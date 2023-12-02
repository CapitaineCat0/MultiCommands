package me.capitainecat0.multicommands.events;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class Fly implements Listener {
    @EventHandler
    public void onFlying(@NotNull PlayerMoveEvent event){
        hideActiveBossBar();
        if(event.getPlayer().isFlying()){
            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.GRAY, 5.0F);
            event.getPlayer().spawnParticle(Particle.REDSTONE, event.getPlayer().getLocation(), 1, dustOptions);
        }
    }
}
