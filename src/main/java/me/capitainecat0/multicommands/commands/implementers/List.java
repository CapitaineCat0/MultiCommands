package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.Messenger.LIST;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;

public class List implements CommandsImpl {

    private final Player player;

    public List(Player player) {
        this.player = player;
    }
    @Override
    public void execute() {
        sendMessage(player, LIST.getMessage().replace("{0}", Bukkit.getOnlinePlayers().size()+"").replace("{1}", Bukkit.getServer().getMaxPlayers()+""));
        sendMessage(player,"");
        for (Player p : Bukkit.getOnlinePlayers()) {
            if(p.customName() == null){
                if (player != null) {
                    sendCommandMessage(player, "<yellow>- <aqua>" + p.getName() + " <red>-> <dark_gray>[ "+p.getName()+" <dark_gray>]", "tp "+player.getName() +" "+p.getName());
                } else {
                    sendConsoleMessage("  &e- &b" + p.getName() + " &c-> &8[]");
                }
                //sender.sendMessage("  §e- §b" + p.getName() + " §c-> §8[]");
            }else{
                if (player != null) {
                    sendCommandMessage(player, "<yellow>- <aqua>" + p.getName() + " <red>-> <dark_gray>[ "+p.customName()+" <dark_gray>]", "tp "+p.getName());
                } else {
                    sendConsoleMessage("  &e- &b" + p.getName() + " &c-> &8[ "+p.customName()+" &8]");
                }
            }
        }
    }
}
