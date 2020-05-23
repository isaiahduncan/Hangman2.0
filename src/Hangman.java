//import java.io.IOException;
//import java.util.concurrent.Executors;
import java.util.Scanner;
import java.io.IOException;

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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String [] wordsAndPhrases = {"I love Computer Science!", "Isaiah is awesome!", "Google", "Amazon", "Japan is Beautiful!", "Air bnb", "New York", "Los Angelas", "Isaiah will live joyfully in Tokyo soon"};
		
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
		
		System.out.println("The hidden word was \""+word+"\"");		
	}

}
