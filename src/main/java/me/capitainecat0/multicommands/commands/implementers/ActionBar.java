package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.MessengerUtils.sendActionBar;

public class ActionBar implements CommandsImpl {
    private final Player player;
    private final String msg;

    public ActionBar(Player player, @NotNull String @NotNull [] message) {
        StringBuilder bc = new StringBuilder();
        for (String part : message) {
            bc.append(part).append(" ");
        }
        this.player = player;
        this.msg = String.valueOf(bc);
    }
    @Override
    public void execute() {
        sendActionBar(player, msg);
    }
}
