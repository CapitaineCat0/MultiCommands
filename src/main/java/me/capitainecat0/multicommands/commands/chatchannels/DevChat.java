package me.capitainecat0.multicommands.commands.chatchannels;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.*;

public class DevChat implements CommandExecutor, Listener {

    /**
     * La commande &quot;/devchat&quot; requiert la permission &quot;multicommands.devchat&quot; pour fonctionner.
     * Cette commande permet de parler avec les joueurs
     * <br>ayant la permission &quot;multicommands.devchat&quot;
     * <br>dans un chat privé.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        hideActiveBossBar();
        if (args.length >= 1) {
            try {
                String s = String.join(" ", args);
                String format = DEVCHAT.getMessage().replace("{0}", DEVCHAT_PREFIX.getMessage()).replace("{1}", sender.getName()).replace("{2}", s);
                List<Player> onlinePlayers = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
                boolean hasPermission = sender.hasPermission(DEVCHAT_PERM.getPermission()) || sender.hasPermission(ALL_CHAT_PERM.getPermission()) || sender.hasPermission(ALL_PERMS.getPermission());
                for (Player player : onlinePlayers) {
                    if (hasPermission) {
                        playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        sendMessage(player, format);
                    } else {
                        playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(player, command.getName(), CMD_NO_PERM.getMessage());
                    }
                }
                sendConsoleMessage(format);
            }catch (Exception e){
                sendCommandExceptionMessage(e, command.getName());
            }
        } else{
            getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<message>"));
        }
        return false;
    }
}


