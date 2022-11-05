package me.capitainecat0.multicommands.utils.storage;

import org.bukkit.entity.Player;

public interface PlayerStorageManager {

	void store(Player player, DataObject data);

	DataObject retrieve(Player player);

}
