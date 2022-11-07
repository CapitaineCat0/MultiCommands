package me.capitainecat0.multicommands.utils.storage;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PDCStorage implements PlayerStorageManager {

	private static NamespacedKey key(String key) {
		return new NamespacedKey(MultiCommands.instance(), key);
	}

	@Override
	public void store(Player player, DataObject data) {
		final PersistentDataContainer container = player.getPersistentDataContainer();
		final PersistentDataContainer dataContainer = player.getPersistentDataContainer();

		container.set(key("data"), PersistentDataType.TAG_CONTAINER, dataContainer);
	}

	@Override
	public DataObject getData(Player player) {
		final PersistentDataContainer container = player.getPersistentDataContainer();
		final PersistentDataContainer dataContainer = container.get(key("data"), PersistentDataType.TAG_CONTAINER);
		assert dataContainer != null;
		dataContainer.set(key("player"), PersistentDataType.STRING, player.getName());

		return new DataObject();
	}

}
