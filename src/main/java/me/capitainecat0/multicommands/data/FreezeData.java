package me.capitainecat0.multicommands.data;

import me.capitainecat0.multicommands.utils.storage.PlayerStorageManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FreezeData {

    private @NotNull Player player;
    private boolean isFrozen;

    public FreezeData(@NotNull Player player) {
        this.player = player;
        this.isFrozen = PlayerStorageManager.manager().getOrDefault("frozen", false, player);
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    private void save() {
        PlayerStorageManager.manager().set("frozen", isFrozen, player);
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
        save();
    }

    public @NotNull Player getPlayer() {
        return player;
    }
}