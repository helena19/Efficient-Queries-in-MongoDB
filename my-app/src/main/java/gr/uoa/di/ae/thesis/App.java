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
        //collection.drop();//delete the collection        
        //fieldCollection.drop();//delete the Keys Collection
       
                       
        System.out.println("Now,The collection has " + collection.count() + " items");
        List<Document> doc32000=new ArrayList<Document>();
        List<Document> doc40000=new ArrayList<Document>();
        List<Document> doc80000=new ArrayList<Document>();
        List<Document> doc160000=new ArrayList<Document>();
        DataFactory dff = new DataFactory();
        for (int i = 0; i <32000; i++) {          
            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            doc32000.add(doc);
            doc40000.add(doc);
            doc80000.add(doc);
            doc160000.add(doc);
        }
        System.out.println("Created 32000 docs "+doc32000.size());
        for (int i = 0; i <8000; i++) {          
            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            doc40000.add(doc);
            doc80000.add(doc);
            doc160000.add(doc);
        }
        System.out.println("Created 40000 docs "+doc40000.size());
        for (int i = 0; i <40000; i++) {          
            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            doc80000.add(doc);
            doc160000.add(doc);
        }
        System.out.println("Created 80000 docs "+doc80000.size());
        for (int i = 0; i <80000; i++) {  
            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
           // Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); plain document
            doc160000.add(doc);
        }
        System.out.println("Created 160000 docs "+doc160000.size());
        PrintWriter writer1 = new PrintWriter("32000Recs.txt", "UTF-8");
        writer1.println(doc32000);
        PrintWriter writer2 = new PrintWriter("40000Recs.txt", "UTF-8");
        writer2.println(doc40000);
        PrintWriter writer3 = new PrintWriter("80000Recs.txt", "UTF-8");
        writer3.println(doc80000);
        PrintWriter writer4 = new PrintWriter("160000Recs.txt", "UTF-8");
        writer4.println(doc160000);
        writer1.close();
        writer2.close();
        writer3.close();
        writer4.close();

		PrintWriter writer = new PrintWriter("measures.txt", "UTF-8");

        
        
        
        /*SHA-256 Insert Measures*/
        /*Bring the Encrypted fields statically/topically*/
		 /*Set the encrypted fields if they aren't already set*/
        EncryptionType encryption = EncryptionType.HASH;
        myCollection.setEncryptedField("name.last", encryption);
        myCollection.importEncryptedFields();
        Map<Integer,Float> shaMeasure=new HashMap<Integer,Float>();
        Map<Integer,Float> shafindMeasure=new HashMap<Integer,Float>();
		int count=0;
		while(count!=4)
		{
	        collection.drop();//delete the collection
	        myCollection = new AEMongoCollection(collection,fieldCollection);
	        System.out.println("After Drop,The collection has " + collection.count() + " items");
	        myCollection.importEncryptedFields();
			long startTime = System.nanoTime();
			if(count==0)
				myCollection.insertMany(doc32000, encryption);
			if(count==1)
				myCollection.insertMany(doc40000, encryption);
			if(count==2)
				myCollection.insertMany(doc80000, encryption);
			if(count==3)
				myCollection.insertMany(doc160000, encryption);
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
	        count++;
		}
		writer.println("SHA-256");
		writer.println("Insert");
		writer.println(shaMeasure);
		writer.println("Find");
		writer.println(shafindMeasure);

		/*-----------------------------------------------------------------------------------*/
		
		
		/*Bcrypt Insert Measures*/
//        collection.drop();//delete the collection        
//        fieldCollection.drop();//delete the Keys Collection
//        EncryptionType encryption2 = EncryptionType.RANDOM;
//        myCollection.setEncryptedField("e-mail", encryption2);
//        /*Bring the Encrypted fields statically/topically*/
//        myCollection.importEncryptedFields();
//        Map<Integer,Float> bcryptMeasure=new HashMap<Integer,Float>();
//        Map<Integer,Float> bcryptfindMeasure=new HashMap<Integer,Float>();
//
//		int count2=0;
//		while(count2!=4)
//		{
//	        collection.drop();//delete the collection  
//	        myCollection = new AEMongoCollection(collection,fieldCollection);
//	        System.out.println("After Drop,The collection has " + collection.count() + " items");
//        	myCollection.importEncryptedFields();
//			long startTime = System.nanoTime();
//			if(count2==0)
//				myCollection.insertMany(doc2000, encryption2);
//			if(count2==1)
//				myCollection.insertMany(doc4000, encryption2);
//			if(count2==2)
//				myCollection.insertMany(doc8000, encryption2);
//			if(count2==3)
//				myCollection.insertMany(doc16000, encryption2);
//	        long endTime = System.nanoTime();
//	        long duration = (endTime - startTime);
//	        System.out.println("Inserting all records with our way " + ((float)duration/1000000000)+" secs");
//	        System.out.println("Now,The collection has " + collection.count() + " items");
//	        bcryptMeasure.put(count2, ((float)duration/1000000000));
//	        long findTime = System.nanoTime();  
//	       Iterator<Document> cursor2 = myCollection.find().iterator();
//	       while (cursor2.hasNext()) {
//	           System.err.println(cursor2.next().toJson());
//	       }
//	       long endfindTime = System.nanoTime();
//	       long durationfind = (endfindTime - findTime);
//	       System.out.println("Finding all records with our way ( \" decoding \") "+((float)durationfind/1000000000)+" secs");
//	       bcryptfindMeasure.put(count2, ((float)durationfind/1000000000));
//	       count2++;
//
//		}
//		
//		writer.println("BCRYPT");
//		writer.println("Insert");
//		writer.println(bcryptMeasure);
//		writer.println("Find");
//		writer.println(bcryptfindMeasure);


		
		/*--------------------------------------------------------------------------------------*/
		
		/*Mongo Insert Measures*/
//        collection.drop();//delete the collection        
//        fieldCollection.drop();//delete the Keys Collection
//        /*Bring the Encrypted fields statically/topically*/
//        Map<Integer,Float> mongoMeasure=new HashMap<Integer,Float>();
//        Map<Integer,Float> mongofindMeasure=new HashMap<Integer,Float>();
//		int count3=0;
//		while(count3!=4)
//		{
//	        collection.drop();//delete the collection   
//	        myCollection = new AEMongoCollection(collection,fieldCollection);
//			long startTime = System.nanoTime();
//			if(count3==0)
//				collection.insertMany(doc32000);
//			if(count3==1)
//				collection.insertMany(doc40000);
//			if(count3==2)
//				collection.insertMany(doc80000);
//			if(count3==3)
//				collection.insertMany(doc160000);
//	        long endTime = System.nanoTime();
//	        long duration = (endTime - startTime);
//	        System.out.println("Inserting all records with our way " + ((float)duration/1000000000)+" secs");
//	        System.out.println("Now,The collection has " + collection.count() + " items");
//	        mongoMeasure.put(count3, ((float)duration/1000000000));
//		 	long findTime = System.nanoTime();  
//	        Iterator<Document> cursor2 = collection.find().iterator();
//	        while (cursor2.hasNext()) {
//	            System.err.println(cursor2.next().toJson());
//	        }
//	        long endfindTime = System.nanoTime();
//	        long durationfind = (endfindTime - findTime);
//	        System.out.println("Finding all records with mongo's way"+((float)durationfind/1000000000)+" secs");
//	        mongofindMeasure.put(count3, ((float)durationfind/1000000000));
//	        count3++;
//		}
//		
//		writer.println("Mongo");
//		writer.println("Insert");
//		writer.println(mongoMeasure);
//		writer.println("Find");
//		writer.println(mongofindMeasure);


		/*----------------------------------------------------------------------------------------*/
		
		writer.close();
        
        
        /*Print All The Documents of the Collection (Using Mongo's way)*/
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
//       MongoCursor<Document> cursor3 = fieldCollection.find().iterator();
//        try {
//            while (cursor3.hasNext()) {
//                System.out.println("Encrypted field"+cursor3.next().toJson());
//            }
//        } finally {
//            cursor3.close();
//        }
        
        /*Print All The Documents of the Collection (modified appropriately)*/
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
