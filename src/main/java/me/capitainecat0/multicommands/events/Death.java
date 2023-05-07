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

public class Death implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        hideActiveBossBar();
        Player player = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();
        assert player != null;
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        if (killer == null) {
            event.setDeathMessage(null);
            sendMessage(ONDEATH.getMessage().replace("{0}", player.getName()));
            sendMessage(player, DEATHLOC.getMessage().replace("{0}", "X :" + x + " Y :" + y + " Z :" + z));
        }else{
            event.setDeathMessage(null);
            Bukkit.broadcastMessage("§d"+player.getName()+" §7à été §c§ltué §7par §4"+killer.getName()+"§c!");
            sendMessage(player, DEATHLOC.getMessage().replace("{0}", "X " + x + "Y " + y + "Z " + z));
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        if(MultiCommands.getInstance().getConfig().get("spawn.name") != null){
            Location location = new Location(
                    Bukkit.getWorld(Objects.requireNonNull(MultiCommands.getInstance().getConfig().getString("spawn.name"))),
                    MultiCommands.getInstance().getConfig().getDouble("spawn.x"),
                    MultiCommands.getInstance().getConfig().getDouble("spawn.y"),
                    MultiCommands.getInstance().getConfig().getDouble("spawn.z"),
                    MultiCommands.getInstance().getConfig().getInt("spawn.yaw"),
                    MultiCommands.getInstance().getConfig().getInt("spawn.pitch"));
            event.getPlayer().teleport(location);
            if(soundEnabled()){
                playSound(event.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            }
            sendMessage(event.getPlayer(), SPAWN_DONE.getMessage());
        }else{
            if(soundEnabled()){
                playSound(event.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            }
            sendMessage(event.getPlayer(), SPAWN_ERROR.getMessage());
        }
    }
}
