package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.utils.AFKHandler;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import me.capitainecat0.multimaintenance.MultiMaintenance;
import me.capitainecat0.multicommands.utils.VanishHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class ServerInfo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(Perms.SERVERINFO_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            sender.sendMessage(Messenger.CMD_NO_PERM.getMessage().replace("%cmd%", command.getName()));
            return true;
        }
        else{
            sender.sendMessage("§a§m-+---------------+- §7 - §e§l{ §cServer Informations §e§l} §7- §a§m-+---------------+-");
            sender.sendMessage("§6IP §c/§6 Port: §c" + Bukkit.getServer().getIp() + " §e:§c " + Bukkit.getServer().getPort());
            sender.sendMessage("§6Jar: §c" + Bukkit.getServer().getVersion());
            sender.sendMessage("§6Version: §c" + Bukkit.getServer().getBukkitVersion());
            sender.sendMessage("§6Joueurs connectés: §c" + Bukkit.getServer().getOnlinePlayers().size() + "§e / §c" + Bukkit.getServer().getMaxPlayers());
            sender.sendMessage("§6Opérateurs: §7(§c" + Bukkit.getServer().getOperators().size() + "§7)");
            for (OfflinePlayer op : Bukkit.getOperators()) {
                sender.sendMessage("§c- §7" + op.getName());
            }
            sender.sendMessage("§6Whitelistés: §7(§c" + Bukkit.getServer().getWhitelistedPlayers().size() + "§7)");
            for(OfflinePlayer wl : Bukkit.getWhitelistedPlayers()){
                sender.sendMessage("§c- §7" + wl.getName());
            }
            sender.sendMessage("§6Bannis: §7(§c" + Bukkit.getServer().getBannedPlayers().size() + "§7) ");
            for(OfflinePlayer ban : Bukkit.getBannedPlayers()){
                sender.sendMessage("§c- §7" + ban.getName());
            }
            sender.sendMessage("§6Vanish: §7(§c" + VanishHandler.getVanished().size() + "§7)");
            for(OfflinePlayer vanished : VanishHandler.getVanished()){
                sender.sendMessage("§c- §7" + vanished.getName());
            }
            sender.sendMessage("§6AFK: §7(§c" + AFKHandler.getAFK().size() + "§7)");
            for(OfflinePlayer afk : AFKHandler.getAFK()){
                sender.sendMessage("§c- §7" + afk.getName());
            }
            try{
                Class.forName("me.capitainecat0.multimaintenance.MultiMaintenance");
                OfflinePlayer player;

                sender.sendMessage("§6Joueurs whitelistés §8(multimaintenance) §7(§c" + MultiMaintenance.getAUTHORIZED().size() + "§7)");
                for (UUID uuid : MultiMaintenance.getAUTHORIZED()) {
                    player = Bukkit.getOfflinePlayer(uuid);
                    sender.sendMessage("§e-§a " + player.getName());
                }
                if (MultiMaintenance.ENABLED) {
                    sender.sendMessage(me.capitainecat0.multimaintenance.utils.Messenger.MAINTENANCE_STATUS.getMessage().replace("%status", "§aactive"));
                }else {
                    sender.sendMessage(me.capitainecat0.multimaintenance.utils.Messenger.MAINTENANCE_STATUS.getMessage().replace("%status", "§cdésactivée"));
                }
            }catch(final Exception ex) {
                sender.sendMessage("§7Installez §cMulti§dMaintenance §7 pour afficher plus d'informations!");
            }
            sender.sendMessage("§a§m-+----------------------------------------------------------------+-");
        }
        return false;
    }
}
