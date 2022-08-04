package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import net.md_5.bungee.api.chat.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.upperlevel.spigot.book.BookUtil;


public class MultiHelp implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            if(MultiCommands.getInstance().HELP_BOOK_ENABLED){
                ItemStack book = BookUtil.writtenBook()
                        .author("CapitaineCat0")
                        .title("MultiCommands")
                        .pages(
                                new BaseComponent[]{
                                        new TextComponent("§3Manuel d'aide \n§3de \n§6§lMultiCommands \n§av "
                                                + MultiCommands.instance().getDescription().getVersion())
                                },
                                new BookUtil.PageBuilder()
                                        .add(new TextComponent("§3Plugin développé par "))
                                        .add(
                                                BookUtil.TextBuilder.of("§b" + MultiCommands.getInstance().getDescription().getAuthors())
                                                        //.onClick(BookUtil.ClickAction.openUrl("https://www.spigotmc.org"))
                                                        .onHover(BookUtil.HoverAction.showText("Développeur(s)"))
                                                        .build()
                                        )
                                        .add(" §3pour §5§lHall§d§lOf§5§lGames §3!")
                                        /*.add(
                                                new ComponentBuilder("Bukkit")
                                                        .color(net.md_5.bungee.api.ChatColor.BLUE)
                                                        .bold(true)
                                                        .italic(true)
                                                        .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://bukkit.org"))
                                                        .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[]{new TextComponent("Open bukkit!")}))
                                                        .create()
                                        )*/
                                        .newLine().newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7Notre §9§lDiscord")
                                                        .onClick(BookUtil.ClickAction.openUrl("https://hallofgames.fr/discord"))
                                                        .onHover(BookUtil.HoverAction.showText("§3Aller sur Discord"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7Notre §6§lSite web")
                                                        .onClick(BookUtil.ClickAction.openUrl("https://hallofgames.fr"))
                                                        .onHover(BookUtil.HoverAction.showText("§3Aller sur notre site"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7Notre chaîne §c§lYou§0§lTube")
                                                        .onClick(BookUtil.ClickAction.openUrl("https://www.youtube.com/channel/UChEDfbc5fxc8PQdTZJhR3tg"))
                                                        .onHover(BookUtil.HoverAction.showText("§3Aller sur Youtube"))
                                                        .build()
                                        )
                                        .newLine().newLine()
                                        .add("§3Commandes à la page suivante.")
                                        .add("§7(cliquez sur les commandes)")
                                        .build(),
                                new BookUtil.PageBuilder()
                                        .add("§6§lCommandes §dpage 1§6:")
                                        .newLine().newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/afk")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("afk").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/afk"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/alert")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("alert").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/alert"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/bc §7ou §6/broadcast")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("broadcast").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/broadcast"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/ci §7ou §6/clearinventory")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("clearinventory").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/ci"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/craft")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("craft").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/craft"))
                                                        .build()
                                        )
                                        /*.newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/debug")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("debug").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/debug"))
                                                        .build()
                                        )*/
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/ec §7ou §6/enderchest")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("enderchest").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/enderchest"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/feed")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("feed").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/feed"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/fly")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("fly").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/fly"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/freeze")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("freeze").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/freeze"))
                                                        .build()
                                        )
                                        .build(),
                                new BookUtil.PageBuilder()
                                        .add("§6§lCommandes §dpage 2§6:")
                                        .newLine().newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/gm §7ou §6/gamemode")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("gamemode").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/gamemode"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/god")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("god").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/god"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/heal")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("heal").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/heal"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/helpop")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("helpop").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/helpop"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/invsee")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("invsee").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/invsee"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/list")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("list").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/list"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/minfos §7ou §6/multiinfos")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("multiinfos").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/multiinfos"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/nick")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("nick").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/nick"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/ping")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("ping").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/ping"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/pl §7ou §6/plugins")
                                                        .onHover(BookUtil.HoverAction.showText("§7Afficher les plugins du serveur"))
                                                        .onClick(BookUtil.ClickAction.runCommand("/plugins"))
                                                        .build()
                                        )
                                        .build(),
                                new BookUtil.PageBuilder()
                                        .add("§6§lCommandes §dpage 3§6:")
                                        .newLine().newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/serverinfo")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("serverinfo").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/serverinfo"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/thelp §7ou §6/togglehelp")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("togglehelp").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/togglehelp"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/tp §7ou §6/teleport")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("teleport").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/teleport"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/v §7ou §6/vanish")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("vanish").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/vanish"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder.of("§7- §6/whois")
                                                        .onHover(BookUtil.HoverAction.showText(MultiCommands.getInstance().getCommand("whois").getDescription()))
                                                        .onClick(BookUtil.ClickAction.runCommand("/whois"))
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

                BookUtil.openPlayer(((Player) sender).getPlayer(), book);
            }else{
                sender.sendMessage("§ele manuel d'aide de MultiCommands à été §cdésactivé§e!");
            }

        }else if(sender instanceof ConsoleCommandSender){
            sender.sendMessage(Messenger.NO_CONSOLE_COMMAND.getMessage().replace("%cmd%", command.getName()));
        }
        return false;
    }
}
