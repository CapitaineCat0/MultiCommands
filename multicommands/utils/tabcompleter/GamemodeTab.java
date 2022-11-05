package me.capitainecat0.multicommands.utils.tabcompleter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GamemodeTab implements org.bukkit.command.TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(args.length == 1){
            List<String> arguments = new ArrayList<>();
            arguments.add("survival");
            arguments.add("creative");
            arguments.add("adventure");
            arguments.add("spectator");
            return arguments;

        } else if(args.length == 2) {
            List<String> playersNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            for (Player player : players) {
                playersNames.add(player.getName());
            }
            return playersNames;
        }
        return null;
    }

}
