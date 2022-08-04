package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.upperlevel.spigot.book.BookUtil;

public class Invsee implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(Perms.INVSEE_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            sender.sendMessage(Messenger.CMD_NO_PERM.getMessage().replace("%cmd%", command.getName()));
            return true;
        }
        else{
            if(sender instanceof Player){
                if(args.length == 0){
                    if(MultiCommands.getInstance().HELP_BOOK_ENABLED){
                        ItemStack book = BookUtil.writtenBook()
                                .author("CapitaineCat0")
                                .title("MultiCommands")
                                .pages(new BookUtil.PageBuilder()
                                                .add("§6§lAide de la commande §b§l/"+command.getName()+"§6:")
                                                .newLine().newLine()
                                                .add("§3Cette commande s'utilise avec des arguments. Sinon, bah elle serait stupide vous ne croyez pas ?")
                                                .newLine()
                                                .newLine()
                                                .add("§3Sans nomer un joueur, vous ne pourrez pas stalker l'inventaire de cette personne .")
                                                .build(),
                                        new BookUtil.PageBuilder()
                                                .add("§3Oui, je suis un livre qui ne mache pas ses mots, et qui vous traite ouvertement de stalker x)")
                                                .newLine()
                                                .newLine()
                                                .add(new TextComponent("§3Donc §5tapez:"))
                                                .add(BookUtil.TextBuilder.of("§6/invsee §fXx_DarkEaven-du93_xX")
                                                        .onClick(BookUtil.ClickAction.runCommand("/invsee "+sender.getName()))
                                                        .build()
                                                )
                                                .build()
                                )
                                .build();

                        BookUtil.openPlayer(((Player) sender).getPlayer(), book);
                    }else{
                        sender.sendMessage(Messenger.INVSEE_ERROR.getMessage().replace("%cmd%", command.getName()));
                        return true;
                    }
                }if(args.length == 1){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target != null){
                        ((Player) sender).openInventory(target.getInventory());
                        sender.sendMessage(Messenger.INVSEE_ADMIN.getMessage().replace("%p", target.getName()));
                    }else{
                        sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                    }
                }
            }else if(sender instanceof ConsoleCommandSender){
                sender.sendMessage(Messenger.NO_CONSOLE_COMMAND.getMessage().replace("%cmd%", command.getName()));
            }
        }
        return false;
    }
}
