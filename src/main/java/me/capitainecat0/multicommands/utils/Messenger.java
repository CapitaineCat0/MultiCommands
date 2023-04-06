package me.capitainecat0.multicommands.utils;

import me.capitainecat0.multicommands.MultiCommands;

import static me.capitainecat0.multicommands.utils.MessengerUtils.lang;

public enum Messenger {


    /* ##########################
     * #       commandes         #
     * ###########################*/

    ADMINCHAT(lang("admin_chat")),
    ADMINCHAT_PREFIX(lang("admin_chat_prefix")),
    STAFFCHAT(lang("staff_chat")),
    STAFFCHAT_PREFIX(lang("staff_chat_prefix")),
    DEVCHAT(lang("dev_chat")),
    DEVCHAT_PREFIX(lang("dev_chat_prefix")),
    BUILDERCHAT(lang("build_chat")),
    BUILDERCHAT_PREFIX(lang("build_chat_prefix")),
    MODOCHAT(lang("modo_chat")),
    MODOCHAT_PREFIX(lang("modo_chat_prefix")),
    NO_CONSOLE_COMMAND(lang("no_console_cmd")),
    NO_CONSOLE_COMMAND_WITHOUT_ARGS(lang("no_console_cmd_without_args")),
    NOT_A_PLAYER(lang("not_a_player")),
    CMD_NO_PERM(lang("no_perm")),
    CMD_NO_PERM_TO_OTHER(lang("no_perm_to_other")),
    CMD_NO_ARGS(lang("no_args")),
    ACTIONBAR_SENT_TO_OTHER(lang("actionbosstitle_sent_to_other")),
    ACTIONBAR_SENT_TO_ALL(lang("actionbosstitle_sent_to_all")),
    AFK_BROADCAST_ENABLED(MultiCommands.colored(lang("afk_enabled_broadcast"))),
    AFK_ENABLED(lang("afk_enabled_self")),
    AFK_BROADCAST_DISABLED(MultiCommands.colored(lang("afk_disabled_broadcast"))),
    AFK_DISABLED(lang("afk_disabled_self")),
    ALERT_PREFIX(lang("alert_prefix")),
    ALERT_CMD(lang("alert_sent")),
    BAN_PREFIX(lang("ban_prefix")),
    BROADCAST_PREFIX(lang("broadcast_prefix")),
    BROADCAST_CMD(lang("broadcast_sent")),
    CLEARINV_SELF_DONE(lang("clear_self")),
    CLEARINV_ADMIN(lang("clear_by_admin")),
    CLEARINV_SENDER(lang("clear_admin")),
    ECONOMY_BALANCE(lang("economy_balance")),
    ECONOMY_BANK_BALANCE(lang("economy_bank_balance")),
    ECONOMY_BANK_CREATED(lang("economy_bank_created")),
    ECONOMY_BANK_DEPOSIT(lang("economy_bank_deposit")),
    ECONOMY_BANK_RESET(lang("economy_bank_reset")),
    ECONOMY_BANK_WITHDRAW(lang("economy_bank_withdraw")),
    ECONOMY_BALANCE_OTHER(lang("economy_balance_other")),
    ECONOMY_PAY_SENT(lang("economy_pay_sender")),
    ECONOMY_PAY_OTHER(lang("economy_pay_receiver")),
    ECONOMY_DEPOSIT(lang("economy_gave")),
    ECONOMY_WITHDRAW(lang("economy_withdraw")),
    ECONOMY_RESET(lang("economy_reset")),
    ECONOMY_SET(lang("economy_set")),

    ENDERCHEST_ADMIN_OPEN(lang("enderchest_opened_by_admin")),
    FEED_SELF(lang("feed_self_done")),
    FEED_OTHER_SENDER(lang("feed_admin")),
    FEED_OTHER(lang("feed_by_admin")),
    FEED_ALREADY(lang("feed_self_already")),
    FEED_ALREADY_SENDER(lang("feed_admin_already")),
    FLY_TOGGLE_ON(lang("fly_enabled_self")),
    FLY_TOGGLE_OFF(lang("fly_disabled_self")),
    FLY_TOGGLE_ON_BY_ADMIN(lang("fly_enabled_by_admin")),
    FLY_TOGGLE_OFF_BY_ADMIN(lang("fly_disabled_by_admin")),
    FLY_TOGGLE_ON_SENDER(lang("fly_enabled_admin")),
    FLY_TOGGLE_OFF_SENDER(lang("fly_disabled_admin")),
    FREEZE_TOGGLE_ON(lang("freeze_enabled_by_admin")),
    FREEZE_TOGGLE_OFF(lang("freeze_disabled_by_admin")),
    FREEZE_TOGGLE_ON_ADMIN(lang("freeze_enabled_admin")),
    FREEZE_TOGGLE_OFF_ADMIN(lang("freeze_disabled_admin")),
    FREEZE_BREAK(lang("freeze_break_block")),
    FREEZE_CHAT(lang("freeze_chat")),
    FREEZE_DROP(lang("freeze_drop_item")),
    FREEZE_MOVE(lang("freeze_move")),
    FREEZE_PICKUP(lang("freeze_pickup_item")),
    FREEZE_PLACE(lang("freeze_place")),
    FURNACE_ERROR(lang("furnace_error")),
    FURNACE_DONE(lang("furnace_done")),
    GAMEMODE_SELF(lang("gamemode_self")),
    GAMEMODE_OTHER(lang("gamemode_by_admin")),
    GAMEMODE_OTHER_ADMIN(lang("gamemode_admin")),
    GOD_SELF_ON(lang("god_enabled_self")),
    GOD_SELF_OFF(lang("god_disabled_self")),
    GOD_OTHER_ON(lang("god_enabled_by_admin")),
    GOD_OTHER_OFF(lang("god_disabled_by_admin")),
    GOD_OTHER_ADMIN_ON(lang("god_enabled_admin")),
    GOD_OTHER_ADMIN_OFF(lang("god_disabled_admin")),
    HELPOP_DONE(lang("helpop_sent")),
    HELPOP_NO_ADMINS(lang("helpop_no_admins")),
    HEAL_SELF(lang("heal_self")),
    HEAL_OTHER(lang("heal_by_admin")),
    HEAL_OTHER_SENDER(lang("heal_admin")),
    HEAL_ALREADY(lang("heal_self_already")),
    HEAL_ALREADY_SENDER(lang("heal_admin_already")),
    INVSEE_ADMIN(lang("invsee_admin")),
    KICK_PREFIX(lang("kick_prefix")),
    LIST(lang("list")),
    MULTIINFOS_MSG(MultiCommands.colored("""
            &a&m-+------------+-&7 - &e&l{ &6%plname%&7 - &2[&dinfos&2] &e&l} &7- &a&m-+-----------+-
             &7- &6/multihelp &7=> Vous permet d'afficher l'aide des plugins de la série Multi
            &eNom&8: &6%plname%
            &bAuteur&8: &3%author%
            &aVersion&8: &e%version%
            &9Description&8: &b%description%
            &a&m-+---------------------------------------------------------+-""")),
    NICKNAME_DONE(lang("nickname_done")),
    PING_SELF_MSG(lang("ping_self")),
    PING_OTHER_MSG(lang("ping_other")),
    PLUGIN_RELOADED(lang("reload")),
    SETSPAWN_DONE(lang("setspawn_done")),
    SPAWN_DONE(lang("spawn_teleport")),
    SPAWN_ERROR(lang("spawn_error")),
    TELEPORT_ERROR(lang("teleport_error")),
    TELEPORT_SELF_TO_PLAYER(lang("teleport_self_to_player")),
    TELEPORT_OTHER_TO_OTHER(lang("teleport_player_to_player_by_admin")),
    TELEPORT_OTHER_TO_OTHER_SENDER(lang("teleport_player_to_player")),
    TELEPORT_INVALID_COORDINATES(lang("teleport_error_coordinates")),
    TELEPORT_OTHER_INVALID_COORDINATES(lang("teleport_error_player_coordinates")),
    TELEPORT_SELF_TO_COORDINATES(lang("teleport_self_to_coordinates")),
    TELEPORT_OTHER_TO_COORDINATES(lang("teleport_player_to_coordinates_by_admin")),
    TELEPORT_OTHER_TO_COORDINATES_SENDER(lang("teleport_player_to_coordinates")),
    VANISH_ENABLED_SELF(lang("vanish_enabled_self")),
    VANISH_DISABLED_SELF(lang("vanish_disabled_self")),
    VANISH_ENABLED_ADMIN(lang("vanish_enabled_admin")),
    VANISH_DISABLED_ADMIN(lang("vanish_disabled_admin")),
    VANISH_ENABLED_OTHER(lang("vanish_enabled_by_admin")),
    VANISH_DISABLED_OTHER(lang("vanish_disabled_by_admin")),

    /* ##########################
     * #        events          #
     * ##########################*/
    ONJOIN(lang("join_message")),
    ONLEAVE(lang("leave_message")),

    ONDEATH(lang("death_message")),
    DEATHLOC(lang("death_message_loc")),
    ;

    private final String msg;
    Messenger(String msg) {
        this.msg = msg;
    }
    public String getMessage() {
        return msg;
    }
}