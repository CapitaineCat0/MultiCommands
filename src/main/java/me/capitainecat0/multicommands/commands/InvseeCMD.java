package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.Invsee;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.permissions.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.permissions.Perms.INVSEE_PERM;

public class InvseeCMD implements CommandExecutor {

    /**
     *
     * The Invsee command can open targeted player's inventory to command sender
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        try{
            if(!perms.hasPermission((Player) sender, INVSEE_PERM.getPermission())
                    || !perms.hasPermission((Player) sender, ALL_PERMS.getPermission())){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                return true;
            }
            else{
                if (args.length == 0) {
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player>"));
                    return true;
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        new Invsee((Player) sender, target);
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), INVSEE_ADMIN.getMessage().replace("{0}", target.getName()));
                    } else {
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                    }
                }
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return false;
    }
}
