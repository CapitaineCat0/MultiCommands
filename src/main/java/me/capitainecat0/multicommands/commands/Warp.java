package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.data.ConfigData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class Warp implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(!sender.hasPermission(WARP_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
            if(soundEnabled()){
                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            }
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
        }else{
            if(sender instanceof Player){
                if(args.length < 1) {
                    getMsgSendConfig(sender, command.getName(), "&6Warp list:");
                    sendMessage(sender, String.valueOf(ConfigData.getConfig("warps.yml").getStringList("warps.name")));
                }else{
                    if(ConfigData.existsConfigData("warps.yml")){
                        FileConfiguration config = ConfigData.getConfig("warps.yml");
                        assert config != null;
                        Location location = new Location(
                                Bukkit.getWorld(Objects.requireNonNull(config.getString("warps.name.world"))),
                                config.getDouble("spawn.x"),
                                config.getDouble("spawn.y"),
                                config.getDouble("spawn.z"),
                                config.getInt("spawn.yaw"),
                                config.getInt("spawn.pitch"));
                        ((Player) sender).teleport(location);
                    }
                }
                if(soundEnabled()){
                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                }
                getMsgSendConfig(sender, command.getName(), SPAWN_DONE.getMessage());
            }else if(sender instanceof ConsoleCommandSender){
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
            }
        }
        return false;
    }
}
