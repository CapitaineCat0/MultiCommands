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

    public Title(Player sender, Player target, String[] args) {
        this.sender = sender;
        this.target = target;
        this.args = args;
    }
    @Override
    public void execute() {
        if(target != null){
            StringBuilder bc = new StringBuilder();
            for(String part : args) {
                bc.append(part).append(" ");
            }
            sendMessage(sender, TITLE_SENT_TO_OTHER.getMessage().replace("{0}", target.getName()));
            sendTitle(target, args[1], bc.toString().replace(args[0], "").replace(args[1], ""));
        }else{
            StringBuilder bc = new StringBuilder();
            for(String part : args) {
                bc.append(part).append(" ");
            }
            sendMessage(sender, TITLE_SENT_TO_ALL.getMessage());
            sendTitle(args[0], bc.toString().replace(args[0], ""));
        }
    }
}
