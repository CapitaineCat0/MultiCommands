package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.entity.Player;

public class Kill implements CommandsImpl {

    private final Player target;

    public Kill(Player target){
        this.target = target;
    }
    @Override
    public void execute() {
        target.setHealth(0.0);
    }
}
