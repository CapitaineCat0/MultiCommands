package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Nick implements CommandsImpl {

    private final Player player;
    private final String[] args;

    public Nick(Player player, String[] args){
        this.player = player;
        this.args = args;
    }
    @Override
    public void execute(){
        Component nick = Component.text(Arrays.toString(args));
        player.customName(nick);
        player.setCustomNameVisible(true);
    }
}
