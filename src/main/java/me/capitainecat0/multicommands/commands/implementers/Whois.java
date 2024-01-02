package me.capitainecat0.multicommands.commands.implementers;

import com.viaversion.viaversion.api.Via;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import me.capitainecat0.multicommands.utils.VanishHandler;
import org.bukkit.entity.Player;

import static me.capitainecat0.multicommands.utils.MessengerUtils.sendCommandMessage;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;

public class Whois implements CommandsImpl {

    private final Player sender;
    private final Player target;

    public Whois(Player sender, Player target) {
        this.sender = sender;
        this.target = target;
    }
    @Override
    public void execute() {
        int playerVersion = Via.getAPI().getPlayerVersion(target.getUniqueId());
        sendMessage(sender, "<green><strikethrough>-+------------+-</strikethrough><gray> - <yellow><bold>{</bold> <gold>MultiCommands<gray> - <dark_green>[<light_purple>whois<dark_green>] <yellow><bold>}</bold> <gray>- <green><strikethrough>-+-------------+-</strikethrough>");
        sendMessage(sender,"<gold>Coordinates<dark_gray>:"
                + " <gold>X<red>" + target.getLocation().getX()
                + " <gold>Y<red> " + target.getLocation().getY()
                + " <gold>Z<red> " + target.getLocation().getZ());
        sendMessage(sender, "<gold>Real name<dark_gray>: " + target.getName());
        sendMessage(sender, "<gold>Nickname<dark_gray>: <red>" + target.customName());
        sendMessage(sender, "<gold>UUID<dark_gray>: <yellow>" + target.getUniqueId());
        sendMessage(sender, "<gold>IP Address<dark_gray>: <red>" + target.getAddress());
        sendCommandMessage(sender, "<gold>Click to teleport to <red>" + target.getName(), "/tp " + target.getName());
        sendMessage(sender, "<gold>World<dark_gray>: <dark_purple>" + target.getWorld().getName());
        sendMessage(sender, "<gold>Fly-mode<dark_gray>: <green>" + target.getAllowFlight());
        sendMessage(sender, "<gold>Gamemode<dark_gray>: <light_purple>" + target.getGameMode());
        sendMessage(sender, "<gold>God-Mode<dark_gray>: <yellow>" + target.isInvulnerable());
        sendMessage(sender, "<gold>Operator<dark_gray>: <aqua>" + target.isOp());
        sendMessage(sender, "<gold>Client name<dark_gray>: <aqua>"+ target.getClientBrandName());
        sendMessage(sender, "<gold>Client version<dark_gray>: <aqua>"+ playerVersion);
        sendMessage(sender, "<gold>Client language<dark_gray>: <aqua>"+ target.locale());
        sendMessage(sender, "<gold>Whitelisted<dark_gray>: <white>" + target.isWhitelisted());
        sendMessage(sender, "<gold>Vanished<dark_gray>: <gray>" + VanishHandler.getInstance().isVanished(target.getPlayer()));
    }
}
