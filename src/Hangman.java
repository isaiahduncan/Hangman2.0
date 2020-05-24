import java.util.concurrent.Executors;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpRequest;

/**
 * 
 */

/**
 * @author isaiahduncan
 *
 */
public class Hangman {

	/**
	 * @param args
	 */
	private static int guesses = 6;// num guesses
	public static void consoleDraw() {
		
		switch (guesses) {
			case 6 :
				System.out.println("      __    ");
				System.out.println("     |  |   ");
				System.out.println("     |      ");
				System.out.println("     |      ");
				System.out.println("     |      ");
				System.out.println("  ___|___   ");
				break;
			case 5 : 
				System.out.println("      __    ");
				System.out.println("     |  |   ");
				System.out.println("     |  o   ");
				System.out.println("     |      ");
				System.out.println("     |      ");
				System.out.println("  ___|___   ");
				break;
			case 4 :
				System.out.println("      __    ");
				System.out.println("     |  |   ");
				System.out.println("     |  o   ");
				System.out.println("     |  |   ");
				System.out.println("     |      ");
				System.out.println("  ___|___   ");
				break;
			case 3 :
				System.out.println("      __    ");
				System.out.println("     |  |   ");
				System.out.println("     |  o   ");
				System.out.println("     | /|   ");
				System.out.println("     |      ");
				System.out.println("  ___|___   ");
				break;
			case 2 : 
				System.out.println("      __    ");
				System.out.println("     |  |   ");
				System.out.println("     |  o   ");
				System.out.println("     | /|\\  ");
				System.out.println("     |      ");
				System.out.println("  ___|___   ");
				break;
			case 1 : 
				System.out.println("      __    ");
				System.out.println("     |  |   ");
				System.out.println("     |  o   ");
				System.out.println("     | /|\\  ");
				System.out.println("     | /    ");
				System.out.println("  ___|___   ");
				break;
			case 0 :
				System.out.println("      __    ");
				System.out.println("     |  |   ");
				System.out.println("     |  o   ");
				System.out.println("     | /|\\  ");
				System.out.println("     | / \\  ");
				System.out.println("  ___|___   ");
				break;
		}

	}
	
	public static StringBuffer hideSecret(String secret) {
		StringBuffer hidden = new StringBuffer(secret);
		
		for(int i = 0; i < hidden.length(); i++) {
			if(Character.isLetter(hidden.charAt(i))) {
				hidden.deleteCharAt(i);
				hidden.insert(i, '_');
			}
		}
		
		return hidden;
		
	}
	
	public static StringBuffer makeGuess(String word, StringBuffer hidden, Scanner scan) {
		String guess = scan.nextLine();
		
		if(guess.equals(word)) {
			return new StringBuffer(word);
		}
		
		int pos = 0;
		while((pos = word.toLowerCase().indexOf(guess.toLowerCase(), pos)) != -1) {//moving pos the the next instance of the guess
			
			hidden.replace(pos, pos+guess.length(), word.substring(pos, pos+guess.length()));//doing replacement in the hidden string
			
			pos += guess.length();//advancing pos so that we don't detect the same instance of the guess
		}
		
		return hidden;
	}
	
	/*public static String apiRequest() throws IOException {
		/*HttpRequest.Builder reqBuilder = HttpRequest.newBuilder().uri(URI.create(
				"https://api.twitter.com/1.1/search/tweets.json?q=naruto+anime+%22demon+slayer%22&count=10"));
		reqBuilder.header("Authorization","Bearer"
				+ "AAAAAAAAAAAAAAAAAAAAAJ%2Fh%2FgAAAAAAHdovpNOGFkwW%2FP6laz0bPmPFNPA%3D17U4WpGQSbSIORrBGCYuA1EiZPEYUiPzD18lx1FoUb4XZlnTI6");

		reqBuilder.method("GET", null);*//*
		
		
		URL url = new URL("https://api.twitter.com/1.1/search/tweets.json?q=naruto+anime+%22demon+slayer%22&count=10");
		String readLine = null;
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		
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
	        } in .close();
	        // print result
	        
	        
	        System.out.println("JSON String Result " + response.toString());
	        //GetAndPost.POSTRequest(response.toString());
	    } else {
	        System.out.println("GET NOT WORKED");
	    }
		
		return null;
	}*/
	
	public static void main(String[] args) throws IOException {
		APIRequest.apiRequest();
		
		/*String [] wordsAndPhrases = {"I love Computer Science!", "Isaiah is awesome!", "Microsoft", "Google", "Amazon", "Japan is Beautiful!", "Air bnb", "New York", "Los Angelas", "Isaiah will live joyfully in Tokyo soon"};
		
		String word = wordsAndPhrases[(int) (Math.random()*wordsAndPhrases.length)];
		System.out.println("Welcome to Hangman!");
		
		boolean done = false;
		StringBuffer hidden = hideSecret(word);//this will be displayed for the player
		String before; 
		
		Scanner scan = new Scanner(System.in);
		
		do {
		before = hidden.toString(); 
		System.out.println("You have " + guesses + " remaining guesses");
		
		consoleDraw();//showing the player the state of the hangman
		
		System.out.println(hidden);//showing the hidden word which reveals more with more correct guesses
		System.out.println("\nMake a Guess!");//prompting for a guess
		
		makeGuess(word, hidden, scan);
		
		if(before.equals(hidden.toString())) {
			System.out.println("You Guessed Incorrectly!");
			guesses--;
		}
		if(hidden.toString().equals(word)) {
			done = true;
		}
		if(guesses == 0) {
			break;
		}
		
		}while (!done);
		
		if(done) {
			System.out.println("Congratulations! You Won!!!");
		}else {
			System.out.println("You ran out of guesses. Better luck next time!");
		}
		
		System.out.println("The hidden word was \""+word+"\"");		*/
	}

}
