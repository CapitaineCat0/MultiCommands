package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.KICK_ALERT;
import static me.capitainecat0.multicommands.utils.Messenger.KICK_PREFIX;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendBroadcastMessage;

public class Kick implements CommandsImpl {

    private final String message;
    private final Player target;

    public Kick(Player target, @NotNull String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        sb.append(args[args.length - 1]);
        this.target = target;
        this.message = sb.toString();
    }
    @Override
    public void execute() {
        String kickReason = message.replace(target.getName(), "");
        target.kickPlayer(kickReason);
        sendBroadcastMessage(KICK_ALERT.getMessage().replace("{0}", target.getName()).replace("{1}", kickReason).replace("{prefix}", KICK_PREFIX.getMessage()));
    }
}
