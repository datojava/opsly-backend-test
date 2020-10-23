package com.opsly.newsfeed.i18n;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MSGBundle {

	private static volatile MSGBundle instance;

	private Map<String, String> items = null;

	private final Logger log = LogManager.getLogger(getClass());

	private MSGBundle() {

		load();
	}

	public static MSGBundle getInstance() {
		if (instance == null) {

			synchronized (MSGBundle.class) {

				if (instance == null) {

					instance = new MSGBundle();

				}
			}

		}

		return instance;
	}

	private void load() {
		items = new ConcurrentHashMap<>();

		Properties props = new Properties();

		try {
			props.load(MSGBundle.class.getClassLoader().getResourceAsStream("messages.properties"));

			if (props != null) {
				for (Map.Entry<Object, Object> e : props.entrySet()) {
					items.put(e.getKey().toString(), e.getValue().toString());
				}
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	public String get(String key) {
		return items.get(key);
	}
}
