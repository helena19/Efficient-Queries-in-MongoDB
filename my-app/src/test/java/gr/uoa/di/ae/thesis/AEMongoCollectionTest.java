package gr.uoa.di.ae.thesis;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class AEMongoCollectionTest {

	AEMongoCollection aeMongoCollection;
	
	Fongo fongo;

	MongoCollection<Document> collection;
	
	MongoCollection<Document> key_collection;
	
	@Before
	public void init() throws NoSuchAlgorithmException, NoSuchPaddingException {
		fongo = new Fongo("fongo db");
		collection = fongo.getDatabase("db").getCollection("collection");
		key_collection = fongo.getDatabase("db").getCollection("collection2");
		aeMongoCollection = new AEMongoCollection(collection,key_collection);
	}
	
	@Test
	public void shouldAllowForSpecifyingEncryptedFields() {
		aeMongoCollection.setEncryptedField("e-mail", EncryptionType.HASH);
		assertEquals("e-mail",key_collection.find(new Document("field","e-mail")).first().get("field"));
		assertEquals("hash", key_collection.find(new Document("field","e-mail")).first().get("enc"));
	}
	
	@Test
	public void shouldAllowForInsertingDocuments() throws Exception {
		
		Document document = new Document("name", "Michael").append("e-mail", "mike@bulls.com");
		
		aeMongoCollection.insertOne(document);
		List<Document> result = aeMongoCollection.find(document);
		assertEquals(document, result.get(0));
	}
	
	
	@Test
	public void shouldAllowForInsertingMultipleDocuments() throws Exception {
		
		Document mike = new Document("name", "Michael").append("e-mail", "mike@bulls.com");
		Document scottie = new Document("name", "Scottie").append("e-mail", "scottie@bulls.com");
		
		aeMongoCollection.insertMany((Arrays.asList(mike, scottie)));
		List<Document> result = aeMongoCollection.find(mike);
		assertEquals(mike, result.get(0));
		result = aeMongoCollection.find(scottie);
		assertEquals(scottie, result.get(0));
		
	}
	
	@Test
	public void shouldStoreHashEncryptedFieldsUsingSHA256Hash() throws Exception {
		aeMongoCollection.setEncryptedField("e-mail", EncryptionType.HASH);
		Document document = new Document("name", "Michael").append("e-mail", "mike@bulls.com");
		aeMongoCollection.insertOne(document);
		
		FindIterable<Document> result = collection.find(new Document("name", "Michael"));
		assertEquals("Michael", result.first().get("name"));
		
		List<Document> result2=aeMongoCollection.find(new Document("name", "Michael"));
		assertEquals("Michael", result2.get(0).get("name"));
		
	}
	
	@Test
	public void shouldStoreHashEncryptedEmbeddedFieldsUsingSHA256Hash() throws Exception {
		aeMongoCollection.setEncryptedField("name.last", EncryptionType.HASH);
		aeMongoCollection.setEncryptedField("salary", EncryptionType.HASH);
		aeMongoCollection.setEncryptedField("name.surname.middle", EncryptionType.HASH);
		Document document = new Document("name", new Document("first", "Michael").append("last", "Jordan")).append("e-mail", "mike@bulls.com").append("salary", 100);
		aeMongoCollection.insertOne(document);
		
	
		
		FindIterable<Document> result = collection.find(new Document("name.first", "Michael").append("e-mail", "mike@bulls.com"));
		assertEquals("mike@bulls.com", result.first().get("e-mail"));
		System.out.println(result.first());
		
		List<Document> result2=aeMongoCollection.find(new Document("name.first", "Michael").append("e-mail", "mike@bulls.com"));
		assertEquals("mike@bulls.com", result2.get(0).get("e-mail"));
		System.out.println(result2.get(0));
		
		List<Document> result3=aeMongoCollection.find(new Document("e-mail", "mike@bulls.com"));
		assertEquals("mike@bulls.com", result3.get(0).get("e-mail"));
		
		Document document2 = new Document("name", new Document("first", "Michael").append("surname", new Document("middle","Phelps").append("last","Jordan"))).append("e-mail", "mike@bulls.com");
		aeMongoCollection.insertOne(document2);
		
		List<Document> result4=aeMongoCollection.find(new Document("name.surname.middle", "Phelps"));
		assertEquals("mike@bulls.com", result4.get(0).get("e-mail"));
		
		List<Document> result5=aeMongoCollection.find(new Document("salary",100));
		assertEquals("SECRET VALUE", result5.get(0).get("salary"));
	}
	
	
	@Test
	public void storeEmbeddeedWithoutEncryption() {
		Document document = new Document("name", new Document("first", "Michael").append("last", "Jordan")).append("e-mail", "mike@bulls.com");
		collection.insertOne(document);
		FindIterable<Document> res = collection.find(new Document("name.first", "Michael").append("e-mail", "mike@bulls.com"));
		System.out.println("1o document "+res.first());
		assertEquals("mike@bulls.com", res.first().get("e-mail"));
	}
	
}
