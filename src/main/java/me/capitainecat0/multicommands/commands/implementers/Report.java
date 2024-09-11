package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import me.capitainecat0.multicommands.utils.storage.StoreReport;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.capitainecat0.multicommands.utils.Messenger.NOT_A_PLAYER;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class Report implements CommandsImpl {

    private static Player player;
    private static Player author;
    private static String message;

    public Report(Player player, Player author, String @NotNull [] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        sb.append(args[args.length - 1]);
        Report.player = player;
        Report.author = author;
        Report.message = sb.toString();
    }

    @Override
    public void execute() {
        if(!player.isOnline()){
            getMsgSendConfig(author, "Report", NOT_A_PLAYER.getMessage().replace("{0}", player.getName()));
            playSoundIfEnabled(author, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1, 1);
            return;
        } else if (StoreReport.findReport(player.getUniqueId()) != null) {
            StoreReport.createReport(player, author, message);
            //StoreReport.saveReport();
            getMsgSendConfig(author, "Report", "<red>This player has already been reported! <newline>New report has been created!");
            playSoundIfEnabled(author, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("no-perm-sound")), 1, 1);
            return;
        }
        StoreReport.createReport(player, author, message);
       //StoreReport.saveReport();
        sendMessage(player, "Report", "<green>The report message: <newline> <yellow>"+message+ "<newline> <green>has been sent!");
        playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1, 1);
    }
}
