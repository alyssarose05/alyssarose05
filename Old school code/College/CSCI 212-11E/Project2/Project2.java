import java.util.StringTokenizer;

/* 
 * Included in this JAR file are the rest of the files that are needed to run this program: 
 * SortedWordList.java (WordList with sorted Words), TextFileInput.java (reads the text file), 
 * UnsortedWordList.java (WordList with unsorted Words), Word.java (uses a String to create a Word), 
 * WordGUI.java (contains the properties needed for the JFrame), WordList.java (creates a list of Words), 
 * WordNode.java (used to add Words to a WordList), and Project2Input.txt (a text file that can be used to run this program).
 */

/**
 * This program is designed to create two WordLists containing Words: one unsorted and one sorted, and show them in a WordGUI window with each column (two in total, with one row) showing one list.
 * Takes a text file containing a paragraph (with only valid words) as an argument.
 * Submitted as a JAR file. 
 * Due Date: November 2nd, 2020.
 * @author Alyssa Ayala, lab section E.
 * @version 1
 */
public class Project2 {
	
	/**
	 * The main method; takes the file and adds the words to an UnsortedWordList and a SortedWordList, and then creates and displays a WordGUI object.
	 * @param args the String array, takes the file as an argument
	 */
	public static void main(String[] args) {
		String[] myArray = new String[100];
		int myArraySize = inputFromFile(args[0], myArray); //We need a definite array size before we can add Words to the UnsortedWordList and the SortedWordList.
		
		TextFileInput tfi = new TextFileInput(args[0]);
		UnsortedWordList uwl = new UnsortedWordList();
		SortedWordList swl = new SortedWordList();
		
		for (int i = 0; i < myArraySize; i++) {
			String line = tfi.readLine();
			StringTokenizer tokens = new StringTokenizer(line);
			while (tokens.hasMoreTokens()) {
				String word = tokens.nextToken();
				Word w = new Word(word);
				uwl.add(w);
				swl.add(w);
			}
		}
		
		WordGUI gui = new WordGUI(uwl, swl); //This is so we can directly create a WordGUI from the constructor.
	}
	
	/**
	 * Gets the length of the text file to be used in the for-loop. 
	 * @param filename the file containing the paragraph
	 * @param words the String array to be filled
	 * @return the length of the file
	 */
	public static int inputFromFile(String filename, String[] words) {
		TextFileInput in = new TextFileInput(filename);
		int lengthFilled = 0;
		String line = in.readLine();
		
		while (lengthFilled < words.length && line != null) { //We need to fill the words String array to find the length.
			words[lengthFilled++] = line; 
			line = in.readLine();
		}
		
		if (line != null) { //The line can't be null.
			System.out.println("File contains too many words.");
			System.out.println("This program can process only " + words.length + " words.");
			System.exit(1);
		}
		
		in.close(); 
		return lengthFilled;
	}
}