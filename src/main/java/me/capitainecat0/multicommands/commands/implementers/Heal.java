package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;

public class Heal implements CommandsImpl {

    private final Player player;
    private final Player target;

    public Heal(Player player, Player target) {
        this.player = player;
        this.target = target;
    }

    @Override
    public void execute() {
        if (target != null) {
            if(target.getHealth() == 20){
                getMsgSendConfig(player, "Heal", HEAL_ALREADY_SENDER.getMessage().replace("{0}", target.getName()));
                return;
            }
            target.setHealth(20);
            playSoundIfEnabled(target, Sound.ENTITY_GENERIC_DRINK, 1f, 1f);
            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            getMsgSendConfig(target, "Heal", HEAL_OTHER.getMessage());
            getMsgSendConfig(player, "Heal", HEAL_OTHER_SENDER.getMessage().replace("{0}", target.getName()));
        } else{
            if(player.getHealth() == 20){
                getMsgSendConfig(player, "Heal", HEAL_ALREADY.getMessage());
                return;
            }
            player.setHealth(20);
            playSoundIfEnabled(player, Sound.ENTITY_GENERIC_DRINK, 1f, 1f);
            getMsgSendConfig(player, "Heal", HEAL_SELF.getMessage());
        }
    }
}
