package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.entity.Player;

public class Invsee implements CommandsImpl {

    private final Player player;
    private final Player target;

    public Invsee(Player player, Player target) {
        this.player = player;
        this.target = target;
    }
    @Override
    public void execute() {
        player.openInventory(target.getInventory());
    }
}
