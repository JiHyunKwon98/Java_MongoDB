import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import org.bson.Document; 

public class ConnectToDB {

	public static void main(String[] args) {
		
		// Creating a Mongo client
		 MongoClient myclient = new MongoClient("localhost" , 27017);
		 System.out.println("Connected to the database successfully");

		 // Accessing the database(db가 없으면 처음 문서 저장 시 새로 생성)
		 MongoDatabase mydb = myclient.getDatabase("sdb"); 
		 
		 mydb.createCollection("sampleCol");
		 System.out.println("Collection created successfully");
		 
		// listing all collections
		 for (String name : mydb.listCollectionNames()) {
		 System.out.println(name);
		 }
		
		 // Retrieving a collection (컬렉션 없으면 처음 문서 저장 시 새로 생성)
		 MongoCollection<Document> mycol = mydb.getCollection("sampleCol");
		 System.out.println("Collection selected successfully"); 

		 
	}

}
