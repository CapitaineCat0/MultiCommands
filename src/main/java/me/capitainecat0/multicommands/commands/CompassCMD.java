package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.Compass;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class CompassCMD implements CommandExecutor {

    /**
     *
     * The Compass command sends compass on chat with localization and bearing
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        try {
            if (sender instanceof Player player) {
                final int bearing = (int) (player.getLocation().getYaw() + 180 + 360) % 360;
                final String loc;
                final String bearingMap;
                String[] directions = {"north", "northEast", "east", "southEast", "south", "southWest", "west", "northWest"};
                String[] maps = {
                        "<red>    N  \n<yellow>    ^  \n<gray>W   <yellow>|   <gray>E\n \n<gray>    S  ",
                        "<red>     NE  \n<yellow>      ^  \n<gray>NW   <yellow>|   <gray>SE\n \n<gray>     SW  ",
                        "<red>    E  \n<yellow>    ^  \n<gray>N   <yellow>|   <gray>S\n \n<gray>    W  ",
                        "<red>     SE  \n<yellow>      ^  \n<gray>NE   <yellow>|   <gray>SW\n \n<gray>     NW  ",
                        "<red>    S  \n<yellow>    ^  \n<gray>E   <yellow>|   <gray>W\n \n<gray>    N  ",
                        "<red>     SW  \n<yellow>      ^  \n<gray>SE   <yellow>|   <gray>NW\n \n<gray>     NE  ",
                        "<red>    W  \n<yellow>    ^  \n<gray>S   <yellow>|   <gray>N\n \n<gray>    E  ",
                        "<red>     NW  \n<yellow>      ^  \n<gray>SW   <yellow>|   <gray>NE\n \n<gray>     SE  "
                };
                int index = bearing / 45;
                loc = directions[index];
                bearingMap = maps[index];
                new Compass(player, loc, bearing, bearingMap).execute();
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            } else if (sender instanceof ConsoleCommandSender) {
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage());
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
    return false;
}
}
