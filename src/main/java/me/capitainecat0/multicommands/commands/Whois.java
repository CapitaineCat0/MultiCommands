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
            if(!MultiCommands.getPermissions().has(sender, WHOIS_PERM.getPermission())){
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
                        sendMessage(sender,"<gold>Coordinates<dark_gray>:"
                                + " <gold>X<red>" + target.getLocation().getX()
                                + " <gold>Y<red> " + target.getLocation().getY()
                                + " <gold>Z<red> " + target.getLocation().getZ());
                        sendMessage(sender, "<gold>Real name<dark_gray>: " + target.getName());
                        sendMessage(sender, "<gold>Nickname<dark_gray>: <red>" + target.getCustomName());
                        sendMessage(sender, "<gold>UUID<dark_gray>: <yellow>" + target.getUniqueId());
                        sendMessage(sender, "<gold>IP Address<dark_gray>: <red>" + target.getAddress());
                        if (sender instanceof Player) {
                            sendCommandMessage(sender, "<gold>Click to teleport to <red>" + target.getName(), "/tp " + target.getName());
                        /*coordinates.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + target.getName()));
                        sendMessage(sender, coordinates.getFont());
                        //sender.spigot().sendMessage(coordinates);*/
                        } else {
                            sendMessage(sender,"<gold>Coordinates<dark_gray>:"
                                    + " <gold>X<red>" + target.getLocation().getX()
                                    + " <gold>Y<red> " + target.getLocation().getY()
                                    + " <gold>Z<red> " + target.getLocation().getZ());
                        }
                        sendMessage(sender, "<gold>World<dark_gray>: <purple>" + target.getWorld().getName());
                        sendMessage(sender, "<gold>Fly-mode<dark_gray>: <green>" + target.getAllowFlight());
                        sendMessage(sender, "<gold>Gamemode<dark_gray>: <light_purple>" + target.getGameMode());
                        sendMessage(sender, "<gold>God-Mode<dark_gray>: <yellow>" + target.isInvulnerable());
                        sendMessage(sender, "<gold>Operato<dark_gray>: <aqua>" + target.isOp());
                        //sender.sendMessage("§6Client version: §f" );
                        sendMessage(sender, "<gold>Whitelisted<dark_gray>: <white>" + target.isWhitelisted());
                        sendMessage(sender, "<gold>Vanished<dark_gray>: <gray>" + VanishHandler.getInstance().isVanished(target.getPlayer()));
                        sendMessage(sender, "<gold>Balance<dark_gray>: <yellow>" + BalanceData.getBalance(target));
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
            sendMessage(sender, CMD_ERROR.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
            sendSuggestCommandMessage(sender, CMD_ERROR_SUGGEST.getMessage(), "helpop" + CMD_ERROR_ASSISTANCE.getMessage().replace("<command>", command.getName()).replace("{e}", e.getMessage()));
        }
        return false;
    }
}
