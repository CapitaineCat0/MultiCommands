package me.capitainecat0.multicommands.utils.permissions;

public enum Perms {

    /* ##########################
     * #       commandes         #
     * ###########################*/
    ALL_PERMS("multicommands.*"),
    ACTIONBAR_PERMS("multicommands.actionbar"),
    AFK_PERM("multicommands.afk"),
    ALERT_PERM("multicommands.alert"),
    ANVIL_PERM("multicommands.anvil"),
    BAN_PERM("multicommands.ban"),
    BANIP_PERM("multicommands.banip"),
    BOSSBAR_PERM("multicommands.bossbar"),
    BROADCAST_PERM("multicommands.broadcast"),
    CLEARINVENTORY_PERM_ALL("multicommands.clearinventory.*"),
    CLEARINVENTORY_PERM_SELF("multicommands.clearinventory.self"),
    CLEARINVENTORY_PERM_OTHER("multicommands.clearinventory.other"),
    CRAFT_PERM("multicommands.craft"),
    ENDERCHEST_PERM_ALL("multicommands.enderchest.*"),
    ENDERCHEST_PERM_SELF("multicommands.enderchest.self"),
    ENDERCHEST_PERM_OTHER("multicommands.enderchest.other"),
    ECONOMY_PERM_ALL("multicommands.economy.*"),
    ECONOMY_PERM_ADD("multicommands.economy.deposit"),
    ECONOMY_PERM_BANK_ADD("multicommands.economy.bank.deposit"),
    ECONOMY_PERM_BALANCE("multicommands.economy.balance"),
    ECONOMY_PERM_BANK_CREATE("multicommands.economy.bank.create"),
    ECONOMY_PERM_BANK_RESET("multicommands.economy.bank.reset"),
    ECONOMY_PERM_BANK_REMOVE("multicommands.economy.bank.withdraw"),
    ECONOMY_PERM_SET("multicommands.economy.set"),
    ECONOMY_PERM_RESET("multicommands.economy.reset"),
    ECONOMY_PERM_REMOVE("multicommands.economy.withdraw"),
    FURNACE_PERM("multicommands.furnace"),
    FEED_PERM_ALL("multicommands.feed.*"),
    FEED_PERM_SELF("multicommands.feed.self"),
    FEED_PERM_OTHER("multicommands.feed.other"),
    FLY_PERM_ALL("multicommands.fly.*"),
    FLY_PERM_SELF("multicommands.fly.self"),
    FLY_PERM_OTHER("multicommands.fly.other"),
    FREEZE_PERM("multicommands.freeze"),
    GAMEMODE_PERM_ALL("multicommands.gamemode.*"),
    GAMEMODE_PERM_OTHER_ALL("multicommands.gamemode.other.*"),
    GAMEMODE_SURVIVAL_PERM_SELF("multicommands.gamemode.survival.self"),
    GAMEMODE_SURVIVAL_PERM_OTHER("multicommands.gamemode.survival.other"),
    GAMEMODE_CREATIVE_PERM_SELF("multicommands.gamemode.creative.self"),
    GAMEMODE_CREATIVE_PERM_OTHER("multicommands.gamemode.creative.other"),
    GAMEMODE_ADVENTURE_PERM_SELF("multicommands.gamemode.adventure.self"),
    GAMEMODE_ADVENTURE_PERM_OTHER("multicommands.gamemode.adventure.other"),
    GAMEMODE_SPECTATOR_PERM_SELF("multicommands.gamemode.spectator.self"),
    GAMEMODE_SPECTATOR_PERM_OTHER("multicommands.gamemode.spectator.other"),
    GIVE_PERM("multicommands.give"),
    GIVE_OVERSIZE_PERM("multicommands.give.oversize"),
    GIVE_UNSAFE_ENCHANTMENT("multicommands.unsafe.enchantment"),
    GOD_PERM_ALL("multicommands.god.*"),
    GOD_PERM_SELF("multicommands.god.self"),
    GOD_PERM_OTHER("multicommands.god.other"),
    HELPOP_PERM("multicommands.helpop"),
    HEAL_PERM_ALL("multicommands.heal.*"),
    HEAL_PERM_SELF("multicommands.heal.self"),
    HEAL_PERM_OTHER("multicommands.heal.other"),
    INVSEE_PERM("multicommands.invsee"),
    KICK_PERM("multicommands.kick"),
    KICKALL_PERM("multicommands.kickall"),
    KICKALL_EXEMPT_PERM("multicommands.kickall.exempt"),
    KILL_PERM("multicommands.kill"),
    LEVEL_ALL_PERM("multicommands.level.*"),
    LEVEL_ADD_PERM("multicommands.level.add"),
    LEVEL_SET_PERM("multicommands.level.set"),
    LEVEL_REMOVE_PERM("multicommands.level.remove"),
    MUTE_PERM("multicommands.mute"),
    MULTIINFOS_PERM("multicommands.multiinfos"),
    NICKNAME_PERMS("multicommands.nick"),
    PLAYERPINGER_PERM("multicommands.playerpinger"),
    PLUGIN_PERM("multicommands.plugins"),
    PRIVATE_MSG_PERM("multicommands.privatemsg"),
    SERVERINFO_PERM("multicommands.serverinfo"),
    SETSPAWN_PERM("multicommands.setspawn"),
    SETWARP_PERM("multicommands.setwarp"),
    SPAWN_PERM("multicommands.spawn"),
    TIME_PERM("multicommands.time"),
    TELEPORT_PERM("multicommands.teleport"),
    TPA_PERM("multicommands.tpa"),
    TPA_ALL_PERM("multicommands.tpa.*"),
    TPA_ACCEPT_PERM("multicommands.tpa.accept"),
    TPA_DENY_PERM("multicommands.tpa.deny"),
    TITLE_PERM("multicommands.title"),
    TOP_PERM("multicommands.top"),
    UNBAN_PERM("multicommands.unban"),
    UNBANIP_PERM("multicommands.unbanip"),
    VANISH_PERM_ALL("multicommands.vanish.*"),
    VANISH_PERM_SELF("multicommands.vanish.self"),
    VANISH_PERM_OTHER("multicommands.vanish.other"),
    WARP_PERM("multicommands.warps"),
    WHOIS_PERM("multicommands.whois"),

    /* ##########################
     * #        events          #
     * ##########################*/

    ALL_CHAT_PERM("multicommands.chat.*"),
    CLEARCHAT_PERM("multicommands.chat.clear"),
    ADMINCHAT_PERM("multicommands.chat.admin"),
    STAFFCHAT_PERM("multicommands.chat.staff"),
    DEVCHAT_PERM("multicommands.chat.developer"),
    MODOCHAT_PERM("multicommands.chat.moderator"),
    BUILDERCHAT_PERM("multicommands.chat.builder"),



    ;
    private final String perm;

    /**
     * The Perms function is used to determine whether a user has the
     * permissions necessary to perform an action.
     * The function takes in a String
     * that represents the permission level required for an action, and returns true if
     * the user's permission level is equal to or greater than that of the required permission.

     *
     * @param perm Set the value of the perm field
     *
     */
    Perms(String perm) {
        this.perm = perm;
    }

    /**
     * The getPermission function returns the permission string of a command.
     *
     *
     *
     * @return The permission of the user
     */
    public String getPermission() {
        return perm;
    }
}
