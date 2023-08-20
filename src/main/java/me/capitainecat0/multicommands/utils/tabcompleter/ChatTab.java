package me.capitainecat0.multicommands.utils.tabcompleter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ChatTab implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if(args.length == 1){
            List<String> arguments = new ArrayList<>();
            arguments.add("clear");
            arguments.add("toggle");
            arguments.add("admin");
            arguments.add("builder");
            arguments.add("dev");
            arguments.add("modo");
            arguments.add("staff");
            return arguments;
        }
        return null;
    }
}
