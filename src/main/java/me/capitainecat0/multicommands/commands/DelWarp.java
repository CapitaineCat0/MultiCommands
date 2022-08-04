package me.capitainecat0.multicommands.commands;

import de.leonhard.storage.Json;
import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.Messenger;
import me.capitainecat0.multicommands.utils.Perms;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class DelWarp implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission(Perms.WARPS.getPermission()) || !sender.hasPermission(Perms.ALL_PERMS.getPermission())){
            sender.sendMessage(Messenger.CMD_NO_PERM.getMessage().replace("%cmd%", command.getName()));
        }
        if(args.length < 1){
            sender.sendMessage(Messenger.WARPS_ERROR_DELETE.getMessage());
        }else{
            if(!new File("plugins/MultiCommands/warps/" + args[0] + ".json").exists()){
                sender.sendMessage(Messenger.WARPS_ERROR.getMessage().replace("%w", args[0]));
            }else{
                Json config = new Json(new File("plugins/MultiCommands/warps/" + args[0] + ".json"));
                //config.clear();
                //sender.sendMessage(Messenger.WARPS_ERROR_DELETE_DONE.getMessage().replace("%w", args[0]));
                sender.sendMessage(MultiCommands.colored("&cCette commande est encore en dévelopement. Vous pouvez le supprimer dans le dossier &eMultiCommands/warps&c!"));
            }
        }
        return false;
    }
}
