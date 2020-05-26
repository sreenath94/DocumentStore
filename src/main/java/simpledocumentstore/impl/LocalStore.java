package simpledocumentstore.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import simpledocumentstore.Store;
import simpledocumentstore.exception.DocumentStoreException;
import simpledocumentstore.models.DocumentModel;
import simpledocumentstore.utils.PropertyUtils;

public class LocalStore implements Store {

	public void uploadDocument(File f) throws DocumentStoreException {
		validateInputFile(f);
		Properties p = PropertyUtils.getProperties();
		String destinationDirectory = p.getProperty("file.destination");
		if (Objects.isNull(destinationDirectory)) {
			throw new DocumentStoreException("file.destination" + "Not available in application.properties");
		}

		String destination = destinationDirectory + f.getName();
		File dest = new File(destination);
		try {
			FileUtils.copyFile(f, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DocumentModel downloadDocument(String documentName) throws DocumentStoreException {
		Properties p = PropertyUtils.getProperties();
		String uploadLocation = p.getProperty("file.destination");
		String destinationFile = p.getProperty("file.download.destination");
		if (Objects.isNull(uploadLocation)) {
			throw new DocumentStoreException("file.destination" + "Not available in application.properties");
		} else if (Objects.isNull(destinationFile)) {
			throw new DocumentStoreException("file.download.destination" + "Not available in application.properties");
		}
		File initialFile = new File(uploadLocation + documentName);

		File dest = new File(destinationFile + documentName);
		DocumentModel documentModel = null;
		try {
			FileUtils.copyFile(initialFile, dest);
			documentModel = new DocumentModel(documentName, IOUtils.toByteArray(new FileInputStream(dest)));
		} catch (IOException e) {
			throw new DocumentStoreException(e.getMessage());
		}

		return documentModel;
	}

}
