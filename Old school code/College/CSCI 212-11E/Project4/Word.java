/**
 * A class that creates a Word as a String.
 * This class now has a comparable implementation. 
 * Due Date: December 9th, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 3
 */
public class Word implements Comparable<Word> {
	private String s; 
	
	/** 
	 * The constructor; takes a String to be converted into a Word.
	 * @param w the String to be converted into a word
	 */
	public Word(String w) {
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
		if (s.compareTo(other.toString()) < 0) 
			return -1;
		else if (s.compareTo(other.toString()) > 0) 
			return 1;
		return 0; //If the Words are neither less than or greater than each other, then they are equal.
	}
}
