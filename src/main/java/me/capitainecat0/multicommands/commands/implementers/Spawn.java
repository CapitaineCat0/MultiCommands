package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Objects;

import static me.capitainecat0.multicommands.utils.Messenger.SPAWN_DONE;
import static me.capitainecat0.multicommands.utils.Messenger.SPAWN_ERROR;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;

public class Spawn implements CommandsImpl {

    private final Player sender;

    public Spawn(Player sender) {
        this.sender = sender;
    }
    @Override
    public void execute() {
        if (MultiCommands.getInstance().getConfig().get("spawn.name") != null) {
            Location location = new Location(
                    Bukkit.getWorld(Objects.requireNonNull(MultiCommands.getInstance().getConfig().getString("spawn.name"))),
                    MultiCommands.getInstance().getConfig().getDouble("spawn.x"),
                    MultiCommands.getInstance().getConfig().getDouble("spawn.y"),
                    MultiCommands.getInstance().getConfig().getDouble("spawn.z"),
                    MultiCommands.getInstance().getConfig().getInt("spawn.yaw"),
                    MultiCommands.getInstance().getConfig().getInt("spawn.pitch"));
            (sender).teleport(location);
            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            getMsgSendConfig(sender, "Spawn", SPAWN_DONE.getMessage());
        } else {
            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            getMsgSendConfig(sender, "Spawn", SPAWN_ERROR.getMessage());
        }
    }
}
