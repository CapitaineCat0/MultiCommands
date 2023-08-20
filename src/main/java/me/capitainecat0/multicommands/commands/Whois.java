package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.data.BalanceData;
import me.capitainecat0.multicommands.utils.VanishHandler;
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

public class Whois implements CommandExecutor {
    
    /**
     * La commande &quot;/whois&quot; requiert la permission &quot;multicommands.whois&quot; pour fonctionner.
     * <br>Cette commande permet d'afficher les informations sur un joueur.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        try{
            if (!sender.hasPermission(WHOIS_PERM.getPermission()) || !sender.hasPermission(ALL_PERMS.getPermission())) {
                playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                getMsgSendConfig(sender, command.getName(), CMD_NO_PERM.getMessage());
                return true;
            }
            else{
                if (args.length == 0) {
                    if(sender instanceof Player){
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        getMsgSendConfig(sender, command.getName(), CMD_NO_ARGS.getMessage().replace("<command>", command.getName()).replace("{0}", "<player>"));
                        return true;
                    }

                }else if (args.length == 1) {
                    if(sender instanceof Player){
                        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                    }
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target != null) {
                    /*TextComponent coordinates = new TextComponent(colored("&6Coordonnées&8:"
                            + " &eX&c" + target.getLocation().getX()
                            + " &eY&c " + target.getLocation().getY()
                            + " &eZ&c " + target.getLocation().getZ()));*/
                        sendMessage(sender,"&6Coordinates&8:"
                                + " &eX&c" + target.getLocation().getX()
                                + " &eY&c " + target.getLocation().getY()
                                + " &eZ&c " + target.getLocation().getZ());
                        sendMessage(sender, "&6Real name&8: " + target.getName());
                        sendMessage(sender, "&6Nickname&8: &c" + target.getCustomName());
                        sendMessage(sender, "&6UUID&8: &e" + target.getUniqueId());
                        sendMessage(sender, "&6IP Address&8: &c" + target.getAddress());
                        if (sender instanceof Player) {
                        /*coordinates.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + target.getName()));
                        sendMessage(sender, coordinates.getFont());
                        //sender.spigot().sendMessage(coordinates);*/
                        } else {
                            sendMessage(sender, "&6Coordinates&8:"
                                    + " &eX&c" + target.getLocation().getX()
                                    + " &eY&c " + target.getLocation().getY()
                                    + " &eZ&c " + target.getLocation().getZ());
                        }
                        sendMessage(sender, "&6World&8: &5" + target.getWorld().getName());
                        sendMessage(sender, "&6Fly-mode&8: &a" + target.getAllowFlight());
                        sendMessage(sender, "&6Gamemode&8: &d" + target.getGameMode());
                        sendMessage(sender, "&6God-Mode&8: &e" + target.isInvulnerable());
                        sendMessage(sender, "&6Operato&8: &b" + target.isOp());
                        //sender.sendMessage("§6Client version: §f" );
                        sendMessage(sender, "&6Whitelisted&8: &f" + target.isWhitelisted());
                        sendMessage(sender, "&6Vanished&8: &7" + VanishHandler.getInstance().isVanished(target.getPlayer()));
                        sendMessage(sender, "&6Balance&8: &e" + BalanceData.getBalance(target));
                    /*try {
                        Class.forName("me.capitainecat0.multimaintenance.MultiMaintenance");
                        if (MultiMaintenance.getAUTHORIZED().contains(target.getUniqueId())) {
                            sendMessage(sender, "&6Maintenance access&8: &f Yes");
                        } else if (!MultiMaintenance.getAUTHORIZED().contains(target.getUniqueId())) {
                            sendMessage(sender, "&6Maintenance access&8: &f No");
                        }
                    } catch (final Exception ex) {
                        sendMessage(sender, "&7Install &cMulti&dMaintenance &7to show more informations!");
                    }*/
                    }else{
                        if(sender instanceof Player){
                            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1f, 1f);
                        }
                        sendMessage(sender, NOT_A_PLAYER.getMessage().replace("{0}", args[0]));
                    }
                }
            }
        }catch (Exception e){
            sendCommandExceptionMessage(e, command.getName());
        }
        return false;
    }
}
