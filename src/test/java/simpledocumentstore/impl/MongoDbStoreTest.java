package simpledocumentstore.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import simpledocumentstore.exception.DocumentStoreException;

public class MongoDbStoreTest {

	
	private MongoDbStore mongoDbStore = null;
	
	@Before
	public void  init() {
		mongoDbStore =  new MongoDbStore();
	}
	

	@Test(expected = DocumentStoreException.class)
	public void whenInputFileIsNull_shouldThrowDocumentStoreException() throws DocumentStoreException {
		mongoDbStore.uploadDocument(null);
	}
	
	@Test
	public void whenInputFileIsAvailable_shouldUploadDocument() throws DocumentStoreException {
		mongoDbStore.uploadDocument(new File("ss.pdf"));
	}
	
	@Test
	public void whenInputFileIsAvailable_shouldDownloadDocument() throws DocumentStoreException {
		mongoDbStore.downloadDocument("sampledocument.pdf");
		File file = new File("download/sampledocument.pdf");
		assertEquals(file.exists(), true);
	}
	
	@Test(expected = DocumentStoreException.class)
	public void whenInputFileIsNotAvailable_shouldThrowDocumentStoreException() throws DocumentStoreException {
		mongoDbStore.downloadDocument("test.pdf");
	}
	
	
	
	
	

}
