package me.capitainecat0.multicommands.commands.implementers;

import me.capitainecat0.multicommands.MultiCommands;
import me.capitainecat0.multicommands.utils.CommandsImpl;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;

import static me.capitainecat0.multicommands.utils.Messenger.*;
import static me.capitainecat0.multicommands.utils.MessengerUtils.*;

public class Level implements CommandsImpl {

    private final Player player;
    private final String action;
    private final int level;
    private final Player target;

    @Contract(pure = true)
    public Level(Player player, Player target, String action, int level) {
        this.player = player;
        this.target = target;
        this.action = action.toLowerCase();
        this.level = level;
    }
    @Override
    public void execute() {
        int level = 0;
        try {
            if (target != null) {
                switch (action) {
                    case "add" -> {
                        playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        getMsgSendConfig(player, "Level " + action, LEVEL_ADD_OTHER.getMessage().replace("{0}", String.valueOf(level)).replace("{1}", target.getName()));
                        getMsgSendConfig(target, "Level " + action, LEVEL_ADD.getMessage().replace("{0}", String.valueOf(level)));
                        target.setLevel(player.getLevel() + level);
                    }
                    case "set" -> {
                        playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        getMsgSendConfig(player, "Level " + action, LEVEL_SET_OTHER.getMessage().replace("{0}", String.valueOf(level)).replace("{1}", target.getName()));
                        getMsgSendConfig(target, "Level " + action, LEVEL_SET.getMessage().replace("{0}", String.valueOf(level)));
                        target.setLevel(level);
                    }
                    case "remove" -> {
                        if (level <= target.getLevel()) {
                            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            getMsgSendConfig(player, "Level " + action, LEVEL_REMOVE_OTHER.getMessage().replace("{0}", String.valueOf(level)).replace("{1}", target.getName()));
                            getMsgSendConfig(target, "Level " + action, LEVEL_REMOVE.getMessage().replace("{0}", String.valueOf(level)));
                            target.setLevel(target.getLevel() - level);
                        } else {
                            target.setLevel(0);
                        }
                    }
                }
            } else {
                switch (action) {
                    case "add" -> {
                        playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        getMsgSendConfig(player, "Level " + action, LEVEL_ADD.getMessage().replace("{0}", String.valueOf(level)));
                        player.setLevel(player.getLevel() + level);
                    }
                    case "set" -> {
                        playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                        getMsgSendConfig(player, "Level " + action, LEVEL_SET.getMessage().replace("{0}", String.valueOf(level)));
                        player.setLevel(level);
                    }
                    case "remove" -> {
                        if (level <= player.getLevel()) {
                            playSoundIfEnabled(player, Sound.valueOf(MultiCommands.getInstance().getConfig().getString("cmd-done-sound")), 1f, 1f);
                            getMsgSendConfig(player, "Level " + action, LEVEL_REMOVE.getMessage().replace("{0}", String.valueOf(level)));
                            player.setLevel(player.getLevel() - level);
                        } else {
                            player.setLevel(0);
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            sendConsoleMessage(e.getMessage());
        }
    }
}
