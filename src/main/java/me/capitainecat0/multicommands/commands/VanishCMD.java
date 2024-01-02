package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.Vanish;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.permissions.Perms.*;

public class VanishCMD implements CommandExecutor {

    /**
     * La commande &quot;/vanish&quot; requiert la permission &quot;multicommands.vanish&quot; pour fonctionner.
     * <br>Cette commande permet de gérer votre vanish.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        try{
            if(sender instanceof Player){
                boolean hasVanishPerm = perms.hasPermission((Player) sender, VANISH_PERM_SELF.getPermission())
                        || perms.hasPermission((Player) sender, VANISH_PERM_ALL.getPermission())
                        || perms.hasPermission((Player) sender, ALL_PERMS.getPermission());
                boolean hasOtherVanishPerm = perms.hasPermission((Player) sender, VANISH_PERM_OTHER.getPermission())
                        || perms.hasPermission((Player) sender, VANISH_PERM_ALL.getPermission())
                        || perms.hasPermission((Player) sender, ALL_PERMS.getPermission());
                if (args.length == 0) {
                    if (hasVanishPerm) {
                        new Vanish((Player) sender, null).execute();
                    } else {
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                        return true;
                    }
                } else if (args.length == 1) {
                    if (hasOtherVanishPerm) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                            new Vanish((Player) sender, target).execute();
                        }
                    }
                }else if(sender instanceof ConsoleCommandSender){
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
            }
            }catch(Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
            sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return false;
    }
}
