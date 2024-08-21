import javax.swing.*;
import javax.swing.filechooser.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

/**
 * A class that handles what happens when the user clicks on either "Open" or "Close" in the JMenu. 
 * Due Date: November 20th, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 1
 */
public class FileMenuHandler implements ActionListener {
	protected JFrame jframe;
	protected JTextArea textArea1, textArea2, textArea3;
	protected JScrollPane scrollPane1, scrollPane2, scrollPane3;

	/**
	 * The constructor; uses the JFrame properties from the WordGUI class to add the three ArrayLists to it.
	 * @param jf the JFrame that will hold the three ArrayLists
	 * @param ta1 the JTextArea that will hold the unsorted Words
	 * @param ta2 the JTextArea that will hold the sorted Words
	 * @param ta3 the JTextArea that will hold the erroneous Words
	 * @param sp1 the JScrollPane for the 1st JTextArea
	 * @param sp2 the JScrollPane for the 2nd JTextArea
	 * @param sp3 the JScrollPane for the 3rd JTextArea
	 */
	public FileMenuHandler(JFrame jf, JTextArea ta1, JTextArea ta2, JTextArea ta3, JScrollPane sp1, JScrollPane sp2, JScrollPane sp3) {
		jframe = jf;
		textArea1 = ta1;
		textArea2 = ta2;
		textArea3 = ta3;
		scrollPane1 = sp1;
		scrollPane2 = sp2;
		scrollPane3 = sp3;
	}
	
	/**
	 * Retrieves whether the user clicks on "Open" or "Exit," and decides what happens based on each response.
	 * @param event the option that the user chooses
	 */
	public void actionPerformed(ActionEvent event) {
		String menuName = event.getActionCommand();
		if (menuName.equals("Open")) {
			openFile();	//Go through the steps to browse through the user's files, and read the text file to put the Words to their corresponding ArrayLists.
		}
		else if (menuName.equals("Quit")) {
			System.exit(0); //Terminates the program for the user
		}
	}
	
	/**
	 * Opens a file directory that lets the user choose a valid text file from their computer.
	 */
	public void openFile() {
		JFileChooser chooser = new JFileChooser("./"); //Browses this project's directory, making it easier to find the input text file
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); //So that the user can only choose files...
		chooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt")); //...and the program will only browse through text files.
		int status = chooser.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION) {
			readSource(chooser.getSelectedFile());
		}
	}
	
	/**
	 * Reads the text file that the user chooses, adding all of its words to their specific columns.
	 * @param chosenFile the text file that the user chooses
	 */
	public void readSource(File chosenFile) {
		scrollPane1.setVisible(true); //Makes the user be able to see the text
		scrollPane2.setVisible(true);
		scrollPane3.setVisible(true);
		
		textArea1.setText("Unsorted Words:\n"); //To clear the last results and label each JTextArea
		textArea2.setText("Sorted Words:\n");
		textArea3.setText("Erroneous Words:\n");
		
		String[] myArray = new String[100];
		String chosenFilePath = chosenFile.getAbsolutePath();
		int myArraySize = inputFromFile(chosenFilePath, myArray);
		TextFileInput tfi = new TextFileInput(chosenFilePath);
		
		ArrayList<Word> unsorted = new ArrayList<Word>();
		ArrayList<Word> sorted = new ArrayList<Word>();
		ArrayList<String> erroneous = new ArrayList<String>(); //As erroneous Words cannot be created due to an IllegalWordException, this ArrayList should have Strings instead of Words.

		for (int i = 0; i < myArraySize; i++) {
			String line = tfi.readLine();
			StringTokenizer tokens = new StringTokenizer(line);
			while (tokens.hasMoreTokens()) {
				String word = tokens.nextToken();
				try {
					Word w = new Word(word); 
					unsorted.add(w); //If the Word ends up not being valid, it cannot be added to the unsorted or sorted Word ArrayList and must instead be added to the erroneous ArrayList
					sorted.add(w);
				}
				catch (IllegalWordException e) {
					erroneous.add(word); //Only occurs if the attempt to create a Word out of the next token fails
				}
			}
		}
		
		Collections.sort(sorted, new WordSort()); //The second ArrayList must be sorted in alphabetical order.
		
		for (int i = 0; i < unsorted.size(); i++) {
			textArea1.append(unsorted.get(i) + "\n"); //Unsorted Words in the left column
		}
		
		for (int i = 0; i < sorted.size(); i++) {
			textArea2.append(sorted.get(i) + "\n"); //Sorted Words in the middle column
		}
		
		for (int i = 0; i < erroneous.size(); i++) {
			textArea3.append(erroneous.get(i) + "\n"); //Erroneous Words in the right column
		}
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
		
		if (line != null) { //The line can't be null.
			System.out.println("File contains too many words.");
			System.out.println("This program can process only " + myArray.length + " words.");
			System.exit(1);
		}
		
		in.close(); 
		return lengthFilled;
	}
}