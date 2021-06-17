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
	
	System.out.println("<�л� ���� ���� ���α׷�>");
	System.out.println("1) ������ ���̽� �� �÷��� �Է�/����");
	System.out.println("2) �л� ���� ����");
	System.out.println("3) �л� ���� ����");
	System.out.println("4) �л� ���� ����");
	System.out.println("5) �л� ���� �˻�(��ü)");
	System.out.println("6) �л� ���� �˻�(����)");
	System.out.println("7) ����");
	System.out.println("----------------------");
	System.out.print("�޴� ���� :");
	
	int frist = sc.nextInt();
	
	if (frist == 1) {
		System.out.print("����� Database���� �Է��ϼ���:");
		String database = sc.next();
		if(database.equals("school")) {
			MongoDatabase mydb = myclient.getDatabase("school"); 	
			System.out.println("Database selected successfully");
			
			System.out.print("����� Collection���� �Է��ϼ���:");
			String collection = sc.next();
			
			if(collection.equals("student")) {
				MongoCollection<Document> mycol = mydb.getCollection("student");
				System.out.println("Collection selected successfully");
				
				while(true) {
					System.out.println("<�л� ���� ���� ���α׷�>");
					System.out.println("2) �л� ���� ����");
					System.out.println("3) �л� ���� ����");
					System.out.println("4) �л� ���� ����");
					System.out.println("5) �л� ���� �˻�(��ü)");
					System.out.println("6) �л� ���� �˻�(����)");
					System.out.println("7) ����");
					System.out.println("----------------------");
					System.out.print("�޴� ���� : ");
					
					int  ch = sc.nextInt();
					
					switch(ch) {
					
				    case 2:		    
				    	 String num, name, college, department, grade, gender, mail;
				    	// �й�, �̸�, �Ҽ�(�ܰ�����, �а���), �г�, ����, �̸��� 
				    	 int count; //��� ���� 
				    	 
				    	 System.out.print("�й��� �Է��ϼ���:");
				    	 num = sc.next();
				    	 System.out.print("�̸��� �Է��ϼ���:");
				    	 name = sc.next();
				    	 System.out.print("�ܰ������� �Է��ϼ���:");
				    	 college = sc.next();
				         System.out.print("�а����� �Է��ϼ���:");
				    	 department = sc.next();
				    	 System.out.print("�г��� �Է��ϼ���:");
				    	 grade = sc.next();
				    	 System.out.print("������ �Է��ϼ���:");
				    	 gender = sc.next();
				    	 System.out.print("�̸��� �� �Է��ϼ���:");
				    	 mail = sc.next();
				    	 System.out.print("�� ���� ��̰� �����Ű���?:");
				    	 count = sc.nextInt();
			
				    	 String[] hobby = new String[count]; //���
				    	 
				    	 for (int i = 0 ; i<count ; i++) {
				    		 System.out.print("��̸� �Է��ϼ���:");
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
				    	System.out.print("�����ϰ��� �ϴ� �л��� �й��� �Է��ϼ���:");
				    	 num = sc.next();
						 if(mycol.find(Filters.eq("num",num)) != null) {
							 //Deleting a document
							 mycol.deleteOne(Filters.eq("num",num)); 
							 System.out.println("Document deleted successfully...");
						 }
						 else{
							 System.out.println("�ش� �л��� �������� �ʽ��ϴ�.");
						 }
						 break;
				    case 4:
				    	System.out.print("�����ϰ��� �ϴ� �л��� �й��� �Է��ϼ���:");
				    	 num = sc.next();
						 if(mycol.find(Filters.eq("num",num)) != null) {
							
							 // Updating a document
							 System.out.print("�г��� �Է��ϼ���:");
					    	 grade = sc.next();
					    	 System.out.print("�̸��� �� �Է��ϼ���:");
					    	 mail = sc.next();
					    	 System.out.print("�� ���� ��̰� �����Ű���?:");
					    	 count = sc.nextInt();
				
					    	 hobby = new String[count]; //���
					    	 
					    	 for (int i = 0 ; i<count ; i++) {
					    		 System.out.print("��̸� �Է��ϼ���:");
					    		 hobby[i] = sc.next();		
					 		 }
							 mycol.updateOne(Filters.eq("num", num), Updates.set("grade", grade));
							 mycol.updateOne(Filters.eq("num", num), Updates.set("mail", mail));
							 mycol.updateOne(Filters.eq("num", num), Updates.set("hobby", Arrays.asList(hobby)));
							 
							 System.out.println("Document update successfully...");
						 }
						 else{
							 System.out.println("�ش� �л��� �������� �ʽ��ϴ�.");
						 }
				    	
				         break;
				         
				    case 5:
				    	// Getting the iterable object
						 FindIterable<Document> iterDoc = mycol.find();
						 // Getting the iterator
						 Iterator it = iterDoc.iterator(); // iterator ��ȯ
						 int i = 0;
						 while (it.hasNext()) {
						 System.out.println(it.next());
						 i++;
						 }
						 System.out.println("��ü �л� �� : "+i);
				         break;
				    case 6:
				    	 System.out.println("<�˻����� �����ϱ�>");
						 System.out.println("1) �̸�");
						 System.out.println("2) �г�");
						 System.out.println("3) �а���");
						 System.out.println("4) ���");
						 System.out.println("----------------------");
						 System.out.print("�޴� ���� :");
							
						 int  find = sc.nextInt();
						 
						 if(find == 1) {
							 System.out.print("�̸��� �Է��ϼ���:");
					    	 name = sc.next();
							 // Getting the iterable object
							 FindIterable<Document> iterDoc1 = mycol.find(Filters.eq("name", name));
							 // Getting the iterator
							 Iterator it1 = iterDoc1.iterator(); // iterator ��ȯ
							 while (it1.hasNext()) {
							 System.out.println(it1.next());
							 }
					
						 }else if(find == 2) {
							 System.out.print("�г��� �Է��ϼ���:");
							 grade = sc.next();
							 // Getting the iterable object
							 FindIterable<Document> iterDoc1 = mycol.find(Filters.eq("grade", grade));
							 // Getting the iterator
							 Iterator it1 = iterDoc1.iterator(); // iterator ��ȯ
							 while (it1.hasNext()) {
							 System.out.println(it1.next());
							 }
							 
						 }else if(find == 3) {
							 System.out.print("�а����� �Է��ϼ���:");
							 department = sc.next();
							 // Getting the iterable object
							 FindIterable<Document> iterDoc1 = mycol.find(Filters.eq("field.department", department));
							 // Getting the iterator
							 Iterator it1 = iterDoc1.iterator(); // iterator ��ȯ
							 while (it1.hasNext()) {
							 System.out.println(it1.next());
							 }
							 
						 }else {
							
							 System.out.print("�� ���� ��̸� �˻��Ͻðڽ��ϱ�?:");
					    	 count = sc.nextInt();
				
					    	 hobby = new String[count]; //���
					    	 
					    	 for (int j = 0 ; j<count ; j++) {
					    		 System.out.print("��̸� �Է��ϼ���:");
					    		 hobby[j] = sc.next();		
					 		 }
							 FindIterable<Document> iterDoc1 = mycol.find(Filters.eq("hobby", Arrays.asList(hobby)));
							 // Getting the iterator
							 Iterator it1 = iterDoc1.iterator(); // iterator ��ȯ
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
			else System.out.print("�ش� Collection�� �������� �ʽ��ϴ�.");
		}
		else System.out.print("�ش� Database�� �������� �ʽ��ϴ�.");
	}
	else System.out.print("1)���� ����� DB�� Collection�� ���� �� �̿� ���� �մϴ�. ");
	
	
			
	}

	
}
