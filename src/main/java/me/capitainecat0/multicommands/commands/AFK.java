package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.AFKHandler;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.AFK_PERM;
import static me.capitainecat0.multicommands.utils.Perms.ALERT_PERM;

public class AFK implements CommandExecutor {

    /**
     * La commande &quot;/afk&quot; requiert la permission &quot;multicommands.afk&quot; pour fonctionner.
     * <br>Cette commande permet de gérer le mode AFK.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(!sender.hasPermission(AFK_PERM.getPermission()) || !sender.hasPermission(ALERT_PERM.getPermission())){
            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            return true;
        }
        else{
            if(sender instanceof Player player){
                try {
                    AFKHandler handler = AFKHandler.getInstance();
                    if(!handler.isAFK(player)){
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        handler.toggleAFK(player);
                    }else if(handler.isAFK(player)){
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        handler.toggleAFK(player);
                    }
                }catch (Exception e){
                    sendCommandExceptionMessage(e, command.getName());
                }
            }else if(sender instanceof ConsoleCommandSender){
                sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("<command>", command.getName()));
            }
        }
        return false;
    }
}
