package gr.uoa.di.ae.thesis;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.fluttercode.datafactory.impl.DataFactory;

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
        MongoCollection <Document> collection = db.getCollection("thesis");
        MongoCollection <Document> field_colletion=db.getCollection("keys");
        System.out.println("The collection has " + collection.count()+" items");
        AEMongoCollection myCollection = new AEMongoCollection(collection,field_colletion);
        
        /*Set the encrypted fields if they aren't already set*/
        myCollection.setEncryptedField("e-mail",EncryptionType.HASH);
        myCollection.setEncryptedField("name.last",EncryptionType.HASH);
               
        /*New 100 Entries*/
        long startTime2 = System.nanoTime();
		DataFactory df = new DataFactory();
        for (int i = 0; i < 100; i++) {          
            Document doc = new Document("name",df.getFirstName()).append("surname", df.getLastName()).append("e-mail",df.getEmailAddress()).append("date",df.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            myCollection.insertOne(doc);
        }
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2);
        System.out.println("Inserting all records with our way "+duration2/1000000000);
        System.out.println("Now,The collection has "+collection.count()+" items");
        
        
        /*Print All The Documents of the Collection (Using Mongo's way)*/
        System.out.println("All the inserts ");
        long startTime1 = System.nanoTime();
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
        long endTime1 = System.nanoTime();
        long duration1 = (endTime1 - startTime1);
        System.out.println("Finding all records mongo's way "+duration1/1000000000);
        
        
       MongoCursor<Document> cursor3 = field_colletion.find().iterator();
        try {
            while (cursor3.hasNext()) {
                System.out.println("Encrypted field"+cursor3.next().toJson());
            }
        } finally {
            cursor3.close();
        }
        
        
        /*Print All The Documents of the Collection (modified appropriately)*/
        long startTime = System.nanoTime();
       Iterator<Document> cursor2 = myCollection.find().iterator();
       while (cursor2.hasNext()) {
           System.out.println(cursor2.next().toJson());
       }
       long endTime = System.nanoTime();
       long duration = (endTime - startTime);
       System.out.println("Finding all records with our way ( \" decoding \") "+duration/1000000000);

        mongoClient.close();
    }
}
