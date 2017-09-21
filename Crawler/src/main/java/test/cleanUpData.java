package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.common.base.CharMatcher;

public class cleanUpData {
	
	private static final CharMatcher ALNUM =
			  CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z'))
			  .or(CharMatcher.inRange('0', '9')).or(CharMatcher.ASCII).precomputed();
			// ...
			
	public static void cleanupData() throws IOException {
		String folder = "/Users/praveenadivi/Documents/document/res2016";
		String endFolder = "/Users/praveenadivi/Documents/document/Cleaned-up-data/data-all.txt";
		File file = new File(folder);
		File[] listOfFiles = file.listFiles();
		File f = new File(endFolder);
		f.createNewFile();
		FileWriter writer = new FileWriter(f);

		    for (int i = 0; i < listOfFiles.length; i++) {
		    	if(listOfFiles[i].equals(".DS_Store")) {
		    		continue;
		    	}
		    	
		    	
		      if (listOfFiles[i] != null && listOfFiles[i].isFile()) {
		    	  BufferedReader br = new BufferedReader(new FileReader(listOfFiles[i]));
		  		String line = null;
		  		StringBuffer strBuffer = new StringBuffer("");
		  		while ((line = br.readLine()) != null) {
		  			strBuffer.append(line);
		  			
		  		}
		  		
		  		Document doc = (Document) Jsoup.parse(strBuffer.toString());
		  		String dat = doc.body().text();
		  		dat = new String(Charset.forName("UTF-8").encode(dat).array());
		  		if (dat.indexOf("FAMILY LAW") >= 0) {
		  			String alphaAndDigits = ALNUM.retainFrom(dat.substring(dat.indexOf("FAMILY LAW"), dat.length()));
		  		writer.write( alphaAndDigits + "\n"+ "\n");
		  		}
		  		
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }
		
	}
	
	public static void main(String args[]) {
		try {
			cleanupData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
