package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.FreezeData;
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
import org.jetbrains.annotations.NotNull;
import xyz.upperlevel.spigot.book.BookUtil;

public class Freeze implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(!sender.hasPermission(Perms.FREEZE_PERM.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
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
            if(args.length == 0){
                if(MultiCommands.getInstance().HELP_BOOK_ENABLED){
                    ItemStack book = BookUtil.writtenBook()
                            .author("CapitaineCat0")
                            .title("MultiCommands")
                            .pages(new BookUtil.PageBuilder()
                                            .add("??6??lAide de la commande ??b??l/"+command.getName()+"??6:")
                                            .newLine().newLine()
                                            .add("??3Cette commande s'utilise avec des arguments. Sinon, bah elle serait stupide vous ne croyez pas ?")
                                            .newLine()
                                            .newLine()
                                            .add("??3Sans nomer un joueur, cel?? veut dire que vous souhaitez vous geler tout seul. ??7(compl??tement con)")
                                            .build(),
                                    new BookUtil.PageBuilder()
                                            .add("??3Ne vous connaissant pas, je ne vais pas vous traiter de con comme ??a. Surtout que je suis un livre,")
                                            .newLine()
                                            .add("??3et que si vous perdez votre temps ?? me lire, vous ??tes une personne tr??s stupide x)")
                                            .newLine()
                                            .newLine()
                                            .add(new TextComponent("??3Donc ??5tapez:"))
                                            .add(BookUtil.TextBuilder.of("??6/freeze ??fXx_DarkEaven-du93_xX")
                                                    .onClick(BookUtil.ClickAction.runCommand("/freeze "+sender.getName()))
                                                    .build()
                                            )
                                            .build()
                            )
                            .build();

                    BookUtil.openPlayer(((Player) sender).getPlayer(), book);
                }else{
                    sender.sendMessage(Messenger.FREEZE_ERROR.getMessage().replace("%cmd%", command.getName()));
                    return false;
                }
            }
            if(args.length == 1) {

                Player target = Bukkit.getPlayerExact(args[0]);
                if(target != null){
                    final FreezeData data = new FreezeData(target);
                    final boolean isFrozen = data.isFrozen();
                    if (isFrozen) {
                        data.setFrozen(false);
                        target.sendMessage(Messenger.FREEZE_TOGGLE_OFF.getMessage());
                        sender.sendMessage(Messenger.FREEZE_TOGGLE_OFF_ADMIN.getMessage().replace("%p", target.getName()));
                    } else {
                        data.setFrozen(true);
                        target.sendMessage(Messenger.FREEZE_TOGGLE_ON.getMessage());
                        sender.sendMessage(Messenger.FREEZE_TOGGLE_ON_ADMIN.getMessage().replace("%p", target.getName()));
                    }
                }else{
                    sender.sendMessage(Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                }
            }
        }
        return false;
    }
}