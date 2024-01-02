package me.capitainecat0.multicommands.commands.chatchannels;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;


import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.permissions.Perms.ADMINCHAT_PERM;

public class AdminChatCMD implements CommandExecutor, Listener {
    /**
     * La commande &quot;/adminchat&quot; requiert la permission &quot;multicommands.adminchat&quot; pour fonctionner.
     * Cette commande permet de parler avec les joueurs
     * <br>ayant la permission &quot;multicommands.adminchat&quot;
     * <br>dans un chat privé.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        hideActiveBossBar();
        if (args.length >= 1) {
            try {
                PermissionManager perms = MultiCommands.getPermissionManager();
                if (!perms.hasPermission((Player) sender, ADMINCHAT_PERM.getPermission())){
                    playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                    return true;
                }
                String s = String.join(" ", args);
                String format = ADMINCHAT.getMessage().replace("{0}", ADMINCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);
                ChatChannels.updatePlayerList();
                new ChatChannels(ADMINCHAT_PERM.getPermission()).broadcast(format);
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                sendConsoleMessage(format);
            }catch (Exception e){
                sendCommandExceptionMessage(e, command.getName());
                sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
            }
        } else{
            getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<message>"));
        }
        return false;
    }
}
