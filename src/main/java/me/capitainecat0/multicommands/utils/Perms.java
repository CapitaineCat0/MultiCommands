package me.capitainecat0.multicommands.utils;

public enum Perms {

    ALL_PERMS("multicommands.*"),

    AFK_PERM("multicommands.afk"),
    ALERT_PERM("multicommands.alert"),
    BROADCAST_PERM("multicommands.broadcast"),
    CLEARINV_PERM("multicommands.clear"),
    CRAFT_PERM("multicommands.craft"),
    DEBUG_PERM("multicommands.debug"),
    ENCHANTING_PERM("multicommands.anvil"),
    ENDERCHEST_PERM("multicommands.enderchest"),
    FEED_PERM("multicommands.feed"),
    FLY_PERM("multicommands.fly"),
    FREEZE_PERM("multicommands.freeze"),
    GAMEMODE_PERM("multicommands.gamemode"),
    GOD_PERM("multicommands.god"),
    HAT_PERM("multicommands.hat"),
    HELPOP_PERM("multicommands.helpoperator"),
    HEAL_PERM("multicommands.heal"),
    INVSEE_PERM("multicommands.invsee"),
    LEPORTE_PERM("multicommands.leporte"),
    MULTIINFOS_PERM("multicommands.multiinfos"),
    NICKNAME_PERMS("multicommands.nick"),
    PLAYERPINGER_PERM("multicommands.playerpinger"),
    PRIVATE_MSG_PERM("multicommands.privatemsg"),
    SERVERINFO_PERM("multicommands.serverinfo"),
    TIME_PERM("multicommands.time"),
    TELEPORT_PERM("multicommands.teleport"),
    VANISH_PERM("multicommands.vanish"),
    WARPS("multicommands.warps"),
    WHOIS_PERM("multicommands.whois"),

    //Items
    KB_STICK("multicommands.kbstick"),
    LIGHTNING("multicommands.lightning"),


    ;


    private final String perm;

    Perms(String perm) {
        this.perm = perm;
    }

    public String getPermission() {
        return perm;
    }
}
