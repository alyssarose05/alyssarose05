/**
 * A class that creates a Word as a String.
 * The constructor now has a way to detect erroneous Words and can use an IllegalWordException to throw an error message to the user.
 * Due Date: November 20th, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 2
 */
public class Word {
	private String s; 
	
	/** 
	 * The constructor; takes a String to be converted into a Word.
	 * This also uses the IllegalWordException that occurs if the Word does not have only letters, making it an erroneous Word.
	 * @param w the String to be converted into a word
	 */
	public Word(String w) {
		if (!(w.matches("[a-zA-Z]+"))) { 
			throw new IllegalWordException("Erroneous Word: " + w); //If the Word does not have just letters, it is erroneous.
		}
		s = w;
	}
	
	/** 
	 * Makes the word be printed as a String.
	 */
	public String toString() {
		return s; //As Words should be treated like Strings
	}
	
	/**
	 * Compares two Words to each other.
	 * @param other the word to be compared to another word
	 * @return -1 if the Word w is less than the other Word, 1 if w is greater than other, or 0 if they are equal
	 */
	public int compareTo(Word other) {
		if (s.compareTo(other.toString()) < 0) {
			return -1;
		}
		else if (s.compareTo(other.toString()) > 0) {
			return 1;
		}
		return 0; //If the Words are neither less than or greater than each other, then they are equal.
	}
}
