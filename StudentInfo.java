import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class StudentInfo {
	
	public static void main(String[] args){
	
    MongoClient myclient = new MongoClient("localhost" , 27017);
	System.out.println("Connected to the database successfully");  
		
	Scanner sc = new Scanner(System.in);
	
	System.out.println("<학생 정보 관리 프로그램>");
	System.out.println("1) 데이터 베이스 및 컬렉션 입력/선택");
	System.out.println("2) 학생 정보 삽입");
	System.out.println("3) 학생 정보 삭제");
	System.out.println("4) 학생 정보 수정");
	System.out.println("5) 학생 정보 검색(전체)");
	System.out.println("6) 학생 정보 검색(조건)");
	System.out.println("7) 종료");
	System.out.println("----------------------");
	System.out.print("메뉴 선택 :");
	
	int frist = sc.nextInt();
	
	if (frist == 1) {
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
				
				while(true) {
					System.out.println("<학생 정보 관리 프로그램>");
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
					
				    case 2:		    
				    	 String num, name, college, department, grade, gender, mail;
				    	// 학번, 이름, 소속(단과대학, 학과명), 학년, 성별, 이메일 
				    	 int count; //취미 개수 
				    	 
				    	 System.out.print("학번을 입력하세요:");
				    	 num = sc.next();
				    	 System.out.print("이름을 입력하세요:");
				    	 name = sc.next();
				    	 System.out.print("단과대학을 입력하세요:");
				    	 college = sc.next();
				         System.out.print("학과명을 입력하세요:");
				    	 department = sc.next();
				    	 System.out.print("학년을 입력하세요:");
				    	 grade = sc.next();
				    	 System.out.print("성별을 입력하세요:");
				    	 gender = sc.next();
				    	 System.out.print("이메일 을 입력하세요:");
				    	 mail = sc.next();
				    	 System.out.print("몇 개의 취미가 있으신가요?:");
				    	 count = sc.nextInt();
			
				    	 String[] hobby = new String[count]; //취미
				    	 
				    	 for (int i = 0 ; i<count ; i++) {
				    		 System.out.print("취미를 입력하세요:");
				    		 hobby[i] = sc.next();		
				 		 }
				    	 Document mydoc = new Document("num", num)
				    				.append("name", name)
				                    .append("field", new Document("college", college).append("department", department))
				    				.append("grade", grade)
				    				.append("gender", gender)
				    				.append("mail", mail)
				    				.append("hobby", Arrays.asList(hobby));
						 mycol.insertOne(mydoc);
				    	 System.out.println("Document inserted successfully");
				         break;
				         
				    case 3:
				    	System.out.print("삭제하고자 하는 학생의 학번을 입력하세요:");
				    	 num = sc.next();
						 if(mycol.find(Filters.eq("num",num)) != null) {
							 //Deleting a document
							 mycol.deleteOne(Filters.eq("num",num)); 
							 System.out.println("Document deleted successfully...");
						 }
						 else{
							 System.out.println("해당 학생이 존재하지 않습니다.");
						 }
						 break;
				    case 4:
				    	System.out.print("수정하고자 하는 학생의 학번을 입력하세요:");
				    	 num = sc.next();
						 if(mycol.find(Filters.eq("num",num)) != null) {
							
							 // Updating a document
							 System.out.print("학년을 입력하세요:");
					    	 grade = sc.next();
					    	 System.out.print("이메일 을 입력하세요:");
					    	 mail = sc.next();
					    	 System.out.print("몇 개의 취미가 있으신가요?:");
					    	 count = sc.nextInt();
				
					    	 hobby = new String[count]; //취미
					    	 
					    	 for (int i = 0 ; i<count ; i++) {
					    		 System.out.print("취미를 입력하세요:");
					    		 hobby[i] = sc.next();		
					 		 }
							 mycol.updateOne(Filters.eq("num", num), Updates.set("grade", grade));
							 mycol.updateOne(Filters.eq("num", num), Updates.set("mail", mail));
							 mycol.updateOne(Filters.eq("num", num), Updates.set("hobby", Arrays.asList(hobby)));
							 
							 System.out.println("Document update successfully...");
						 }
						 else{
							 System.out.println("해당 학생이 존재하지 않습니다.");
						 }
				    	
				         break;
				         
				    case 5:
				    	// Getting the iterable object
						 FindIterable<Document> iterDoc = mycol.find();
						 // Getting the iterator
						 Iterator it = iterDoc.iterator(); // iterator 반환
						 int i = 0;
						 while (it.hasNext()) {
						 System.out.println(it.next());
						 i++;
						 }
						 System.out.println("전체 학생 수 : "+i);
				         break;
				    case 6:
				    	 System.out.println("<검색조건 선택하기>");
						 System.out.println("1) 이름");
						 System.out.println("2) 학년");
						 System.out.println("3) 학과명");
						 System.out.println("4) 취미");
						 System.out.println("----------------------");
						 System.out.print("메뉴 선택 :");
							
						 int  find = sc.nextInt();
						 
						 if(find == 1) {
							 System.out.print("이름을 입력하세요:");
					    	 name = sc.next();
							 // Getting the iterable object
							 FindIterable<Document> iterDoc1 = mycol.find(Filters.eq("name", name));
							 // Getting the iterator
							 Iterator it1 = iterDoc1.iterator(); // iterator 반환
							 while (it1.hasNext()) {
							 System.out.println(it1.next());
							 }
					
						 }else if(find == 2) {
							 System.out.print("학년을 입력하세요:");
							 grade = sc.next();
							 // Getting the iterable object
							 FindIterable<Document> iterDoc1 = mycol.find(Filters.eq("grade", grade));
							 // Getting the iterator
							 Iterator it1 = iterDoc1.iterator(); // iterator 반환
							 while (it1.hasNext()) {
							 System.out.println(it1.next());
							 }
							 
						 }else if(find == 3) {
							 System.out.print("학과명을 입력하세요:");
							 department = sc.next();
							 // Getting the iterable object
							 FindIterable<Document> iterDoc1 = mycol.find(Filters.eq("field.department", department));
							 // Getting the iterator
							 Iterator it1 = iterDoc1.iterator(); // iterator 반환
							 while (it1.hasNext()) {
							 System.out.println(it1.next());
							 }
							 
						 }else {
							
							 System.out.print("몇 개의 취미를 검색하시겠습니까?:");
					    	 count = sc.nextInt();
				
					    	 hobby = new String[count]; //취미
					    	 
					    	 for (int j = 0 ; j<count ; j++) {
					    		 System.out.print("취미를 입력하세요:");
					    		 hobby[j] = sc.next();		
					 		 }
							 FindIterable<Document> iterDoc1 = mycol.find(Filters.eq("hobby", Arrays.asList(hobby)));
							 // Getting the iterator
							 Iterator it1 = iterDoc1.iterator(); // iterator 반환
							 while (it1.hasNext()) {
							 System.out.println(it1.next());
							 }
						 }
				         break;
				    default:
				    	 System.exit(0);
				         break;
						
					}
					
					}
				
			}
			else System.out.print("해당 Collection이 존재하지 않습니다.");
		}
		else System.out.print("해당 Database가 존재하지 않습니다.");
	}
	else System.out.print("1)번의 사용할 DB와 Collection를 선택 후 이용 가능 합니다. ");
	
	
			
	}

	
}
