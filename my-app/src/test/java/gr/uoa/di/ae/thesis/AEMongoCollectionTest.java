package gr.uoa.di.ae.thesis;

import static org.junit.Assert.assertEquals;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

public class AEMongoCollectionTest {

	AEMongoCollection aeMongoCollection;
	
	Fongo fongo;

	MongoCollection<Document> collection;
	
	@Before
	public void init() {
		fongo = new Fongo("fongo db");
		collection = fongo.getDatabase("db").getCollection("collection");
		aeMongoCollection = new AEMongoCollection(collection);
	}
	
	@Test
	public void shouldAllowForSpecifyingEncryptedFields() {
		aeMongoCollection.setEncryptedField("e-mail", EncryptionType.HASH);
		assertEquals(aeMongoCollection.getEncryptedFields().contains("e-mail"),true);
		//assertEquals("e-mail", aeMongoCollection.getEncryptedFields().get(0));
	}
	
	@Test
	public void shouldAllowForInsertingDocuments() {
		
		Document document = new Document("name", "Michael").append("e-mail", "mike@bulls.com");
		
		aeMongoCollection.insertOne(document);
		Document result = aeMongoCollection.find(document);
		assertEquals(document, result);
	}
	
	
}
