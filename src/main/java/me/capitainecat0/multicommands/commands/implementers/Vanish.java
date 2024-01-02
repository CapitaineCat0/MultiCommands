package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import me.capitainecat0.multicommands.utils.VanishHandler;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.Messenger.VANISH_DISABLED_ADMIN;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;

public class Vanish implements CommandsImpl {

    private final Player sender;
    private final Player target;

    public Vanish(Player sender, Player target) {
        this.sender = sender;
        this.target = target;
    }
    @Override
    public void execute() {
        VanishHandler handler = VanishHandler.getInstance();
        if(target == null){
            boolean isVanished = handler.isVanished(sender);
            if (!isVanished) {
                handler.toggleVanish(sender);
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                getMsgSendConfig(sender, "Vanish", VANISH_ENABLED_SELF.getMessage());
            } else {
                handler.toggleVanish(sender);
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                getMsgSendConfig(sender, "Vanish", VANISH_DISABLED_SELF.getMessage());
            }
        }else{                            boolean isVanished = handler.isVanished(target);
            if (!isVanished) {
                handler.toggleVanish(target);
                String doneSound = MultiCommands.getInstance().getConfig().getString("cmd-done-sound");
                playSoundIfEnabled(target, Sound.valueOf(doneSound), 1f, 1f);
                playSoundIfEnabled(sender, Sound.valueOf(doneSound), 1f, 1f);
                getMsgSendConfig(target, "Vanish", VANISH_ENABLED_OTHER.getMessage());
                getMsgSendConfig(sender, "Vanish", VANISH_ENABLED_ADMIN.getMessage().replace("{0}", target.getName()));
            } else {
                handler.toggleVanish(target);
                String doneSound = MultiCommands.getInstance().getConfig().getString("cmd-done-sound");
                playSoundIfEnabled(target, Sound.valueOf(doneSound), 1f, 1f);
                playSoundIfEnabled(sender, Sound.valueOf(doneSound), 1f, 1f);
                getMsgSendConfig(target, "Vanish", VANISH_DISABLED_OTHER.getMessage());
                getMsgSendConfig(sender, "Vanish", VANISH_DISABLED_ADMIN.getMessage().replace("{0}", target.getName()));
            }

        }
    }
}
