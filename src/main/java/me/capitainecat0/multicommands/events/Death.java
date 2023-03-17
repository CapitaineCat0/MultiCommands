package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Objects;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;

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
            sendMessage(player, DEATHLOC.getMessage().replace("%loc%", "X :" + x + " Y :" + y + " Z :" + z));
        }else{
            e.setDeathMessage(null);
            Bukkit.broadcastMessage("§d"+player.getName()+" §7à été §c§ltué §7par §4"+killer.getName()+"§c!");
            sendMessage(player, DEATHLOC.getMessage().replace("%loc", "X " + x + "Y " + y + "Z " + z));
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Location location = new Location(
                e.getPlayer().getWorld(),
                MultiCommands.getInstance().getConfig().getDouble("spawn.x"),
                MultiCommands.getInstance().getConfig().getDouble("spawn.y"),
                MultiCommands.getInstance().getConfig().getDouble("spawn.z"),
                MultiCommands.getInstance().getConfig().getInt("spawn.yaw"),
                MultiCommands.getInstance().getConfig().getInt("spawn.pitch"));
        if(location != null){
            e.setRespawnLocation(location);
            e.getPlayer().teleport(location);
            getMsgSendConfig(e.getPlayer(), "Spawn", SPAWN_DONE.getMessage());
        }else{
            getMsgSendConfig(e.getPlayer(), "Spawn", SPAWN_ERROR.getMessage());
        }
    }
}
