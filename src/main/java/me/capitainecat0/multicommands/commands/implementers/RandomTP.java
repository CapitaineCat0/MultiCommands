package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Random;

import static me.capitainecat0.multicommands.utils.Messenger.CMD_ERROR;
import static me.capitainecat0.multicommands.utils.Messenger.TELEPORT_SELF_TO_COORDINATES;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class RandomTP implements CommandsImpl {

    private final Player player;
    private final int x;
    private final int z;

    public RandomTP(Player player, int x, int z) {
        this.player = player;
        this.x = x;
        this.z = z;
    }
    @Override
    public void execute() {
        try{
            if(x == 0 && z == 0){
                int maxX = MultiCommands.getInstance().getConfig().getInt("random-teleporter-max-X");
                int maxZ = MultiCommands.getInstance().getConfig().getInt("random-teleporter-max-Z");
                int minX = player.getLocation().getBlockX();
                int minZ = player.getLocation().getBlockZ();
                int x = new Random().nextInt(maxX + minX);
                int z = new Random().nextInt(maxZ + minZ);
                Location location = new Location(player.getWorld(), x, 0, z);
                int y = location.getWorld().getHighestBlockYAt(location);
                location.setY(y);
                player.teleport(location);
                getMsgSendConfig(player, "RandomTP", TELEPORT_SELF_TO_COORDINATES.getMessage().replace("{0}", "X "+x+" Y "+y+" Z "+z));
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            }else{
                int maxX = x;
                int maxZ = z;
                int minX = player.getLocation().getBlockX();
                int minZ = player.getLocation().getBlockZ();
                int x = new Random().nextInt(maxX + minX);
                int z = new Random().nextInt(maxZ + minZ);
                Location location = new Location(player.getWorld(), x, 0, z);
                int y = location.getWorld().getHighestBlockYAt(location);
                location.setY(y);
                player.teleport(location);
                getMsgSendConfig(player, "RandomTP", TELEPORT_SELF_TO_COORDINATES.getMessage().replace("{0}", "X "+x+" Y "+y+" Z "+z));
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, "RandomTP");
            sendMessage(player, CMD_ERROR.getMessage().replace("<command>", "RandomTP").replace("{e}", e.getMessage()));
        }
    }
}
