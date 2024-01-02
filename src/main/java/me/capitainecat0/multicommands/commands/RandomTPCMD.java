package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.RandomTP;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.permissions.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.permissions.Perms.TELEPORT_PERM;

public class RandomTPCMD implements CommandExecutor{

    /**
     * La commande &quot;/randomtp&quot; requiert la permission &quot;multicommands.randomtp&quot; pour fonctionner.
     * <br>Cette commande permet de se téléporter aléatoirement sur la carte.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        if(!perms.hasPermission((Player) sender, TELEPORT_PERM.getPermission())
                || !perms.hasPermission((Player) sender, ALL_PERMS.getPermission())){
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
        }else{
            if(sender instanceof ConsoleCommandSender){
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
            }else{
                try{
                    Player player = ((Player)sender);
                    if(args.length < 2){
                        new RandomTP(player, 0, 0).execute();
                    }else if(args.length == 2){
                        new RandomTP(player, Integer.parseInt(args[0]), Integer.parseInt(args[1])).execute();
                    }
                }catch(NumberFormatException e){
                    sendCommandExceptionMessage(e, command.getName());
                }
            }
        }
        return false;
    }
}
