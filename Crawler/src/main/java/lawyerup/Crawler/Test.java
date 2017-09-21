package lawyerup.Crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {
	
	static String elecArray[] = new String[7];
	
	static {
		elecArray[1] = "9B8D1B119ECC22585742007DDC9E17C4A05B38D88742C4F35B028AEE71123B90&page=1";
		elecArray[2] = "5AB21E54902FEB4BD2E3F80E15869B17&page=1";
		elecArray[3] = "9F0A023BBBC959214F8D0D85CB1B9436&page=1";
		elecArray[4] = "1B5B8519574FAA6FC2C79383CDFDFC1B&page=1";
		elecArray[5] = "1A600230F42507DA6FF8CE222C0F77C8&page=1";
		elecArray[6] = "E1C21D4432ED249C672075D5194463C4&page=1";
	}
	
	public static void getData(String url, long count) {
		  try {

				Thread.sleep(100);

				URL obj = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
				conn.setReadTimeout(5000);
				conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
				conn.addRequestProperty("User-Agent", "Mozilla");
				conn.addRequestProperty("Referer", "google.com");
				conn.addRequestProperty("cookie", "PHPSESSID=204522f951c21f2aba56694a259eb4cf; _adstanding_local_id=87e2cbaeadfe737b8fc1511442c29bd9; __utma=164580960.1894420684.1497728770.1497728770.1497731468.2; __utmb=164580960.1.10.1497731468; __utmc=164580960; __utmz=164580960.1497731468.2.2.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); _ga=GA1.3.1894420684.1497728770; _gid=GA1.3.1666862386.1497728770; __utma=15341348.1894420684.1497728770.1497731274.1497731274.1; __utmb=15341348.4.10.1497731274; __utmc=15341348; __utmz=15341348.1497731274.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); _ga=GA1.4.1894420684.1497728770; _gid=GA1.4.1666862386.1497728770; _gu=44d07979-7d4b-423f-917f-21c8309f45c6; _gs=2.s(src=http://citoyens.soquij.qc.ca/php/resultat.php?liste=101228356)c[Desktop,Chrome,232:793:75956:,Mac OS,70.52.210.227]; _gw=2.u[~0,~0,~0,~0,~0]v[~ev0y9,~4,~0]a()");

				System.out.println("Request URL ... " + url);

				boolean redirect = false;

				// normally, 3xx is redirect
				int status = conn.getResponseCode();
				if (status != HttpURLConnection.HTTP_OK) {
					if (status == HttpURLConnection.HTTP_MOVED_TEMP
						|| status == HttpURLConnection.HTTP_MOVED_PERM
							|| status == HttpURLConnection.HTTP_SEE_OTHER)
					redirect = true;
				}

				System.out.println("Response Code ... " + status);

				if (redirect) {

					// get redirect url from "location" header field
					String newUrl = conn.getHeaderField("Location");

					// get the cookie if need, for login
					String cookies = conn.getHeaderField("Set-Cookie");

					// open the new connnection again
					conn = (HttpURLConnection) new URL(newUrl).openConnection();
					conn.setRequestProperty("Cookie", cookies);
					conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
					conn.addRequestProperty("User-Agent", "Mozilla");
					conn.addRequestProperty("Referer", "google.com");

					System.out.println("Redirect to URL : " + newUrl);

				}

				BufferedReader in = new BufferedReader(
			                              new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer html = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					html.append(inputLine);
				}
				in.close();

				File file = new File("/Users/praveenadivi/Documents/document/Test/" + count + ".txt");
				FileWriter fw = new FileWriter(file);
				fw.write(html.toString());
				fw.flush();
				System.out.println("URL Content... \n" + html.toString());
				System.out.println("Done");

			    } catch (Exception e) {
				e.printStackTrace();
			    }

	}
}
