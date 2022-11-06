package me.capitainecat0.multicommands.utils.storage;

import me.capitainecat0.multicommands.MultiCommands;
import org.bukkit.entity.Player;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileStorage implements PlayerStorageManager {

	@Override
	public void store(Player player, JSONObject data) {
		final File file = new File(MultiCommands.instance().getDataFolder(), "data/" + player.getUniqueId() + ".json");
		file.getParentFile().mkdirs();

		try {
			Files.write(file.toPath(), data.toString(4).getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public JSONObject retrieve(Player player) {
		final File file = new File(MultiCommands.instance().getDataFolder(), "data/" + player.getUniqueId() + ".json");
		if (!file.exists()) return new JSONObject();

		try {
			return new JSONObject(new String(Files.readAllBytes(file.toPath())));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
