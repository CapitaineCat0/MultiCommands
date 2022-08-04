package me.capitainecat0.multicommands.events;

import me.capitainecat0.multicommands.utils.Messenger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity().getPlayer();
        Player killer = e.getEntity().getKiller();
        String name = player.getCustomName();
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        if (killer == null) {
            e.setDeathMessage(Messenger.ONDEATH.getMessage().replace("%p", name));
            player.sendMessage(Messenger.DEATHLOC.getMessage().replace("%loc", "X " + x + "Y " + y + "Z " + z));
        }
        e.setDeathMessage(Messenger.ONKILLED.getMessage().replace("%p", player.getName()).replace("%k", killer.getName()));
        player.sendMessage(Messenger.DEATHLOC.getMessage().replace("%loc", "X " + x + "Y " + y + "Z " + z));
    }
}
