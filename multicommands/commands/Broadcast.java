package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.upperlevel.spigot.book.BookUtil;

public class Broadcast implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission(Perms.BROADCAST_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.CMD_NO_PERM.getMessage());
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
                                                .add("§3Par pur exemple, pour faire une annonce dans la rue, vous criez seulement `VOTRE ATTENTION !`?").build(),
                                        new BookUtil.PageBuilder()
                                                .add("§3Je ne crois pas, car à part si vous êtes stupide, personne au monde le ferais.")
                                                .newLine()
                                                .add(new TextComponent("§3Donc, pour utiliser cette commande comme il se doit, §5tapez"))
                                                .add(BookUtil.TextBuilder.of("§6/bc §cCette information est stupide !")
                                                        .onClick(BookUtil.ClickAction.runCommand("/bc Cette information est stupide !"))
                                                        .build()
                                                )
                                                .build()
                                )
                                .build();

                        BookUtil.openPlayer(((Player) sender).getPlayer(), book);
                    }else{
                        MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.BROADCAST_ERROR.getMessage().replace("%cmd%", command.getName()));
                        return true;
                    }
                }
                if(args.length == 1){

                    StringBuilder bc = new StringBuilder();
                    for(String part : args) {
                        bc.append(part).append(" ");
                    }
                    MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.BROADCAST_CMD.getMessage());
                    Bukkit.broadcastMessage(MultiCommands.colored(Messenger.BROADCAST_PREFIX.getMessage() + "&r " + bc));
                }
            }
        return false;
    }
}
