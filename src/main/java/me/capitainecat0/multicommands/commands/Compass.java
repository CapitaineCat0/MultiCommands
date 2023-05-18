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
            String bearingMap = "";
            if (bearing < 23) {
                loc = "north";
                bearingMap =
                        "     \n" +
                        "  &cN  \n" +
                                "&7W &e^ &7E\n" +
                                "  &7S  ";
            } else if (bearing < 68) {
                loc = "northEast";
                bearingMap =
                        "     \n" +
                                "  &cN  \n" +
                                "&7W &e^ &7E\n" +
                                "  &7S  ";
            } else if (bearing < 113) {
                loc = "east";
            } else if (bearing < 158) {
                loc = "southEast";
            } else if (bearing < 203) {
                loc = "south";
            } else if (bearing < 248) {
                loc = "southWest";
            } else if (bearing < 293) {
                loc = "west";
            } else if (bearing < 338) {
                loc = "northWest";
            } else {
                loc = "north";
            }
            sendMessage(sender, COMPASS_LOC.getMessage().replace("{0}", loc).replace("{1}", bearing + ""));
            sendMessage(sender, bearingMap);

        } else if (sender instanceof ConsoleCommandSender) {
            sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage());
        }
        return false;
    }
}
