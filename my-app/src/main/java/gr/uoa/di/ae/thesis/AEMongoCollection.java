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
	private static final String EMPTY_STRING="";
	
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
	/*Function who generates and returns the Key used fore Encryption*/
	public static Key generateKey() throws Exception {
		String keyValue = RandomStringUtils.random(32);
		byte[] aesKey = keyValue.getBytes();
//		System.out.println("The key length is " + keyValue.length());
		return new SecretKeySpec(aesKey, ALGORITHM);
	}

	/*Insert the encrypted field in the dedicated collection,
	 * if it doesn't exist already*/	
	public void setEncryptedField(String field, EncryptionType enc) {
		String encryptionType = EMPTY_STRING;
		if (enc == EncryptionType.HASH)
			encryptionType += HASH;
		else if (enc == EncryptionType.RANDOM)
			encryptionType += RANDOM;
		Document doc = new Document(FIELD, field).append(ENCODING_TYPE, encryptionType);
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

	/*Insert Function for One Document*/
	public void insertOne(Document document, EncryptionType enc) throws Exception {
		if (enc == EncryptionType.HASH) {
			insertOneHash(document);
		}
		else if (enc == EncryptionType.RANDOM) {
			insertOneRandomPass(document);
		}
	}
	
	/*Insert a Document with SHA-256 Approach*/
	public void insertOneHash(Document document) throws Exception {
		Document doc = encryptDocument(document,EMPTY_STRING);
		collection.insertOne(doc);
	}
	
	/*Encrypt the document with random pass and keep doc id with its random pass*/
	public void insertOneRandomPass(Document document) throws Exception {
		String key = EMPTY_STRING;
		//EncryptionResult result = new EncryptionResult();
//		encryptDocumentRandomPass(document,EMPTY_STRING, key/*, result*/);
		collection.insertOne(encryptDocumentRandomPass(document,EMPTY_STRING, key/*, result*/));
	}
	
	/*Find the Documents that match the Document given in SHA-256's Approach*/
	public List<Document> find(Document document) throws Exception {
		FindIterable<Document> doc = collection.find(encryptDocument(document,EMPTY_STRING));
		if (doc == null)
			return null;
		else {
			List<Document> set=new ArrayList<Document>();
			doc.forEach((Block <Document>) document2 -> 
			{ 
				set.add(decryptDocument(document2,EMPTY_STRING));
			}
			);
			return set;
		}	
	}
	
	/*Find the Documents that match the Document given in BCrypt's Approach*/
	public List<Document> find2(Document document){
		HashMap<String,Object> fields=new HashMap<String,Object>();
		Document temp = new Document();
		parseDocument(document,temp,fields,EMPTY_STRING); /*Find the encrypted values,construct the temp Document with non encrypted fields*/
		FindIterable<Document> doc = collection.find(temp);/* This way we minimize the loops*/
		if (doc == null)
			return null;
		else {
			List<Document> set=new ArrayList<Document>();
			if(fields.isEmpty()) /*The document given in the query has no encrypted fields*/
			{
				doc.forEach((Block <Document>) document2 -> 
				{ 
					set.add(decryptDocument(document2,EMPTY_STRING));
				}
				);
				return set;
			}
			else { /*The document given in the query has encrypted fields*/
				doc.forEach((Block <Document>) document2 ->{ 
					match(document2,fields,set);
				}
				);
				return set;
			}
		}	
	}
	
	/*Parse the Document and Return in the HashMap fields all the encrypted values <FieldName,FieldValue>
	 * and in the ret parameter return The document constructed of the Fields which are not encrypted*/
	public void parseDocument(Document document,Document ret,HashMap<String,Object> fields,String pathD) {
		String fieldName;
		String path;
		Object fieldValue;
		for (Entry<String, Object> field: document.entrySet()) {
			fieldName = field.getKey();
			path = EMPTY_STRING;
			if (!pathD.equals(EMPTY_STRING))
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
	
	/*This Function checks if the Document given contains the fields in HashMap fields
	 If they do not match it is deleted from the list returnSet*/
	public void match(Document document,HashMap<String,Object> fields,List<Document> returnSet) {
		 Set<Entry<String, Object>> set = fields.entrySet();
	     Iterator<Entry<String, Object>> iterator = set.iterator();
	     HashMap<String,Object> flattenDocument=new HashMap<String,Object>();
	     flattenDocument(document,flattenDocument,EMPTY_STRING);
	     while(iterator.hasNext()) 
	     {
	         HashMap.Entry mentry = (HashMap.Entry)iterator.next();
	         Object value=flattenDocument.get(mentry.getKey());
	         if(value!=null)
	         {
		         Object value2=mentry.getValue();
		         if(value2 instanceof String)
		         {
		        	 String toCompare= objectToString(value);
		        	 String toCompare2=objectToString(value2);
		        	 if(!encoder.matches(toCompare2, toCompare))
		        		 return;
		         }
		         else if(value2 instanceof Float)
		         { 
		        	 String toCompare= objectToString(value);
		        	 String toCompare2=objectToString(value2);
		        	 if(!encoder.matches(toCompare2, toCompare))
		        		 return;
		         }
		         else if(value2 instanceof Integer)
		         { 
		        	 String toCompare= objectToString(value);
		        	 String toCompare2=objectToString(value2);
		        	 if(!encoder.matches(toCompare2,toCompare))
		        		 return;
		         }
	         }
	         else
	        	 return;
	      }
	     returnSet.add(decryptDocument(document,EMPTY_STRING));
	}
	
	/*Convert Object to String and return It*/
	public String objectToString(Object object) {
		if(object instanceof String)
			return (String) object;
		else if(object instanceof Integer)
		{
			Integer obj=(Integer) object;
			return Integer.toString(obj);
		}
		else
		{
			Float obj=(Float) object;
			return Float.toString(obj);
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
				set.add(decryptDocument(document2,EMPTY_STRING));
			}
			);
			return set;
		}
	}
	
	/*Insert Many Records*/
	public void insertMany(List<Document> records, EncryptionType enc) throws Exception {
		List<Document> recs=new ArrayList<Document>();
		if (enc == EncryptionType.HASH) 
		{
			for(Document doc:records) {
				recs.add(encryptDocument(doc,""));
			}
		}
		else {
			for(Document doc:records) {
				String key = EMPTY_STRING;
			//	EncryptionResult result = new EncryptionResult();
			//	encryptDocumentRandomPass(doc,EMPTY_STRING, key, result);	
				recs.add(encryptDocumentRandomPass(doc,EMPTY_STRING, key/*, result*/));
			}
		}
		collection.insertMany(recs);
	}
	
	/*Encrypt a Document using the right type of encryption*/
	public Document encryptDocument(Document document, String pathD) throws Exception {
		String fieldName;
		String path;
		Object fieldValue;
		for (Entry<String, Object> field: document.entrySet()) 
		{
			fieldName = field.getKey();
			path = EMPTY_STRING;
			if (!pathD.equals(EMPTY_STRING))
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
		
	public Document encryptDocumentRandomPass(Document document, String pathD, String docKey/*, EncryptionResult res*/) throws Exception {
		String fieldName;
		String path;
		Object fieldValue;
		for (Entry<String, Object> field: document.entrySet()) {
			fieldName = field.getKey();
			path = EMPTY_STRING;
			if (!pathD.equals(EMPTY_STRING))
				path = pathD + "." + fieldName;
			else
				path += fieldName;
			fieldValue = field.getValue();
			if (fieldValue instanceof Document) {
				Document tempDoc = (Document) fieldValue;
				encryptDocumentRandomPass(tempDoc, path, docKey/*, res*/);
			}
			else if (fieldValue instanceof String) {
				String stringValue = (String) fieldValue;
				encryptIfNeeded2(path, stringValue, field);
				//if (!stringValue.equals((String) field.getValue()))
					//docKey = (String) field.getValue();
			}
			else if (fieldValue instanceof Integer) {
				Integer integerValue = (Integer) fieldValue;
				encryptIfNeeded2(path, integerValue.toString(), field);
			//	if (!integerValue.equals(field.getValue()))
				//	docKey = field.getValue().toString();
			}
			else if (fieldValue instanceof Float) {
				Float floatValue = (Float) fieldValue;
				encryptIfNeeded2(path, floatValue.toString(), field);
				//if (!floatValue.equals(field.getValue()))
					//docKey = field.getValue().toString();
			}
		}
	//	res.setDocument(document);
	//	res.setKey(docKey);
		return document;
	}	
	
	/*Place "SECRET VALUE" at all the encrypted fields of a Document*/
	public Document decryptDocument(Document document, String pathD) {
		String fieldName;
		String path;
		for (Entry<String,Object> field: document.entrySet()) {
			fieldName = field.getKey();
			path = EMPTY_STRING;
			if (!pathD.equals(EMPTY_STRING))
				path = pathD + "." + fieldName;
			else
				path = path + fieldName;
			Object fieldValue = field.getValue();
			if (fieldValue instanceof Document) {
				Document tempDoc = (Document) fieldValue;
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
				String encoded = encryption.sha256Encrypt(value);
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
		if (encFields.containsKey(path)) {
			if (encFields.get(path).equals(HASH)) {
				String encoded = encryption.sha256Encrypt(value);
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
	
	
	/*Flatten the Document ,Return all the fields(embedded or not) in the HashMap fields*/
	public void flattenDocument(Document document,HashMap<String,Object> fields,String pathD) 
	{
		String fieldName;
		String path;
		for (Entry<String,Object> field: document.entrySet()) {
			fieldName = field.getKey();
			path = EMPTY_STRING;
			if (!pathD.equals(EMPTY_STRING))
				path = pathD + "." + fieldName;
			else
				path = path + fieldName;
			Object fieldValue = field.getValue();
			if (fieldValue instanceof Document) {
				Document tempDoc = (Document) fieldValue;
				flattenDocument(tempDoc,fields,path);
			}		
			else{
				fields.put(path,fieldValue);
			}
		}
	}
}
