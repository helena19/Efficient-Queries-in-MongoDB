package gr.uoa.di.ae.thesis;

import java.security.Key;
//import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.RandomStringUtils;
import org.bson.Document;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;

import com.mongodb.client.MongoCollection;

public class AEMongoCollection {
	
	private MongoCollection<Document> collection;
	private MongoCollection<Document> fieldCollection;
		
	private Encryption encryption;
	
	private RandomPassEncryption randomPassEncryption;
	private BCryptPasswordEncoder encoder;
	
	private Map<String,String> encFields;
	
	private static final String FIELD = "field";
	private static final String ENCODING_TYPE = "enc";
	private static final String HASH = "hash";
	private static final String RANDOM = "random";
	private static final String SECRET_VALUE = "SECRET VALUE";
	
	private static final String ALGORITHM = "AES";
	private Cipher cipher;
	private Key key;
	
	//private static final String RANDOM_KEY = "RandomKey";
	//private static final String DOC_ID = "DocID";
	//private static final String ID = "_id";
	
	public AEMongoCollection(MongoCollection<Document> collection, MongoCollection<Document> fieldCollection) throws Exception {
		this.collection = collection;
		this.fieldCollection = fieldCollection;
		this.encryption = new Encryption();
		this.encFields = new HashMap<String,String>();
		this.randomPassEncryption = new RandomPassEncryption();
		this.encoder = new BCryptPasswordEncoder();
		//this.cipher = Cipher.getInstance(ALGORITHM);
		//this.key = generateKey();
	}
	
	public static Key generateKey() throws Exception {
		String keyValue = RandomStringUtils.random(32);
		byte[] aesKey = keyValue.getBytes();
		System.out.println("The key length is " + keyValue.length());
		return new SecretKeySpec(aesKey, ALGORITHM);
	}

	/*Insert the encrypted field in the dedicated collection,
	 * if it doesn't exist already*/	
	public void setEncryptedField(String field, EncryptionType enc) {
		String encryption_type = "";
		if (enc == EncryptionType.HASH)
			encryption_type += HASH;
		else if (enc == EncryptionType.RANDOM)
			encryption_type += RANDOM;
		Document doc = new Document(FIELD, field).append(ENCODING_TYPE, encryption_type);
		if (fieldCollection.find(doc).first() != null)
			System.out.println("You have already defined an encryption type for the field " + field);
		else
			fieldCollection.insertOne(doc);
	}
	
	/*Bring all the EncryptedFields at the Online Collection statically (topically)*/
	public void importEncryptedFields() {
		fieldCollection.find().forEach( (Block <Document>) document ->
		{
			encFields.put((String) document.get(FIELD),(String) document.get(ENCODING_TYPE));
		});
	}

	public void insertOne(Document document, EncryptionType enc) throws Exception {
		if (enc == EncryptionType.HASH) {
			System.out.println("bika edw");
			insertOneHash(document);
		}
		else if (enc == EncryptionType.RANDOM) {
			insertOneRandomPass(document);
		}
	}
	
	/*Insert a Document*/
	public void insertOneHash(Document document) throws Exception {
		Document doc = encryptDocument(document,"");
		collection.insertOne(doc);
	}
	
	/*Encrypt the document with random pass and keep doc id with its random pass*/
	public void insertOneRandomPass(Document document) throws Exception {
		String key = "";
		EncryptionResult result = new EncryptionResult();
		encryptDocumentRandomPass(document, "", key, result);
		collection.insertOne(result.getDocument());
	}
	
	/*Find the Documents that match the Document given*/
	public List<Document> find(Document document) throws Exception {
		FindIterable<Document> doc = collection.find(encryptDocument(document,""));
		if (doc == null)
			return null;
		else {
			List<Document> set=new ArrayList<Document>();
			doc.forEach((Block <Document>) document2 -> 
			{ 
				set.add(decryptDocument(document2,""));
			}
			);
			return set;
		}	
	}
	
	public List<Document> find2(Document document){
		HashMap<String,Object> fields=new HashMap<String,Object>();
		Document temp = new Document();
		parseDocument(document,temp,fields,"");
		FindIterable<Document> doc = collection.find(temp);
		if (doc == null)
			return null;
		else {
			List<Document> set=new ArrayList<Document>();
			if(fields.isEmpty())
			{
				doc.forEach((Block <Document>) document2 -> 
				{ 
					set.add(decryptDocument(document2,""));
				}
				);
				return set;
			}
			else {
				doc.forEach((Block <Document>) document2 ->{ 
					match(document2,fields,set);
				}
				);
				return set;
			}
		}	
	}
	
	public void parseDocument(Document document,Document ret,HashMap<String,Object> fields,String pathD) {
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
				parseDocument(tempDoc,ret,fields,pathD);
			}
			else
			{
				if (encFields.containsKey(path)) {
					fields.put(path,fieldValue);
				}
				else
					ret.append(path,fieldValue);
			}
		}
	}
	
	public void match(Document document,HashMap<String,Object> fields,List<Document> returnset) {
		 Set<Entry<String, Object>> set = fields.entrySet();
	     Iterator<Entry<String, Object>> iterator = set.iterator();
	     while(iterator.hasNext()) 
	     {
	         HashMap.Entry mentry = (HashMap.Entry)iterator.next();
	         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	         System.out.println(mentry.getValue());
	         Object value=document.get(mentry.getKey());
	         if(value instanceof String)
	         {
	        	 String toCompare=(String) value;
	        	 System.out.println("Sugkrinw "+toCompare+" me "+ (String )mentry.getValue());
	        	// if(!toCompare.matches((String) mentry.getValue()))
	        		//return;	 
	         }
	         else if(value instanceof Float)
	         { 
	        	 Float toCompare=(Float) value;
	        	 System.out.println("Sugkrinw "+toCompare+" me "+ (Float )mentry.getValue());
	        	// if(!toCompare.)
	         }
	         else if(value instanceof Integer)
	         { 
	        	 Integer toCompare=(Integer) value;
	        	 System.out.println("Sugkrinw "+toCompare+" me "+ (Integer )mentry.getValue());
	        	 //if(!toCompare.)
	         }
	      }
	}
	
	/*Find and return all the Documents */
	public List<Document> find() {
		FindIterable<Document> doc = collection.find();
		if (doc == null)
			return null;
		else {
			List<Document> set = new ArrayList<Document>();
			doc.forEach((Block <Document>) document2 -> 
			{ 
				set.add(decryptDocument(document2,""));
			}
			);
			return set;
		}
	}
	
	/*Insert Many Records*/
	public void insertMany(List<Document> records) {
		for (Document doc : records) {
			collection.insertOne(doc);
		}
	}
	
	/*Encrypt a Document using the right type of encryption*/
	public Document encryptDocument(Document document, String pathD) throws Exception {
		String fieldName;
		String path;
		Object fieldValue;
		for (Entry<String, Object> field: document.entrySet()) 
		{
			fieldName = field.getKey();
			path = "";
			if (!pathD.equals(""))
				path = pathD + "." + fieldName;
			else
				path = path + fieldName;
			
			fieldValue = field.getValue();
			if (fieldValue instanceof Document) 
			{
				Document tempDoc = (Document) fieldValue;
				encryptDocument(tempDoc, path);
			}		
			else if (fieldValue instanceof String) 
			{
				String stringValue = (String) fieldValue;
				encryptIfNeeded2(path, stringValue, field);
			}
			else if (fieldValue instanceof Integer) 
			{
				Integer integerValue = (Integer) fieldValue;
				encryptIfNeeded2(path, Integer.toString(integerValue), field);
			}
			else if (fieldValue instanceof Float)
			{
				Float floatValue = (Float) fieldValue;
				encryptIfNeeded2(path, Float.toString(floatValue), field);
			}			
		}    
		return document;
	}
		
	public void encryptDocumentRandomPass(Document document, String pathD, String docKey, EncryptionResult res) throws Exception {
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
				encryptDocumentRandomPass(tempDoc, path, docKey, res);
			}
			else if (fieldValue instanceof String) {
				String stringValue = (String) fieldValue;
				encryptIfNeeded2(path, stringValue, field);
				if (!stringValue.equals((String) field.getValue()))
					docKey = (String) field.getValue();
			}
			else if (fieldValue instanceof Integer) {
				Integer integerValue = (Integer) fieldValue;
				encryptIfNeeded2(path, integerValue.toString(), field);
				if (!integerValue.equals(field.getValue()))
					docKey = field.getValue().toString();
			}
			else if (fieldValue instanceof Float) {
				Float floatValue = (Float) fieldValue;
				encryptIfNeeded2(path, floatValue.toString(), field);
				if (!floatValue.equals(field.getValue()))
					docKey = field.getValue().toString();
			}
		}
		res.setDocument(document);
		res.setKey(docKey);
	}	
	
	/*Place "SECRET VALUE" at all the encrypted fields of a Document*/
	public Document decryptDocument(Document document, String pathD) {
		String field_name;
		String path;
		for (Entry<String,Object> field: document.entrySet()) {
			field_name = field.getKey();
			path = "";
			if (!pathD.equals(""))
				path = pathD + "." + field_name;
			else
				path = path + field_name;
			Object field_value = field.getValue();
			if (field_value instanceof Document) {
				Document tempDoc = (Document) field_value;
				decryptDocument(tempDoc, path);
			}		
			else {
				if (encFields.containsKey(path)) {
					field.setValue(SECRET_VALUE);
				}
			}
		}
		return document;
	}
	
	/*Return True if the field is in the encrypted "list",otherwise false*/
	public boolean isEncryptedField(String field) {	
		if (fieldCollection.find(new Document(FIELD,field)).first() != null)
			return true;
		else
			return false;
	}
	
	/*Return the Type of encryption the field uses*/
	public EncryptionType usesEncryption(String field) {
		if (fieldCollection.find(new Document(FIELD, field)).first().get(ENCODING_TYPE).equals(HASH))
			return EncryptionType.HASH;
		else if (fieldCollection.find(new Document(FIELD, field)).first().get(ENCODING_TYPE).equals(RANDOM))
			return EncryptionType.RANDOM;
		return null;
	}
	
	/*Encrypt the field "path" of the document if needed connecting to FieldCollection*/
	public void encryptIfNeeded(String path,String value,Entry<String,Object> field) throws Exception {
		Document doc = fieldCollection.find(new Document(FIELD, path)).first();
		if ( doc != null) {
			if(doc.get(ENCODING_TYPE).equals(HASH)) {
				String encoded = encryption.sha256_encrypt(value);
				field.setValue(encoded);
			}
			else if (doc.get(ENCODING_TYPE).equals(RANDOM)) {
				String encoded = randomPassEncryption.randomPassEncryptBCRYPT(value, encoder);
				field.setValue(encoded);
				//Encoding encoding = randomPassEncryption.randomPassEncryptAES(value, cipher, key);
				//field.setValue(encoding.getEncoded());
			}
				
		}
	}
	
	/*Encrypt the field "path" of the document if needed using the static Map for enc_fields*/
	public void encryptIfNeeded2(String path,String value,Entry<String,Object> field) throws Exception {
		//Document doc =field_collection.find(new Document("field",path)).first();
		if (encFields.containsKey(path)) {
			if (encFields.get(path).equals(HASH)) {
				String encoded = encryption.sha256_encrypt(value);
				field.setValue(encoded);
			}
			else if (encFields.get(path).equals(RANDOM)) {
				String encoded = randomPassEncryption.randomPassEncryptBCRYPT(value, encoder);
				field.setValue(encoded);
				//Encoding encoding = randomPassEncryption.randomPassEncryptAES(value, cipher, key);
				//field.setValue(encoding.getEncoded());
			}
		}
	}
}
