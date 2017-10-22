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
        List<Document> doc10=new ArrayList<Document>();
        List<Document> doc20=new ArrayList<Document>();
        List<Document> doc50=new ArrayList<Document>();
        List<Document> doc100=new ArrayList<Document>();
        List<Document> doc300=new ArrayList<Document>();
        List<Document> doc700=new ArrayList<Document>();
        List<Document> doc900=new ArrayList<Document>();
        List<Document> doc1200=new ArrayList<Document>();
        List<Document> doc1400=new ArrayList<Document>();
        List<Document> doc1600=new ArrayList<Document>();
        List<Document> doc1800=new ArrayList<Document>();

        List<Document> doc500=new ArrayList<Document>();
        List<Document> doc1000=new ArrayList<Document>();
        List<Document> doc2000=new ArrayList<Document>();
        List<Document> doc4000=new ArrayList<Document>();
        List<Document> doc8000=new ArrayList<Document>();
        List<Document> doc16000=new ArrayList<Document>();
        List<Document> doc20000=new ArrayList<Document>();
        List<Document> doc30000=new ArrayList<Document>();
        List<Document> doc40000=new ArrayList<Document>();
        List<Document> doc50000=new ArrayList<Document>();
        List<Document> doc60000=new ArrayList<Document>();
        List<Document> doc70000=new ArrayList<Document>();
        List<Document> doc80000=new ArrayList<Document>();
        List<Document> doc90000=new ArrayList<Document>();
        List<Document> doc100000=new ArrayList<Document>();
        List<Document> doc110000=new ArrayList<Document>();
        List<Document> doc120000=new ArrayList<Document>();
        List<Document> doc130000=new ArrayList<Document>();
        List<Document> doc140000=new ArrayList<Document>();
        List<Document> doc150000=new ArrayList<Document>();
        List<Document> doc160000=new ArrayList<Document>();
        List<Document> doc170000=new ArrayList<Document>();
        List<Document> doc180000=new ArrayList<Document>();
        List<Document> doc190000=new ArrayList<Document>();
        List<Document> doc200000=new ArrayList<Document>();
        List<Document> doc250000=new ArrayList<Document>();
        
        
        DataFactory dff = new DataFactory();
        for (int i = 0; i <10; i++) {          
//          Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
           Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

          doc10.add(doc);
      }
        for (int i = 0; i <20; i++) {          
//          Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
           Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

          doc20.add(doc);
      }
        for (int i = 0; i <50; i++) {          
//          Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
           Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

          doc50.add(doc);
      }
        for (int i = 0; i <100; i++) {          
//          Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
           Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

          doc100.add(doc);
      }
        for (int i = 0; i <300; i++) {          
//          Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
           Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

          doc300.add(doc);
      }
        for (int i = 0; i <700; i++) {          
//          Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
           Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

          doc700.add(doc);
      }
        for (int i = 0; i <900; i++) {          
//          Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
           Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

          doc900.add(doc);
      }
        for (int i = 0; i <1200; i++) {          
//          Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
           Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

          doc1200.add(doc);
      }
        for (int i = 0; i <1400; i++) {          
//          Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
           Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

          doc1400.add(doc);
      }
        for (int i = 0; i <1600; i++) {          
//          Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
           Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

          doc1600.add(doc);
      }
        for (int i = 0; i <1800; i++) {          
//          Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
           Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

          doc1800.add(doc);
      }
        for (int i = 0; i <500; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));// //plain document

            doc500.add(doc);
            doc1000.add(doc);
            doc2000.add(doc);
            doc4000.add(doc);
            doc8000.add(doc);
            doc16000.add(doc);
            doc20000.add(doc);
            doc30000.add(doc);
            doc40000.add(doc);
            doc50000.add(doc);
            doc60000.add(doc);
            doc70000.add(doc);
            doc80000.add(doc);
            doc90000.add(doc);
            doc100000.add(doc);
            doc110000.add(doc);
            doc120000.add(doc);
            doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 500 docs "+doc500.size());
        
        for (int i = 0; i <500; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

            doc1000.add(doc);
            doc2000.add(doc);
            doc4000.add(doc);
            doc8000.add(doc);
            doc16000.add(doc);
            doc20000.add(doc);
            doc30000.add(doc);
            doc40000.add(doc);
            doc50000.add(doc);
            doc60000.add(doc);
            doc70000.add(doc);
            doc80000.add(doc);
            doc90000.add(doc);
            doc100000.add(doc);
            doc110000.add(doc);
            doc120000.add(doc);
            doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 1000 docs "+doc1000.size());
        
        for (int i = 0; i <1000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); ////plain document

        	doc2000.add(doc);
            doc4000.add(doc);
            doc8000.add(doc);
            doc16000.add(doc);
            doc20000.add(doc);
            doc30000.add(doc);
            doc40000.add(doc);
            doc50000.add(doc);
            doc60000.add(doc);
            doc70000.add(doc);
            doc80000.add(doc);
            doc90000.add(doc);
            doc100000.add(doc);
            doc110000.add(doc);
            doc120000.add(doc);
            doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 2000 docs "+doc2000.size());
        
        for (int i = 0; i <2000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

        	doc4000.add(doc);
            doc8000.add(doc);
            doc16000.add(doc);
            doc20000.add(doc);
            doc30000.add(doc);
            doc40000.add(doc);
            doc50000.add(doc);
            doc60000.add(doc);
            doc70000.add(doc);
            doc80000.add(doc);
            doc90000.add(doc);
            doc100000.add(doc);
            doc110000.add(doc);
            doc120000.add(doc);
            doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 4000 docs "+doc4000.size());
        
        for (int i = 0; i <4000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

        	doc8000.add(doc);
            doc16000.add(doc);
            doc20000.add(doc);
            doc30000.add(doc);
            doc40000.add(doc);
            doc50000.add(doc);
            doc60000.add(doc);
            doc70000.add(doc);
            doc80000.add(doc);
            doc90000.add(doc);
            doc100000.add(doc);
            doc110000.add(doc);
            doc120000.add(doc);
            doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 8000 docs "+doc8000.size());
        
        for (int i = 0; i <8000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

        	doc16000.add(doc);
            doc20000.add(doc);
            doc30000.add(doc);
            doc40000.add(doc);
            doc50000.add(doc);
            doc60000.add(doc);
            doc70000.add(doc);
            doc80000.add(doc);
            doc90000.add(doc);
            doc100000.add(doc);
            doc110000.add(doc);
            doc120000.add(doc);
            doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 16000 docs "+doc16000.size());
        
        for (int i = 0; i <4000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

        	doc20000.add(doc);
            doc30000.add(doc);
            doc40000.add(doc);
            doc50000.add(doc);
            doc60000.add(doc);
            doc70000.add(doc);
            doc80000.add(doc);
            doc90000.add(doc);
            doc100000.add(doc);
            doc110000.add(doc);
            doc120000.add(doc);
            doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 20000 docs "+doc20000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

            doc30000.add(doc);
            doc40000.add(doc);
            doc50000.add(doc);
            doc60000.add(doc);
            doc70000.add(doc);
            doc80000.add(doc);
            doc90000.add(doc);
            doc100000.add(doc);
            doc110000.add(doc);
            doc120000.add(doc);
            doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 30000 docs "+doc30000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

            doc40000.add(doc);
            doc50000.add(doc);
            doc60000.add(doc);
            doc70000.add(doc);
            doc80000.add(doc);
            doc90000.add(doc);
            doc100000.add(doc);
            doc110000.add(doc);
            doc120000.add(doc);
            doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 40000 docs "+doc40000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

            doc50000.add(doc);
            doc60000.add(doc);
            doc70000.add(doc);
            doc80000.add(doc);
            doc90000.add(doc);
            doc100000.add(doc);
            doc110000.add(doc);
            doc120000.add(doc);
            doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 50000 docs "+doc50000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

        	doc60000.add(doc);
            doc70000.add(doc);
            doc80000.add(doc);
            doc90000.add(doc);
            doc100000.add(doc);
            doc110000.add(doc);
            doc120000.add(doc);
            doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 60000 docs "+doc60000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

        	doc70000.add(doc);
            doc80000.add(doc);
            doc90000.add(doc);
            doc100000.add(doc);
            doc110000.add(doc);
            doc120000.add(doc);
            doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 70000 docs "+doc70000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

        	doc80000.add(doc);
            doc90000.add(doc);
            doc100000.add(doc);
            doc110000.add(doc);
            doc120000.add(doc);
            doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 80000 docs "+doc80000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

        	doc90000.add(doc);
            doc100000.add(doc);
            doc110000.add(doc);
            doc120000.add(doc);
            doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 90000 docs "+doc90000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

        	doc100000.add(doc);
            doc110000.add(doc);
            doc120000.add(doc);
            doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 100000 docs "+doc100000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

        	doc110000.add(doc);
            doc120000.add(doc);
            doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 110000 docs "+doc110000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

        	doc120000.add(doc);
            doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 120000 docs "+doc120000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

        	doc130000.add(doc);
            doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        } 
        System.out.println("Created 130000 docs "+doc130000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

        	doc140000.add(doc);
            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 140000 docs "+doc140000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

            doc150000.add(doc);
            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 150000 docs "+doc150000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

            doc160000.add(doc);
            doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 160000 docs "+doc160000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

        	doc170000.add(doc);
            doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 170000 docs "+doc170000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

        	doc180000.add(doc);
            doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 180000 docs "+doc180000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

        	doc190000.add(doc);
            doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 190000 docs "+doc190000.size());
        
        for (int i = 0; i <10000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

        	doc200000.add(doc);
            doc250000.add(doc);
        }
        System.out.println("Created 200000 docs "+doc200000.size());
        
        for (int i = 0; i <50000; i++) {          
//            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
             Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document

            doc250000.add(doc);
        }
        System.out.println("Created 250000 docs "+doc250000.size());
        
        /*System.out.println("Created 40000 docs "+doc40000.size());
        for (int i = 0; i <40000; i++) {          
            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
            doc80000.add(doc);
            doc160000.add(doc);
        }
        System.out.println("Created 80000 docs "+doc80000.size());
        for (int i = 0; i <80000; i++) {  
            Document doc = new Document("name",new Document("first",dff.getFirstName()).append("last",dff.getLastName())).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime()));
           // Document doc = new Document("name",dff.getFirstName()).append("surname", dff.getLastName()).append("e-mail",dff.getEmailAddress()).append("date",dff.getDateBetween(new GregorianCalendar(1920, 1, 1).getTime(), new GregorianCalendar(2017, 12, 31).getTime())); //plain document
            doc160000.add(doc);
        }
        System.out.println("Created 160000 docs "+doc160000.size());*/
        PrintWriter writer1 = new PrintWriter("25000Recs.txt", "UTF-8");
        writer1.println(doc250000);
        PrintWriter writer2 = new PrintWriter("20000Recs.txt", "UTF-8");
        writer2.println(doc200000);
        PrintWriter writer3 = new PrintWriter("190000Recs.txt", "UTF-8");
        writer3.println(doc190000);
        PrintWriter writer4 = new PrintWriter("180000Recs.txt", "UTF-8");
        writer4.println(doc180000);
        PrintWriter writer5 = new PrintWriter("17000Recs.txt", "UTF-8");
        writer5.println(doc170000);
        PrintWriter writer6 = new PrintWriter("25000Recs.txt", "UTF-8");
        writer6.println(doc160000);
        PrintWriter writer7 = new PrintWriter("25000Recs.txt", "UTF-8");
        writer7.println(doc150000);
        PrintWriter writer8 = new PrintWriter("25000Recs.txt", "UTF-8");
        writer8.println(doc140000);
        PrintWriter writer9 = new PrintWriter("25000Recs.txt", "UTF-8");
        writer9.println(doc130000);
        PrintWriter writer10 = new PrintWriter("25000Recs.txt", "UTF-8");
        writer10.println(doc120000);
        PrintWriter writer11 = new PrintWriter("25000Recs.txt", "UTF-8");
        writer11.println(doc110000);
        PrintWriter writer12 = new PrintWriter("25000Recs.txt", "UTF-8");
        writer12.println(doc100000);
        PrintWriter writer13 = new PrintWriter("25000Recs.txt", "UTF-8");
        writer13.println(doc90000);
        PrintWriter writer14 = new PrintWriter("25000Recs.txt", "UTF-8");
        writer14.println(doc80000);
        PrintWriter writer15 = new PrintWriter("25000Recs.txt", "UTF-8");
        writer15.println(doc70000);
        PrintWriter writer16 = new PrintWriter("25000Recs.txt", "UTF-8");
        writer16.println(doc60000);
        PrintWriter writer17 = new PrintWriter("25000Recs.txt", "UTF-8");
        writer17.println(doc50000);
        PrintWriter writer18 = new PrintWriter("25000Recs.txt", "UTF-8");
        writer18.println(doc40000);
        PrintWriter writer19 = new PrintWriter("25000Recs.txt", "UTF-8");
        writer19.println(doc30000);
        PrintWriter writer20 = new PrintWriter("25000Recs.txt", "UTF-8");
        writer20.println(doc20000);
        PrintWriter writer21 = new PrintWriter("16000Recs.txt", "UTF-8");
        writer21.println(doc16000);
        PrintWriter writer22 = new PrintWriter("8000Recs.txt", "UTF-8");
        writer22.println(doc8000);
        PrintWriter writer23 = new PrintWriter("4000Recs.txt", "UTF-8");
        writer23.println(doc4000);
        PrintWriter writer24 = new PrintWriter("2000Recs.txt", "UTF-8");
        writer24.println(doc2000);
        PrintWriter writer25 = new PrintWriter("1000Recs.txt", "UTF-8");
        writer25.println(doc1000);
        PrintWriter writer26 = new PrintWriter("500Recs.txt", "UTF-8");
        writer26.println(doc500);
        
        writer1.close();
        writer2.close();
        writer3.close();
        writer4.close();
        writer5.close();
        writer6.close();
        writer7.close();
        writer8.close();
        writer9.close();
        writer10.close();
        writer11.close();
        writer12.close();
        writer13.close();
        writer14.close();
        writer15.close();
        writer16.close();
        writer17.close();
        writer18.close();
        writer19.close();
        writer20.close();
        writer21.close();
        writer22.close();
        writer23.close();
        writer24.close();
        writer25.close();
        writer26.close();

        /*PrintWriter writer1 = new PrintWriter("32000Recs.txt", "UTF-8");
        writer1.println(doc32000);
        PrintWriter writer2 = new PrintWriter("40000Recs.txt", "UTF-8");
        writer2.println(doc40000);
        PrintWriter writer3 = new PrintWriter("80000Recs.txt", "UTF-8");
        writer3.println(doc80000);
        PrintWriter writer4 = new PrintWriter("160000Recs.txt", "UTF-8");
        writer4.println(doc160000);
        PrintWriter writer5 = new PrintWriter("16000Recs.txt", "UTF-8");
        writer5.println(doc16000);
        PrintWriter writer6 = new PrintWriter("8000Recs.txt", "UTF-8");
        writer6.println(doc8000);
        PrintWriter writer7 = new PrintWriter("4000Recs.txt", "UTF-8");
        writer7.println(doc4000);
        PrintWriter writer8 = new PrintWriter("2000Recs.txt", "UTF-8");
        writer8.println(doc2000);
        PrintWriter writer9 = new PrintWriter("1000Recs.txt", "UTF-8");
        writer9.println(doc1000);
        PrintWriter writer10 = new PrintWriter("500Recs.txt", "UTF-8");
        writer10.println(doc500);
        writer1.close();
        writer2.close();
        writer3.close();
        writer4.close();
        writer5.close();
        writer6.close();
        writer7.close();
        writer8.close();
        writer9.close();
        writer10.close();*/


		PrintWriter writer = new PrintWriter("measures.txt", "UTF-8");

        
        
        
        /*SHA-256 Insert Measures*/
        /*Bring the Encrypted fields statically/topically*/
		 /*Set the encrypted fields if they aren't already set*/
//       EncryptionType encryption = EncryptionType.HASH;
//        myCollection.setEncryptedField("e-mail", encryption);
//        myCollection.importEncryptedFields();
//        Map<Integer,Float> shaMeasure=new HashMap<Integer,Float>();
//        Map<Integer,Float> shafindMeasure=new HashMap<Integer,Float>();
//		int count=0;
//		while(count!=15)//26 for big
//		{
//	        collection.drop();//delete the collection
//	        myCollection = new AEMongoCollection(collection,fieldCollection);
//	        System.out.println("After Drop,The collection has " + collection.count() + " items");
//	        myCollection.importEncryptedFields();
//			long startTime = System.nanoTime();
//		if(count==0)
//		myCollection.insertMany(doc10, encryption);
//	else if(count==1)
//		myCollection.insertMany(doc20, encryption);
//	else if(count==2)
//		myCollection.insertMany(doc50, encryption);
//	else if(count==3)
//		myCollection.insertMany(doc100, encryption);
//	else if(count==4)
//		myCollection.insertMany(doc300, encryption);
//	else if(count==5)
//		myCollection.insertMany(doc500, encryption);
//	else if(count==6)
//		myCollection.insertMany(doc700, encryption);
//	else if(count==7)
//		myCollection.insertMany(doc900, encryption);
//	else if(count==8)
//		myCollection.insertMany(doc1000, encryption);
//	else if(count==9)
//		myCollection.insertMany(doc1200, encryption);
//	else if(count==10)
//		myCollection.insertMany(doc1400, encryption);
//	else if(count==11)
//		myCollection.insertMany(doc1600, encryption);
//	else if(count==12)
//		myCollection.insertMany(doc1800, encryption);
//	else if(count==13)
//		myCollection.insertMany(doc2000, encryption);
//	else if(count==14)
//		myCollection.insertMany(doc4000, encryption);
//			/*if(count==0)
//				myCollection.insertMany(doc500, encryption);
//			else if(count==1)
//				myCollection.insertMany(doc1000, encryption);
//			else if(count==2)
//				myCollection.insertMany(doc2000, encryption);
//			else if(count==3)
//				myCollection.insertMany(doc4000, encryption);
//			else if(count==4)
//				myCollection.insertMany(doc8000, encryption);
//			else if(count==5)
//				myCollection.insertMany(doc16000, encryption);
//			else if(count==6)
//				myCollection.insertMany(doc20000, encryption);
//			else if(count==7)
//				myCollection.insertMany(doc30000, encryption);
//			else if(count==8)
//				myCollection.insertMany(doc40000, encryption);
//			else if(count==9)
//				myCollection.insertMany(doc50000, encryption);
//			else if(count==10)
//				myCollection.insertMany(doc60000,encryption);
//			else if(count==11)
//				myCollection.insertMany(doc70000,encryption);
//			else if(count==12)
//				myCollection.insertMany(doc80000,encryption);
//			else if(count==13)
//				myCollection.insertMany(doc90000,encryption);
//			else if(count==14)
//				myCollection.insertMany(doc100000,encryption);
//			else if(count==15)
//				myCollection.insertMany(doc110000,encryption);
//			else if(count==16)
//				myCollection.insertMany(doc120000,encryption);
//			else if(count==17)
//				myCollection.insertMany(doc130000,encryption);
//			else if(count==18)
//				myCollection.insertMany(doc140000,encryption);
//			else if(count==19)
//				myCollection.insertMany(doc150000,encryption);
//			else if(count==20)
//				myCollection.insertMany(doc160000,encryption);
//			else if(count==21)
//				myCollection.insertMany(doc170000,encryption);
//			else if(count==22)
//				myCollection.insertMany(doc180000,encryption);
//			else if(count==23)
//				myCollection.insertMany(doc190000,encryption);
//			else if(count==24)
//				myCollection.insertMany(doc200000,encryption);
//			else if(count==25)
//				myCollection.insertMany(doc250000,encryption);Big Measures*/
//	        long endTime = System.nanoTime();
//	        long duration = (endTime - startTime);
//	        System.out.println("Inserting all records with our way " + ((float)duration/1000000000)+" secs");
//	        System.out.println("Now,The collection has " + collection.count() + " items");
//	        shaMeasure.put(count, ((float)duration/1000000000));
//	        long findTime = System.nanoTime();  
//		    Iterator<Document> cursor2 = myCollection.find().iterator();
//		    while (cursor2.hasNext()) {
//		        System.err.println(cursor2.next().toJson());
//		    }
//		    long endfindTime = System.nanoTime();
//		    long durationfind = (endfindTime - findTime);
//		    System.out.println("Finding all records with our way ( \" decoding \") "+((float)durationfind/1000000000)+" secs");
//		    shafindMeasure.put(count, ((float)durationfind/1000000000));
//	        count++;
//		}
//		writer.println("SHA-256");
//		writer.println("Insert");
//		writer.println(shaMeasure);
//		writer.println("Find");
//		writer.println(shafindMeasure);

		/*-----------------------------------------------------------------------------------*/
		
		
		/*Bcrypt Insert Measures*/
//        collection.drop();//delete the collection        
//        fieldCollection.drop();//delete the Keys Collection
//        EncryptionType encryption2 = EncryptionType.RANDOM;
//        myCollection.setEncryptedField("name.last", encryption2);
//        /*Bring the Encrypted fields statically/topically*/
//        myCollection.importEncryptedFields();
//        Map<Integer,Float> bcryptMeasure=new HashMap<Integer,Float>();
//        Map<Integer,Float> bcryptfindMeasure=new HashMap<Integer,Float>();
//
//		int count2=0;
//		while(count2!=15)
//		{
//	        collection.drop();//delete the collection  
//	        myCollection = new AEMongoCollection(collection,fieldCollection);
//	        System.out.println("After Drop,The collection has " + collection.count() + " items");
//        	myCollection.importEncryptedFields();
//			long startTime = System.nanoTime();
//			if(count2==0)
//				myCollection.insertMany(doc10, encryption2);
//			else if(count2==1)
//				myCollection.insertMany(doc20, encryption2);
//			else if(count2==2)
//				myCollection.insertMany(doc50, encryption2);
//			else if(count2==3)
//				myCollection.insertMany(doc100, encryption2);
//			else if(count2==4)
//				myCollection.insertMany(doc300, encryption2);
//			else if(count2==5)
//				myCollection.insertMany(doc500, encryption2);
//			else if(count2==6)
//				myCollection.insertMany(doc700, encryption2);
//			else if(count2==7)
//				myCollection.insertMany(doc900, encryption2);
//			else if(count2==8)
//				myCollection.insertMany(doc1000, encryption2);
//			else if(count2==9)
//				myCollection.insertMany(doc1200, encryption2);
//			else if(count2==10)
//				myCollection.insertMany(doc1400, encryption2);
//			else if(count2==11)
//				myCollection.insertMany(doc1600, encryption2);
//			else if(count2==12)
//				myCollection.insertMany(doc1800, encryption2);
//			else if(count2==13)
//				myCollection.insertMany(doc2000, encryption2);
//			else if(count2==14)
//				myCollection.insertMany(doc4000, encryption2);
//			//myCollection.insertMany(doc100, encryption2);
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
        collection.drop();//delete the collection        
        fieldCollection.drop();//delete the Keys Collection
        /*Bring the Encrypted fields statically/topically*/
        Map<Integer,Float> mongoMeasure=new HashMap<Integer,Float>();
        Map<Integer,Float> mongofindMeasure=new HashMap<Integer,Float>();
		int count3=0;
		while(count3!=15)//26 for big measures
		{
	        collection.drop();//delete the collection   
	        myCollection = new AEMongoCollection(collection,fieldCollection);
			long startTime = System.nanoTime();
			if(count3==0)
			collection.insertMany(doc10);
		else if(count3==1)
			collection.insertMany(doc20);
		else if(count3==2)
			collection.insertMany(doc50);
		else if(count3==3)
			collection.insertMany(doc100);
		else if(count3==4)
			collection.insertMany(doc300);
		else if(count3==5)
			collection.insertMany(doc500);
		else if(count3==6)
			collection.insertMany(doc700);
		else if(count3==7)
			collection.insertMany(doc900);
		else if(count3==8)
			collection.insertMany(doc1000);
		else if(count3==9)
			collection.insertMany(doc1200);
		else if(count3==10)
			collection.insertMany(doc1400);
		else if(count3==11)
			collection.insertMany(doc1600);
		else if(count3==12)
			collection.insertMany(doc1800);
		else if(count3==13)
			collection.insertMany(doc2000);
		else if(count3==14)
			collection.insertMany(doc4000);
		/*	if(count3==0)
				collection.insertMany(doc500);
			else if(count3==1)
				collection.insertMany(doc1000);
			else if(count3==2)
				collection.insertMany(doc2000);
			else if(count3==3)
				collection.insertMany(doc4000);
			else if(count3==4)
				collection.insertMany(doc8000);
			else if(count3==5)
				collection.insertMany(doc16000);
			else if(count3==6)
				collection.insertMany(doc20000);
			else if(count3==7)
				collection.insertMany(doc30000);
			else if(count3==8)
				collection.insertMany(doc40000);
			else if(count3==9)
				collection.insertMany(doc50000);
			else if(count3==10)
				collection.insertMany(doc60000);
			else if(count3==11)
				collection.insertMany(doc70000);
			else if(count3==12)
				collection.insertMany(doc80000);
			else if(count3==13)
				collection.insertMany(doc90000);
			else if(count3==14)
				collection.insertMany(doc100000);
			else if(count3==15)
				collection.insertMany(doc110000);
			else if(count3==16)
				collection.insertMany(doc120000);
			else if(count3==17)
				collection.insertMany(doc130000);
			else if(count3==18)
				collection.insertMany(doc140000);
			else if(count3==19)
				collection.insertMany(doc150000);
			else if(count3==20)
				collection.insertMany(doc160000);
			else if(count3==21)
				collection.insertMany(doc170000);
			else if(count3==22)
				collection.insertMany(doc180000);
			else if(count3==23)
				collection.insertMany(doc190000);
			else if(count3==24)
				collection.insertMany(doc200000);
			else if(count3==25)
				collection.insertMany(doc250000);  BIG MEASURES*/
			
	        long endTime = System.nanoTime();
	        long duration = (endTime - startTime);
	        System.out.println("Inserting all records with mongos " + ((float)duration/1000000000)+" secs");
	        System.out.println("Now,The collection has " + collection.count() + " items");
	        mongoMeasure.put(count3, ((float)duration/1000000000));
		 	long findTime = System.nanoTime();  
	        Iterator<Document> cursor2 = collection.find().iterator();
	        while (cursor2.hasNext()) {
	            System.err.println(cursor2.next().toJson());
	        }
	        long endfindTime = System.nanoTime();
	        long durationfind = (endfindTime - findTime);
	        System.out.println("Finding all records with mongo's way"+((float)durationfind/1000000000)+" secs");
	        mongofindMeasure.put(count3, ((float)durationfind/1000000000));
	        count3++;
		}
		
		writer.println("Mongo");
		writer.println("Insert");
		writer.println(mongoMeasure);
		writer.println("Find");
		writer.println(mongofindMeasure);


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
