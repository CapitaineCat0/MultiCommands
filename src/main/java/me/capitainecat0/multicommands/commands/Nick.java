package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.upperlevel.spigot.book.BookUtil;

public class Nick implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(Perms.NICKNAME_PERMS.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
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
                                            .add("§3Cette commande s'utilise avec des arguments. Sinon, bah elle serait stupide vous ne croyez pas ?")
                                            .newLine()
                                            .newLine()
                                            .add("§3Sans donner un nom à vous assigner, celà veut donc dire que vous me donnez la possibilité d'en ")
                                            .build(),
                                    new BookUtil.PageBuilder()
                                            .add("§3choisir un à votre place, parmis la vaste liste de §c§k000 §3noms stupides x)")
                                            .newLine()
                                            .newLine()
                                            .add(new TextComponent("§3Donc §5tapez:"))
                                            .add(BookUtil.TextBuilder.of("§6/nick §fXx_DarkEaven-du93_xX")
                                                    .onClick(BookUtil.ClickAction.runCommand("/nick Dark_Golum"))
                                                    .build()
                                            )
                                            .build()
                            )
                            .build();

                    BookUtil.openPlayer(((Player) sender).getPlayer(), book);
                }else{
                    sender.sendMessage(Messenger.NICKNAME_ERROR.getMessage());
                    return true;
                }
            }
            else if(args.length == 1){
                Player player = (Player) sender;
                sender.sendMessage(Messenger.NICKNAME_DONE.getMessage().replace("%newName", MultiCommands.colored(args[0])));
                player.setCustomName(MultiCommands.colored(args[0]));
                player.setCustomNameVisible(true);
            }
            if(sender instanceof ConsoleCommandSender){
                sender.sendMessage(Messenger.NO_CONSOLE_COMMAND.getMessage());
                return true;
            }
        }
        return false;
    }
}
