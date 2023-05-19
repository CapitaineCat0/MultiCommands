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
            Location loc = ((Player) sender).getLocation();
            Location highest = (Location) ((Player) sender).getLocation().getWorld().getHighestBlockAt(loc);
            ((Player) sender).teleport(highest);
        }else if(sender instanceof ConsoleCommandSender){
            sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
        }
        return false;
    }
}
