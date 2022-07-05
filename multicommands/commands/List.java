package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class List implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(MultiCommands.getInstance().DEBUG_ENABLED) {
            for (OfflinePlayer operators : Bukkit.getOperators()) {
                if (operators.getPlayer().hasPermission(Perms.ALL_PERMS.getPermission()) || operators.getPlayer().hasPermission(Perms.DEBUG_PERM.getPermission())) {
                    operators.getPlayer().sendMessage(Messenger.CMD_DEBUG_SUCCES.getMessage()
                            .replace("%p", sender.getName())
                            .replace("%cmd%", command.getName()));
                }
            }
        }
        sender.sendMessage("§aVous avez §e" + Bukkit.getOnlinePlayers().size() + " §ajoueurs sur §c" + Bukkit.getServer().getMaxPlayers() + " §aconnectés:");
        sender.sendMessage("§7Les crochets §8[] §7vous affichent le pseudo modifié avec §e/nick§7.");
        sender.sendMessage("§7Cliquer sur le pseudo vous téléportera au joueur.");
        sender.sendMessage(" ");
        for (Player p : Bukkit.getOnlinePlayers()) {
            if(Objects.equals(p.getCustomName(), p.getName())){
                TextComponent playerTP = new TextComponent("  §e- §b" + p.getName() + " §c-> §8[ null ]");
                if (sender instanceof Player) {
                    playerTP.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + p.getName()));
                    sender.spigot().sendMessage(playerTP);
                } else {
                    sender.sendMessage("  §e- §b" + p.getName() + " §c-> §8[]");
                }
                //sender.sendMessage("  §e- §b" + p.getName() + " §c-> §8[]");
            }else{
                TextComponent playerTP = new TextComponent("  §e- §b" + p.getName() + " §c-> §8[ "+p.getCustomName()+" §8]");
                if (sender instanceof Player) {
                    playerTP.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + p.getName()));
                    sender.spigot().sendMessage(playerTP);
                } else {
                    sender.sendMessage("  §e- §b" + p.getName() + " §c-> §8[ "+p.getCustomName()+" §8]");
                }
                //sender.sendMessage("  §e- §b" + p.getName() + " §c-> §8[ "+p.getCustomName()+" §8]");
            }
        }
        return false;
    }
}
