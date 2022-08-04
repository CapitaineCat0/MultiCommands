package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.upperlevel.spigot.book.BookUtil;

public class Alert implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(Perms.ALERT_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            sender.sendMessage(Messenger.CMD_NO_PERM.getMessage().replace("%cmd%", command.getName()));
            return true;
        }
        else{
                if(args.length == 0){
                    if(MultiCommands.getInstance().HELP_BOOK_ENABLED){
                        ItemStack book = BookUtil.writtenBook()
                                .author("CapitaineCat0")
                                .title("MultiCommands")
                                .pages(new BookUtil.PageBuilder()
                                                .add("§6§lAide de la commande §b§l/"+command.getName()+"§6:")
                                                .newLine().newLine()
                                                .add("§3Cette commande s'utilise avec des arguments. Sinon, bah elle serait inutile vous ne croyez pas ?")
                                                .newLine()
                                                .add("§3Par pur exemple, pour interpeler quelqu'un sur un danger dans la rue §7(oui je suis précis)§3, vous criez seulement `ALERTE !`?")
                                                .build(),
                                        new BookUtil.PageBuilder()
                                                .add("§3Je ne crois pas. Parceque déjà, je n'ôserais pas, mais en plus c'est inutile si cette personne ne sait pas quoi faire!")
                                                .newLine()
                                                .add(new TextComponent("§3Donc, pour utiliser cette commande comme il se doit, §5tapez"))
                                                .add(BookUtil.TextBuilder.of("§6/alert §cLe fondateur est un grand gamin taré !")
                                                        .onClick(BookUtil.ClickAction.runCommand("/alert Le fondateur est un grand gamin taré !"))
                                                        .build()
                                                )
                                                .build()
                                )
                                .build();

                        BookUtil.openPlayer(((Player) sender).getPlayer(), book);
                    }else{
                        sender.sendMessage(Messenger.ALERT_ERROR.getMessage().replace("%cmd%", command.getName()));
                        return true;
                    }

                }
                if(args.length == 1){

                    StringBuilder bc = new StringBuilder();
                    for(String part : args) {
                        bc.append(part).append(" ");
                    }
                    sender.sendMessage(Messenger.ALERT_CMD.getMessage());
                    Bukkit.broadcastMessage(MultiCommands.colored(Messenger.ALERT_PREFIX.getMessage() + "&r " + bc));
                }
        }
        return false;
    }
}
