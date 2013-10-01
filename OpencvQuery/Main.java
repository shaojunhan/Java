
import java.util.Scanner;

public class Main{
	
	static String file="opencv.txt";

	public static void main(String arg[]){

		OpencvQuery cvQuery=new OpencvQuery(file,'.',':',';');
		String query="q";
		Scanner scan=new Scanner(System.in);
		do
		{
			System.out.print(">");
			query=scan.next();
			if(query.equals("q"))
				break;
			String result=cvQuery.Query(query);
			System.out.println(result);	
		}while(true);
		System.out.println("exit...");
	}
}
