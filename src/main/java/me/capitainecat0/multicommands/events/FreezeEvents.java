package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.data.FreezeData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.MessengerUtils.hideActiveBossBar;

public class FreezeEvents implements Listener {

    @EventHandler
    public void onBreak(@NotNull BlockBreakEvent event) {
        Player player = event.getPlayer();
        FreezeData data = new FreezeData(player);
        boolean isFrozen = data.isFrozen();
        if (isFrozen) {
            if(!MultiCommands.getInstance().getCooldownManager().isPlayerCooldown(player)){
                MultiCommands.getInstance().getCooldownManager().addPlayerCooldown(player, 5);
            }
            event.setCancelled(true);
            hideActiveBossBar();
            if(MultiCommands.getInstance().getCooldownManager().isPlayerCooldown(player)){
                getMsgSendConfig(player, "BreakBlock", FREEZE_BREAK.getMessage());
            }

        }
    }

    @EventHandler
    public void onDrop(@NotNull PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        FreezeData data = new FreezeData(player);
        boolean isFrozen = data.isFrozen();
        if (isFrozen) {
            event.setCancelled(true);
            hideActiveBossBar();
            getMsgSendConfig(player, "DropItem", FREEZE_DROP.getMessage());
        }
    }

    @EventHandler
    public void onMove(@NotNull PlayerMoveEvent event) {
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
            hideActiveBossBar();
            getMsgSendConfig(player, "Move", FREEZE_MOVE.getMessage());
        }
    }

    @EventHandler
    public void onPickup(@NotNull PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        FreezeData data = new FreezeData(player);
        boolean isFrozen = data.isFrozen();
        if (isFrozen) {
            event.setCancelled(true);
            hideActiveBossBar();
            getMsgSendConfig(player, "PickupItem", FREEZE_PICKUP.getMessage());
        }
    }

    @EventHandler
    public void onPlace(@NotNull BlockPlaceEvent event) {
        Player player = event.getPlayer();
        FreezeData data = new FreezeData(player);
        boolean isFrozen = data.isFrozen();
        if (isFrozen) {
            event.setCancelled(true);
            hideActiveBossBar();
            getMsgSendConfig(player, "PlaceBlock", FREEZE_PLACE.getMessage());
        }
    }

    @EventHandler
    public void onChat(@NotNull AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        FreezeData data = new FreezeData(player);
        boolean isFrozen = data.isFrozen();
        if (isFrozen) {
            event.setCancelled(true);
            hideActiveBossBar();
            getMsgSendConfig(player, "Chat", FREEZE_CHAT.getMessage());
        }
    }

    @EventHandler
    public void onDamage(@NotNull EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            FreezeData data = new FreezeData(player);
            boolean isFrozen = data.isFrozen();
            if (isFrozen) {
                hideActiveBossBar();
                event.setCancelled(true);
            }
        }
    }
}
