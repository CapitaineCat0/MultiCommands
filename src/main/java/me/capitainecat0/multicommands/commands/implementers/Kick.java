package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.KICK_ALERT;
import static me.capitainecat0.multicommands.utils.Messenger.KICK_PREFIX;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendBroadcastMessage;

public class Kick implements CommandsImpl {

    private final String[] args;
    private final Player target;

    public Kick(Player target, @NotNull String[] args) {
        this.target = target;
        this.args = args;
    }
    @Override
    public void execute() {
        StringBuilder bc = new StringBuilder();
        for(String part : args) {
            bc.append(part).append(" ");
        }
        String kickReason = bc.toString().replace(args[0], "");
        target.kickPlayer(kickReason);
        sendBroadcastMessage(KICK_ALERT.getMessage().replace("{0}", target.getName()).replace("{1}", kickReason).replace("{prefix}", KICK_PREFIX.getMessage()));
    }
}
