package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Objects;

import static me.capitainecat0.multicommands.utils.Messenger.SPAWN_ERROR;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
public class SpawnEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        hideActiveBossBar();
        if(MultiCommands.getInstance().getConfig().get("spawn.name") != null){
            Location location = new Location(
                    Bukkit.getWorld(Objects.requireNonNull(MultiCommands.getInstance().getConfig().getString("spawn.name"))),
                    MultiCommands.getInstance().getConfig().getDouble("spawn.x"),
                    MultiCommands.getInstance().getConfig().getDouble("spawn.y"),
                    MultiCommands.getInstance().getConfig().getDouble("spawn.z"),
                    MultiCommands.getInstance().getConfig().getInt("spawn.yaw"),
                    MultiCommands.getInstance().getConfig().getInt("spawn.pitch"));
            event.getPlayer().teleport(location);
        }else{
            if(soundEnabled()){
                playSound(event.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            }
            sendMessage(event.getPlayer(), SPAWN_ERROR.getMessage());
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        hideActiveBossBar();
        if(MultiCommands.getInstance().getConfig().get("spawn.name") != null){
            Location location = new Location(
                    Bukkit.getWorld(Objects.requireNonNull(MultiCommands.getInstance().getConfig().getString("spawn.name"))),
                    MultiCommands.getInstance().getConfig().getDouble("spawn.x"),
                    MultiCommands.getInstance().getConfig().getDouble("spawn.y"),
                    MultiCommands.getInstance().getConfig().getDouble("spawn.z"),
                    MultiCommands.getInstance().getConfig().getInt("spawn.yaw"),
                    MultiCommands.getInstance().getConfig().getInt("spawn.pitch"));
            event.getPlayer().teleport(location);
        }else{
            if(soundEnabled()){
                playSound(event.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            }
            sendMessage(event.getPlayer(), SPAWN_ERROR.getMessage());
        }
    }
}
