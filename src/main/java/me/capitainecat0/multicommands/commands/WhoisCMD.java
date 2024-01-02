package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.commands.implementers.Whois;
import me.capitainecat0.multicommands.utils.inventories.PlayerGUI;
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
import static me.capitainecat0.multicommands.utils.permissions.Perms.WHOIS_PERM;

public class WhoisCMD implements CommandExecutor {
    
    /**
     * La commande &quot;/whois&quot; requiert la permission &quot;multicommands.whois&quot; pour fonctionner.
     * <br>Cette commande permet d'afficher les informations sur un joueur.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        try{
            if(!perms.hasPermission((Player) sender, WHOIS_PERM.getPermission())
                    || !perms.hasPermission((Player) sender, WHOIS_PERM.getPermission())){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                return true;
            }
            else{
                if (args.length == 0) {
                    /*playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player>"));
                    return true;*/
                    new PlayerGUI().open((Player) sender);

                }else if (args.length == 1) {
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                        new Whois((Player) sender, target).execute();
                    }else{
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        sendMessage(sender, NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                    }
                }
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
            sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return false;
    }
}
