package simpledocumentstore.models;

public class DocumentModel {
	
	private String fileName;
	
	private byte[] document;
	
	

	public DocumentModel(String fileName, byte[] document) {
		super();
		this.fileName = fileName;
		this.document = document;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

}
