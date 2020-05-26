package simpledocumentstore.impl;



import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import simpledocumentstore.exception.DocumentStoreException;


public class LocalStoreTest {
	
	private LocalStore localStore = null;
	

	

	@Test(expected = DocumentStoreException.class)
	public void whenInputFileIsNull_shouldThrowDocumentStoreException() throws DocumentStoreException {
		localStore =  new LocalStore();
		localStore.uploadDocument(null);
		//assertNotNull(store);
	}
	
	@Test
	public void whenInputFileIsAvailable_shouldUploadDocument() throws DocumentStoreException {
		localStore =  new LocalStore();
		localStore.uploadDocument(new File("ss.pdf"));
		File file = new File("test/sampledocument.pdf");
		assertEquals(file.exists(), true);
	}
	
	@Test
	public void whenInputFileIsAvailable_shouldDownloadDocument() throws DocumentStoreException {
		localStore =  new LocalStore();
		localStore.downloadDocument("sampledocument.pdf");
		File file = new File("download/sampledocument.pdf");
		assertEquals(file.exists(), true);
	}
	
	@Test(expected = DocumentStoreException.class)
	public void whenInputFileIsNotAvailable_shouldThrowDocumentStoreException() throws DocumentStoreException {
		localStore =  new LocalStore();
		localStore.downloadDocument("test.pdf");
	}
	
	
	
	
	
}
