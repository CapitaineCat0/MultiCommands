package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.MessengerUtils.sendActionBar;

public class ActionBar implements CommandsImpl {
    private final Player player;
    private final String message;

    public ActionBar(Player player, @NotNull String @NotNull [] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        sb.append(args[args.length - 1]);
        this.player = player;
        this.message = sb.toString();
    }
    @Override
    public void execute() {
        sendActionBar(player, message);
    }
}
