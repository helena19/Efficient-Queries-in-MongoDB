package gr.uoa.di.ae.thesis;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bson.Document;

import com.mongodb.Block;

import com.mongodb.client.MongoCollection;

public class AEMongoCollectionRandomPass {

	/*The documents*/
	private MongoCollection<Document> randomPassCollection;
	/*The encrypted field + type of encryption*/
	private MongoCollection<Document> randomPassFieldCollection;
	/*The random key with the document id*/
	private MongoCollection<Document> randomPassDocKey;
	private RandomPassEncryption encryption;
	private Map<String, String> encryptedFields;
	
	/*Constructor*/
	public AEMongoCollectionRandomPass(MongoCollection<Document> randomPassCollection, MongoCollection<Document> randomPassFieldCollection, MongoCollection<Document> randomPassDocKey) {
		this.randomPassCollection = randomPassCollection;
		this.randomPassFieldCollection = randomPassFieldCollection;
		this.randomPassDocKey = randomPassDocKey;
		encryption = new RandomPassEncryption();
		encryptedFields = new HashMap<String, String>();
	}
	
	/*Set the encryption to random*/
	public void setEncryptedFieldRandomPass(String field, EncryptionType enc) {
		String encryptionType = "";
		if (enc == EncryptionType.RANDOM) {
			encryptionType = encryptionType + "random";
		}
		Document doc = new Document("field", field).append("Encryption Type", encryptionType);
		if (randomPassFieldCollection.find(doc).first() != null) {
			System.out.println("You have already defined an encryption type for the field " + field);			
		}
		else {
			randomPassFieldCollection.insertOne(doc);
		}
	}
	
	/*Encrypt the document with random pass and keep doc id with its random pass*/
	public void insertOneRandomPass(Document document) {
		String key = "";
		EncryptionResult result = encryptDocumentRandomPass(document, "", key);
		randomPassCollection.insertOne(result.getDocument());
		Document tempDoc = new Document("DocID", result.getDocument().get("_id")).append("RandomKey", result.getKey());
		randomPassDocKey.insertOne(tempDoc);
	}
	
	public EncryptionResult encryptDocumentRandomPass(Document document, String pathD, String docKey) {
		String fieldName;
		String path;
		Object fieldValue;
		for (Entry<String, Object> field: document.entrySet()) {
			fieldName = field.getKey();
			path = "";
			if (!pathD.equals(""))
				path = pathD + "." + fieldName;
			else
				path += fieldName;
			fieldValue = field.getValue();
			if (fieldValue instanceof Document) {
				Document tempDoc = (Document) fieldValue;
				encryptDocumentRandomPass(tempDoc, path, docKey);
			}
			else if (fieldValue instanceof String) {
				String stringValue = (String) fieldValue;
				encryptFieldRandomPass(path, stringValue, field);
				if (!stringValue.equals((String) field.getValue()))
					docKey = (String) field.getValue();
			}
			else if (fieldValue instanceof Integer) {
				Integer integerValue = (Integer) fieldValue;
				encryptFieldRandomPass(path, integerValue.toString(), field);
				if (!integerValue.equals(field.getValue()))
					docKey = (String) field.getValue();
			}
			else if (fieldValue instanceof Float) {
				Float floatValue = (Float) fieldValue;
				encryptFieldRandomPass(path, floatValue.toString(), field);
				if (!floatValue.equals(field.getValue()))
					docKey = (String) field.getValue();
			}
		}
		EncryptionResult res = new EncryptionResult(document, docKey);
		return res;
	}
	
	public void encryptFieldRandomPass(String path, String valueToEncrypt, Entry<String, Object> field) {
		Document doc = randomPassFieldCollection.find(new Document("field", path)).first();
		if (doc != null) {
			if (doc.get("Encryption Type").equals("random")) {
				String encoded = encryption.randomPassEncrypt(valueToEncrypt);
				field.setValue(encoded);
			}
		}
	}
	
	public void importEncryptedFields() {
		randomPassFieldCollection.find().forEach((Block <Document>) document ->
		{
			encryptedFields.put((String)document.get("field"), (String) document.get("enc"));
		});
	}
	
}
