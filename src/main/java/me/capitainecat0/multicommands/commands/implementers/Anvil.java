package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;

public class Anvil implements CommandsImpl {
    private final Player player;

    public Anvil(Player player) {
        this.player = player;
    }

    public void execute() {
        playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
        player.openAnvil(player.getLocation(), true);
    }
}
