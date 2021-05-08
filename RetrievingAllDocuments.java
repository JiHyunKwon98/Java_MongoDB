import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class RetrievingAllDocuments {

	public static void main(String[] args) {
		
		// Creating a Mongo client
		MongoClient myclient = new MongoClient("localhost" , 27017);
		System.out.println("Connected to the database successfully");

		// Accessing the database(db가 없으면 처음 문서 저장 시 새로 생성)
		MongoDatabase mydb = myclient.getDatabase("sdb"); 
						 	
		MongoCollection<Document> mycol = mydb.getCollection("sampleCol");
		System.out.println("Collection selected successfully"); 
		
		// Getting the iterable object
		 FindIterable<Document> iterDoc = mycol.find();
		 // Getting the iterator
		 Iterator it = iterDoc.iterator(); // iterator 반환
		 int i = 0;
		 while (it.hasNext()) {
		 System.out.println(it.next());
		 i++;
		 }

		System.out.println(i);

	}

}
