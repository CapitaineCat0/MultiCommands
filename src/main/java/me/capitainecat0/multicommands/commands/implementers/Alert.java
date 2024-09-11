package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.ALERT_PREFIX;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendBroadcastMessage;

public class Alert implements CommandsImpl{
    private final String message;

    public Alert(String @NotNull [] args){
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        sb.append(args[args.length - 1]);
        this.message = sb.toString();
    }
    @Override
    public void execute() {
        sendBroadcastMessage(ALERT_PREFIX.getMessage() + "<reset> " + message);
    }
}
