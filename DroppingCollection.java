import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DroppingCollection {

	public static void main(String[] args) {
		
		// Creating a Mongo client
		MongoClient myclient = new MongoClient("localhost" , 27017);
		System.out.println("Connected to the database successfully");

		// Accessing the database
		 MongoDatabase mydb = myclient.getDatabase("sdb");
		 // Retrieving a collection
		 MongoCollection<Document> mycol = mydb.getCollection("sampleCol");
		 // Dropping a Collection
		 mycol.drop();
		 System.out.println("Collection dropped successfully");

	}

}
