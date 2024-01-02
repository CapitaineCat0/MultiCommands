package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;

public class Fly implements CommandsImpl {
    private final Player player;
    private final Player target;

    public Fly(Player player, Player target) {
        this.player = player;
        this.target = target;
    }
    @Override
    public void execute() {
        if (target != null) {
            boolean allowFlight = target.getAllowFlight();
            target.setAllowFlight(!allowFlight);
            String messageTarget = allowFlight ? FLY_TOGGLE_OFF_BY_ADMIN.getMessage() : FLY_TOGGLE_ON_BY_ADMIN.getMessage();
            String messageSender = allowFlight ? FLY_TOGGLE_OFF_SENDER.getMessage() : FLY_TOGGLE_ON_SENDER.getMessage();
            getMsgSendConfig(target, "Fly", messageTarget);
            getMsgSendConfig(player, "Fly", messageSender.replace("{0}", target.getName()));
        }else{
            boolean allowFlight = player.getAllowFlight();
            player.setAllowFlight(!allowFlight);
            String message = allowFlight ? FLY_TOGGLE_OFF.getMessage() : FLY_TOGGLE_ON.getMessage();
            getMsgSendConfig(player, "Fly", message.replace("{prefix}", PLUGIN_PREFIX.getMessage()));
        }
    }
}
