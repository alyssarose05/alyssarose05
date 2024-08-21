/*
 * Included in this JAR file are the rest of the files needed to run this program:
 * FileMenuHandler.java (handles what happens depends on the menu option the user chooses), IllegalWordException.java (displays an error message when a Word is erroneous,
 * TextFileInput.java (reads the text file), Word.java (creates a Word from a String; has the properties of what makes a Word erroneous), 
 * WordGUI.java (creates and displays the ArrayLists in a JFrame), WordSort.java (the basis for sorting two Words in a Word ArrayList using the Collections() method).
 * Project3Input.txt (a text file that can be used to test this program).
 */

/**
 * This project is a JFrame with a menu that lets you access your computer's file directory to choose a valid text file with Words in it.
 * Once that's done, three ArrayLists that are unsorted, sorted, and have erroneous Words will be displayed to the user.
 * Due Date: November 20th, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 1
 */
public class Project3 {
	
	/*
	 * The main method; creates the WordGUI object and executes the program.
	 * @param args the String array, though no arguments are taken.
	 */
	public static void main(String[] args) {
		WordGUI gui = new WordGUI(); //The WordGUI's constructor directly runs the properties to create the JFrame.
	}
}