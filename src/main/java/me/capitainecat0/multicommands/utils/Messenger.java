package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;

import static me.capitainecat0.multicommands.utils.MessengerUtils.lang;

public enum Messenger {


    /* ##########################
     * #       commandes         #
     * ###########################*/

    ADMINCHAT(lang("admin_chat")),
    STAFFCHAT(lang("staff_chat")),
    DEVCHAT(lang("dev_chat")),
    BUILDERCHAT(lang("build_chat")),
    MODOCHAT(lang("modo_chat")),
    NO_CONSOLE_COMMAND(lang("no_console_cmd")),
    NO_CONSOLE_COMMAND_WITHOUT_ARGS(lang("no_console_cmd_without_args")),
    NOT_A_PLAYER(lang("not_a_player")),
    CMD_NO_PERM(lang("no_perm")),
    CMD_NO_PERM_TO_OTHER("&cVous n'avez pas la permission d'utiliser cette commande sur un autre joueur!"),

    CMD_NO_ARGS("&cLa commande &e/%cmd% &cs'utilise avec des arguments!\n"+"&e- /%cmd% %args%"),

    AFK_BROADCAST_ENABLED(MultiCommands.colored("&3%p &7est AFK")),
    AFK_ENABLED("&7Vous êtes maintenant AFK"),
    AFK_BROADCAST_DISABLED(MultiCommands.colored("&3%p &7n'est plus AFK")),
    AFK_DISABLED("&7Vous n'êtes plus AFK"),

    ALERT_PREFIX("&c&l-&4&l{ &f&lAlerte &4&l}&c&l-"),
    ALERT_CMD("&7Votre message à été envoyé."),

    BROADCAST_PREFIX("&3&l-&9&l{ &5&lH&d&lO&5&lG &9&l}&3&l-"),
    BROADCAST_CMD("&7Votre message à été envoyé."),

    CLEARINV_SELF_DONE("&7Votre inventaire à été vidé!"),
    CLEARINV_ADMIN("&5&lHall&d&lOf&5&lGames &8> &eUn &cadministrateur &eà vidé votre inventaire!"),
    CLEARINV_SENDER("&7L'inventaire de &3%p &7à été vidé!"),

    ENDERCHEST_ADMIN_OPEN("&5&lHall&d&lOf&5&lGames &8> &eUn &cadministrateur &eà ouvert votre enderchest!"),

    FEED_SELF("&7Vous avez été &arassasié&7!"),
    FEED_OTHER_SENDER("&7Vous avez &arassasié &3%p&7!"),
    FEED_OTHER("&5&lHall&d&lOf&5&lGames &8> &eUn &cadministrateur &evous à &anourris&e!"),
    FEED_ALREADY("&7Vous êtes déjà rassasié!"),
    FEED_ALREADY_SENDER("&3%p &eà déjà été rassasié!"),

    FLY_TOGGLE_ON("&7Votre &6Fly-mode &7à été &aactivé&7!"),
    FLY_TOGGLE_OFF("&7Votre &6Fly-mode &7à été &cdésactivé&7!"),
    FLY_TOGGLE_ON_BY_ADMIN("&5&lHall&d&lOf&5&lGames &8> &eUn &cadministrateur &eà &aactivé &evotre &6Fly_mode&e!"),
    FLY_TOGGLE_OFF_BY_ADMIN("&5&lHall&d&lOf&5&lGames &8> &eUn &cadministrateur &eà &cdésactivé &evotre &6Fly_mode&e!"),
    FLY_TOGGLE_ON_SENDER("&7Le &6Fly-mode &7de &3%p &7à été &aactivé&7!"),
    FLY_TOGGLE_OFF_SENDER("&7Le &6Fly-mode &7de &3%p &7à été &cdésactivé&7!"),

    FREEZE_TOGGLE_ON("&5&lHall&d&lOf&5&lGames &8> &eVous avez été &cgelé &epar un &cadministrateur&e!"),
    FREEZE_TOGGLE_OFF("&5&lHall&d&lOf&5&lGames &8> &eVous avez été &alibéré &epar un &cadministrateur&e!"),
    FREEZE_TOGGLE_ON_ADMIN("&7Vous venez d'ajouter &3%p &7aux joueurs &bfreeze&7!"),
    FREEZE_TOGGLE_OFF_ADMIN("&7Vous venez de retirer &3%p &7des joueurs &bfreeze&7!"),
    FREEZE_BREAK("&cVous ne pouvez pas casser de blocs en étant &egelé&c!"),
    FREEZE_PLACE("&cVous ne pouvez pas placer de blocs en étant &egelé&c!"),
    FREEZE_MOVE("&cVous ne pouvez pas vous déplacer en étant &egelé&c!"),
    FREEZE_DROP("&cVous ne pouvez pas jeter d'items en étant &egelé&c!"),
    FREEZE_PICKUP("&cVous ne pouvez pas récupérer d'items en étant &egelé&c!"),
    FREEZE_CHAT("&cVous ne pouvez pas parler en étant &egelé&c!"),
    FURNACE_ERROR("&cL'item &e%item% &cne peux pas être cuit!"),
    FURNACE_DONE("&aVous avez fait cuire &e%items%&a."),
    GAMEMODE_SELF("&7Votre &6Gamemode &7à été changé en mode &d%gamemode%&7!"),
    GAMEMODE_OTHER("&5&lHall&d&lOf&5&lGames &8> &eVotre &6Gamemode &eà été changé en mode &d%gamemode%&epar un &cadministrateur&e!"),
    GAMEMODE_OTHER_ADMIN("&7Le &6Gamemode &7de &3%p &7à été changé en mode &d%gamemode%&7!"),
    GOD_SELF_ON("&7Vous avez &aactivé &7le &6God-mode&7!"),
    GOD_SELF_OFF("&7Vous avez &cdésactivé &7le &6God-mode&7!"),
    GOD_OTHER_ON("&5&lHall&d&lOf&5&lGames &8> &eUn &cadministrateur &evient de vous &aactiver &evotre &6God-mode&e!"),
    GOD_OTHER_OFF("&5&lHall&d&lOf&5&lGames &8> &eUn &cadministrateur &evient de vous &cdésactiver &evotre &6God-mode&e!"),
    GOD_OTHER_ADMIN_ON("&7Le &6God-mode &7de &3%p&7 à été &aactivé&7!"),
    GOD_OTHER_ADMIN_OFF("&7Le &6God-mode &7de &3%p&7 à été &cdésactivé&7!"),

    HELPOP_DONE("&eVotre message à été envoyé aux &c&lAdministrateurs&e!"),
    HELPOP_NO_ADMINS("&cDésolé mais aucuns administrateurs n'est actuellement connecté! Merci de réessayer plus tard."),
    HEAL_SELF("&7Vous avez été &asoigné&7!"),
    HEAL_OTHER("&5&lHall&d&lOf&5&lGames &8> &eUn &cadministrateur&e vous à &asoigné&e!"),
    HEAL_OTHER_SENDER("&7Vous avez &asoigné &3%p&7!"),
    HEAL_ALREADY("&7Vous êtes déjà en bonne santé!"),
    HEAL_ALREADY_SENDER("&3%p &7est déjà en bonne santé!"),

    INVSEE_ADMIN("&7Vous avez affiché l'inventaire de &3%p&7!"),

    MULTIINFOS_MSG(MultiCommands.colored("""
            &a&m-+------------+-&7 - &e&l{ &6%plname%&7 - &2[&dinfos&2] &e&l} &7- &a&m-+-------------+-
             &7- &6/multihelp &7=> Vous permet d'afficher l'aide des plugins de la série Multi
            &eNom&8: &6%plname%
            &bAuteur&8: &3%author%
            &aVersion&8: &e%version%
            &9Description&8: &b%description%
            &a&m-+-------------------------------------------------------------+-""")),
    NICKNAME_DONE("&7Votre pseudo à été changé en &a%newName&7!"),

    PING_SELF_MSG("&7Votre latence est de %ping%&7!"),
    PING_OTHER_MSG("&7La latence de &3%target &7est de %ping%&7!"),
    PLUGIN_RELOADED("&&Les fichiers de &6MultiCommands &aont été rechargés!"),
    SETSPAWN_DONE("&aLe spawn principal à été placé à votre emplacement!"),
    SPAWN_DONE("&aVous avez été téléporté au spawn!"),
    SPAWN_ERROR("&cLe spawn n'à pas été définis / est introuvable, merci de le signaler à un membre du Staff!"),
    TELEPORT_ERROR("&cMerci de taper &e/<tp|teleport> (<x><y><z>|<joueur>)&c!"),
    TELEPORT_SELF_TO_PLAYER("&7Vous avez été téléporté à &3%p&7!"),
    TELEPORT_OTHER_TO_OTHER("&5&lMulti&d&lCommands &8> &eVous avez été téléporté vers &3%p&e par un &cadministrateur&e!"),
    TELEPORT_OTHER_TO_OTHER_SENDER("&eVous avez téléporté &d%p1 &evers &3%p2&e!"),
    TELEPORT_INVALID_COORDINATES("&cMerci de taper &e/tp <X> <Y> <Z>&c!"),
    TELEPORT_OTHER_INVALID_COORDINATES("&cMerci de taper &e/tp <joueur> <X> <Y> <Z>&c!"),
    TELEPORT_SELF_TO_COORDINATES("&aVous avez été téléporté en &e%loc%&a!"),
    TELEPORT_OTHER_TO_COORDINATES("&5&lMulti&d&lCommands &8> &eVous avez été téléporté en &6%loc%&e par un &cadministrateur&e!"),
    TELEPORT_OTHER_TO_COORDINATES_SENDER("&eVous avez téléporté &3%p &een &6%loc%&e!"),

    VANISH_TOGGLE_SELF("&7Vous avez %status% &7le &6vanish&7!"),
    VANISH_TOGGLE_ADMIN("&7Vous avez %status% &7le &6vanish&7 de &b%p&7!"),
    VANISH_TOGGLE_OTHER("&5&lHall&d&lOf&5&lGames &8> &eUn &cadministrateur &evient de vous %status% &evotre &6vanish&e!"),




    /* ##########################
     * #        events          #
     * ##########################*/
    ONJOIN(MultiCommands.colored("&8{ &a+ &8} &e-&a> &3%p")),
    ONLEAVE(MultiCommands.colored("&8{ &c- &8} &e-&c< &3%p")),
    DEATHLOC(MultiCommands.colored("&7Vous êtes mort en &e%loc&7!")),

    ;

    private final String msg;

    Messenger(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }
}
