package me.capitainecat0.multicommands.commands;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.HelpGUI;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.NO_CONSOLE_COMMAND;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;


public class MultiHelp implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        hideActiveBossBar();
        if(sender instanceof Player){
            if(soundEnabled()){
                playSound(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
            }
            if(MultiCommands.getInstance().getConfig().getBoolean("enable-help-gui")){
                new HelpGUI().open((Player) sender);
            }else{
                sendMessage(sender,
                        "&a&m-+------------+-&7 - &e&l{ &6MultiCommands&7 - &2[&dhelp&2] &e&l} &7- &a&m-+-------------+-\n"
                                +"&6 - &b/afk &7Permet de gérer le mode AFK\n"
                                +"&6 - &b/alert <message> &7Envoyer un message d'alerte\n"
                                +"&6 - &b/bc | broadcast <message> &7Envoyer un broadcast\n"
                                +"&6 - &b/ci | clearinventory [joueur] &7Vider son inventaire / celui d'un autre joueur\n"
                                +"&6 - &b/craft | workbench &7Ouvrir l'établi\n"
                                +"&6 - &b/ec | enderchest [joueur] &7Ouvrir votre enderchest / celui d'un autre joueur\n"
                                +"&6 - &b/eco | economy <add | set | remove | reset> <joureur> &7Permet de gérer l'économie du serveur\n"
                                +"&6 - &b/feed [joueur] &7Permet de se nourrir / un autre joueur\n"
                                +"&6 - &b/fly [joueur] &7Permet de gérer votre fly-mode / celui d'un autre joueur\n"
                                +"&6 - &b/freeze <joueur> &7Geler un joueur\n"
                                +"&6 - &b/furnace &7Permet de cuire les items que vous tenez\n"
                                +"&6 - &b/gm | gamemode <mode> [joueur] &7Permet de changer votre mode de jeu / celui d'un autre joueur\n"
                                +"&6 - &b/god [joueur] &7Permet de gérer votre invulnérabilité / celle d'un autre joueur\n"
                                +"&6 - &b/heal [joueur] &7Permet de vous soigner / un autre joueur\n"
                                +"&6 - &b/helpop <message> &7Permet d'envoyer un message aux opérateurs\n"
                                +"&6 - &b/invsee <joueur> &7Permet d'afficher et d'interragir avec l'inventaire d'un autre joueur\n"
                                +"&6 - &b/list &7Permet d'afficher les joueurs connectés\n"
                                +"&6 - &b/mi | multiinfos &7Permet d'afficher les informations des plugins de la série Multi\n"
                                +"&6 - &b/nick &7Permet de modifier votre pseudo en jeu\n"
                                +"&6 - &b/ping | playerpinger &7Permet de calculer votre latence\n"
                                +"&6 - &b/pl | plugins &7 Permet d'afficher la liste des plugins installés\n"
                                +"&6 - &b/serverinfo &7Permet d'afficher les informations d'un serveur\n"
                                +"&6 - &b/tp | teleport <coordonées | joueur> &7Permet de vous téléporter\n"
                                +"&6 - &b/v | vanish &7Permet de vous faire disparaître du serveur\n"
                                +"&6 - &b/whois <joueur> &7Permet d'afficher les informations d'un joueur\n");
            }
        }else if(sender instanceof ConsoleCommandSender){
            sendConsoleMessage(NO_CONSOLE_COMMAND.getMessage().replace("%cmd%", command.getName()));
        }
        return false;
    }
}
