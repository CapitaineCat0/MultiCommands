package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.MessengerUtils.sendBossBar;

public class BossBar implements CommandsImpl {
    private final Player player;
    private final String msg;

    public BossBar(Player player, @NotNull String @NotNull [] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        sb.append(args[args.length - 1]);
        this.player = player;
        this.msg = sb.toString().replace(player.getName(), "");
    }
    @Override
    public void execute() {
        sendBossBar(player,(float) 1, net.kyori.adventure.bossbar.BossBar.Color.GREEN, net.kyori.adventure.bossbar.BossBar.Overlay.NOTCHED_20, msg);
    }
}
