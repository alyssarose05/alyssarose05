import java.util.*; // The package for the Scanner class.
import java.io.*; // The package for the File and PrintStream classes.
/**
 * Project 2 (Project 1++): This project is the main program of Project 1 Part 3, except it now has a way to look up Customers by account number, through an array of Customers. 
 * If the user knows the account number, it'll search by account number from the array. If not, it'll search by name from the BST.
 * @author Alyssa Ayala, CSCI 313-13
 * Due Date: December 19th, 2021 @ 11:59pm
 */
public class MainProgram {
	private static myBST NameDB = new myBST(); // 2 databases: a name database with a BST, and an account number database with an array.
	private static Customer[] AcctDB = new Customer[1000]; 
	private static Scanner in = new Scanner(System.in);
	
	/**
	 * The main method, which executes the main program: A menu of options on accessing the bank.
	 * @param args A list of arguments taken by a command prompt, but this is not being used here.
	 * @throws FileNotFoundException An exception thrown if before.txt does not exist.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		loadCustomers(); // Loads the Customers from before.txt into the BST and array.
		while (true) { 
			System.out.println("Bank Menu:"); 
			System.out.println("1. Make a deposit to existing account.");
			System.out.println("2. Make a withdrawl from existing account.");
			System.out.println("3. Check balance of existing account.");
			System.out.println("4. Open new account.");
			System.out.println("5. Close existing account.");
			System.out.println("6. Exit program.");
			System.out.print("Number Selection: ");
			int input = in.nextInt();
			if (input == 1) deposit(); 
			if (input == 2) withdraw();
			if (input == 3) checkBalance();
			if (input == 4) open();
			if (input == 5) close();
			if (input == 6) exit();	
		} 
	}
	
	/**
	 * Reads the before.txt text file and inserts each Customer into the NameDB myBST.
	 * @throws FileNotFoundException An exception thrown if before.txt does not exist.
	 */
	public static void loadCustomers() throws FileNotFoundException {
		Scanner in = new Scanner(new File("before.txt")); 
		while (in.hasNextLine()) {
			Customer customer = new Customer(in.next(), in.next(), in.next(), in.nextDouble());
			NameDB.insert(customer); // Inserts the Customer into the BST.
			AcctDB[Integer.parseInt(customer.getAcctNo().trim())] = customer; // Inserts the Customer into the array.
		}
		in.close(); 
	}
	
	/**
	 * Deposits an amount of money into a Customer's bank account.
	 */
	public static void deposit() {
		Customer customer;
		System.out.print("Enter name or account number, and deposit amount: "); // ex. Ayala Alyssa 20, or 0001 20
		if (in.hasNextInt()) customer = AcctDB[in.nextInt()]; // Account number: search from the array.
		else customer = (Customer) NameDB.search(new Customer(in.next(), in.next())); // Name: search from the BST.
		customer.deposit(in.nextDouble()); 
	}
		
	/**
	 * Withdraws an amount of money from a Customer's bank account.
	 */
	public static void withdraw() {
		Customer customer;
		System.out.print("Enter name or account number, and withdraw amount: "); // ex. Ayala Alyssa 20, or 0001 20
		if (in.hasNextInt()) customer = AcctDB[in.nextInt()]; // Account number: search from the array. 
		else customer = (Customer) NameDB.search(new Customer(in.next(), in.next())); // Name: search from the BST.
		double withdraw = in.nextDouble();
		if (withdraw > customer.getBalance()) throw new IllegalArgumentException("Withdraw is greater than balance.");
		customer.withdraw(withdraw);
	}
	
	/**
	 * Checks the balance of a Customer's bank account.
	 */
	public static void checkBalance() {
		Customer customer;
		System.out.print("Enter name or account number: "); // ex. Ayala Alyssa, or 0001
		if (in.hasNextInt()) customer = AcctDB[in.nextInt()]; // Account number: search from the array. 
		else customer = (Customer) NameDB.search(new Customer(in.next(), in.next())); // Name: search from the BST.
		System.out.println("$" + customer.getBalance());
	}
	
	/**
	 * Opens a new bank account.
	 */
	public static void open() {
		System.out.print("Enter name and account number: "); // ex. Ayala Alyssa 0001
		Customer customer = new Customer(in.next(), in.next(), in.next());
		NameDB.insert(customer); // Inserts the Customer into the BST.
		AcctDB[Integer.parseInt(customer.getAcctNo().trim())] = customer; // Inserts the Customer into the array.
		System.out.print("Deposit amount: $"); 
		double deposit = in.nextDouble();
		customer.deposit(deposit); 
	}
	
	/**
	 * Closes a Customer's bank account.
	 */
	public static void close() {
		Customer customer;
		System.out.print("Enter name or account number: "); // ex. Ayala Alyssa, or 0001
		if (in.hasNextInt()) customer = AcctDB[in.nextInt()]; // Account number: search from the array. 
		else customer = (Customer) NameDB.search(new Customer(in.next(), in.next())); // Name: search from the BST.
		NameDB.delete(customer); // Deletes the Customer from the BST.
		AcctDB[Integer.parseInt(customer.getAcctNo().trim())] = null; // Deletes the Customer from the array.
	}
	
	/**
	 * Saves the BST into a separate text file and exists the program.
	 * @throws FileNotFoundException An exception thrown if after.txt does not exist.
	 */
	public static void exit() throws FileNotFoundException {
		System.setOut(new PrintStream("after.txt")); 
		NameDB.PreOrder(); // Lists all Customers in the order it was inserted (root -> left -> right) according to the BST.
		System.exit(0); 
	}
}