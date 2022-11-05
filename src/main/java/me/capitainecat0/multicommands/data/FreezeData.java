package me.capitainecat0.multicommands.data;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class FreezeData {

    private @NotNull OfflinePlayer player;
    private boolean isFrozen;

    public FreezeData(@NotNull OfflinePlayer player) {
        /*Json config = new Json(new File("plugins/MultiCommands/frozen_players/" + player.getUniqueId() + ".json"));
        this.player = player;
        this.isFrozen = config.getOrSetDefault("IsFrozen", false);*/
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    private void save() {
        /*Json config = new Json(new File("plugins/MultiCommands/frozen_players/" + player.getUniqueId() + ".json"));
        config.set("pseudo", player.getName());
        config.set("IsFrozen", isFrozen);*/
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
        save();
    }

    public @NotNull OfflinePlayer getPlayer() {
        return player;
    }
}