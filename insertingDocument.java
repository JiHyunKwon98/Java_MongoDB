import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import org.bson.Document;

import com.mongodb.client.MongoCollection;

public class insertingDocument {

	public static void main(String[] args) {
		
		// Creating a Mongo client
		MongoClient myclient = new MongoClient("localhost" , 27017);
		System.out.println("Connected to the database successfully");

		// Accessing the database(db가 없으면 처음 문서 저장 시 새로 생성)
		MongoDatabase mydb = myclient.getDatabase("sdb"); 
				 	
		 MongoCollection<Document> mycol = mydb.getCollection("sampleCol");
		 System.out.println("Collection selected successfully"); 
		
		// bson Document 객체 생성
		Document mydoc = new Document("title", "java")
			.append("id", 1)
			.append("description", "javaProgram")
			.append("likes", 150)
			.append("url", "http://www.tutorialspoint.com/mongodb/")
			.append("by", "tutorials point");
		mycol.insertOne(mydoc);
		System.out.println("Document inserted successfully");

	}

}
