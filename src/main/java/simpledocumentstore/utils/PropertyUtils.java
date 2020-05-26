package simpledocumentstore.utils;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import simpledocumentstore.constants.Constants;
import simpledocumentstore.exception.DocumentStoreException;

/**
 * Utility class to fetch application.properties file from
 * parent resource folder
 * 
 * @author Sreenath S
 *
 */
public class PropertyUtils {
	
	private static Properties properties=null;
	
	private static final String EXCEPTION_MSG = "application.properties File Not Available";
	
	private PropertyUtils() {
	}
	
	public static Properties getProperties() throws DocumentStoreException {
		
		if(Objects.isNull(properties)) {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			Properties p = new Properties();
			try {
				p.load(loader.getResourceAsStream(Constants.PROPERTIES_FILE));
			} catch (IOException e1) {
				throw new DocumentStoreException(EXCEPTION_MSG);
			}
			properties = p;
			return p;
		}else {
			return properties;
		}
		
	}
	
}
