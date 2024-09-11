package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Objects;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;
import static me.capitainecat0.multicommands.utils.permissions.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.permissions.Perms.HELPOP_PERM;

public class HelpOP implements CommandsImpl {

    private final String message;
    private final Player player;

    public HelpOP(Player player, String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        sb.append(args[args.length - 1]);
        this.player = player;
        this.message = sb.toString();
    }
    @Override
    public void execute() {
        for (Player operators : Bukkit.getOnlinePlayers()) {
            if(Bukkit.getOnlinePlayers().contains(operators)){
                if(Objects.requireNonNull(operators.getPlayer()).hasPermission(ALL_PERMS.getPermission()) || operators.getPlayer().hasPermission(HELPOP_PERM.getPermission())){
                    playSoundIfEnabled(operators.getPlayer(), Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    sendConsoleMessage(HELPOP_FORMAT.getMessage().replace("{0}", player.getName()).replace("{1}", message));
                    sendMessage(operators.getPlayer(), HELPOP_FORMAT.getMessage().replace("{0}", player.getName()).replace("{1}", message));
                }
                playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                getMsgSendConfig(player, "Helpop", HELPOP_DONE.getMessage());
                sendMessage(player," <dark_gray>- <gray>" + message);
            }else{
                sendMessage(player, HELPOP_NO_ADMINS.getMessage());
            }
        }
    }
}
