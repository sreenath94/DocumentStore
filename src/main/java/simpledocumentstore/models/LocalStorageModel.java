package simpledocumentstore.models;

import java.util.Properties;

public class LocalStorageModel extends AbstractModel {

	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalStorageModel() {
		Properties p =  getProperties();
		setLocation(p.getProperty("file.destination"));
	}

	@Override
	public void validatePropertyFile() {
		
		
	}
	
	
}
