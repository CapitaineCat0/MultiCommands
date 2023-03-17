package me.capitainecat0.multicommands.utils.tabcompleter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RandomTPTab implements org.bukkit.command.TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(args.length == 1){
            List<String> arguments = new ArrayList<>();
            arguments.add("250");
            arguments.add("500");
            arguments.add("1000");
            arguments.add("1500");
            arguments.add("2000");
            return arguments;
        }
        if(args.length == 2){
            List<String> arguments = new ArrayList<>();
            arguments.add("250");
            arguments.add("500");
            arguments.add("1000");
            arguments.add("1500");
            arguments.add("2000");
            return arguments;
        }
        return null;
    }

}
