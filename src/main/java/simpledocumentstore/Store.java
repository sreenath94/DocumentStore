package simpledocumentstore;

import java.io.File;
import java.util.Objects;

import simpledocumentstore.exception.DocumentStoreException;
import simpledocumentstore.models.DocumentModel;

/**
 * Interface with methods to be implemented by all 
 * types of Stores
 * 
 * @author Sreenath S
 *
 */
public interface Store {
	
	public void uploadDocument(File f) throws DocumentStoreException;
	
	public DocumentModel downloadDocument(String documentName) throws DocumentStoreException;
	
	public default void validateInputFile(File file) throws DocumentStoreException {
		if(Objects.isNull(file) || !file.exists() ) {
			throw new DocumentStoreException("Input file is not valid");
		}
	}
	
}
