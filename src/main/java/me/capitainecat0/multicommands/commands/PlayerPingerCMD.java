package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
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
import static me.capitainecat0.multicommands.utils.permissions.Perms.PLAYERPINGER_PERM;

public class PlayerPingerCMD implements CommandExecutor {
    /**
     * La commande &quot;/playerpinger&quot; requiert la permission &quot;multicommands.playerpinger&quot; pour fonctionner.
     * <br>Cette commande permet d'afficher le ping d'un joueur ou le vôtre.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        PermissionManager perms = MultiCommands.getPermissionManager();
        try{
            if(!perms.hasPermission((Player) sender, PLAYERPINGER_PERM.getPermission())
            || !perms.hasPermission((Player) sender, ALL_PERMS.getPermission())){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                return true;
            }else{
                if(args.length == 0){
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    int ping = ((Player) sender).getPing();
                    if(ping < 50){
                        sendMessage(sender, PING_SELF_MSG.getMessage().replace("{0}", "<green>" + ping + " ms"));
                    }
                    if(ping > 50){
                        sendMessage(sender, PING_SELF_MSG.getMessage().replace("{0}", "<yellow>" + ping + " ms"));
                    }
                    if(ping > 300){
                        sendMessage(sender, PING_SELF_MSG.getMessage().replace("{0}", "<red>" + ping + " ms"));
                    }
                }else if(args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    assert target != null;
                    int ping = target.getPing();
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    if (ping < 50) {
                        sendMessage(sender, PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", "<green>" + ping + " ms"));
                    }
                    if (ping > 50) {
                        sendMessage(sender, PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", "<yellow>" + ping + " ms"));
                    }
                    if (ping > 300) {
                        sendMessage(sender, PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", "<red>" + ping + " ms"));
                    }
                }
            }
        }catch(Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
            sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return false;
    }
}
