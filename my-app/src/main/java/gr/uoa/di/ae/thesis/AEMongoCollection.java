package gr.uoa.di.ae.thesis;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import org.bson.Document;
//import org.bson.codecs.configuration.CodecRegistry;
//import org.bson.conversions.Bson;


import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
/*import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.client.ListCollectionsIterable;*/
import com.mongodb.client.MongoCollection;
/*import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.CreateViewOptions;*/
import com.mongodb.client.MongoCursor;

public class AEMongoCollection {
	
	private Map<String,EncryptionType> encrypted_fields;
	
	private MongoCollection<Document> collection;
	
	private Encryption encryption;
	
	public AEMongoCollection(MongoCollection<Document> collection) {
		// TODO Auto-generated constructor stub
		encrypted_fields = new HashMap<String,EncryptionType>();
		this.collection = collection;
		encryption = new Encryption();
	}

	public void AEMongoCollectionCreateDoc() {	
		Scanner keyboard = new Scanner(System.in);
		while (true) {
			Document doc = new Document();
			System.out.println("Enter your name");
			String name = keyboard.next();
			doc.append("name", name);
			System.out.println("Enter your surname");
			String surname = keyboard.next();
			doc.append("surname", surname);
			System.out.println("Enter your age");
			int age = keyboard.nextInt();
			doc.append("age", age);
			System.out.println("Enter your class");
			int classof = keyboard.nextInt();
			doc.append("class", classof);
			collection.insertOne(doc);
			System.out.println("Do you want to create another document? (yes/no)");
			String answer = keyboard.next();
			if (answer.equals("no")) 
				break;
		} 
		keyboard.close();
	}
	
	public void setEncryptedField(String field, EncryptionType enc) {
		if (encrypted_fields.containsKey(field))
			throw new IllegalStateException("You have already defined an encryption type for the field " + field);
		else
			encrypted_fields.put(field, enc);
	}
	
	public Set<String> getEncryptedFields() {
		return encrypted_fields.keySet();
	}
	
	
	public void insertOne(Document document) {
		for (Entry<String, Object> field: document.entrySet()) {
			String field_name = field.getKey();
			Object field_value = "";
			//need here loop too to check how deep the embedded document is
			if (field.getValue() instanceof String) {
				field_value = (String) field.getValue();
			}
			else if (field.getValue() instanceof Integer) {
				field_value = (Integer) field.getValue();
			}
			else if (field.getValue() instanceof Float) {
				field_value = (Float) field.getValue();
			}
			else if (field.getValue() instanceof Document) {
				System.out.println("I am a document");
				Document tempDoc = (Document) field.getValue();
				int i = 0;
				for (Entry<String, Object> field1: tempDoc.entrySet()) {
					System.out.println("i is " + i);
					i += 1;
					String field_name1 = field1.getKey();
					field_name = field_name + "." + field_name1;
					field_value = (String) field1.getValue();
					System.out.println("Key: " + field_name + " Value: " + field_value);
				}
			}
			
			if (encrypted_fields.containsKey(field_name)) {
				EncryptionType enc = encrypted_fields.get(field_name);
				System.out.println("I have to encrypt field " + field_name + " the value " + field_value);
//				String encoded=encryption.sha256_encrypt(field_value);
				String encoded="sha256 of " + field_value;
				field.setValue(encoded);
			}
					
		}    
		collection.insertOne(document);
	}
	
	public List<Document> find(Document document) {
		FindIterable<Document> doc = collection.find(document);
		if (doc == null)
			return null;
		else {
//			doc.first().replace("e-mail", "mike@bulls.com");
			/*for(Document current:doc)
			{
				for(Entry<String,Object> field:current.entrySet())
				{
					String field_name=field.getKey();
					Object field_value=field.getValue();
					if(encrypted_fields.containsKey(field_name))
					{
						EncryptionType enc=encrypted_fields.get(field_name);
						System.out.println("I have to decrypt field "+field_name+" the value "+field_value);
						String decrypted=((String) field_value).replaceAll("sha256 of ","");
						current.replace(field_name,decrypted);
						System.out.println("The decrypted field should be"+decrypted);
						System.out.println("the decrypted field is "+field.getValue());
					}
				}
			}*/
			List<Document> set=new ArrayList<Document>();
			doc.forEach((Block <Document>) document2 -> 
			{ 
				for (Entry<String,Object> field:document2.entrySet()) {
					String field_name = field.getKey();
					Object field_value = field.getValue();
					if (encrypted_fields.containsKey(field_name)) {
						EncryptionType enc = encrypted_fields.get(field_name);
						System.out.println("I have to decrypt field " + field_name + " the value "+field_value);
						String decrypted = ((String) field_value).replaceAll("sha256 of ","");
//						String decrypted=encryption.sha256_decrypt(((String) field_value));
						document2.replace(field_name,decrypted);
						System.out.println("The decrypted field should be " + decrypted);
						System.out.println("the decrypted field is " + field.getValue());
					}
				}
				set.add(document2);
				System.out.println("New doc " + document2);
			}
			);
			return set;
		}
		
	}
	
	
	public void insertMany(List<Document> records) {
		for (Document doc:records) {
			collection.insertOne(doc);
		}
	}
	
}
