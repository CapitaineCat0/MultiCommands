package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.inventories.HelpGUI;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.MessengerUtils.*;
import static me.capitainecat0.multicommands.utils.Messenger.*;


public class MultiHelp implements CommandExecutor {

    /**
     *
     * The MultiHelp command sends a help menu of multicommands
     */
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if (sender instanceof Player) {
            playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            if (MultiCommands.getInstance().getConfig().getBoolean("enable-help-gui")) {
                (new HelpGUI()).open((Player)sender);
            } else {
                sendMessage(sender, "<green><strikethrough>-+------------+-</strikethrough><gray> - <yellow><bold>{</bold> <gold>MultiCommands<gray> - <dark_green>[<light_purple>help<dark_green>] <yellow><bold>}</bold> <gray>- <green><strikethrough>-+-------------+-</strikethrough>"+
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
        } else if (sender instanceof ConsoleCommandSender) {
            sendConsoleMessage("&a-+------------+-&7 - &e{ &6MultiCommands&7 - &2[&dhelp&2] &e} &7- &a-+-------------+-" +
                    "\n&6 - &b/afk " + HELP_AFK.getMessage() +
                    "\n&6 - &b/alert <message> " + HELP_ALERT.getMessage() +
                    "\n&6 - &b/anvil " + HELP_ANVIL.getMessage() +
                    "\n&6 - &b/ban <player> " + HELP_BAN.getMessage() +
                    "\n&6 - &b/banip <player> "+
                    "\n&6 - &b/bc | broadcast <message> " + HELP_BROADCAST.getMessage() +
                    "\n&6 - &b/ci | clearinventory [player] " + HELP_CLEARINV.getMessage() +
                    "\n&6 - &b/craft | workbench " + HELP_CRAFT.getMessage() +
                    "\n&6 - &b/compass " + HELP_COMPASS.getMessage() +
                    "\n&6 - &b/ec | enderchest [player] " + HELP_ENDERCHEST.getMessage() +
                    //"\n&6 - &b/eco | economy <add | set | remove | reset> <player> " + HELP_ECONOMY.getMessage() +
                    "\n&6 - &b/feed [player] " + HELP_FEED.getMessage() +
                    "\n&6 - &b/fly [player] " + HELP_FLY.getMessage() +
                    "\n&6 - &b/freeze <player> " + HELP_FREEZE.getMessage() +
                    "\n&6 - &b/furnace " + HELP_FURNACE.getMessage() +
                    "\n&6 - &b/gm | gamemode <mode> [player] " + HELP_GAMEMODE.getMessage() +
                    "\n&6 - &b/god [player] " + HELP_GOD.getMessage() +
                    "\n&6 - &b/heal [player] " + HELP_HEAL.getMessage() +
                    "\n&6 - &b/helpop <message> " + HELP_HELPOP.getMessage() +
                    "\n&6 - &b/invsee <player> " + HELP_INVSEE.getMessage() +
                    "\n&6 - &b/kick <player> " + HELP_KICK.getMessage() +
                    "\n&6 - &b/kickall " + HELP_KICKALL.getMessage() +
                    "\n&6 - &b/kill [player] " + HELP_KILL.getMessage() +
                    "\n&6 - &b/level <add | set | remove> [player] " + HELP_LEVEL.getMessage() +
                    "\n&6 - &b/list " + HELP_LIST.getMessage() +
                    "\n&6 - &b/mi | multiinfos " + HELP_MULTIINFOS.getMessage() +
                    "\n&6 - &b/mute <player> [reason] " + HELP_MUTE.getMessage() +
                    "\n&6 - &b/nick " + HELP_NICK.getMessage() +
                    "\n&6 - &b/ping | playerpinger " + HELP_PING.getMessage() +
                    "\n&6 - &b/pl | plugins " + HELP_PLUGINS.getMessage() +
                    "\n&6 - &b/mreload | multireload " + HELP_RELOAD.getMessage() +
                    "\n&6 - &b/serverinfo " + HELP_SERVERINFO.getMessage() +
                    "\n&6 - &b/setspawn " + HELP_SETSPAWN.getMessage() +
                    "\n&6 - &b/spawn " + HELP_SPAWN.getMessage() +
                    "\n&6 - &b/tp | teleport <coordinates | player> " + HELP_TELEPORT.getMessage() +
                    "\n&6 - &b/top " + HELP_TOP.getMessage() +
                    "\n&6 - &b/unban <player> " + HELP_UNBAN.getMessage() +
                    "\n&6 - &b/unbanip <player> " +
                    "\n&6 - &b/v | vanish " + HELP_VANISH.getMessage() +
                    "\n&6 - &b/whois <player> " + HELP_WHOIS.getMessage() +
                    "\n");
        }
        return false;
    }
}
