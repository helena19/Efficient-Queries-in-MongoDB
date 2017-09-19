package gr.uoa.di.ae.thesis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import org.bson.Document;



import com.mongodb.Block;
import com.mongodb.client.FindIterable;

import com.mongodb.client.MongoCollection;

public class AEMongoCollection {
	
	private MongoCollection<Document> collection;
	
	private MongoCollection<Document> field_collection;
	
	private Encryption encryption;
	
	private Map<String,String> enc_fields;
	
	public AEMongoCollection(MongoCollection<Document> collection,MongoCollection<Document> field_collection) {
		// TODO Auto-generated constructor stub
		this.collection = collection;
		this.field_collection=field_collection;
		encryption = new Encryption();
		enc_fields=new HashMap<String,String>();
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
	
	/*Bring all the EncryptedFields at the Online Collection statically (topically)*/
	public void importEncryptedFields() 
	{
		field_collection.find().forEach( (Block <Document>) document ->
		{
			enc_fields.put((String )document.get("field"),(String) document.get("enc"));
		});
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
		String field_name;
		String path;
		Object field_value;
		for (Entry<String, Object> field: document.entrySet()) 
		{
			field_name = field.getKey();
			path="";
			if(!pathD.equals(""))
				path=pathD+"."+field_name;
			else
				path=path+field_name;
			
			field_value =field.getValue();
			if (field_value instanceof Document) 
			{
				Document tempDoc = (Document) field_value;
				encryptDocument(tempDoc,path);
			}		
			else if (field_value instanceof String) 
			{
				String string_value = (String) field_value;
				encryptIfNeeded2(path,string_value,field);
			}
			else if (field_value instanceof Integer) 
			{
				Integer integer_value = (Integer) field_value;
				encryptIfNeeded2(path,Integer.toString(integer_value),field);
			}
			else if (field_value instanceof Float)
			{
				Float float_value = (Float) field_value;
				encryptIfNeeded2(path,Float.toString(float_value),field);
			}			
		}    
		return document;
	}
	
	
	/*Place "SECRET VALUE" at all the encrypted fields of a Document*/
	public Document decryptDocument(Document document,String pathD)
	{
		String field_name;
		String path;
		for (Entry<String,Object> field:document.entrySet()) 
		{
			field_name = field.getKey();
			path="";
			if(!pathD.equals(""))
				path=pathD+"."+field_name;
			else
				path=path+field_name;
			Object field_value=field.getValue();
			if (field_value instanceof Document) {
				Document tempDoc = (Document) field_value;
				decryptDocument(tempDoc,path);
			}		
			else
			{
				if ( enc_fields.containsKey(path)) {
					field.setValue("SECRET VALUE");
				}
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
	
	/*Encrypt the field "path" of the document if needed connecting to FieldCollection*/
	public void encryptIfNeeded(String path,String value,Entry<String,Object> field)
	{
		Document doc =field_collection.find(new Document("field",path)).first();
		if ( doc!=null)
		{
			if(doc.get("enc").equals("hash"))
			{
				String encoded=encryption.sha256_encrypt(value);
				field.setValue(encoded);
			}
				
		}
	}
	
	/*Encrypt the field "path" of the document if needed using the static Map for enc_fields*/
	public void encryptIfNeeded2(String path,String value,Entry<String,Object> field)
	{
		//Document doc =field_collection.find(new Document("field",path)).first();
		if ( enc_fields.containsKey(path))
		{
			if(enc_fields.get(path).equals("hash"))
			{
				String encoded=encryption.sha256_encrypt(value);
				field.setValue(encoded);
			}
				
		}
	}
}
