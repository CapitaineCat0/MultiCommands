package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import me.capitainecat0.multicommands.utils.FreezeHandler;
import me.capitainecat0.multicommands.utils.storage.FreezeData;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;

public class Freeze implements CommandsImpl {
    private final Player target;
    private final Player player;

    public Freeze(Player player, Player target) {
        this.player = player;
        this.target = target;
    }
    @Override
    public void execute() {
        final FreezeData data = new FreezeData();
        final boolean isFrozen = FreezeData.isFrozen(target);
        if (isFrozen && FreezeHandler.getInstance().isFreeze(target)) {
            FreezeHandler.getInstance().toggleFreeze(target);
            data.remove(target);
            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            getMsgSendConfig(player, "Freeze", FREEZE_TOGGLE_OFF.getMessage().replace("{0}", target.getName()));
        } else {
            FreezeHandler.getInstance().toggleFreeze(target);
            data.add(target);
            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            getMsgSendConfig(player, "Freeze", FREEZE_TOGGLE_ON.getMessage().replace("{0}", target.getName()));
        }
    }
}
