package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class Top implements CommandExecutor {

    /**
     * La commande &quot;/top&quot; requiert la permission &quot;multicommands.top&quot; pour fonctionner.
     * <br>Cette commande permet de se téléporter au-dessus des blocs au-dessus de vous.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        try{
            if(sender instanceof Player){
                if(!sender.hasPermission(TOP_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())){
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                    return true;
                }
                else {
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    final double topX = ((Player) sender).getLocation().getBlockX();
                    final double topZ = ((Player) sender).getLocation().getBlockZ();
                    final double topY = ((Player) sender).getWorld().getHighestBlockYAt((int) topX, (int) topZ);
                    final float pitch = ((Player) sender).getLocation().getPitch();
                    final float yaw = ((Player) sender).getLocation().getYaw();
                    ((Player) sender).teleport(new Location(((Player) sender).getWorld(), topX, topY+1, topZ, yaw, pitch));
                }
            }else if(sender instanceof ConsoleCommandSender){
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
            }
        }catch(Exception e){
            sendCommandExceptionMessage(e, command.getName());
        }
        return false;
    }
}
