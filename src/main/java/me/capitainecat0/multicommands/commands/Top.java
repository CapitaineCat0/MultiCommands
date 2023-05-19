package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.utils.Messenger;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.NO_CONSOLE_COMMAND;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendConsoleMessage;

public class Top implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            final double topX = ((Player) sender).getLocation().getBlockX();
            final double topZ = ((Player) sender).getLocation().getBlockZ();
            final double topY = ((Player) sender).getWorld().getHighestBlockYAt((int) topX, (int) topZ);
            final float pitch = ((Player) sender).getLocation().getPitch();
            final float yaw = ((Player) sender).getLocation().getYaw();
            ((Player) sender).teleport(new Location(((Player) sender).getWorld(), topX, topY, topZ, yaw, pitch));
        }else if(sender instanceof ConsoleCommandSender){
            sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
        }
        return false;
    }
}
