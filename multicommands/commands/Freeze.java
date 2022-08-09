package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.FreezeData;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
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
                                            .add("§3Cette commande s'utilise avec des arguments. Sinon, bah elle serait stupide vous ne croyez pas ?")
                                            .newLine()
                                            .newLine()
                                            .add("§3Sans nomer un joueur, celà veut dire que vous souhaitez vous geler tout seul. §7(complétement con)")
                                            .build(),
                                    new BookUtil.PageBuilder()
                                            .add("§3Ne vous connaissant pas, je ne vais pas vous traiter de con comme ça. Surtout que je suis un livre,")
                                            .newLine()
                                            .add("§3et que si vous perdez votre temps à me lire, vous êtes une personne très stupide x)")
                                            .newLine()
                                            .newLine()
                                            .add(new TextComponent("§3Donc §5tapez:"))
                                            .add(BookUtil.TextBuilder.of("§6/freeze §fXx_DarkEaven-du93_xX")
                                                    .onClick(BookUtil.ClickAction.runCommand("/freeze "+sender.getName()))
                                                    .build()
                                            )
                                            .build()
                            )
                            .build();

                    BookUtil.openPlayer(((Player) sender).getPlayer(), book);
                }else{
                    MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.FREEZE_ERROR.getMessage());
                    return true;
                }
            }
            if(args.length == 1) {

                Player target = Bukkit.getPlayerExact(args[0]);
                if(target != null){
                    final FreezeData data = new FreezeData(target);
                    final boolean isFrozen = data.isFrozen();
                    if (isFrozen) {
                        data.setFrozen(false);
                        MultiCommands.getInstance().getMsgSendConfig(target, command.getName(), Messenger.FREEZE_TOGGLE_OFF.getMessage());
                        MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.FREEZE_TOGGLE_OFF_ADMIN.getMessage().replace("%p", target.getName()));
                        sender.sendMessage(Messenger.FREEZE_TOGGLE_OFF_ADMIN.getMessage().replace("%p", target.getName()));
                    } else {
                        data.setFrozen(true);
                        MultiCommands.getInstance().getMsgSendConfig(target, command.getName(), Messenger.FREEZE_TOGGLE_ON.getMessage());
                        MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.FREEZE_TOGGLE_ON_ADMIN.getMessage().replace("%p", target.getName()));
                    }
                }else{
                    MultiCommands.getInstance().getMsgSendConfig(sender, command.getName(), Messenger.NOT_A_PLAYER.getMessage().replace("%p", args[0]));
                }
            }
        }
        return false;
    }
}