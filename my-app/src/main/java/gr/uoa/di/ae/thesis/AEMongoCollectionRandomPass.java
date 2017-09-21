package gr.uoa.di.ae.thesis;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.NoSuchPaddingException;

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

	private final String ENCRYPTION_TYPE;
	private final String RANDOM;
	private final String FIELD;
	private final String RANDOM_KEY;
	private final String DOC_ID;
	private final String ID;
	
	/*Constructor*/
	public AEMongoCollectionRandomPass(MongoCollection<Document> randomPassCollection, MongoCollection<Document> randomPassFieldCollection, MongoCollection<Document> randomPassDocKey) {
		this.randomPassCollection = randomPassCollection;
		this.randomPassFieldCollection = randomPassFieldCollection;
		this.randomPassDocKey = randomPassDocKey;
		encryption = new RandomPassEncryption();
		encryptedFields = new HashMap<String, String>();
		ENCRYPTION_TYPE = "Encryption Type";
		RANDOM = "random";
		FIELD = "field";
		RANDOM_KEY= "RandomKey";
		DOC_ID = "DocID";
		ID = "_id";
		
	}
	
	/*Set the encryption to random*/
	public void setEncryptedFieldRandomPass(String field, EncryptionType enc) {
		String encryptionType = "";
		if (enc == EncryptionType.RANDOM) {
			encryptionType = encryptionType + RANDOM;
		}
		Document doc = new Document(FIELD, field).append(ENCRYPTION_TYPE, encryptionType);
		if (randomPassFieldCollection.find(doc).first() != null) {
			System.out.println("You have already defined an encryption type for the field " + field);			
		}
		else {
			randomPassFieldCollection.insertOne(doc);
		}
	}
	
	/*Encrypt the document with random pass and keep doc id with its random pass*/
	public void insertOneRandomPass(Document document) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		String key = "";
		EncryptionResult result = encryptDocumentRandomPass(document, "", key);
		randomPassCollection.insertOne(result.getDocument());
		Document tempDoc = new Document(DOC_ID, result.getDocument().get(ID)).append(RANDOM_KEY, result.getKey());
		randomPassDocKey.insertOne(tempDoc);
	}
	
	public EncryptionResult encryptDocumentRandomPass(Document document, String pathD, String docKey) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
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
				//String key = "";
				encryptFieldRandomPass(path, stringValue, field);//, key);
				if (!stringValue.equals((String) field.getValue()))
					docKey = (String) field.getValue();
			}
			else if (fieldValue instanceof Integer) {
				Integer integerValue = (Integer) fieldValue;
				//String key = "";
				encryptFieldRandomPass(path, integerValue.toString(), field);//, key);
				if (!integerValue.equals(field.getValue()))
					docKey = field.getValue().toString();
			}
			else if (fieldValue instanceof Float) {
				Float floatValue = (Float) fieldValue;
				//String key = "";
				encryptFieldRandomPass(path, floatValue.toString(), field);//, key);
				if (!floatValue.equals(field.getValue()))
					docKey = field.getValue().toString();
			}
		}
		EncryptionResult res = new EncryptionResult(document, docKey);
		return res;
	}
	
	public void encryptFieldRandomPass(String path, String valueToEncrypt, Entry<String, Object> field) {
		Document doc = randomPassFieldCollection.find(new Document(FIELD, path)).first();
		if (doc != null) {
			if (doc.get(ENCRYPTION_TYPE).equals(RANDOM)) {
				String encoded = encryption.randomPassEncrypt1(valueToEncrypt);
				field.setValue(encoded);
			}
		}
	}
	
	//public void encryptFieldRandomPass(String path, String valueToEncrypt, Entry<String, Object> field, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
	//	Document doc = randomPassFieldCollection.find(new Document("field", path)).first();
	//	if (doc != null) {
	//		if (doc.get("Encryption Type").equals("random")) {
	//			byte[] value = valueToEncrypt.getBytes();
	//			Encoding password = encryption.randomPassEncrypt2(value);
	//			key = password.getKey().toString();
	//			field.setValue(password.getEncoded());
	//		}
	//	}
	//}
	
	public void importEncryptedFields() {
		randomPassFieldCollection.find().forEach((Block <Document>) document ->
		{
			encryptedFields.put((String)document.get(FIELD), (String) document.get(ENCRYPTION_TYPE));
		});
	}
	
}
