package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;

public class EnderChest implements CommandsImpl{
    private final Player player;
    private final Player target;

    public EnderChest(Player player, Player target){
        this.player = player;
        this.target = target;
    }
    @Override
    public void execute() {
        player.openInventory(target.getEnderChest());
        playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
    }
}
