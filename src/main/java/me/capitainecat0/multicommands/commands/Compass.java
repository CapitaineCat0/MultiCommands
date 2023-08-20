package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class Compass implements CommandExecutor {

    /**
     *
     * The Compass command sends compass on chat with localization and bearing
     */
    @Override
public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        try {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                final int bearing = (int) (player.getLocation().getYaw() + 180 + 360) % 360;
                final String loc;
                final String bearingMap;
                String[] directions = {"north", "northEast", "east", "southEast", "south", "southWest", "west", "northWest"};
                String[] maps = {
                        "&c    N  \n&e    ^  \n&7W   &e|   &7E\n \n&7    S  ",
                        "&c     NE  \n&e      ^  \n&7NW   &e|   &7SE\n \n&7     SW  ",
                        "&c    E  \n&e    ^  \n&7N   &e|   &7S\n \n&7    W  ",
                        "&c     SE  \n&e      ^  \n&7NE   &e|   &7SW\n \n&7     NW  ",
                        "&c    S  \n&e    ^  \n&7E   &e|   &7W\n \n&7    N  ",
                        "&c     SW  \n&e      ^  \n&7SE   &e|   &7NW\n \n&7     NE  ",
                        "&c    W  \n&e    ^  \n&7S   &e|   &7N\n \n&7    E  ",
                        "&c     NW  \n&e      ^  \n&7SW   &e|   &7NE\n \n&7     SE  "
                };
                int index = bearing / 45;
                loc = directions[index];
                bearingMap = maps[index];
                Location location = player.getLocation();
                int x = location.getBlockX();
                int y = location.getBlockY();
                int z = location.getBlockZ();
                sendMessage(sender, COMPASS_LOC.getMessage().replace("{0}", loc).replace("{1}", String.valueOf(bearing)).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                sendMessage(sender, bearingMap);
                sendMessage(sender, "&7Localization: &eX"+x+" &eY"+y+" &eZ"+z);
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            } else if (sender instanceof ConsoleCommandSender) {
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage());
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, command.getName());
        }
    return false;
}
}
