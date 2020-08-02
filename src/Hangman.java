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
		
		return validate(secret, hidden);
		
	}
	
	public static StringBuffer validate(String secret, StringBuffer hidden) {
		if(secret.indexOf("RT") == 0 || secret.indexOf("@") == 0) {
			System.out.println(hidden);
			System.out.println("my pocket my pockets");
			hidden.replace(0, secret.indexOf(':'), secret.substring(0, secret.indexOf(':')));
		}
		int hti = 0;
		while((hti = secret.indexOf("https://", hti)) > 0 || (hti = secret.indexOf("#", hti)) > 0) {
			int endInd;
			if((endInd = secret.indexOf(' ', hti)) > 0) {
				hidden.replace(hti, endInd, secret.substring(hti, endInd));
				hti = endInd;
			} else if((endInd = secret.indexOf('\n', hti)) > 0) {
				hidden.replace(hti, endInd, secret.substring(hti, endInd));
				hti = endInd;
			} else {
				System.out.println("seeing if the code reaches here");
				hidden.replace(hti, hidden.length(), secret.substring(hti,secret.length()));
				hti = hidden.length();
			}
		}
		
		return hidden;
	}// I need to test to see the output
	
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
		
		
		Scanner scan = new Scanner(System.in); Scanner intscan = new Scanner(System.in);
		String [] wordsAndPhrases = {"I love Computer Science!", "Isaiah is awesome!", "Microsoft", "Google", "Amazon", "Japan is Beautiful!", "Air bnb", "New York", "Los Angelas", "Isaiah will live joyfully in Tokyo soon"};
		int apiInd = 0;
		String word; 
		
		System.out.println("Welcome to Hangman!");
		System.out.println("Which method of retrieving guessing phrases do you prefer?\n"
				+ "1 - Phrases from Twitter -- Uses Twiter API to retrieve guessing phrases based off of your topic choice\n"
				+ "2 - Phrases from a pre-made list");
		
		int choice = intscan.nextInt();
		if(choice == 1) {
			System.out.println("Which topic would you like your phrase to be about?");
			int topic = intscan.nextInt();
			wordsAndPhrases = APIRequest.apiRequest(topic);
			word = wordsAndPhrases[apiInd++];
		} else {
			word = wordsAndPhrases[(int) (Math.random()*wordsAndPhrases.length)];
		}
		
		boolean done = false;
		StringBuffer hidden = hideSecret(word);//this will be displayed for the player
		String before; 
		
		
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
				done = true;//this marks the game as a win
			}
			if(guesses == 0) {
				break;//the player lost
			}
		
		}while (!done);
		
		if(done) {
			System.out.println("Congratulations! You Won!!!");
		}else {
			System.out.println("You ran out of guesses. Better luck next time!");
		}
		done = false;
		
		intscan.close();
		scan.close();
		System.out.println("The hidden word was \""+word+"\"");		
	}

}
