package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.HelpGUI;
import me.capitainecat0.multicommands.utils.Messenger;
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

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if (sender instanceof Player) {
            if (soundEnabled()) {
                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1.0F, 1.0F);
            }

            if (MultiCommands.getInstance().getConfig().getBoolean("enable-help-gui")) {
                (new HelpGUI()).open((Player)sender);
            } else {
                sendMessage(sender, "&a&m-+------------+-&7 - &e&l{ &6MultiCommands&7 - &2[&dhelp&2] &e&l} &7- &a&m-+-------------+-" +
                        "\n&6 - &b/afk " + HELP_AFK.getMessage() +
                        "\n&6 - &b/alert <message> " + HELP_ALERT.getMessage() +
                        "\n&6 - &b/anvil " + HELP_ANVIL.getMessage() +
                        "\n&6 - &b/ban <player> " + HELP_BAN.getMessage() +
                        "\n&6 - &b/banip <player> "+
                        "\n&6 - &b/bc | broadcast <message> " + HELP_BROADCAST.getMessage() +
                        "\n&6 - &b/ci | clearinventory [player] " + HELP_CLEARINV.getMessage() +
                        "\n&6 - &b/craft | workbench " + HELP_CRAFT.getMessage() +
                        "\n&6 - &b/compass" + HELP_COMPASS.getMessage() +
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
                        "\n&6 - &b/list " + HELP_LIST.getMessage() +
                        "\n&6 - &b/mi | multiinfos " + HELP_MULTIINFOS.getMessage() +
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
        } else if (sender instanceof ConsoleCommandSender) {
            sendConsoleMessage("&a&m-+------------+-&7 - &e&l{ &6MultiCommands&7 - &2[&dhelp&2] &e&l} &7- &a&m-+-------------+-" +
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
                    "\n&6 - &b/list " + HELP_LIST.getMessage() +
                    "\n&6 - &b/mi | multiinfos " + HELP_MULTIINFOS.getMessage() +
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
