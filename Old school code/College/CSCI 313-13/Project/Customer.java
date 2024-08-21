/**
 * Project 1, Part 2: This class implements the Comparable interface and creates a Customer to be used as the data type of the myBST (my binary search tree) class.
 * The following methods are used to accomplish this: 3 constructors, sets and gets, deposit, withdraw, equals, toString, and compareTo.
 * @author Alyssa Ayala, CSCI 313-13
 * Due Date: November 22nd, 2021 @ 9:00am.
 */
public class Customer implements Comparable {
	private String last, first; // Attributes: last and first name, 4-digit account number, and balance.
	private String acctNo;
	private double balance;
	
	/**
	 * A constructor that takes a last name, first name, account number, and balance. Used to insert a Customer into the BST from before.txt.
	 * @param last the Customer's last name.
	 * @param first the Customer's first name.
	 * @param acctNo the Customer's account number.
	 * @param balance the Customer's balance.
	 */
	public Customer(String last, String first, String acctNo, double balance) {
		this.last = last;
		this.first = first;
		this.acctNo = acctNo;
		this.balance = balance;
	}
	
	/**
	 * A constructor that takes a last name and first name. Used to search for a Customer by name.
	 * @param last the Customer's last name.
	 * @param first the Customer's first name.
	 */
	public Customer(String last, String first) {
		this.last = last;
		this.first = first;
		this.acctNo = "0000";
		this.balance = 0;
	}
	
	/**
	 * A constructor that takes a last name, first name, and account number. Used to open a bank account.
	 * @param last the Customer's last name.
	 * @param first the Customer's first name.
	 * @param acctNo the Customer's account number.
	 */
	public Customer(String last, String first, String acctNo) {
		this.last = last;
		this.first = first;
		this.acctNo = acctNo;
		this.balance = 0;
	}

	/**
	 * Sets the Customer's last name.
	 * @param last The Customer's last name.
	 */
	public void setLast(String last) {
		this.last = last;
	}
	
	/**
	 * Gets the Customer's last name.
	 * @return The Customer's last name.
	 */
	public String getLast() {
		return this.last;
	}
	
	/**
	 * Sets the Customer's first name.
	 * @param first The Customer's first name.
	 */
	public void setFirst(String first) {
		this.first = first;
	}
	
	/**
	 * Gets the Customer's first name.
	 * @return The Customer's first name.
	 */
	public String getFirst() {
		return this.first;
	}
	
	/**
	 * Sets the Customer's account number.
	 * @param acctNo The Customer's account number.
	 */
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	
	/**
	 * Gets the Customer's account number.
	 * @return The Customer's account number.
	 */
	public String getAcctNo() {
		return this.acctNo;
	}
	
	/**
	 * Sets the Customer's balance.
	 * @param balance The Customer's balance.
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/**
	 * Gets the Customer's balance.
	 * @return The customer's balance.
	 */
	public double getBalance() {
		return this.balance;
	}
	
	/**
	 * Deposits a certain amount to the balance.
	 * @param amount The amount to be deposited to the balance.
	 */
	public void deposit(double amount) {
		this.balance += amount;
	}
	
	/**
	 * Withdraws a certain amount from the balance.
	 * @param amount The amount to be withdrawn from the balance.
	 */
	public void withdraw(double amount) {
		this.balance -= amount;
	}
	
	/**
	 * Tests for equality by account number.
	 * @param other The Customer to be compared to this Customer.
	 * @return true if equal, and false otherwise.
	 */
	public boolean equals(Customer other) {
		return this.acctNo.compareTo(other.acctNo) == 0;
	}
	
	@Override
	/**
	 * The specific guidelines on how to return a Customer as a String.
	 * @return "this.last this.first this.acctNo this.balance"
	 */
	public String toString() {
		return this.last + " " + this.first + " " + this.acctNo + " " + this.balance;
	}
	
	@Override
	/**
	 * Tests for equality by last name, then first name if needed. Alphabetical order.
	 * @return 1 if greater, -1 is less, and 0 if equal.
	 */
	public int compareTo(Object o) {
		Customer other = (Customer) o;
		int value = this.last.compareTo(other.last);
		if (value != 0) return value;
		return this.first.compareTo(other.first);
	}
}