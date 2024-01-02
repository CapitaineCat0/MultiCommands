package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import me.capitainecat0.multicommands.utils.Messenger;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;

public class SetSpawn implements CommandsImpl {

    private final Player sender;

    public SetSpawn(Player sender) {
        this.sender = sender;
    }
    @Override
    public void execute() {
        Location location = (sender).getLocation();
        MultiCommands.getInstance().getConfig().set("spawn.name", location.getWorld().getName());
        MultiCommands.getInstance().getConfig().set("spawn.x", location.getX());
        MultiCommands.getInstance().getConfig().set("spawn.y", location.getY());
        MultiCommands.getInstance().getConfig().set("spawn.z", location.getZ());
        MultiCommands.getInstance().getConfig().set("spawn.yaw", location.getYaw());
        MultiCommands.getInstance().getConfig().set("spawn.pitch", location.getPitch());
        MultiCommands.getInstance().saveConfig();
        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
        getMsgSendConfig(sender, "SetSpawn", Messenger.SETSPAWN_DONE.getMessage().replace("{0}", ""+location));
    }
}
