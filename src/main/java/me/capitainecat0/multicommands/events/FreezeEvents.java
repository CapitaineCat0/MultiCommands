package me.capitainecat0.multicommands.events;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.capitainecat0.multicommands.utils.storage.FreezeData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class FreezeEvents implements Listener {

    @EventHandler
    public void onBreak(@NotNull BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (FreezeData.isFrozen(player)) {
            event.setCancelled(true);
            hideActiveBossBar();
            getMsgSendConfig(player, event.getEventName(), FREEZE_BREAK.getMessage());
        }
    }

    @EventHandler
    public void onDrop(@NotNull PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (FreezeData.isFrozen(player)) {
            event.setCancelled(true);
            hideActiveBossBar();
            getMsgSendConfig(player, event.getEventName(), FREEZE_DROP.getMessage());
        }
    }

    @EventHandler
    public void onMove(@NotNull PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (FreezeData.isFrozen(player)) {
            int x = (int)player.getLocation().getX();
            int y = (int)player.getLocation().getY();
            int z = (int)player.getLocation().getZ();
            int pitch = (int)player.getLocation().getPitch();
            int yaw = (int)player.getLocation().getYaw();
            Location loc = new Location(player.getWorld(), x, y, z, (float)pitch, (float)yaw);
            event.setFrom(loc);
            event.setTo(loc);
            player.teleport(loc);
            hideActiveBossBar();
            getMsgSendConfig(player, event.getEventName(), FREEZE_MOVE.getMessage());
        }
    }

    @EventHandler
    public void onPickup(@NotNull PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (FreezeData.isFrozen(player)) {
            event.setCancelled(true);
            hideActiveBossBar();
            getMsgSendConfig(player, event.getEventName(), FREEZE_PICKUP.getMessage());
        }
    }

    @EventHandler
    public void onPlace(@NotNull BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (FreezeData.isFrozen(player)) {
            event.setCancelled(true);
            hideActiveBossBar();
            getMsgSendConfig(player, event.getEventName(), FREEZE_PLACE.getMessage());
        }
    }

    @EventHandler
    public void onChat(@NotNull AsyncChatEvent event){
        Player player = event.getPlayer();
        if (FreezeData.isFrozen(player)) {
            event.setCancelled(true);
            hideActiveBossBar();
            getMsgSendConfig(player, event.getEventName(), FREEZE_CHAT.getMessage());
        }
    }

    @EventHandler
    public void onDamage(@NotNull EntityDamageEvent event){
        if(event.getEntity() instanceof Player player){
            if (FreezeData.isFrozen(player)) {
                hideActiveBossBar();
                event.setCancelled(true);
            }
        }
    }
}
