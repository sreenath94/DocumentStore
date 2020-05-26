package simpledocumentstore.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import simpledocumentstore.Store;
import simpledocumentstore.constants.Constants;
import simpledocumentstore.exception.DocumentStoreException;
import simpledocumentstore.models.DocumentModel;
import simpledocumentstore.utils.PropertyUtils;

public class LocalStore implements Store {
	
	private final String EXP_NOT_AVL = " Not available in application.properties";

	public void uploadDocument(File f) throws DocumentStoreException {
		validateInputFile(f);
		Properties p = PropertyUtils.getProperties();
		String destinationDirectory = p.getProperty(Constants.FILE_UPLOAD_DESTINATION);
		if (Objects.isNull(destinationDirectory)) {
			throw new DocumentStoreException(Constants.FILE_UPLOAD_DESTINATION + EXP_NOT_AVL);
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
		String uploadLocation = p.getProperty(Constants.FILE_UPLOAD_DESTINATION);
		String destinationFile = p.getProperty(Constants.FILE_DOWNLOAD_DESTINATION);
		if (Objects.isNull(uploadLocation)) {
			throw new DocumentStoreException(Constants.FILE_UPLOAD_DESTINATION +EXP_NOT_AVL);
		} else if (Objects.isNull(destinationFile)) {
			throw new DocumentStoreException(Constants.FILE_DOWNLOAD_DESTINATION + EXP_NOT_AVL);
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
