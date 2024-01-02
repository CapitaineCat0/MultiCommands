package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.TPAManager;
import me.capitainecat0.multicommands.utils.permissions.Perms;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;

public class TeleportA implements CommandsImpl {

    private final Player sender;
    private final Player target;
    private final TPAManager tpaManager = new TPAManager();
    private final String[] args;

    public TeleportA(Player sender, Player target, String[] args) {
        this.sender = sender;
        this.target = target;
        this.args = args;
    }
    @Override
    public void execute() {
        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
        getMsgSendConfig(target, "TeleportA", Messenger.TPA_RECEIVED_REQUEST.getMessage().replace("{0}", sender.getName()));
        sendHoverCommandMessage(target, "&a&lACCEPT", Messenger.TPA_ACCEPT_HOVER.getMessage(), "TeleportA"+" accept");
        sendHoverCommandMessage(target, "&c&lDENY", Messenger.TPA_DENY_HOVER.getMessage(), "TeleportA"+" deny");
        if (args[1].equalsIgnoreCase("accept")) {
            if(!sender.hasPermission(Perms.TPA_ACCEPT_PERM.getPermission())){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, "TeleportA", Messenger.CMD_NO_PERM.getMessage());
            }else {
                tpaManager.acceptTPA(target);
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                playSoundIfEnabled(target, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                getMsgSendConfig(sender, "TeleportA", Messenger.TPA_REQUEST_ACCEPT.getMessage().replace("{0}", target.getName()));
                getMsgSendConfig(target, "TeleportA", Messenger.TPA_ACCEPT.getMessage().replace("{0}", sender.getName()));
            }
        } else if (args[1].equalsIgnoreCase("deny")) {
            if(!target.hasPermission(Perms.TPA_DENY_PERM.getPermission())){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(target, "TeleportA", Messenger.CMD_NO_PERM.getMessage());
            }
            tpaManager.denyTPA(target);
            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            getMsgSendConfig(sender, "TeleportA", Messenger.TPA_REQUEST_DENY.getMessage().replace("{0}", target.getName()));
            getMsgSendConfig(target, "TeleportA", Messenger.TPA_DENY.getMessage().replace("{0}", sender.getName()));
        }
    }
}
