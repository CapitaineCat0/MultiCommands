package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.Messenger.MULTIINFOS_MSG;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;

public class MultiInfos implements CommandsImpl {

    private final Player player;

    public MultiInfos(Player player) {
        this.player = player;
    }
    @Override
    public void execute() {
        try {
            Class.forName("me.capitainecat0.multimaintenance.MultiMaintenance");
            assert MultiCommands.getInstance().getDescription().getDescription() != null;
            sendMessage(player, MULTIINFOS_MSG.getMessage()
                    .replace("%plname%", MultiCommands.getInstance().getDescription().getName())
                    .replace("%author%", "CapitaineCat0")
                    .replace("%version%", MultiCommands.getInstance().getDescription().getVersion())
                    .replace("%description%", MultiCommands.getInstance().getDescription().getDescription()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
