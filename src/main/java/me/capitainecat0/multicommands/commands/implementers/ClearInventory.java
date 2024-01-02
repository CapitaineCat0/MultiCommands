package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.Messenger.CLEARINV_SELF_DONE;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;

public class ClearInventory implements CommandsImpl {

    private final Player player;

    public ClearInventory(Player player) {
        this.player = player;
    }
    @Override
    public void execute() {
        player.getInventory().clear();
        playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
        getMsgSendConfig(player, "ClearInventory", CLEARINV_SELF_DONE.getMessage());
    }
}
