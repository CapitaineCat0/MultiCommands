package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.AFKHandler;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;

public class AFK implements CommandsImpl {

    private final Player sender;
    AFKHandler handler = AFKHandler.getInstance();
    private final Player player;

    public AFK(Player sender, Player player){
        this.sender = sender;
        this.player = player;
    }
    @Override
    public void execute() {
        if(!handler.isAFK(player)){
            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            handler.toggleAFK(player);
        }else if(handler.isAFK(player)){
            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            handler.toggleAFK(player);
        }
    }
}
