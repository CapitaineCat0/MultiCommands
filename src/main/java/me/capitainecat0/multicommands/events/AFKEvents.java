package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.utils.AFKHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class AFKEvents implements Listener {
    @EventHandler
    public void onMove(@NotNull PlayerMoveEvent e){
        hideActiveBossBar();
        Player player = e.getPlayer();
        if(AFKHandler.getInstance().isAFK(player)){
            AFKHandler.getInstance().toggleAFK(player);
        }
    }
    @EventHandler
    public void onPlace(@NotNull BlockPlaceEvent e){
        hideActiveBossBar();
        Player player = e.getPlayer();
        if(AFKHandler.getInstance().isAFK(player)){
            AFKHandler.getInstance().toggleAFK(player);
        }
    }
    @EventHandler
    public void onBreak(@NotNull BlockBreakEvent e){
        hideActiveBossBar();
        Player player = e.getPlayer();
        if(AFKHandler.getInstance().isAFK(player)){
            AFKHandler.getInstance().toggleAFK(player);
        }
    }
}
