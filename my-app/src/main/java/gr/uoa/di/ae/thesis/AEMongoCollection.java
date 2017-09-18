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
	
	private MongoCollection<Document> collection;
	
	private MongoCollection<Document> field_collection;
	
	private Encryption encryption;
	
	public AEMongoCollection(MongoCollection<Document> collection,MongoCollection<Document> field_collection) {
		// TODO Auto-generated constructor stub
		this.collection = collection;
		this.field_collection=field_collection;
		encryption = new Encryption();
	}

	/*Insert the encrypted field in the dedicated collection,
	 * if it doesn't exist already*/	
	public void setEncryptedField(String field, EncryptionType enc) 
	{
		String enc2="";
		if(enc==EncryptionType.HASH)
			enc2+="hash";
		Document doc=new Document("field",field).append("enc",enc2);
		if(field_collection.find(doc).first()!=null)
			System.out.println("You have already defined an encryption type for the field " + field);
		else
		{
			field_collection.insertOne(doc);
		}
			
	}

	/*Insert a Document*/
	public void insertOne(Document document) 
	{
		Document doc=encryptDocument(document,"");
		collection.insertOne(doc);
	}
	
	/*Find the Documents that match the Document given*/
	public List<Document> find(Document document) 
	{
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
	
	/*Find and return all the Documents */
	public List<Document> find() 
	{
		FindIterable<Document> doc = collection.find();
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
	
	/*Insert Many Records*/
	public void insertMany(List<Document> records) {
		for (Document doc:records) {
			collection.insertOne(doc);
		}
	}
	
	/*Encrypt a Document using the right type of encryption*/
	public Document encryptDocument(Document document,String pathD) 
	{
		for (Entry<String, Object> field: document.entrySet()) 
		{
			String field_name = field.getKey();
			String path="";
			if(!pathD.equals(""))
				path=pathD+"."+field_name;
			else
				path=path+field_name;

			if (field.getValue() instanceof String) 
			{
				String string_value = (String) field.getValue();
				if ( isEncryptedField(path))
				{
					if(usesEncryption(path).equals("hash"))
					{
						String encoded=encryption.sha256_encrypt( string_value);
						field.setValue(encoded);
					}
				}
			}
			else if (field.getValue() instanceof Integer) 
			{
				Integer integer_value = (Integer) field.getValue();
				if ( isEncryptedField(path))
				{
					if(usesEncryption(path).equals("hash"))
					{
						String encoded=encryption.sha256_encrypt(Integer.toString(integer_value));
						field.setValue(encoded);
					}
				}
			}
			else if (field.getValue() instanceof Float)
			{
				Float float_value = (Float) field.getValue();
				if ( isEncryptedField(path))
				{
					if(usesEncryption(path).equals("hash"))
					{
						String encoded=encryption.sha256_encrypt(Float.toString(float_value));
						field.setValue(encoded);
					}
				}
			}
			else if (field.getValue() instanceof Document) 
			{
				Document tempDoc = (Document) field.getValue();
				encryptDocument(tempDoc,path);
			}				
		}    
		return document;
	}
	
	
	/*Place "SECRET VALUE" at all the encrypted fields of a Document*/
	public Document decryptDocument(Document document,String pathD)
	{
		for (Entry<String,Object> field:document.entrySet()) 
		{
			String field_name = field.getKey();
			String path="";
			if(!pathD.equals(""))
				path=pathD+"."+field_name;
			else
				path=path+field_name;
			if (field.getValue() instanceof String) {
				if ( isEncryptedField(path)) {
					field.setValue("SECRET VALUE");
				}
			}
			else if (field.getValue() instanceof Integer) {
				if ( isEncryptedField(path)) {
					field.setValue("SECRET VALUE");
				}
			}
			else if (field.getValue() instanceof Float) {
				if ( isEncryptedField(path)) {
					field.setValue("SECRET VALUE");
				}
			}
			else if (field.getValue() instanceof Document) {
				Document tempDoc = (Document) field.getValue();
				decryptDocument(tempDoc,path);
			}				
		}
		return document;
	}
	
	/*Return True if the field is in the encrypted "list",otherwise false*/
	public boolean isEncryptedField(String field){	
		if(field_collection.find(new Document("field",field)).first()!=null)
			return true;
		else
			return false;
	}
	
	/*Return the Type of encryption the field uses*/
	public EncryptionType  usesEncryption(String field)
	{
		if(field_collection.find(new Document("field",field)).first().get("enc").equals("hash"))
			return EncryptionType.HASH;
		return null;
	}
	
}
