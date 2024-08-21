import java.util.StringTokenizer;

/* 
 * This program needs the WordGUI and TextFileInput classes, which are both submitted with this file.
 * The WordsGUI class contains all the properties needed to create a WordGUI object, and the TextFileInput class is responsible for reading files.
 */

/** 
 * This is a program that takes a file with paragraph and using a GUI, prints all words in one column, valid words in another, and invalid words in the last.
 * Each column has all of their corresponding String's array's Strings sorted using selection sort.
 * Due Date: October 9th, 2020.
 * @author Alyssa Ayala, lab section E
 * @version 1
 */
public class Project1 {

	/**
	* The main method is where the String array and its size are applied to the WordGUI class.
	* @param args the String array for the main method; takes one text file as an argument
	*/
	public static void main(String[] args) {
		String filename = args[0];
		String[] myArray = new String[100];
		int myArraySize = inputFromFile(filename, myArray); //This is how we'll get the length of the text file.
		
		//We need the values of these arrays to fill the columns of the WordGUI object.
		String[] allWords = all(myArray, myArraySize);
		String[] validWords = valid(allWords);
		String[] invalidWords = invalid(allWords);
	
		WordGUI myGUI = new WordGUI(allWords, validWords, invalidWords); //We need the main method to develop the WordGUI object.
	}
	
	/** 
	 * The inputfromFile method gets the length of the text file.
	 * @param filename the file itself
	 * @param words the array that will carry the words of the file
	 * @return the number of lines that the text file has filled
	 */
	public static int inputFromFile(String filename, String[] words) {
		TextFileInput in = new TextFileInput(filename);
		int lengthFilled = 0;
		String line = in.readLine();
		while (lengthFilled < words.length && line != null) { //Filling the words String array here determines the length of the file.
			words[lengthFilled++] = line; 
			line = in.readLine();
		}
		
		if (line != null) { //There can't be any null lines in a functioning String array.
			System.out.println("File contains too many words.");
			System.out.println("This program can process only " + words.length + " words.");
			System.exit(1);
		}
		in.close(); 
		return lengthFilled;
	}
	
	/** 
	 * The selectionSort method uses selection sort to put the valid Strings of a String array in alphabetical order.
	 * @param myArray the String array to be put in alphabetical order
	 */
	public static void selectionSort(String[] myArray) {
		for (int i = 0; i < myArray.length - 1; i++) {
			int indexLowest = i;
			for (int j = i + 1; j < myArray.length; j++) {
				if (myArray[j].compareTo(myArray[i]) < 0) {
					indexLowest = j;
					if (myArray[indexLowest].compareTo(myArray[i]) < 0) {
						swap(myArray, indexLowest, i);
					}
				}
			}
		}
	}
	
	/** 
	 * The swap method swaps two Strings in a String array.
	 * @param myArray the String array with Strings a and b
	 * @param a the String to be swapped for String b
	 * @param b the String to be swapped for String a
	 */
	public static void swap(String[] myArray, int a, int b) {
		String temp = myArray[a]; //A temporary String is needed here because myArray[a] is about to be changed to myArray[b], and myArray[b] needs to get myArray[a]'s original value.
		myArray[a] = myArray[b];
		myArray[b] = temp;
	}
	
	/** 
	 * The all method puts the words of the paragraph into the allWords String array.
	 * @param myArray the array with the paragraph
	 * @param myArraySize the size of the above array
	 * @return the completed allWords String array
	 */
	public static String[] all(String[] myArray, int myArraySize) {
		String combined = "";
		
		for (int i = 0; i < myArraySize; i++) { 
			if (i != 0) {
				combined += " "; //Adding a space to the end of a line ensures that all words in the paragraph have one space apart from each other, even those at the end of a line, as we'll need this for the StringTokenizer.
			}
			combined += myArray[i]; //One combined String makes it easier to use a StringTokenizer to separate the words into the allWords String array.
		}

		StringTokenizer myTokens = new StringTokenizer(combined); //A StringTokenizer is used to prepare words to be put into the allWords array
		String[] allWords = new String[myTokens.countTokens()];
		int index = 0;
		
		while (myTokens.hasMoreTokens()) {
			allWords[index] = myTokens.nextToken(); 
			index++;
		}
		
		return allWords;
	}
	
	/**
	 * The valid method initializes a new String array with valid words from the allWords String array.
	 * @param myArray the String array with the paragraph's words
	 * @return the completed validWords String array
	 */
	public static String[] valid(String[] myArray) {
		int length = 0;
		for (int i = 0; i < myArray.length; i++) { //We need to set the length of the validWords array before actually creating it to avoid any null places.
			if (myArray[i].matches("[a-zA-Z]+")) {
				length++;
			}
		}
		
		String[] validWords = new String[length]; //Now we can create and fill the validWords array with the correct length.
		int index = 0;
		for (int i = 0; i < myArray.length; i++) { 
			if (myArray[i].matches("[a-zA-Z]+")) {
				validWords[index] = myArray[i];
				index++;
			}
		}
		
		selectionSort(validWords); //The valid words need to be displayed in alphabetical order.
		return validWords;
	}
	
	/** 
	 * The invalid method initializes a new String array with invalid words from the allWords String array.
	 * @param myArray the String array with the paragraph's words
	 * @return the completed invalidWords String array
	 */
	public static String[] invalid(String[] myArray) {
		int length = 0;
		for (int i = 0; i < myArray.length; i++) { //We need to set the length of the invalidWords array before actually creating it to avoid any null places.
			if (!(myArray[i].matches("[a-zA-Z]+"))) {
				length++;
			}
		}
		
		String[] invalidWords = new String[length];  //Now we can create and fill the invalidWords array with the correct length.
		int index = 0;
		for (int i = 0; i < myArray.length; i++) { 
			if (!(myArray[i].matches("[a-zA-Z]+"))) {
				invalidWords[index] = myArray[i];
				index++;
			}
		}
		
		return invalidWords;
	}
}