package networks.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Application properties.
 * 
 * @author Syed Firoze K
 *
 */
public enum ApplicationProperties {
	INSTANCE;

	private final Properties properties;

	ApplicationProperties() {
		properties = new Properties();
		ClassLoader lClassLoader = this.getClass().getClassLoader();
		try (InputStream stream = lClassLoader.getResourceAsStream("application.properties")) {
			properties.load(stream);
		} catch (IOException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
		}
	}

	/**
	 * Returns Application Name
	 * 
	 * @return
	 */
	public static String getAppName() {
		return get("app.name");
	}

	public static String get(String pKey) {
		return INSTANCE.properties.getProperty(pKey);
	}

	/**
	 * Returns the Banner content.
	 * 
	 * @return
	 */
	public static String getBanner() {
		return get("banner");
	}

	/**
	 * Returns the Source-User id.<br>
	 * Source-User is the user from which the shortest-route to a given User is
	 * calculated.
	 * 
	 * @return
	 */
	public static String getShippingSource() {
		return get("shipping.source");
	}

	public static String getExitMessage() {
		return get("exit.message");
	}

}