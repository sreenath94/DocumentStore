package simpledocumentstore;

import java.io.File;

import simpledocumentstore.exception.DocumentStoreException;
import simpledocumentstore.factory.StoreFactory;
import simpledocumentstore.models.DocumentModel;

public class DocumentStore {

//	public static void main(String args[]) throws DocumentStoreException {
//		DocumentStore.uploadDocument(new File("target/sampledocument.pdf"));
//		System.out.println(DocumentStore.downloadDocument("sampledocument.pdf").getDocument());
//	}

	/**
	 * Method used to upload file to the configured Store in application.properties
	 * file
	 * 
	 * @param file
	 * @throws DocumentStoreException
	 */
	public static void uploadDocument(File file) throws DocumentStoreException {
		Store store = StoreFactory.getStore();
		store.uploadDocument(file);
	}

	/**
	 * Method used to download file and save to the configured location
	 * Configuration are to be done in application.properties file as per the
	 * configurations
	 * 
	 * @param documentName
	 * @return
	 * @throws DocumentStoreException
	 */
	public static DocumentModel downloadDocument(String documentName) throws DocumentStoreException {
		Store store = StoreFactory.getStore();
		return store.downloadDocument(documentName);
	}

}
