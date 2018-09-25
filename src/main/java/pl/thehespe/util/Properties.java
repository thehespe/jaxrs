package pl.thehespe.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Properties {

	public java.util.Properties getProperties(String path) {
		InputStream inputStream = null;
		java.util.Properties prop = new java.util.Properties();

		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(path);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + path + "' not found in the classpath");
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
