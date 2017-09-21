package lawyerup.Crawler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final String url = "http://citoyens.soquij.qc.ca/index.php/?type=listemc&recher=0_divorce+pension+alimentaire_0_20170501_20170502_pert";
    public static void main( String[] args ) throws IOException
    {
    	URL obj = new URL(url);
    	HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
    	conn.setInstanceFollowRedirects(true);  //you still need to handle redirect manully.
    	HttpURLConnection.setFollowRedirects(true);
    	
    	
    }
}
