package simpledocumentstore.factory;

import java.util.Objects;

import simpledocumentstore.Store;
import simpledocumentstore.constants.Constants;
import simpledocumentstore.exception.DocumentStoreException;
import simpledocumentstore.impl.LocalStore;
import simpledocumentstore.impl.MongoDbStore;
import simpledocumentstore.utils.PropertyUtils;

public class StoreFactory {
	/**
	 * Method will return Store depending upon the type of storage 
	 * added by user in application.properties file
	 * 
	 * @return
	 * @throws DocumentStoreException
	 */
	public static Store getStore() throws DocumentStoreException {
		String storeType = PropertyUtils.getProperties().getProperty("type");
		if (Objects.isNull(storeType)) {
			throw new DocumentStoreException("type" + " missing in properties file");
		}
		if (Constants.LOCALSTORAGE.equalsIgnoreCase(storeType)) {
			return new LocalStore();
		} else if (Constants.MONGODB.equals(storeType)) {
			return new MongoDbStore();
		} else {
			throw new DocumentStoreException("Invalid Store type");
		}

	}

}
