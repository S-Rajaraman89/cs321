import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class GradeServer implements Runnable{
	
	private static StringBuffer buffer = new StringBuffer();
	private static BufferedWriter gradeFile = null;
			

	public void run(){
		while (true) {
		    try {
		        Thread.sleep(6 * 1000);
		        flush();
		    }
		    catch (Exception ie) {
		        ie.printStackTrace();
		    }
		}
		
	}
	
	public static void record(String item){
		//System.err.println("adding " + item + " to buffer");
		buffer.append(item);
	}
	
	private static void flush()throws IOException{
		gradeFile = new BufferedWriter(new FileWriter("grades.txt",true));
		//System.out.println("file flushed with " + buffer.toString());
		gradeFile.append(buffer.toString());
		buffer = new StringBuffer();
		gradeFile.close();
		log();
	}
	
	private static void log(){
		System.err.println("This is the mock logging");
	}
	
	public static ArrayList<String> lookup(String id){
		ArrayList<String> result = new ArrayList<String>();
		try {
			BufferedReader file = new BufferedReader(new FileReader("grades.txt"));
			String line = null;
			//HashMap<String,String> newestData = new HashMap<String,String>();
            ArrayList<String> data = new ArrayList<String>();
			while ((line=file.readLine())!= null){
                //System.out.println("Grade server line "+line);
                //String itemId = line.substring(0,line.indexOf(","));
				//newestData.put(itemId, line);
                data.add(line);
			}
			file.close();
			Iterator<String> i = data.iterator();
			while (i.hasNext()){
				String key = i.next();
				if (key.contains(id)){
                    //System.out.println("adding this to return list "+key);
                    result.add(key);
                }
			}
		}catch (Exception e){
			result.add(e.getMessage());
		}
		return result;
	}
}
