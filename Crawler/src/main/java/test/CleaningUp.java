package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import lawyerup.Crawler.Crawler;
import lawyerup.Crawler.Test;

public class CleaningUp {
	public static void main(String args[]) throws IOException {
		String file = "/Users/praveenadivi/Documents/Data1.txt";
		
		FileReader fileReader = new FileReader(file);
		BufferedReader buffer = new BufferedReader(fileReader);
		String line = "";
		
		while ((line = buffer.readLine()) != null) {
			//Test.getData(Crawler.URL.replace(Crawler.ID, line), line.substring(0, line.indexOf("&")));

		}
	}
}
