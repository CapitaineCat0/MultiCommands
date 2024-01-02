package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import me.capitainecat0.multicommands.utils.permissions.PermissionManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.Messenger.CMD_INVALID_ARGS;
import static me.capitainecat0.multicommands.utils.Messenger.CMD_NO_PERM;
import static me.capitainecat0.multicommands.utils.MessengerUtils.getMsgSendConfig;
import static me.capitainecat0.multicommands.utils.permissions.Perms.*;
import static me.capitainecat0.multicommands.utils.permissions.Perms.GAMEMODE_SPECTATOR_PERM_OTHER;

public class Gamemode implements CommandsImpl {

    private final String[] args;
    private final Player player;
    private final Player target;
    private GameMode mode;
    private String permission;

    public Gamemode(Player player, Player target, String[] args) {
        this.player = player;
        this.target = target;
        this.args = args;
    }
    @Override
    public void execute() {
        PermissionManager perms = MultiCommands.getPermissionManager();
        String gamemode;
        if (target != null) {
            gamemode = args[0].toLowerCase();
            switch (gamemode) {
                case "0":
                case "survival":
                    mode = GameMode.SURVIVAL;
                    permission = GAMEMODE_SURVIVAL_PERM_OTHER.getPermission();
                    break;
                case "1":
                case "creative":
                    mode = GameMode.CREATIVE;
                    permission = GAMEMODE_CREATIVE_PERM_OTHER.getPermission();
                    break;
                case "2":
                case "adventure":
                    mode = GameMode.ADVENTURE;
                    permission = GAMEMODE_ADVENTURE_PERM_OTHER.getPermission();
                    break;
                case "3":
                case "spectator":
                    mode = GameMode.SPECTATOR;
                    permission = GAMEMODE_SPECTATOR_PERM_OTHER.getPermission();
                    break;
                default: getMsgSendConfig(player, "Gamemode", CMD_INVALID_ARGS.getMessage().replace("{0}", gamemode));
                    break;
            }
            if(perms.hasPermission(player, permission)
                    || perms.hasPermission(player, GAMEMODE_PERM_OTHER_ALL.getPermission())
                    || perms.hasPermission(player, GAMEMODE_PERM_ALL.getPermission())
                    || perms.hasPermission(player, ALL_PERMS.getPermission())){
                target.setGameMode(mode);
            } else {
                getMsgSendConfig(player, "Gamemode", CMD_NO_PERM.getMessage().replace("<command>","Gamemode"));
            }
        } else {
            gamemode = args[0].toLowerCase();
            switch (gamemode) {
                case "0":
                case "survival":
                    mode = GameMode.SURVIVAL;
                    gamemode = "0 (survival)";
                    break;
                case "1":
                case "creative":
                    mode = GameMode.CREATIVE;
                    gamemode = "1 (creative)";
                    break;
                case "2":
                case "adventure":
                    mode = GameMode.ADVENTURE;
                    gamemode = "2 (adventure)";
                    break;
                case "3":
                case "spectator":
                    mode = GameMode.SPECTATOR;
                    gamemode = "3 (spectator)";
                    break;
                default:
                    getMsgSendConfig(player, "Gamemode", CMD_INVALID_ARGS.getMessage().replace("{0}", gamemode));
                    break;
            }
            player.setGameMode(mode);
        }
    }
}
