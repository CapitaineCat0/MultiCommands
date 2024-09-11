package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import me.capitainecat0.multicommands.utils.storage.BannedData;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

import static me.capitainecat0.multicommands.MultiCommands.getInstance;
import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.playSoundIfEnabled;
import static me.capitainecat0.multicommands.utils.MessengerUtils.sendMessage;

public class Ban implements CommandsImpl {
    private final Player player;
    private final Player sender;
    private final String msg;

    public Ban(OfflinePlayer target, CommandSender sender, @NotNull String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        sb.append(args[args.length - 1]);
        this.player = (Player) target;
        this.sender = (Player) sender;
        this.msg = sb.toString();
    }

    @Override
    public void execute() {
        BannedData bannedData = new BannedData();
        bannedData.add(player);
        getInstance().getServer().getBanList(BanList.Type.NAME).addBan(Objects.requireNonNull(player.getName()), BAN_PREFIX.getMessage().replace("{0}", msg), null, sender.getName());
        playSoundIfEnabled(sender, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
        sendMessage(sender, BAN_DONE.getMessage().replace("{0}", player.getName()));
        for (OfflinePlayer player : Bukkit.getOperators()){
            sendMessage((Player) player, BAN_ADMIN.getMessage().replace("{0}", Objects.requireNonNull(player.getName())).replace("{1}", sender.getName()).replace("{2}", msg));
        }
    }
}
