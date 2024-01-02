package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.entity.Player;

public class Craft implements CommandsImpl {

    private final Player player;

    public Craft(Player player){
        this.player = player;
    }
    @Override
    public void execute() {
        player.openWorkbench(player.getLocation(), true);
    }
}
