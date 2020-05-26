package simpledocumentstore.models;

import java.io.IOException;
import java.util.Properties;

public abstract class AbstractModel {

	public String type;

	public Properties getProperties() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties p = new Properties();
		try {
			p.load(loader.getResourceAsStream("application.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		setType(p.getProperty("type"));
		return p;
	}
	
	public abstract void validatePropertyFile();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
