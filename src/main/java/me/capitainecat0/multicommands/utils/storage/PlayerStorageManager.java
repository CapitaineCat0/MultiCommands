package me.capitainecat0.multicommands.utils.storage;

import org.bukkit.entity.Player;
import org.json.JSONObject;

public interface PlayerStorageManager {

	static PlayerStorageManager manager() {
		try {
			Class.forName("org.bukkit.persistence.PersistentDataHolder");
			return new PDCStorage();
		} catch (Exception ex) {
			return new FileStorage();
		}
	}

	void store(Player player, JSONObject data);

	JSONObject retrieve(Player player);


	default <T> T get(String key, Player player) {
		return (T) retrieve(player).opt(key);
	}

	default <T> void set(String key, T value, Player player) {
		JSONObject data = retrieve(player);
		data.put(key, value);
		store(player, data);
	}

	default void remove(String key, Player player) {
		JSONObject data = retrieve(player);
		data.remove(key);
		store(player, data);
	}

	default boolean has(String key, Player player) {
		return retrieve(player).has(key);
	}

	default void clear(Player player) {
		store(player, new JSONObject());
	}

	default <T> T getOrDefault(String key, T def, Player player) {
		return has(key, player) ? get(key, player) : def;
	}
}
