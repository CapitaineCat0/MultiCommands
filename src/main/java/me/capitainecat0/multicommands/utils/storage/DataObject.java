package me.capitainecat0.multicommands.utils.storage;

import java.util.HashMap;
import java.util.Map;

public class DataObject {

	private final Map<String, Object> data;

	public DataObject(Map<String, Object> data) {
		this.data = data;
	}

	public DataObject() {
		this(new HashMap<>());
	}

	public Map<String, Object> getData() {
		return data;
	}
}
