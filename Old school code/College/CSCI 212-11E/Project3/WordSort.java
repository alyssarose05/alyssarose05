import java.util.Comparator;

/**
 * This class allows Words to be sorted using the Collections() method.
 * Due Date: November 20th, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 1
 */
public class WordSort implements Comparator<Word> {
	
	/**
	 * Sets up how two Words should be sorted in a Word ArrayList.
	 * @param w the Word to be compared to the Word other
	 * @param other the Word to be compared to the Word w
	 * @return -1 if the Word w is less than the Word other, 1 if w is greater than other, or 0 if they are equal
	 */
	public int compare(Word w, Word other) {
		return w.compareTo(other); //Compares two Words using the compareTo method of the Word class.
	}
}