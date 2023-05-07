package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Messenger.LIST;
import static me.capitainecat0.multicommands.utils.Messenger.LIST_HOVER;

public class List implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(sender instanceof Player){
            if(soundEnabled()){
                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            }
        }

        sendMessage(sender, LIST.getMessage().replace("{0}", Bukkit.getOnlinePlayers().size()+"").replace("{1}", Bukkit.getServer().getMaxPlayers()+""));
        sendMessage(sender,"");
        for (Player p : Bukkit.getOnlinePlayers()) {
            if(Objects.equals(p.getCustomName(), p.getName())){
                if (sender instanceof Player) {
                    sendCommandMessage(sender, "  §e- §b" + p.getName() + " §c-> §8[]", "/tp"+p.getName());
                    sendHoverMessage(sender, "  §e- §b" + p.getName() + " §c-> §8[]", p.getName(), LIST_HOVER.getMessage().replace("{0}", p.getName()));
                    //sender.spigot().sendMessage(playerTP);
                } else {
                    sendMessage(sender,"  §e- §b" + p.getName() + " §c-> §8[]");
                }
                //sender.sendMessage("  §e- §b" + p.getName() + " §c-> §8[]");
            }else{
                if (sender instanceof Player) {
                    sendCommandMessage(sender, "  §e- §b" + p.getName() + " §c-> §8[ "+p.getCustomName()+" ]", "/tp"+p.getName());
                    sendHoverMessage(sender, "  §e- §b" + p.getName() + " §c-> §8[ "+p.getCustomName()+" ]", p.getCustomName(), LIST_HOVER.getMessage().replace("{0}", Objects.requireNonNull(p.getCustomName())));
                    //sender.spigot().sendMessage(playerTP);
                } else {
                    sendMessage(sender, "  §e- §b" + p.getName() + " §c-> §8[ "+p.getCustomName()+" §8]");
                }
                //sender.sendMessage("  §e- §b" + p.getName() + " §c-> §8[ "+p.getCustomName()+" §8]");
            }
        }
        return false;
    }
}
