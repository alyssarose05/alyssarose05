/** 
 * A class containing the unsorted version of a WordList.
 * Due Date: November 2nd, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 1
 */
public class UnsortedWordList extends WordList {
	
	/**
	 * A no-argument constructor with the properties of the abstract WordList class..
	 */
	public UnsortedWordList() {
		super();
	}
	
	/** 
	 * Uses the abstract WordList class's append method to add a Word to the last line of the list.
	 * @param w the word to be added to the UnsortedWordList
	 */
	public void add(Word w) {
		super.append(w); //We need the append method of the WordList class to add each Word to the last line.
	}
}