package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;

public class Top implements CommandsImpl {

    private final Player sender;

    public Top(Player sender) {
        this.sender = sender;
    }
    @Override
    public void execute() {
        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
        final double topX = sender.getLocation().getBlockX();
        final double topZ = sender.getLocation().getBlockZ();
        final double topY = sender.getWorld().getHighestBlockYAt((int) topX, (int) topZ);
        final float pitch = sender.getLocation().getPitch();
        final float yaw = sender.getLocation().getYaw();
        sender.teleport(new Location(sender.getWorld(), topX, topY+1, topZ, yaw, pitch));
    }
}
