import java.util.Iterator;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class DeletingDocuments {

	public static void main(String[] args) {
		
		// Creating a Mongo client
		MongoClient myclient = new MongoClient("localhost" , 27017);
		System.out.println("Connected to the database successfully");

		// Accessing the database
		 MongoDatabase mydb = myclient.getDatabase("sdb");
		 // Retrieving a collection
		 MongoCollection<Document> mycol = mydb.getCollection("sampleCol");
		 
		 //Deleting a document
		 mycol.deleteOne(Filters.eq("id",1)); //deleteMany() 메소드 
		 System.out.println("Document deleted successfully...");
		 
		 //Getting the iterable object
		 FindIterable<Document> iterDoc = mycol.find();
		 int i = 1;
		 //Getting the iterator
		 Iterator it = iterDoc.iterator();  // iterator 반환
		 while(it.hasNext()) {
			 System.out.println(it.next()); i++;
		 }
	
	}

}
