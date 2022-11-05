package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.utils.AFKHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import static me.capitainecat0.multicommands.utils.MessengerUtils.hideActiveBossBar;

public class AFKEvents implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player player = e.getPlayer();
        if(AFKHandler.getInstance().isAFK(player)){
            AFKHandler.getInstance().toggleAFK(player);
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Player player = e.getPlayer();
        if(AFKHandler.getInstance().isAFK(player)){
            AFKHandler.getInstance().toggleAFK(player);
        }
    }
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player player = e.getPlayer();
        if(AFKHandler.getInstance().isAFK(player)){
            AFKHandler.getInstance().toggleAFK(player);
        }
    }
}
