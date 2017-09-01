package gr.uoa.di.ae.thesis;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class App
{	
    public static void main( String[] args )
    {
        
        MongoCredential credential = MongoCredential.createCredential("admin", "thesisdi", "asdf-1234!".toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress("ds143151.mlab.com:43151"), Arrays.asList(credential));

        MongoDatabase db = mongoClient.getDatabase("thesisdi");
        MongoCollection<Document> collection = db.getCollection("thesis");
        System.out.println("The collection has "+collection.count()+" items");
        AEMongoCollection myCollection = new AEMongoCollection(collection);
        
        /*Set the encrypted field*/
        myCollection.setEncryptedField("e-mail",EncryptionType.HASH);
        
        /*New Entry*/
		Document document = new Document("name", "Michael").append("e-mail", "mike@bulls.com");
        myCollection.insertOne(document,collection);
        
        //myCollection.AEMongoCollectionCreateDoc(collection);
        System.out.println("Now,The collection has "+collection.count()+" items");
        
        System.out.println("The encrypted fields so far "+myCollection.getEncryptedFields());
        
        /*Print All Inserts At the Collection*/
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
        
        
//        BasicDBObject doc = new BasicDBObject("name", "Mary").append("surname", "Jane").append("age", 21).append("class", 2014);
//        BasicDBObject doc = new BasicDBObject("name", "MongoDB").append("type", "database").append("count", 1).append("info", new BasicDBObject("x", 203).append("y", 102));
//        DB db = mongo.getDB("thesisdi");
        mongoClient.close();
    }
}
