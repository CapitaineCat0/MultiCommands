package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.utils.storage.FreezeData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.ONLEAVE;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class Leave implements Listener {

    @EventHandler
    public void onQuit(@NotNull PlayerQuitEvent event){
        hideActiveBossBar();
        try {
            Player player = event.getPlayer();
            FreezeData.frozenData.remove(player.getUniqueId());
            event.quitMessage(null);
            sendBroadcastMessage(ONLEAVE.getMessage().replace("{0}", player.getName()));
        } catch (Exception e) {
            sendEventExceptionMessage(e, "Leave.onQuit");
        }
    }
}
