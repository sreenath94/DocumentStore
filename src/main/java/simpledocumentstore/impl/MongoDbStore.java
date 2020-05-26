package simpledocumentstore.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;

import simpledocumentstore.Store;
import simpledocumentstore.exception.DocumentStoreException;
import simpledocumentstore.models.DocumentModel;
import simpledocumentstore.utils.PropertyUtils;

public class MongoDbStore implements Store {

	private Properties properties;

	public void uploadDocument(File file) throws DocumentStoreException {
		validateInputFile(file);
		properties = PropertyUtils.getProperties();
		GridFSBucket gridFSFilesBucket = getGridFSBucket();
		GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(358400)
				.metadata(new Document("type", "presentation"));
		InputStream streamToUploadFrom = null;
		try {
			streamToUploadFrom = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (Objects.nonNull(streamToUploadFrom)) {
			gridFSFilesBucket.uploadFromStream(file.getName(), streamToUploadFrom, options);
		}
	}

	public DocumentModel downloadDocument(String documentName) throws DocumentStoreException {
		properties = PropertyUtils.getProperties();
		String downloadLocation = properties.getProperty("file.download.destination");
		if (Objects.isNull(downloadLocation)) {
			throw new DocumentStoreException("file.download.destination" + "Not available in application.properties");
		}
		GridFSBucket gridFSFilesBucket = getGridFSBucket();
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(downloadLocation + documentName);
		} catch (FileNotFoundException e1) {
			throw new DocumentStoreException(e1.getMessage());
		}
		try {
			gridFSFilesBucket.downloadToStream(documentName, fileOutputStream);
		} catch (Exception e) {
			throw new DocumentStoreException("File Does Not Exist");
		}
		DocumentModel documentModel = null;
		try {
			fileOutputStream.close();
			File newFile = new File(downloadLocation + documentName);
			documentModel = new DocumentModel(documentName, IOUtils.toByteArray(new FileInputStream(newFile)));
		} catch (IOException e1) {
			throw new DocumentStoreException(e1.getMessage());
		}

		return documentModel;
	}


	private GridFSBucket getGridFSBucket() {
		MongoClient mongo = new MongoClient(properties.getProperty("host"),
				Integer.parseInt(properties.getProperty("port")));
		MongoDatabase db = mongo.getDatabase(properties.getProperty("db"));
		GridFSBucket gridFSFilesBucket = GridFSBuckets.create(db, "files");
		return gridFSFilesBucket;
	}

	

}
