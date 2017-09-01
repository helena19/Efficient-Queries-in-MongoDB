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
	
	@Test void shouldAllowForSpecifyingEncryptedFields() {
		aeMongoCollection.setEncryptedField("e-mail", EncryptionType.HASH);
		assertEquals("e-mail", aeMongoCollection.getEncryptedFields().get(0));
	}
	
	@Test void shouldAllowForInsertingDocuments() {
		
		BasicDBObject document = new BasicDBObject("name", "Michael").append("e-mail", "mike@bulls.com")
		
		aeMongoCollection.insertOne(document);
		Document result = aeMongoCollection.find(document).first();
		assertEquals(document, result);
	}
	
	
}
