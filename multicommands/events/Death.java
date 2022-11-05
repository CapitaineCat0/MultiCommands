package me.capitainecat0.multicommands.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.hideActiveBossBar;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;

public class Death implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        hideActiveBossBar();
        Player player = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();
        assert player != null;
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        if (killer == null) {
            e.setDeathMessage(null);
            Bukkit.broadcastMessage("§3"+player.getName()+" §7est mort!");
            sendMessage(player, DEATHLOC.getMessage().replace("%loc", "X " + x + "Y " + y + "Z " + z));
        }else{
            e.setDeathMessage(null);
            Bukkit.broadcastMessage("§d"+player.getName()+" §7à été §c§ltué §7par §4"+killer.getName()+"§c!");
            sendMessage(player, DEATHLOC.getMessage().replace("%loc", "X " + x + "Y " + y + "Z " + z));
        }
    }
}
