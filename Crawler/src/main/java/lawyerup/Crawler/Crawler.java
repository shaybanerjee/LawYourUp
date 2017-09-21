package lawyerup.Crawler;

import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class Crawler {
	private static final int MAX_DEPTH = 2;
	public static final String ID = "<ID>";
	//public static final String URL = "http://citoyens.soquij.qc.ca/php/decision.php?ID=<ID>&page=1";
	//private static final String URL = "http://citoyens.soquij.qc.ca/index.php/?type=listemc&recher=0_divorce+pension+alimentaire_0_20170501_20170502_pert";
    //private static final String URL = "http://www.montclair.edu";
	private HashSet<String> links;
	
	private static final String URL = "http://www.austlii.edu.au/au/cases/cth/family_ct/2017/";

    public Crawler() {
        links = new HashSet<String>();
    }

    public void getDocumentData() {
    	
    }
    
    public void getPageLinks(String URL, int depth) {
        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
            System.out.println(">> Depth: " + depth + " [" + URL + "]");
            try {
                links.add(URL);

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
        new Crawler().getPageLinks(URL, 0);
    }
}
