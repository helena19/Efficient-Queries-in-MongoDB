package gr.uoa.di.ae.thesis;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class App
{	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
//        MongoClient mongo = new MongoClient( "ds143151.mlab.com" , 43151 );
        
        MongoCredential credential = MongoCredential.createCredential("admin", "thesisdi", "asdf-1234!".toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress("ds143151.mlab.com:43151"), Arrays.asList(credential));
        MongoDatabase db = mongoClient.getDatabase("thesisdi");
        MongoCollection<Document> collection = db.getCollection("thesis");
        System.out.println(collection.count());
        AEMongoCollection myCollection = new AEMongoCollection(collection);
        myCollection.AEMongoCollectionCreateDoc(collection);
        System.out.println(collection.count());
        
//        BasicDBObject doc = new BasicDBObject("name", "Mary").append("surname", "Jane").append("age", 21).append("class", 2014);
//        BasicDBObject doc = new BasicDBObject("name", "MongoDB").append("type", "database").append("count", 1).append("info", new BasicDBObject("x", 203).append("y", 102));
//        DB db = mongo.getDB("thesisdi");
        mongoClient.close();
    }
}
