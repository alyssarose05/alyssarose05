import javax.swing.*;
import javax.swing.filechooser.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

/**
 * A class that handles what happens when the user clicks on either "Open" or "Close" in the JMenu. 
 * This class now sorts Words using a TreeMap instead of an ArrayList.
 * Due Date: December 9th, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 2
 */
public class FileMenuHandler implements ActionListener {
	protected WordGUI wordFrame;

	/**
	 * The constructor; uses the JFrame properties from the WordGUI class to add the three ArrayLists to it.
	 * @param wg the WordGUI that holds the ArrayLists and TreeMap
	 */
	public FileMenuHandler(WordGUI wg) {
		wordFrame = wg;
	}
	
	/**
	 * Retrieves whether the user clicks on "Open" or "Exit," and decides what happens based on each response.
	 * @param event the option that the user chooses
	 */
	public void actionPerformed(ActionEvent event) {
		String menuName = event.getActionCommand();
		if (menuName.equals("Open")) 
			openFile();	//Go through the steps to browse through the user's files, and read the text file to put the Words to their corresponding ArrayLists.
		else if (menuName.equals("Quit")) 
			System.exit(0); //Terminates the program for the user
	}
	
	/**
	 * Opens a file directory that lets the user choose a valid text file from their computer.
	 */
	public void openFile() {
		JFileChooser chooser = new JFileChooser("./"); //Browses this project's directory, making it easier to find the input text file
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); //So that the user can only choose files...
		chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt")); //...and the program will only browse through text files.
		int status = chooser.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION) 
			readSource(chooser.getSelectedFile());
	}
	
	/**
	 * Reads the text file that the user chooses, adding all of its words to their specific columns.
	 * @param chosenFile the text file that the user chooses
	 */
	public void readSource(File chosenFile) {
		wordFrame.scrollPane1.setVisible(true); //Makes the user be able to see the text
		wordFrame.scrollPane2.setVisible(true);
		
		wordFrame.textArea1.setText("Unsorted Words:\n"); //To clear the last results and label each JTextArea
		wordFrame.textArea2.setText("Sorted Words:\n");
		
		wordFrame.unsorted = new ArrayList<Word>(); 
		wordFrame.sorted = new TreeMap<>(); //Automatically adds Words in the right place to keep them sorted in an efficient way
		
		String[] myArray = new String[100];
		String chosenFilePath = chosenFile.getAbsolutePath();
		int myArraySize = inputFromFile(chosenFilePath, myArray);
		TextFileInput tfi = new TextFileInput(chosenFilePath);
		
		for (int i = 0; i < myArraySize; i++) {
			String line = tfi.readLine();
			StringTokenizer tokens = new StringTokenizer(line);
			int j = 0;
			while (tokens.hasMoreTokens()) {
				Word w = new Word(tokens.nextToken()); 
				wordFrame.unsorted.add(w); //If the Word ends up not being valid, it cannot be added to the unsorted or sorted Word ArrayList and must instead be added to the erroneous ArrayList
				wordFrame.sorted.put(w, j + 1);
				j++;
			}
		}
		
		for (int i = 0; i < wordFrame.unsorted.size(); i++) 
			wordFrame.textArea1.append(wordFrame.unsorted.get(i) + "\n"); //Unsorted Words in the left column
		for (Map.Entry<Word, Integer> me: wordFrame.sorted.entrySet()) 
			wordFrame.textArea2.append(me.getKey() + "\n"); //We must print each sorted Word in the TreeMap on its own line in the JTextArea
	}
	
	/**
	 * Gets the length of the user's text file.
	 * @param filePath the file path of the user's text file
	 * @param myArray the String array to be filled
	 * @return the number of lines in the user's text file
	 */
	public int inputFromFile(String filePath, String[] myArray) {
		TextFileInput in = new TextFileInput(filePath);
		int lengthFilled = 0;
		String line = in.readLine();
		
		while (lengthFilled < myArray.length && line != null) { //We need to fill the words String array to find the length.
			myArray[lengthFilled++] = line; 
			line = in.readLine();
		}
		
		if (line != null) { //The line can't be null, or the text file cannot be read.
			System.out.println("File contains too many words.");
			System.out.println("This program can process only " + myArray.length + " words.");
			System.exit(1);
		}
		
		in.close(); 
		return lengthFilled;
	}
}