import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class APIRequest {
	public static String qFilter(int filter) {
		switch (filter) { 
			case 1: //anime
				return "q=naruto+OR+anime+OR+%22demon+slayer%22+OR+%22Rising+of+the+Shield+Hero%22";
			case 2: //hip hop
				return "q=%22J.+Cole%22+OR+Hip-Hop+OR+%22Kendrick+Lamar%22";
			case 3: //
				System.out.println("No Filter. This will not work"); return "";
			case 4: //
				System.out.println("No Filter. This will not work"); return "";
			default://no filter
				System.out.println("No Filter. This will not work"); return "";
		}
	}

	public static String [] apiRequest(int topic) throws IOException {
		String [] tweetTexts = new String[100];
		
		String q = qFilter(topic);
		
		//this is setting the url for the api request
		URL url = new URL("https://api.twitter.com/1.1/search/tweets.json?"+q+"&tweet_mode=extended&lang=en&count=100"); 
		String readLine = null;
		
		//the connection variable is the means of making a request to the api
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");//setting request method
		//the following line sets the bearer token which gives the application access to the twitter api
		connection.setRequestProperty("Authorization", "Bearer "
				+ "AAAAAAAAAAAAAAAAAAAAAJ%2Fh%2FgAAAAAAHdovpNOGFkwW%2FP6laz0bPmPFNPA%3D17U4WpGQSbSIORrBGCYuA1EiZPEYUiPzD18lx1FoUb4XZlnTI6");
		
		//connection.setRequestProperty("userId", "a1bcdef");

		int responseCode = connection.getResponseCode();
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(connection.getInputStream()));
	        StringBuffer response = new StringBuffer();
	        while ((readLine = in .readLine()) != null) {
	            response.append(readLine);
	        }
	        in .close();
	        // print result
	        
	        JSONParser parser = new JSONParser();
	        JSONArray twitterStatuses;
	        JSONObject twitterResponse;// = null;
	        
	        try {
	        	Object obj = parser.parse(response.toString());
	        	twitterResponse = (JSONObject) obj;
	        	
	        	twitterStatuses = (JSONArray) twitterResponse.get("statuses");	        	
	        } catch (ParseException e) {
	        	System.out.println("bad parsing");
	        	return null;
	        }
	        
	        
	        

	        for(int i = 0; i < twitterStatuses.size(); i++) {
		        JSONObject tweet = (JSONObject) twitterStatuses.get(i);
		        String tweetText = (String) tweet.get("full_text");
		        tweetTexts[i] = tweetText;
		        System.out.println("Tweet Text:\n"+tweetText+"\n\n");
	        }
	        
	        //System.out.println("JSON String Result " + response.toString()+"\n\n");
	        
	        //System.out.println("Twitter Response:\n"+twitterResponse.toString()+"\n\n");
	        
	        
	        //GetAndPost.POSTRequest(response.toString());
	    } else {
	        System.out.println("GET NOT WORKING");
	    }
		
		return tweetTexts;
	}
	
	public static void main(String [] args) throws IOException {
		apiRequest(1);
	}
	
}
