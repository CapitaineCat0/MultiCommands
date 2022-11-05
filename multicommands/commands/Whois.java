package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.data.BalanceData;
import me.capitainecat0.multicommands.utils.VanishHandler;
import me.capitainecat0.multimaintenance.MultiMaintenance;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Perms.ALL_PERMS;
import static me.capitainecat0.multicommands.utils.Perms.WHOIS_PERM;
import static me.capitainecat0.multicommands.utils.PluginCore.colored;

public class Whois implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if (!sender.hasPermission(WHOIS_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())) {
            if(soundEnabled()){
                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
            }
            getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
            return true;
        }
        else{
            if (args.length == 0) {
                if(soundEnabled()){
                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                }
                getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("%cmd%", command.getName()).replace("%args%", "<joueur>"));
                return true;
            }else if (args.length == 1) {
                if(soundEnabled()){
                    playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                }
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target != null) {
                    TextComponent coordinates = new TextComponent(colored("&6Coordonnées&8:"
                            + " &eX&c" + target.getLocation().getX()
                            + " &eY&c " + target.getLocation().getY()
                            + " &eZ&c " + target.getLocation().getZ()));
                    sendMessage(sender, "&6Vrai pseudo&8: " + target.getName());
                    sendMessage(sender, "&6Nickname&8: &c" + target.getCustomName());
                    sendMessage(sender, "&6UUID&8: &e" + target.getUniqueId());
                    sendMessage(sender, "&6Adresse IP&8: &c" + target.getAddress());
                    if (sender instanceof Player) {
                        coordinates.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + target.getName()));
                        sender.spigot().sendMessage(coordinates);
                    } else {
                        sendMessage(sender, "&6Coordonnées&8:"
                                + " &eX&c" + target.getLocation().getX()
                                + " &eY&c " + target.getLocation().getY()
                                + " &eZ&c " + target.getLocation().getZ());
                    }
                    sendMessage(sender, "&6Monde&8: &5" + target.getWorld().getName());
                    sendMessage(sender, "&6Fly-mode&8: &a" + target.getAllowFlight());
                    sendMessage(sender, "&6Gamemode&8: &d" + target.getGameMode());
                    sendMessage(sender, "&6God-Mode&8: &e" + target.isInvulnerable());
                    sendMessage(sender, "&6Operateur&8: &b" + target.isOp());
                    //sender.sendMessage("§6Client version: §f" );
                    sendMessage(sender, "&6Whitelisté&8: &f" + target.isWhitelisted());
                    sendMessage(sender, "&6Vanish&8: &7" + VanishHandler.getInstance().isVanished(target.getPlayer()));
                    sendMessage(sender, "&6Balance&8: &e" + BalanceData.getBalance(target));
                    final boolean installed;
                    boolean installed1;
                    try {
                        Class.forName("me.capitainecat0.multimaintenance.MultiMaintenance");
                        installed1 = true;
                        if (MultiMaintenance.getAUTHORIZED().contains(target.getUniqueId())) {
                            sendMessage(sender, "&6Accès lors de maintenance&8: &f Oui");
                        } else if (!MultiMaintenance.getAUTHORIZED().contains(target.getUniqueId())) {
                            sendMessage(sender, "&6Accès lors de maintenance&8: &f Non");
                        }
                    } catch (final Exception ex) {
                        installed1 = false;
                        sendMessage(sender, "&7Installez &cMulti§dMaintenance &7 pour afficher plus d'informations!");
                    }
                    installed = installed1;
                }else{
                    if(soundEnabled()){
                        playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                    }
                    sendMessage(sender, NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                }
            }
        }
        return false;
    }
}
