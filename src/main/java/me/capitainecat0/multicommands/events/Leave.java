package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.MessengerUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.capitainecat0.multicommands.utils.Messenger.ONLEAVE;
import static me.capitainecat0.multicommands.utils.MessengerUtils.hideActiveBossBar;

public class Leave implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        hideActiveBossBar();
        Player player = event.getPlayer();
        event.setQuitMessage(MultiCommands.colored(ONLEAVE.getMessage().replace("%player%", player.getName())));
    }
}
