package networks.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import networks.NetworkApp;

/**
 * A Util class to display application banner at the application Start-up.
 * 
 * @author Syed Firoze K
 *
 */
public class BannerUtil {
	/**
	 * Prints the application banner.
	 */
	public static void printBanner() {

		try {

			ClassLoader lClassLoader = NetworkApp.class.getClassLoader();

			try (InputStream stream = lClassLoader.getResourceAsStream(ApplicationProperties.getBanner())) {
				BufferedReader lBufferedReader = new BufferedReader(new InputStreamReader(stream));
				String lLine = null;
				while ((lLine = lBufferedReader.readLine()) != null) {
					System.out.println(lLine);
				}
			} catch (IOException e) {
				System.out.println(String.format("**********%s******", ApplicationProperties.getAppName()));
			}

		} catch (Exception e) {
			System.out.println(String.format("**********%s******", ApplicationProperties.getAppName()));
		}

	}
}
