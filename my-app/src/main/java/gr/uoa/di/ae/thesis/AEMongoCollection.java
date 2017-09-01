package gr.uoa.di.ae.thesis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.bson.Document;
//import org.bson.codecs.configuration.CodecRegistry;
//import org.bson.conversions.Bson;


import com.mongodb.BasicDBObject;
/*import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.client.ListCollectionsIterable;*/
import com.mongodb.client.MongoCollection;
/*import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.CreateViewOptions;*/

public class AEMongoCollection {
	
	private Map<String,EncryptionType> encrypted_fields;
	
	public AEMongoCollection(MongoCollection<Document> collection) {
		// TODO Auto-generated constructor stub
		encrypted_fields=new HashMap<String,EncryptionType>();
	}

	public void AEMongoCollectionCreateDoc(MongoCollection<Document> collection) {	
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
	
	public void setEncryptedField(String field,EncryptionType enc)
	{
		if(encrypted_fields.containsKey(field))
			throw new IllegalStateException("You have already defined an encryption type for the field "+field);
		else
			encrypted_fields.put(field, enc);
	}
	
	public List<String> getEncryptedFields()
	{
		List<String> enc_fields;
		enc_fields= new ArrayList<String>() ;
		for(String field: encrypted_fields.keySet())
		{
			enc_fields.add(field);
		}
		return enc_fields;
	}
	
	public void insertOne(Document document,MongoCollection<Document> collection) 
	{
		for(Entry<String, Object> field:document.entrySet())
		{
			String field_name=field.getKey();
			Object field_value=field.getValue();
			if(encrypted_fields.containsKey(field_name))
			{
				EncryptionType enc=encrypted_fields.get(field_name);
				System.out.println("I have to encrypt field "+field_name+" the value "+field_value);
				field.setValue("encrypted-email:)");
			}
					
		}
		collection.insertOne(document);
	}
	
	public Document find(Document document)
	{
		//TO-DO
		return null;
	}
	
	
}
