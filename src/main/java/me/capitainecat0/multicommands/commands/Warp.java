package me.capitainecat0.multicommands.commands;

import de.leonhard.storage.Json;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class Warp implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(Perms.WARPS.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            sender.sendMessage(Messenger.CMD_NO_PERM.getMessage().replace("%cmd%", command.getName()));
        }
        if(sender instanceof Player){
            if(args.length < 1){
                sender.sendMessage(Messenger.WARPS_ERROR_NAME.getMessage());
                return false;
            }else{
                if(!new File("plugins/MultiCommands/warps/" + args[0] + ".json").exists()){
                    sender.sendMessage(Messenger.WARPS_ERROR.getMessage().replace("%w", args[0]));
                }else{
                    Json config = new Json(new File("plugins/MultiCommands/warps/" + args[0] + ".json"));
                    String world = (String) config.get("world");
                    float yaw = config.getInt("yaw");
                    float pitch = config.getInt("pitch");
                    float X = config.getInt("X");
                    float Y = config.getInt("Y");
                    float Z = config.getInt("Z");
                    Location loc = new Location(Bukkit.getWorld(world), X,Y,Z,yaw,pitch);
                    ((Player) sender).teleport(loc);
                    sender.sendMessage(Messenger.WARPS_TP.getMessage().replace("%w", args[0]));
                }
            }
        }else if(sender instanceof ConsoleCommandSender){
            sender.sendMessage(Messenger.NO_CONSOLE_COMMAND.getMessage().replace("%cmd%", command.getName()));
        }
        return false;
    }
}
