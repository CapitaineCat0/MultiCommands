package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class SpawnEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        hideActiveBossBar();
        if(!event.getPlayer().hasPlayedBefore()){
            Location location = MultiCommands.getInstance().getConfig().getLocation("spawn");
            if(location != null){
                event.getPlayer().teleport(location);
                if(soundEnabled()){
                    playSound(event.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                }
                getMsgSendConfig(event.getPlayer(), "spawn", Messenger.SPAWN_DONE.getMessage());
            }
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        hideActiveBossBar();
        Location location = MultiCommands.getInstance().getConfig().getLocation("spawn");
        if(location != null){
            event.setRespawnLocation(location);
            if(soundEnabled()){
                playSound(event.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            }
            getMsgSendConfig(event.getPlayer(), "spawn", Messenger.SPAWN_DONE.getMessage());
        }
    }
}
