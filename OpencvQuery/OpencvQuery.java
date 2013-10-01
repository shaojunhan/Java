
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OpencvQuery{
	private OpencvQuery(){}
	public OpencvQuery(String file,char start,char split,char end){
		wordsMap=new HashMap<String,String>();
		this.start=start;this.split=split;this.end=end;
		System.out.println(file);
		try{
			FileInputStream is=new FileInputStream(file);
			Init(is);
		}catch(FileNotFoundException e)
		{
			System.out.println(e);
			return;
		}
	}
	
	public String Query(String query){
		String result=wordsMap.get(query.toLowerCase());
		if(result!=null)
			return result;
		return new String("未查询到!");
	}
	private void Init(InputStream is){
		Scanner scan=new Scanner(is);
		String line;
		while(scan.hasNextLine()){
			line=scan.nextLine();
			int s=line.indexOf(start);
			int e=line.indexOf(split);
			
			wordsMap.put(line.substring(s+1,e).toLowerCase(),line.substring(e+1,line.indexOf(end)).toLowerCase());
		}	
	}

	private Map<String,String> wordsMap;
	private char start,split,end;
}
