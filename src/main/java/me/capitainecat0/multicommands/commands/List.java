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

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class List implements CommandExecutor {
    
    /**
     *
     * The List commands can list all connected players
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        try{
            if(sender instanceof Player){
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            }
            sendMessage(sender, LIST.getMessage().replace("{0}", Bukkit.getOnlinePlayers().size()+"").replace("{1}", Bukkit.getServer().getMaxPlayers()+""));
            sendMessage(sender,"");
            for (Player p : Bukkit.getOnlinePlayers()) {
                if(p.getCustomName() == null){
                    if (sender instanceof Player) {
                        sendCommandMessage((Player) sender, "<yellow>- <aqua>" + p.getName() + " <red>-> <dark_gray>[ "+p.getName()+" <dark_gray>]", "tp "+p.getName());
                        //sendCommandMessage((Player) sender, adventureColors(" " + p.getName() + "  ->  [ "+p.getName()+"  ]"), "tp "+p.getName());
                        //sender.spigot().sendMessage(playerTP);
                    } else {
                        sendConsoleMessage("  &e- &b" + p.getName() + " &c-> &8[]");
                    }
                    //sender.sendMessage("  §e- §b" + p.getName() + " §c-> §8[]");
                }else{
                    if (sender instanceof Player) {
                        sendCommandMessage((Player) sender, "<yellow>- <aqua>" + p.getName() + " <red>-> <dark_gray>[ "+p.getCustomName()+" <dark_gray>]", "tp "+p.getName());
                        //sender.spigot().sendMessage(playerTP);
                    } else {
                        sendConsoleMessage("  &e- &b" + p.getName() + " &c-> &8[ "+p.getCustomName()+" &8]");
                    }
                    //sender.sendMessage("  §e- §b" + p.getName() + " §c-> §8[ "+p.getCustomName()+" §8]");
                }
            }
        }catch(Exception e){
            sendCommandExceptionMessage(e, command.getName());
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return false;
    }
}
