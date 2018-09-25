package pl.thehespe.util;

import java.util.Properties;

public class ConnectData {

	protected final static String URL = "url";
	protected final static String USERNAME = "username";
	protected final static String PASSWORD = "password";

	public Properties getPropValues() {
		return new pl.thehespe.util.Properties().getProperties("liquibase/liquibase.properties");

	}
}
