package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.Messenger.COMPASS_LOC;
import static me.capitainecat0.multicommands.utils.Messenger.PLUGIN_PREFIX;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;

public class Compass implements CommandsImpl {
    private final Player player;
    private final String loc;
    private final int bearing;
    private final String args;

    public Compass(Player player, String loc, int bearing, String args) {
        this.player = player;
        this.loc = loc;
        this.bearing = bearing;
        this.args = args;
    }
    @Override
    public void execute() {
        Location location = player.getLocation();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        sendMessage(player, COMPASS_LOC.getMessage().replace("{0}", loc).replace("{1}", String.valueOf(bearing)).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        sendMessage(player, args);
        sendMessage(player, "<gray>Localization: <yellow>X"+x+" <yellow>Y"+y+" <yellow>Z"+z);
    }
}
