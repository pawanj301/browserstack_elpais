package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class APIConfig {

	private static final Properties props = new Properties();

	static {
		try {
			FileInputStream fis = new FileInputStream("src/main/resources/rapid_translation_api.properties");
			props.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load rapid_translation_api.properties", e);
		}
	}

	public static String get(String key) {
		return props.getProperty(key);
	}
}
