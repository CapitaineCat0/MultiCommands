package me.capitainecat0.multicommands.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.COMPASS_LOC;
import static me.capitainecat0.multicommands.utils.Messenger.NO_CONSOLE_COMMAND;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendConsoleMessage;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;

public class Compass implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            final int bearing = (int) (player.getLocation().getYaw() + 180 + 360) % 360;
            final String loc;
            final String bearingMap;
            if (bearing < 23) {
                loc = "north";
                bearingMap =
                        " \n" +
                                "    &cN  \n" +
                                "    &e^  \n" +
                                "&7W   &e|   &7E\n" +
                                " \n" +
                                "    &7S  ";
            } else if (bearing < 68) {
                loc = "northEast";
                bearingMap =
                        " \n" +
                                "    &cNE  \n" +
                                "     &e^  \n" +
                                "&7NW   &e|   &7SE\n" +
                                " \n" +
                                "    &7SW  ";
            } else if (bearing < 113) {
                loc = "east";
                bearingMap =
                        " \n" +
                                "    &cE  \n" +
                                "    &e^  \n" +
                                "&7N   &e|   &7S\n" +
                                " \n" +
                                "    &7W  ";
            } else if (bearing < 158) {
                loc = "southEast";
                bearingMap =
                        " \n" +
                                "    &cSE  \n" +
                                "     &e^  \n" +
                                "&7NE   &e|   &7SW\n" +
                                " \n" +
                                "    &7NW  ";
            } else if (bearing < 203) {
                loc = "south";
                bearingMap =
                        " \n" +
                                "    &cS  \n" +
                                "    &e^  \n" +
                                "&7E   &e|   &7W\n" +
                                " \n" +
                                "    &7N  ";
            } else if (bearing < 248) {
                loc = "southWest";
                bearingMap =
                        " \n" +
                                "    &cSW  \n" +
                                "     &e^  \n" +
                                "&7SE   &e|   &7NW\n" +
                                " \n" +
                                "    &7NE  ";
            } else if (bearing < 293) {
                loc = "west";
                bearingMap =
                        " \n" +
                                "    &cW  \n" +
                                "    &e^  \n" +
                                "&7S   &e|   &7N\n" +
                                " \n" +
                                "    &7E  ";
            } else if (bearing < 338) {
                loc = "northWest";
                bearingMap =
                        " \n" +
                                "    &cNW  \n" +
                                "     &e^  \n" +
                                "&7SW   &e|   &7NE\n" +
                                " \n" +
                                "    &7SE  ";
            } else {
                loc = "north";
                bearingMap =
                        " \n" +
                                "    &cN  \n" +
                                "    &e^  \n" +
                                "&7W   &e|   &7E\n" +
                                " \n" +
                                "    &7S  ";
            }
            int x = ((Player) sender).getLocation().getBlockX();
            int y = ((Player) sender).getLocation().getBlockY();
            int z = ((Player) sender).getLocation().getBlockZ();
            sendMessage(sender, COMPASS_LOC.getMessage().replace("{0}", loc).replace("{1}", bearing + ""));
            sendMessage(sender, bearingMap);
            sendMessage(sender, "&7Localisation: &eX"+x+" &eY"+y+" &eZ"+z);

        } else if (sender instanceof ConsoleCommandSender) {
            sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage());
        }
        return false;
    }
}
