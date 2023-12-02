package me.capitainecat0.multicommands.utils.tabcompleter;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EconomyBankTab implements org.bukkit.command.TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String @NotNull [] args) {
        if(args.length == 1){
            List<String> arguments = new ArrayList<>();
            arguments.add("deposit");
            arguments.add("get");
            arguments.add("reset");
            arguments.add("withdraw");
            return arguments;
        }
        else if(args.length == 2) {
            List<String> arguments = new ArrayList<>();
            arguments.add("your");
            arguments.add("bank");
            arguments.add("name");
            return arguments;
        }
        else if(args.length == 3){
            List<String> arguments = new ArrayList<>();
            arguments.add("10");
            arguments.add("100");
            arguments.add("1000");
            return arguments;

        }
        return null;
    }

}
