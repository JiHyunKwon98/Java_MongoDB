package homework;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class StudentInfo {
	
	public static void main(String[] args){
	
    MongoClient myclient = new MongoClient("localhost" , 27017);
	System.out.println("Connected to the database successfully");  
		
	Scanner sc = new Scanner(System.in);	
	
	while(true) {
		
		System.out.println("<학생 정보 관리 프로그램>");
		System.out.println("1) 데이터 베이스 및 컬렉션 입력/선택");
		System.out.println("2) 학생 정보 삽입");
		System.out.println("3) 학생 정보 삭제");
		System.out.println("4) 학생 정보 수정");
		System.out.println("5) 학생 정보 검색(전체)");
		System.out.println("6) 학생 정보 검색(조건)");
		System.out.println("7) 종료");
		System.out.println("----------------------");
		System.out.print("메뉴 선택 : ");
		
		int  ch = sc.nextInt();
		
		switch(ch) {
	    case 1:
	    	System.out.print("사용할 Database명을 입력하세요:");
			String database = sc.next();
			if(database.equals("school")) {
				MongoDatabase mydb = myclient.getDatabase("school"); 	
				System.out.println("Database selected successfully");
				
				System.out.print("사용할 Collection명을 입력하세요:");
				String collection = sc.next();
				if(collection.equals("student")) {
					MongoCollection<Document> mycol = mydb.getCollection("student");
					System.out.println("Collection selected successfully");
				}
				else System.out.print("해당 Collection이 존재하지 않습니다.");
			}
			else System.out.print("해당 Database가 존재하지 않습니다.");
	        break;
	         
	    case 2:
	    	
	         break;
	    case 3:
	         break;
	    case 4:
	         break;
	    case 5:
	         break;
	    case 6:
	         break;
	    default:
	    	 System.exit(0);
	         break;
			
		}
		
		}
			
	}
	
	
}
