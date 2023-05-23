package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.data.ConfigData;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.SETWARP_PERM;

public class SetWarp implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(!sender.hasPermission(SETWARP_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
            if(soundEnabled()){
                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            }
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        }else{
          if(sender instanceof Player){
              if(args.length == 1){
                  if(ConfigData.existsConfigData("oldconfig.dat")){
                      String warpName = args[0];
                      Location location = ((Player) sender).getLocation();
                      FileConfiguration config = (YamlConfiguration) ConfigData.getConfig("oldconfig.dat").get(warpName);
                      ConfigData.getConfig("oldconfig.dat").set("warps.name", warpName);
                      config.set("warps.name.world", location.getWorld().getName());
                      config.set("warps.name.x", location.getX());
                      config.set("warps.name.y", location.getY());
                      config.set("warps.name.z", location.getZ());
                      config.set("warps.name.yaw", location.getYaw());
                      config.set("warps.name.pitch", location.getPitch());
                      config.saveToString();
                      try {
                          config.save("oldconfig.dat");
                      } catch (IOException e) {
                          sendConsoleMessage(e.getMessage());
                      }
                      MultiCommands.getInstance().saveConfig();
                  }else{
                      try {
                          ConfigData.createConfiguration("oldconfig.dat");
                      } catch (IOException e) {
                          sendConsoleMessage(e.getMessage());
                      }
                  }
              }else{
                  getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<warpName>").replace("{prefix}", PLUGIN_PREFIX.getMessage()));
              }
              if(soundEnabled()){
                  playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
              }
              getMsgSendConfig(sender, command.getName(), SETSPAWN_DONE.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage())//.replace("%loc%", (CharSequence) location)
              );
          }else if(sender instanceof ConsoleCommandSender){
              sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
          }
        }
        return true;
    }
}
