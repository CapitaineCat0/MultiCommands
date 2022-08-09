package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import me.capitainecat0.multicommands.utils.VanishHandler;
import me.capitainecat0.multimaintenance.MultiMaintenance;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.upperlevel.spigot.book.BookUtil;

public class Whois implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission(Perms.WHOIS_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())) {
            MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
            return true;
        }
        else{
            if (args.length == 0) {
                if(MultiCommands.getInstance().HELP_BOOK_ENABLED){
                    double y = 500;
                    ItemStack book = BookUtil.writtenBook()
                            .author("CapitaineCat0")
                            .title("MultiCommands")
                            .pages(new BookUtil.PageBuilder()
                                            .add("§6§lAide de la commande §b§l/"+command.getName()+"§6:")
                                            .newLine().newLine()
                                            .add("§3Cette commande s'utilise avec des arguments. Sinon, bah je vous affiche quoi ?")
                                            .newLine()
                                            .newLine()
                                            .add("§3Sans nomer un joueur, je pourrais vous donner des informations inutiles ")
                                            .build(),
                                    new BookUtil.PageBuilder()
                                            .add("§3tout comme je le suis x)")
                                            .newLine()
                                            .newLine()
                                            .add(new TextComponent("§3Donc §5tapez:"))
                                            .add(BookUtil.TextBuilder.of("§6/whois §fXx_DarkEaven-du93_xX")
                                                    .onClick(BookUtil.ClickAction.runCommand("/whois"+sender.getName()))
                                                    .build()
                                            )
                                            .build()
                            )
                            .build();

                    BookUtil.openPlayer(((Player) sender).getPlayer(), book);
                }else{
                    sender.sendMessage(Messenger.WHOIS_ERROR.getMessage().replace("%cmd%", command.getName()));
                    return true;
                }
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
