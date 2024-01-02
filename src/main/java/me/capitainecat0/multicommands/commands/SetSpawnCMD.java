package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.SetSpawn;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import me.capitainecat0.multicommands.utils.permissions.Perms;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.Messenger.CMD_ERROR_ASSISTANCE;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
public class SetSpawnCMD implements CommandExecutor {

    /**
     *
     * La commande &quot;/setspawn&quot; requiert la permission &quot;multicommands.setspawn&quot; pour fonctionner.
     * <br>Cette commande permet de placer le spawn du monde.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        try{
            if(!perms.hasPermission((Player) sender, Perms.SETSPAWN_PERM.getPermission())
                    || !perms.hasPermission((Player) sender, Perms.ALL_PERMS.getPermission())){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
            }else{
                new SetSpawn((Player) sender).execute();
            }
        }catch(Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
            sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return true;
    }
}
