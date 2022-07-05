package me.capitainecat0.multicommands.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class FreezeEvents implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        FreezeData data = new FreezeData(player);
        boolean isFrozen = data.isFrozen();
        if (isFrozen) {
            event.setCancelled(true);
            player.sendMessage(Messenger.FREEZE_BREAK.getMessage());
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        FreezeData data = new FreezeData(player);
        boolean isFrozen = data.isFrozen();
        if (isFrozen) {
            event.setCancelled(true);
            player.sendMessage(Messenger.FREEZE_DROP.getMessage());
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        FreezeData data = new FreezeData(player);
        boolean isFrozen = data.isFrozen();
        if (isFrozen) {
            int x = (int)player.getLocation().getX();
            int y = (int)player.getLocation().getY();
            int z = (int)player.getLocation().getZ();
            int pitch = (int)player.getLocation().getPitch();
            int yaw = (int)player.getLocation().getYaw();
            Location loc = new Location(player.getWorld(), x, y, z, (float)pitch, (float)yaw);
            event.setFrom(loc);
            event.setTo(loc);
            player.teleport(loc);
            player.sendMessage(Messenger.FREEZE_MOVE.getMessage());
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        FreezeData data = new FreezeData(player);
        boolean isFrozen = data.isFrozen();
        if (isFrozen) {
            event.setCancelled(true);
            player.sendMessage(Messenger.FREEZE_PICKUP.getMessage());
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        FreezeData data = new FreezeData(player);
        boolean isFrozen = data.isFrozen();
        if (isFrozen) {
            event.setCancelled(true);
            player.sendMessage(Messenger.FREEZE_PLACE.getMessage());
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        FreezeData data = new FreezeData(player);
        boolean isFrozen = data.isFrozen();
        if (isFrozen) {
            event.setCancelled(true);
            player.sendMessage(Messenger.FREEZE_CHAT.getMessage());
        }
    }
}
