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
		
		System.out.println("<�л� ���� ���� ���α׷�>");
		System.out.println("1) ������ ���̽� �� �÷��� �Է�/����");
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
	    case 1:
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
				}
				else System.out.print("�ش� Collection�� �������� �ʽ��ϴ�.");
			}
			else System.out.print("�ش� Database�� �������� �ʽ��ϴ�.");
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
