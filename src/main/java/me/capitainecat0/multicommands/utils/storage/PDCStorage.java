package me.capitainecat0.multicommands.utils.storage;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.json.JSONObject;

public class PDCStorage implements PlayerStorageManager {

	private static NamespacedKey key(String key) {
		return new NamespacedKey(MultiCommands.instance(), key);
	}

	@Override
	public void store(Player player, JSONObject data) {
		final PersistentDataContainer container = player.getPersistentDataContainer();

		PersistentDataContainer dataContainer = container.get(key("data"), PersistentDataType.TAG_CONTAINER);
		if (dataContainer == null)
			container.set(key("data"), PersistentDataType.TAG_CONTAINER, dataContainer = player.getPersistentDataContainer());

		dataContainer.set(key("data"), PersistentDataType.STRING, data.toString(4));
	}

	@Override
	public JSONObject retrieve(Player player) {
		final PersistentDataContainer container = player.getPersistentDataContainer();

		PersistentDataContainer dataContainer = container.get(key("data"), PersistentDataType.TAG_CONTAINER);
		if (dataContainer == null)
			return new JSONObject();

		String data = dataContainer.get(key("data"), PersistentDataType.STRING);
		if (data == null)
			return new JSONObject();

		return new JSONObject(data);
	}

}
