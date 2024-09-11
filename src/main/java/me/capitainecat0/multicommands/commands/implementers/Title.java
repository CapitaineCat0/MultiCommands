package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.Messenger.TITLE_SENT_TO_ALL;
import static me.capitainecat0.multicommands.utils.Messenger.TITLE_SENT_TO_OTHER;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendTitle;

public class Title implements CommandsImpl {

    private final Player sender;
    private final Player target;
    private final String[] args;
    private final String message;

    public Title(Player sender, Player target, String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        sb.append(args[args.length - 1]);
        this.sender = sender;
        this.target = target;
        this.args = args;
        this.message = sb.toString();
    }
    @Override
    public void execute() {
        if(target != null){
            sendMessage(sender, TITLE_SENT_TO_OTHER.getMessage().replace("{0}", target.getName()));
            sendTitle(target, args[1], message.replace(args[0], "").replace(args[1], ""));
        }else{
            sendMessage(sender, TITLE_SENT_TO_ALL.getMessage());
            sendTitle(args[0], message.replace(args[0], ""));
        }
    }
}
