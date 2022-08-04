package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Helpop implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            if(args.length < 1){
                sender.sendMessage(Messenger.HELPOP_NO_ARGS.getMessage());
                return true;
            }
                StringBuilder bc = new StringBuilder();
                for(String part : args) {
                    bc.append(part).append(" ");
                }
                if(Bukkit.getOperators() != null){
                    for(OfflinePlayer operators : Bukkit.getOperators()){
                        if(operators.getPlayer().hasPermission(Perms.ALL_PERMS.getPermission()) || operators.getPlayer().hasPermission(Perms.HELPOP_PERM.getPermission())){
                            operators.getPlayer().sendMessage("§c[Aide Admin] §7" + sender.getName() + "§8: §f" + bc.toString());
                        }
                    }
                }
                else{
                    sender.sendMessage(Messenger.HELPOP_NO_ADMINS.getMessage());
                }
                sender.sendMessage(Messenger.HELPOP_DONE.getMessage());
                sender.sendMessage(" §8- §7" + bc.toString());

        }else if(sender instanceof ConsoleCommandSender){
            sender.sendMessage(Messenger.NO_CONSOLE_COMMAND.getMessage().replace("%cmd%", command.getName()));
        }
        return false;
    }
}
