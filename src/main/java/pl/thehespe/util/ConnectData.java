package pl.thehespe.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectData {

	protected final static String URL = "url";
	protected final static String USERNAME = "username";
	protected final static String PASSWORD = "password";

	public Properties getPropValues() {
		InputStream inputStream = null;
		Properties prop = new Properties();

		try {
			String propFileName = "liquibase/liquibase.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

		} catch (IOException e) {
			System.out.println("Exception: " + e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					System.out.println("Exception: " + e);
				}
			}
		}

		return prop;

	}
}
