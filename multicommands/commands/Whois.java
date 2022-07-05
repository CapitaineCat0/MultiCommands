package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import me.capitainecat0.multicommands.utils.VanishHandler;
import me.capitainecat0.multimaintenance.MultiMaintenance;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Whois implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission(Perms.WHOIS_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())) {
            sender.sendMessage(Messenger.CMD_NO_PERM.getMessage().replace("%cmd%", command.getName()));
            if(MultiCommands.getInstance().DEBUG_ENABLED) {
                for (OfflinePlayer operators : Bukkit.getOperators()) {
                    if (operators.getPlayer().hasPermission(Perms.ALL_PERMS.getPermission()) || operators.getPlayer().hasPermission(Perms.DEBUG_PERM.getPermission())) {
                        operators.getPlayer().sendMessage(Messenger.CMD_DEBUG_NO_PERM.getMessage()
                                .replace("%p", sender.getName())
                                .replace("%cmd%", command.getName()));
                    }
                }
            }
            return true;
        }
        else{
            if(MultiCommands.getInstance().DEBUG_ENABLED) {
                for (OfflinePlayer operators : Bukkit.getOperators()) {
                    if (operators.getPlayer().hasPermission(Perms.ALL_PERMS.getPermission()) || operators.getPlayer().hasPermission(Perms.DEBUG_PERM.getPermission())) {
                        operators.getPlayer().sendMessage(Messenger.CMD_DEBUG_SUCCES.getMessage()
                                .replace("%p", sender.getName())
                                .replace("%cmd%", command.getName()));
                    }
                }
            }
            if (args.length == 0) {
                sender.sendMessage(Messenger.WHOIS_ERROR.getMessage().replace("%cmd%", command.getName()));
            }
            if (args.length == 1) {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target != null) {
                    TextComponent coordinates = new TextComponent("§aCoordonnées§8:"
                            + " §eX §c" + target.getLocation().getX()
                            + " §eY§c " + target.getLocation().getY()
                            + " §eZ§c " + target.getLocation().getZ());
                    sender.sendMessage("§6Vrai pseudo§8: " + target.getName());
                    sender.sendMessage("§6Nickname§8: §c" + target.getCustomName());
                    sender.sendMessage("§6UUID§8: §e" + target.getUniqueId());
                    sender.sendMessage("§6Adresse IP§8: §c" + target.getAddress());
                    if (sender instanceof Player) {
                        coordinates.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + target.getName()));
                        sender.spigot().sendMessage(coordinates);
                    } else {
                        sender.sendMessage("§aCoordonnées§8:"
                                + " §eX §c" + target.getLocation().getX()
                                + " §eY§c " + target.getLocation().getY()
                                + " §eZ§c " + target.getLocation().getZ());
                    }
                    sender.sendMessage("§6Monde§8: §5" + target.getWorld().getName());
                    sender.sendMessage("§6Fly-mode§8: §a" + target.getAllowFlight());
                    sender.sendMessage("§6Gamemode§8: §d" + target.getGameMode());
                    sender.sendMessage("§6God-Mode§8: §e" + target.isInvulnerable());
                    sender.sendMessage("§6Operateur§8: §b" + target.isOp());
                    //sender.sendMessage("§6Client version: §f" );
                    sender.sendMessage("§6Whitelisté§8: §f" + target.isWhitelisted());
                    sender.sendMessage("§6Vanish§8: §7" + VanishHandler.getInstance().isVanished(target.getPlayer()));
                    final boolean installed;
                    boolean installed1;
                    try {
                        Class.forName("me.capitainecat0.multimaintenance.MultiMaintenance");
                        installed1 = true;
                        if (MultiMaintenance.getAUTHORIZED().contains(target.getUniqueId())) {
                            sender.sendMessage("§6Accès lors de maintenance§8: §f Oui");
                        } else if (!MultiMaintenance.getAUTHORIZED().contains(target.getUniqueId())) {
                            sender.sendMessage("§6Accès lors de maintenance§8: §f Non");
                        }
                    } catch (final Exception ex) {
                        installed1 = false;
                        sender.sendMessage("§7Installez §cMulti§dMaintenance §7 pour afficher plus d'informations!");
                    }
                    installed = installed1;
                }else{
                    sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                }
            }
        }
        return false;
    }
}
