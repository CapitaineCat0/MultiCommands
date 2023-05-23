package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.PLUGIN_PREFIX;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
public class SetSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(!sender.hasPermission(Perms.SETSPAWN_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            if(soundEnabled()){
                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            }
            getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        }else{
          if(sender instanceof Player){
              Location location = ((Player) sender).getLocation();
              MultiCommands.getInstance().getConfig().set("spawn.name", location.getWorld().getName());
              MultiCommands.getInstance().getConfig().set("spawn.x", location.getX());
              MultiCommands.getInstance().getConfig().set("spawn.y", location.getY());
              MultiCommands.getInstance().getConfig().set("spawn.z", location.getZ());
              MultiCommands.getInstance().getConfig().set("spawn.yaw", location.getYaw());
              MultiCommands.getInstance().getConfig().set("spawn.pitch", location.getPitch());
              MultiCommands.getInstance().saveConfig();
              if(soundEnabled()){
                  playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
              }
              getMsgSendConfig(sender, command.getName(), Messenger.SETSPAWN_DONE.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage())//.replace("%loc%", (CharSequence) location)
              );
          }else if(sender instanceof ConsoleCommandSender){
              sendConsoleMessage(Messenger.NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()).replace("{prefix}", PLUGIN_PREFIX.getMessage()));
          }
        }
        return true;
    }
}
