import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Parser {
	public static void main(String args[]){
            String str="f3.txt";

		HashMap<String, String> database = fileToHashMap(str);
		msriesa sa = new msriesa();
		
		sa.start(database);	
		
	}


	public static HashMap<String,String> fileToHashMap(String filename){
		if(!filename.contains(".txt")) filename = filename+".txt";
		HashMap<String, String> hash_map = new HashMap<String, String>();
		String id="";
		StringBuilder str=new StringBuilder();
		try
		{
			BufferedReader text = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = text.readLine()) != null)
			{
				line = line.trim();
				if(line.startsWith(">", 0)){
					if(id!="" && str.length()!=0){
						hash_map.put(id, str.toString());
						id="";
						str.setLength(0);
					}
					id = line.split(" ")[0].substring(5);
				}
				else{
					str.append(line);
				}

			}
			hash_map.put(id, str.toString());
			text.close();
		}
		catch (Exception e)
		{
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
		}
		return hash_map;
	}
}
