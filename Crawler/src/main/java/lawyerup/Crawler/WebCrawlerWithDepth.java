package lawyerup.Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class WebCrawlerWithDepth {
    private static final int MAX_DEPTH = 2;
    private static HashSet<String> links = new HashSet<String>();
    
    private static String data = "/Users/praveenadivi/Documents/document/Test";
    private static long count = 0;
    public static void getPageLinks(String URL, int depth) {
        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            System.out.println( URL);
//            File file = new File(data + "/" + (count ++) + ".txt");
//            try {
//				file.createNewFile();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//            try {
//				FileWriter fw = new FileWriter(file);
//				fw.write(URL);
//				fw.flush();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
            try {
                links.add(URL);
                //Test.getData(URL, count ++ );
                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");

                depth++;
                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"), depth);
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        getPageLinks("http://www.austlii.edu.au/au/cases/cth/family_ct/2016/", 0);
        
        long count = 0;
        
    }
}