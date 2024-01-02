package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.MessengerUtils.sendBossBar;

public class BossBar implements CommandsImpl {
    private final Player player;
    private final String msg;

    public BossBar(Player player, @NotNull String @NotNull [] message) {
        StringBuilder bc = new StringBuilder();
        for (String part : message) {
            bc.append(part).append(" ");
        }
        this.player = player;
        this.msg = String.valueOf(bc).replace(player.getName(), "");
    }
    @Override
    public void execute() {
        sendBossBar(player,(float) 1, net.kyori.adventure.bossbar.BossBar.Color.GREEN, net.kyori.adventure.bossbar.BossBar.Overlay.NOTCHED_20, msg);
    }
}
