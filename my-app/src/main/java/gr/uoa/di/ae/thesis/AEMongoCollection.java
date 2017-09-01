package gr.uoa.di.ae.thesis;

//import java.util.List;
import java.util.Scanner;

import org.bson.Document;
//import org.bson.codecs.configuration.CodecRegistry;
//import org.bson.conversions.Bson;

/*import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.client.ListCollectionsIterable;*/
import com.mongodb.client.MongoCollection;
/*import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.CreateViewOptions;*/

public class AEMongoCollection {
	
	public AEMongoCollection(MongoCollection<Document> collection) {
		// TODO Auto-generated constructor stub
	}

	public void AEMongoCollectionCreateDoc(MongoCollection<Document> collection) {
		Document doc = new Document();
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter your name");
		String name = keyboard.next();
		doc.append("name", name);
		System.out.println("Enter your surname");
		String surname = keyboard.next();
		doc.append("surname", surname);
		System.out.println("Enter your age");
		int age = keyboard.nextInt();
		doc.append("age", age);
		System.out.println("Enter your class");
		int classof = keyboard.nextInt();
		doc.append("class", classof);
		collection.insertOne(doc);
		keyboard.close();
	}
	
}
