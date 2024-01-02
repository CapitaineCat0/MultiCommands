package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.TeleportA;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import me.capitainecat0.multicommands.utils.permissions.Perms;
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

public class TeleportACMD implements CommandExecutor {

    /**
     * La commande &quot;/tpa&quot; requiert la permission &quot;multicommands.tpa&quot; pour fonctionner.
     * <br>Cette commande permet d'envoyer une demande de téléportation à d'autres joueurs.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        if (sender instanceof Player) {
            if(!perms.hasPermission((Player) sender, Perms.TPA_PERM.getPermission())
                    || !perms.hasPermission((Player) sender, Perms.TPA_ALL_PERM.getPermission())
                    || !perms.hasPermission((Player) sender, Perms.ALL_PERMS.getPermission())){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
            }else{
                try{
                    if (args.length < 1) {
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player> | accept | deny"));
                    } else if (args.length > 1) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            new TeleportA((Player) sender, target, args);
                        } else {
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                            getMsgSendConfig(sender, command.getName(), Messenger.NOT_A_PLAYER.getMessage().replace("<player>", args[0]));
                        }
                    }
                }catch(Exception e){
                    sendCommandExceptionMessage(e, command.getName());
                    sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                    sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
                }
            }
        }else if (sender instanceof ConsoleCommandSender) {
            sendConsoleMessage(Messenger.NO_CONSOLE_COMMAND.getMessage());
        }
        return false;
    }
}
