package me.capitainecat0.multicommands.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.capitainecat0.multicommands.utils.Messenger.ONLEAVE;
import static me.capitainecat0.multicommands.utils.MessengerUtils.hideActiveBossBar;

public class Leave implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        hideActiveBossBar();
        Player player = e.getPlayer();
        e.setQuitMessage(ONLEAVE.getMessage().replace("%p", player.getName()));
    }
}
