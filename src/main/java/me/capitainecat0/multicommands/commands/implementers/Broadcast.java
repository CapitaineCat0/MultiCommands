package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.ALERT_PREFIX;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendBroadcastMessage;

public class Broadcast implements CommandsImpl {
    private final String msg;

    public Broadcast(String @NotNull [] message){
        StringBuilder bc = new StringBuilder();
        for (String part : message) {
            bc.append(part).append(" ");
        }
        this.msg = String.valueOf(bc);
    }
    @Override
    public void execute() {
        sendBroadcastMessage(ALERT_PREFIX.getMessage() + "<reset> " + msg);
    }
}
