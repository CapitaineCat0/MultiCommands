package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;

public class Feed implements CommandsImpl {

    private final Player target;
    private final Player player;

    public Feed(Player player, Player target) {
        this.player = player;
        this.target = target;
    }
    @Override
    public void execute() {
        if(target.getFoodLevel() != 20){
            target.setFoodLevel(20);
            playSoundIfEnabled(target, Sound.ENTITY_GENERIC_EAT);
            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            getMsgSendConfig(player, "Feed", FEED_OTHER_SENDER.getMessage().replace("{0}", target.getName()));
            getMsgSendConfig(target, "Feed", FEED_OTHER.getMessage());
        }else{
            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            getMsgSendConfig(player, "Feed", FEED_ALREADY_SENDER.getMessage().replace("{0}", target.getName()));
        }
    }
}
