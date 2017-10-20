package gr.uoa.di.ae.thesis;

import java.io.PrintWriter;
import java.util.ArrayList;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import javax.crypto.NoSuchPaddingException;

import org.bson.Document;
import org.fluttercode.datafactory.impl.DataFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class App
{	
    public static void main( String[] args ) throws Exception
    {
        
        MongoCredential credential = MongoCredential.createCredential("admin", "thesisdi", "asdf-1234!".toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress("ds143151.mlab.com:43151"), Arrays.asList(credential));

        MongoDatabase db = mongoClient.getDatabase("thesisdi");
        MongoCollection <Document> collection = db.getCollection("thesis");
        MongoCollection <Document> fieldCollection = db.getCollection("keys");
        
        System.out.println("The collection has " + collection.count()+" items");
        AEMongoCollection myCollection = new AEMongoCollection(collection,fieldCollection);
        collection.drop();//delete the collection        
        fieldCollection.drop();//delete the Keys Collection
        /*Set the encrypted fields if they aren't already set*/
        EncryptionType encryption = EncryptionType.HASH;
        myCollection.setEncryptedField("e-mail", encryption);
                       
        System.out.println("Now,The collection has " + collection.count() + " items");
     
        
        
        /*SHA-256 Insert Measures*/
        /*Bring the Encrypted fields statically/topically*/
        myCollection.importEncryptedFields();
        Map<Integer,Float> shaMeasure=new HashMap<Integer,Float>();
        Map<Integer,Float> shafindMeasure=new HashMap<Integer,Float>();
		int count=1000;
		while(count!=3200)
		{
	        collection.drop();//delete the collection
	        myCollection = new AEMongoCollection(collection,fieldCollection);
	        System.out.println("Afte Drop,The collection has " + collection.count() + " items");
			long startTime = System.nanoTime();
			DataFactory dff = new DataFactory();
	        for (int i = 0; i <count; i++) {          
	            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
	            myCollection.insertOne(doc, encryption);
	            //collection.insertOne(doc); //Mongodb's driver
	        }
	        long endTime = System.nanoTime();
	        long duration = (endTime - startTime);
	        System.out.println("Inserting all records with our way " + ((float)duration/1000000000)+" secs");
	        System.out.println("Now,The collection has " + collection.count() + " items");
	        shaMeasure.put(count, ((float)duration/1000000000));
	        long findTime = System.nanoTime();  
		    Iterator<Document> cursor2 = myCollection.find().iterator();
		    while (cursor2.hasNext()) {
		        System.err.println(cursor2.next().toJson());
		    }
		    long endfindTime = System.nanoTime();
		    long durationfind = (endfindTime - findTime);
		    System.out.println("Finding all records with our way ( \" decoding \") "+((float)durationfind/1000000000)+" secs");
		    shafindMeasure.put(count, ((float)durationfind/1000000000));
	        count=count*2;
		}
		PrintWriter writer = new PrintWriter("measures.txt", "UTF-8");
		writer.println("SHA-256");
		writer.println("Insert");
		writer.println(shaMeasure);
		writer.println("Find");
		writer.println(shafindMeasure);

		/*-----------------------------------------------------------------------------------*/
		
		
		/*Bcrypt Insert Measures*/
        collection.drop();//delete the collection        
        fieldCollection.drop();//delete the Keys Collection
        EncryptionType encryption2 = EncryptionType.RANDOM;
        myCollection.setEncryptedField("e-mail", encryption2);
        /*Bring the Encrypted fields statically/topically*/
        myCollection.importEncryptedFields();
        Map<Integer,Float> bcryptMeasure=new HashMap<Integer,Float>();
        Map<Integer,Float> bcryptfindMeasure=new HashMap<Integer,Float>();

		int count2=1000;
		while(count2!=32000)
		{
	        collection.drop();//delete the collection  
	        myCollection = new AEMongoCollection(collection,fieldCollection);
	        System.out.println("Afte Drop,The collection has " + collection.count() + " items");
			long startTime = System.nanoTime();
			DataFactory dff = new DataFactory();
	        for (int i = 0; i <count2; i++) {          
	            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
	            myCollection.insertOne(doc, encryption);
	            //collection.insertOne(doc); //Mongodb's driver
	        }
	        long endTime = System.nanoTime();
	        long duration = (endTime - startTime);
	        System.out.println("Inserting all records with our way " + ((float)duration/1000000000)+" secs");
	        System.out.println("Now,The collection has " + collection.count() + " items");
	        bcryptMeasure.put(count2, ((float)duration/1000000000));
	        long findTime = System.nanoTime();  
	       Iterator<Document> cursor2 = myCollection.find().iterator();
	       while (cursor2.hasNext()) {
	           System.err.println(cursor2.next().toJson());
	       }
	       long endfindTime = System.nanoTime();
	       long durationfind = (endfindTime - findTime);
	       System.out.println("Finding all records with our way ( \" decoding \") "+((float)durationfind/1000000000)+" secs");
	       bcryptfindMeasure.put(count2, ((float)durationfind/1000000000));
	       count2=count2*2;

		}
		
		writer.println("BCRYPT");
		writer.println("Insert");
		writer.println(bcryptMeasure);
		writer.println("Find");
		writer.println(bcryptfindMeasure);


		
		/*--------------------------------------------------------------------------------------*/
		
		/*Mongo Insert Measures*/
        collection.drop();//delete the collection        
        fieldCollection.drop();//delete the Keys Collection
        /*Bring the Encrypted fields statically/topically*/
        Map<Integer,Float> mongoMeasure=new HashMap<Integer,Float>();
        Map<Integer,Float> mongofindMeasure=new HashMap<Integer,Float>();
		int count3=1000;
		while(count3!=32000)
		{
	        collection.drop();//delete the collection   
	        myCollection = new AEMongoCollection(collection,fieldCollection);
			long startTime = System.nanoTime();
			DataFactory dff = new DataFactory();
	        for (int i = 0; i <count3; i++) {          
	            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
	            collection.insertOne(doc); //Mongodb's driver
	        }
	        long endTime = System.nanoTime();
	        long duration = (endTime - startTime);
	        System.out.println("Inserting all records with our way " + ((float)duration/1000000000)+" secs");
	        System.out.println("Now,The collection has " + collection.count() + " items");
	        mongoMeasure.put(count3, ((float)duration/1000000000));
		 	long findTime = System.nanoTime();  
	        Iterator<Document> cursor2 = collection.find().iterator();
	        while (cursor2.hasNext()) {
	            System.err.println(cursor2.next().toJson());
	        }
	        long endfindTime = System.nanoTime();
	        long durationfind = (endfindTime - findTime);
	        System.out.println("Finding all records with our way ( \" decoding \") "+((float)durationfind/1000000000)+" secs");
	        mongofindMeasure.put(count3, ((float)durationfind/1000000000));
	        count3=count3*2;
		}
		
		writer.println("Mongo");
		writer.println("Insert");
		writer.println(mongoMeasure);
		writer.println("Find");
		writer.println(mongofindMeasure);


		/*----------------------------------------------------------------------------------------*/
		
		writer.close();
        
        
//        /*Print All The Documents of the Collection (Using Mongo's way)*/
//        System.out.println("All the inserts");
//        long startTime1 = System.nanoTime();
//        MongoCursor<Document> cursor = collection.find().iterator();
//        try {
//            while (cursor.hasNext()) {
//                System.out.println(cursor.next().toJson());
//            }
//        } finally {
//            cursor.close();
//        }
//        long endTime1 = System.nanoTime();
//        long duration1 = (endTime1 - startTime1);
//        System.out.println("Finding all records mongo's way "+((float)duration1/1000000000)+" secs");
//        
//        
//       MongoCursor<Document> cursor3 = fieldCollection.find().iterator();
//        try {
//            while (cursor3.hasNext()) {
//                System.out.println("Encrypted field"+cursor3.next().toJson());
//            }
//        } finally {
//            cursor3.close();
//        }
//        
//        /*Print All The Documents of the Collection (modified appropriately)*/
//       long startTime = System.nanoTime();
//       /*Option 1
//       collection.find().forEach((Block <Document>) document2 -> 
//		{ 
//			function(document2,myCollection);
//		}
//		);*/
//       
//       
//       /* Option 2*/
//       Iterator<Document> cursor2 = myCollection.find().iterator();
//       while (cursor2.hasNext()) {
//           System.out.println(cursor2.next().toJson());
//       }
//       long endTime = System.nanoTime();
//       long duration = (endTime - startTime);
//       System.out.println("Finding all records with our way ( \" decoding \") "+((float)duration/1000000000)+" secs");
        
        Runtime runtime = Runtime.getRuntime();
        // Run the garbage collector
        runtime.gc();
        // Calculate the used memory
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        mongoClient.close();
    }
    
    static public void function(Document document,AEMongoCollection myCollection) {
    	myCollection.decryptDocument(document,"");
    	System.out.println(document);
    }

}
