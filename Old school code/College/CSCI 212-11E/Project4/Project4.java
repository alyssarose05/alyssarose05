/*
 * Included in this JAR file are the rest of the files needed to run this program:
 * EditMenuHandler.java (handles what happens when the user clicks on "Search"), FileMenuHandler.java (handles what happens when the user clicks on "Open" or "Quit"), 
 * TextFileInput.java (reads the text file), Word.java (creates a Word from a String; has the properties of what makes a Word erroneous),
 * WordGUI.java (creates and displays the ArrayLists and TreeMap in a JFrame), Project4Input.txt (a text file that can be used to test this program).
 */

/**
 * This project creates a WordGUI object and takes a user's chosen text file, sorting its Words as unsorted or sorted.
 * The unsorted Words are in an ArrayList, and a TreeMap is used to sort Words.
 * There is also an Edit menu that lets the user search for a Word in the TreeMap.
 * Due Date: December 9th, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 1
 */
public class Project4 {
	
	/**
	 * The main method; creates the WordGUI object and executes the program.
	 * @param args the String array, though no arguments are taken.
	 */
	public static void main(String[] args) {
		WordGUI gui = new WordGUI(); //The WordGUI's constructor directly runs the properties to create the JFrame.
	}
}