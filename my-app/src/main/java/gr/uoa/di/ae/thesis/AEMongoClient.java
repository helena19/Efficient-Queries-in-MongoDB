package gr.uoa.di.ae.thesis;

import java.util.List;


import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class AEMongoClient {

	private MongoClient mc;
	
	public AEMongoClient(ServerAddress serverAddress, List<MongoCredential> asList) {
		mc = new MongoClient(serverAddress, asList);
	}
	
	public AEMongoCollection getCollection(String databaseName, String collectionName) throws Exception {
		return new AEMongoCollection(mc.getDatabase(databaseName).getCollection(collectionName), null);
	}
	
}
