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
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class Death implements Listener {

    @EventHandler
    public void onDeath(@NotNull PlayerDeathEvent event) {
        hideActiveBossBar();
        Player player = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();
        assert player != null;
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        if (killer == null) {
            event.setDeathMessage(null);
            sendBroadcastMessage(ONDEATH.getMessage().replace("{0}", player.getName()));
            sendMessage(player, DEATHLOC.getMessage().replace("{0}", "X :" + x + " Y :" + y + " Z :" + z));
        }else{
            event.setDeathMessage(null);
            sendMessage(DEATHBYKILLER.getMessage().replace("{0}", player.getName()).replace("{1}", killer.getName()));
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
            playSoundIfEnabled(event.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            sendMessage(event.getPlayer(), SPAWN_DONE.getMessage());
        }else{
            playSoundIfEnabled(event.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            sendMessage(event.getPlayer(), SPAWN_ERROR.getMessage());
        }
    }
}
