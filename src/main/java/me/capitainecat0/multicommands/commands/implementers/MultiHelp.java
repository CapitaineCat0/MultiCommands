package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import me.capitainecat0.multicommands.utils.inventories.HelpGUI;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;

public class MultiHelp implements CommandsImpl {

    private final Player player;
    public MultiHelp(Player player) {
        this.player = player;
    }
    @Override
    public void execute() {
        if (MultiCommands.getInstance().getConfig().getBoolean("enable-help-gui")) {
            (new HelpGUI()).open(player);
        } else {
            sendMessage(player, "<green><strikethrough>-+------------+-</strikethrough><gray> - <yellow><bold>{</bold> <gold>MultiCommands<gray> - <dark_green>[<light_purple>help<dark_green>] <yellow><bold>}</bold> <gray>- <green><strikethrough>-+-------------+-</strikethrough>"+
                    "<newline><gold> - <aqua>/afk " + HELP_AFK.getMessage() +
                    "<newline><gold> - <aqua>/alert <message> " + HELP_ALERT.getMessage() +
                    "<newline><gold> - <aqua>/anvil " + HELP_ANVIL.getMessage() +
                    "<newline><gold> - <aqua>/ban <player> " + HELP_BAN.getMessage() +
                    "<newline><gold> - <aqua>/banip <player> "+
                    "<newline><gold> - <aqua>/bc | broadcast <message> " + HELP_BROADCAST.getMessage() +
                    "<newline><gold> - <aqua>/ci | clearinventory [player] " + HELP_CLEARINV.getMessage() +
                    "<newline><gold> - <aqua>/craft | workbench " + HELP_CRAFT.getMessage() +
                    "<newline><gold> - <aqua>/compass " + HELP_COMPASS.getMessage() +
                    "<newline><gold> - <aqua>/ec | enderchest [player] " + HELP_ENDERCHEST.getMessage() +
                    //"<newline><gold> - <aqua>/eco | economy <add | set | remove | reset> <player> " + HELP_ECONOMY.getMessage() +
                    "<newline><gold> - <aqua>/feed [player] " + HELP_FEED.getMessage() +
                    "<newline><gold> - <aqua>/fly [player] " + HELP_FLY.getMessage() +
                    "<newline><gold> - <aqua>/freeze <player> " + HELP_FREEZE.getMessage() +
                    "<newline><gold> - <aqua>/furnace " + HELP_FURNACE.getMessage() +
                    "<newline><gold> - <aqua>/gm | gamemode <mode> [player] " + HELP_GAMEMODE.getMessage() +
                    "<newline><gold> - <aqua>/god [player] " + HELP_GOD.getMessage() +
                    "<newline><gold> - <aqua>/heal [player] " + HELP_HEAL.getMessage() +
                    "<newline><gold> - <aqua>/helpop <message> " + HELP_HELPOP.getMessage() +
                    "<newline><gold> - <aqua>/invsee <player> " + HELP_INVSEE.getMessage() +
                    "<newline><gold> - <aqua>/kick <player> " + HELP_KICK.getMessage() +
                    "<newline><gold> - <aqua>/kickall " + HELP_KICKALL.getMessage() +
                    "<newline><gold> - <aqua>/kill [player] " + HELP_KILL.getMessage() +
                    "<newline><gold> - <aqua>/level <add | set | remove> [player] " + HELP_LEVEL.getMessage() +
                    "<newline><gold> - <aqua>/list " + HELP_LIST.getMessage() +
                    "<newline><gold> - <aqua>/mi | multiinfos " + HELP_MULTIINFOS.getMessage() +
                    "<newline><gold> - <aqua>/mute <player> [reason] " + HELP_MUTE.getMessage() +
                    "<newline><gold> - <aqua>/nick " + HELP_NICK.getMessage() +
                    "<newline><gold> - <aqua>/ping | playerpinger " + HELP_PING.getMessage() +
                    "<newline><gold> - <aqua>/pl | plugins " + HELP_PLUGINS.getMessage() +
                    "<newline><gold> - <aqua>/mreload | multireload " + HELP_RELOAD.getMessage() +
                    "<newline><gold> - <aqua>/serverinfo " + HELP_SERVERINFO.getMessage() +
                    "<newline><gold> - <aqua>/setspawn " + HELP_SETSPAWN.getMessage() +
                    "<newline><gold> - <aqua>/spawn " + HELP_SPAWN.getMessage() +
                    "<newline><gold> - <aqua>/tp | teleport <coordinates | player> " + HELP_TELEPORT.getMessage() +
                    "<newline><gold> - <aqua>/top " + HELP_TOP.getMessage() +
                    "<newline><gold> - <aqua>/unban <player> " + HELP_UNBAN.getMessage() +
                    "<newline><gold> - <aqua>/unbanip <player> " +
                    "<newline><gold> - <aqua>/v | vanish " + HELP_VANISH.getMessage() +
                    "<newline><gold> - <aqua>/whois <player> " + HELP_WHOIS.getMessage() +
                    "<newline>");
        }
    }
}
