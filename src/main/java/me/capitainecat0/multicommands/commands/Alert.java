package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.ALERT_PERM;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;

public class Alert implements CommandExecutor {

    /**
     * La commande &quot;/alert&quot; requiert la permission &quot;multicommands.alert&quot; pour fonctionner.
     * <br>Cette commande permet d'envoyer un message d'alerte à l'ensemble des joueurs connectés.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        hideActiveBossBar();
        try {
            boolean isPlayer = sender instanceof Player;
            boolean hasPermission = isPlayer && (sender.hasPermission(ALERT_PERM.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission()));
            if (isPlayer && !hasPermission) {
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                return true;
            } else if (isPlayer && args.length == 0) {
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("{0}", "<message>"));
                return true;
            } else if (isPlayer) {
                StringBuilder bc = new StringBuilder();
                for (String part : args) {
                    bc.append(part).append(" ");
                }
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), ALERT_CMD.getMessage().replace("{prefix}", PLUGIN_PREFIX.getMessage()));
                sendBroadcastMessage(ALERT_PREFIX.getMessage() + "&r " + bc);
            } else if (sender instanceof ConsoleCommandSender) {
                StringBuilder bc = new StringBuilder();
                for (String part : args) {
                    bc.append(part).append(" ");
                }
                if(args.length != 0){
                    sendBroadcastMessage(ALERT_PREFIX.getMessage() + "&r " + bc);
                }else{
                    sendConsoleMessage(CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<message>"));
                }
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return false;
    }
}
