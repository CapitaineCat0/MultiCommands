package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.Messenger.PING_OTHER_MSG;
import static me.capitainecat0.multicommands.utils.Messenger.PING_SELF_MSG;
import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;

public class PlayerPinger implements CommandsImpl {

    private final Player player;
    private final Player target;

    public PlayerPinger(Player player, Player target) {
        this.player = player;
        this.target = target;
    }
    @Override
    public void execute() {
        int ping;
        if(target == null){
            ping = player.getPing();
            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            if(ping < 50){
                sendMessage(player, PING_SELF_MSG.getMessage().replace("{0}", "<green>" + ping + " ms"));
            }
            if(ping > 50){
                sendMessage(player, PING_SELF_MSG.getMessage().replace("{0}", "<yellow>" + ping + " ms"));
            }
            if(ping > 300){
                sendMessage(player, PING_SELF_MSG.getMessage().replace("{0}", "<red>" + ping + " ms"));
            }
        }else {
            ping = target.getPing();
            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            if (ping < 50) {
                sendMessage(player, PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", "<green>" + ping + " ms"));
            }
            if (ping > 50) {
                sendMessage(player, PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", "<yellow>" + ping + " ms"));
            }
            if (ping > 300) {
                sendMessage(player, PING_OTHER_MSG.getMessage().replace("{0}", target.getName()).replace("{1}", "<red>" + ping + " ms"));
            }
        }
    }
}
