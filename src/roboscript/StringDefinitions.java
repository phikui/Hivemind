package roboscript;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/*
 * 
 * This class loads all the strings for keywords and constants from properties files
 * 
 */
public class StringDefinitions {
	private static Properties keywords, constants;

	public static Properties getKeywords() {
		if (keywords == null) {
			keywords = new Properties();
			InputStream input = null;

			try {

				input = new FileInputStream("./properties/roboscript/keywords.properties");

				// load a properties file
				keywords.load(input);

			} catch (IOException ex) {
				// ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						// e.printStackTrace();
					}
				}
			}
		}

		return keywords;
	}

	public static Properties getConstants() {
		if (constants == null) {
			constants = new Properties();
			InputStream input = null;

			try {

				input = new FileInputStream("./properties/roboscript/constants.properties");

				// load a properties file
				constants.load(input);

			} catch (IOException ex) {
				// ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						// e.printStackTrace();
					}
				}
			}
		}

		return constants;
	}
}
