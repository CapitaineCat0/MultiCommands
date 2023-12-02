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

import java.util.Random;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.TELEPORT_PERM;

public class RandomTP implements CommandExecutor{

    /**
     * La commande &quot;/randomtp&quot; requiert la permission &quot;multicommands.randomtp&quot; pour fonctionner.
     * <br>Cette commande permet de se téléporter aléatoirement sur la carte.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!MultiCommands.getPermissions().has(sender, TELEPORT_PERM.getPermission()) || !MultiCommands.getPermissions().has(sender, ALL_PERMS.getPermission())){
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
        }else{
            if(sender instanceof ConsoleCommandSender){
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
            }else{
                try{
                    Player player = ((Player)sender);
                    if(args.length < 2){
                        int maxX = MultiCommands.getInstance().getConfig().getInt("random-teleporter-max-X");
                        int maxZ = MultiCommands.getInstance().getConfig().getInt("random-teleporter-max-Z");
                        int minX = player.getLocation().getBlockX();
                        int minZ = player.getLocation().getBlockZ();

                        int x = new Random().nextInt(maxX + minX);
                        int z = new Random().nextInt(maxZ + minZ);

                        Location location = new Location(player.getWorld(), x, 0, z);
                        int y = location.getWorld().getHighestBlockYAt(location);
                        location.setY(y);

                        player.teleport(location);
                        getMsgSendConfig(sender, command.getName(), TELEPORT_SELF_TO_COORDINATES.getMessage().replace("{0}", "X "+x+" Y "+y+" Z "+z));
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }else if(args.length == 2){
                        int maxX = Integer.parseInt(args[0]);
                        int maxZ = Integer.parseInt(args[1]);
                        int minX = player.getLocation().getBlockX();
                        int minZ = player.getLocation().getBlockZ();
                        int x = new Random().nextInt(maxX + minX);
                        int z = new Random().nextInt(maxZ + minZ);

                        Location location = new Location(player.getWorld(), x, 0, z);
                        int y = location.getWorld().getHighestBlockYAt(location);
                        location.setY(y);
                        player.teleport(location);
                        getMsgSendConfig(sender, command.getName(), TELEPORT_SELF_TO_COORDINATES.getMessage().replace("{0}", "X "+x+" Y "+y+" Z "+z));
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                }catch(NumberFormatException e){
                    sendCommandExceptionMessage(e, command.getName());
                }
            }
        }
        return false;
    }
}
