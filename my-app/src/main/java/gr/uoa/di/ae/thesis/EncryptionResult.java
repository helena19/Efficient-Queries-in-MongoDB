package gr.uoa.di.ae.thesis;

import org.bson.Document;

public class EncryptionResult {
	private Document document;
	private String key;
	
	public EncryptionResult(Document doc, String key) {
		this.document = doc;
		this.key = key;
	}
	
	public Document getDocument() {
		return this.document;
	}
	
	public String getKey() {
		return this.key;
	}
}
